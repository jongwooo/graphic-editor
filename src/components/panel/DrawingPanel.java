package components.panel;

import components.popup.PanelPopup;
import components.tool.spinner.DashSizeSpinner;
import components.tool.spinner.OutlineSizeSpinner;
import global.Constant;
import global.draw.Anchor;
import global.transformer.Mode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import utils.draw.DrawGroup;
import utils.draw.DrawPolygon;
import utils.draw.DrawSelection;
import utils.draw.DrawShape;
import utils.transformer.Drawer;
import utils.transformer.Mover;
import utils.transformer.Resizer;
import utils.transformer.Rotator;
import utils.transformer.Selector;
import utils.transformer.Transformer;

public class DrawingPanel extends JPanel implements Printable {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final DrawingPanel INSTANCE = new DrawingPanel();
  }

  private boolean update;
  private Mode mode;
  private List<DrawShape> shapes;
  private BufferedImage bufferedImage;
  private Graphics2D bufferedImageGraphics2D;
  private final UndoManager undoManager;
  private final MouseHandler mouseHandler;
  private Transformer transformer;
  private Class<? extends DrawShape> shapeClass;
  private DrawShape currentShape;
  private Color outlineColor, fillColor;
  private int outlineSize, dashSize;
  private final Clipboard clipboard;
  private final PanelPopup panelPopup;

  private DrawingPanel() {
    setBackground(Constant.BACKGROUND_COLOR);

    update = false;
    mode = Mode.IDLE;
    shapes = new ArrayList<>();
    undoManager = new UndoManager();
    mouseHandler = new MouseHandler();
    transformer = null;
    shapeClass = null;
    currentShape = null;
    outlineColor = Constant.DEFAULT_OUTLINE_COLOR;
    fillColor = Constant.DEFAULT_FILL_COLOR;
    outlineSize = Constant.DEFAULT_OUTLINE_SIZE;
    dashSize = Constant.DEFAULT_DASH_SIZE;
    clipboard = Clipboard.getInstance();
    panelPopup = PanelPopup.getInstance();
  }

  public static DrawingPanel getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void initialize() {
    panelPopup.initialize();
    addMouseListener(mouseHandler);
    addMouseMotionListener(mouseHandler);
  }

  private void showPanelPopup(Point currentPoint) {
    panelPopup.show(this, currentPoint.x, currentPoint.y);
    setIDLEMode();
  }

  public Object getShapes() {
    return shapes;
  }

  @SuppressWarnings("unchecked")
  public void setShapes(Object shapeObject) {
    this.shapes = (List<DrawShape>) shapeObject;
  }

  public boolean checkUpdate() {
    return update;
  }

  public void setUpdate(boolean update) {
    this.update = update;
  }

  private boolean isCurrentMode(Mode... modes) {
    return Arrays.stream(modes).anyMatch(mode -> this.mode == mode);
  }

  private void setCurrentMode(Mode mode) {
    this.mode = mode;
  }

  public void updateShapeClass(Class<? extends DrawShape> shapeClass) {
    this.shapeClass = shapeClass;
    updateCursorStyle();
    setIDLEMode();
  }

  private boolean isCurrentShapeClass(Class<?>... classes) {
    return Arrays.stream(classes).anyMatch(aClass -> shapeClass == aClass);
  }

  public DrawShape newShapeInstance() {
    try {
      return shapeClass.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
             NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  public void setCurrentShape(DrawShape shape) {
    this.currentShape = shape;
  }

  private DrawShape getSelectedShape(Point currentPoint) {
    List<DrawShape> temp = new ArrayList<>(shapes);
    Collections.reverse(temp);
    return temp.stream().filter(shape -> shape.contains(currentPoint)).findFirst().orElse(null);
  }

  private List<DrawShape> getSelectedShapes() {
    return shapes.stream().filter(DrawShape::isSelected).collect(Collectors.toList());
  }

  public void removeSelectedShapes() {
    List<DrawShape> selectedShapes  = getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      shapes.removeAll(selectedShapes);
      setUpdate(true);
      setIDLEMode();
    }
  }

  public void clearSelected() {
    List<DrawShape> selectedShapes  = getSelectedShapes();
    selectedShapes.forEach(selectedShape -> selectedShape.setSelected(false));
    repaint();
  }

  private boolean isCursorOnShape(Point currentPoint) {
    return shapes.stream().anyMatch(shape -> shape.contains(currentPoint));
  }

  public void updateCursorStyle() {
    setCursor(shapeClass == DrawSelection.class ? Constant.DEFAULT_STYLE_CURSOR
        : Constant.CROSSHAIR_STYLE_CURSOR);
  }

  private void updateCursorStyle(Point currentPoint) {
    setCursor(isCursorOnShape(currentPoint) ? Constant.HAND_STYLE_CURSOR
        : Constant.DEFAULT_STYLE_CURSOR);
    List<DrawShape> selectedShapes = getSelectedShapes();
    selectedShapes.stream().map(shape -> shape.getCurrentAnchor(currentPoint)).filter(
            Objects::nonNull).findFirst()
        .ifPresent(currentAnchor -> setCursor(currentAnchor.getCursorStyle()));
  }

  private Optional<Transformer> getTransformer() {
    return Optional.ofNullable(transformer);
  }

  private void setTransformer(Transformer transformer) {
    this.transformer = transformer;
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    bufferedImage = (BufferedImage) createImage(this.getWidth(), this.getHeight());
    bufferedImageGraphics2D = (Graphics2D) bufferedImage.getGraphics();

    bufferedImageGraphics2D.setBackground(Constant.BACKGROUND_COLOR);
    bufferedImageGraphics2D.clearRect(0, 0, this.getWidth(), this.getHeight());
    shapes.forEach(shape -> shape.draw(bufferedImageGraphics2D));

    graphics.drawImage(bufferedImage, 0, 0, this);
  }

  public void setIDLEMode() {
    setCurrentMode(Mode.IDLE);
    setTransformer(null);
    repaint();
  }

  private void setShapeAttributes(DrawShape shape) {
    shape.setOutlineColor(outlineColor);
    shape.setFillColor(fillColor);
    shape.setOutlineSize(outlineSize);
    shape.setDashSize(dashSize);
    shape.setStroke();
  }

  private void finishDraw() {
    undoManager.undoableEditHappened(
        new UndoableEditEvent(this, new UndoablePanel(currentShape)));
    currentShape.setSelected(true);
    setUpdate(true);
    setIDLEMode();
  }

  public void clearPanel() {
    shapes.clear();
    setUpdate(true);
    setIDLEMode();
  }

  private Color chooseColor(Color defaultColor, Color currentColor) {
    setIDLEMode();
    Optional<Color> chosenColor = Optional.ofNullable(
        JColorChooser.showDialog(this, Constant.COLOR_CHOOSER_TITLE, defaultColor));
    return chosenColor.orElse(currentColor);
  }

  public void chooseOutlineColor() {
    Color chosenColor = chooseColor(Constant.DEFAULT_OUTLINE_COLOR, outlineColor);
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (selectedShapes.isEmpty()) {
      outlineColor = chosenColor;
    } else {
      selectedShapes.forEach(shape -> shape.setOutlineColor(chosenColor));
      repaint();
    }
  }

  public void chooseFillColor() {
    Color chosenColor = chooseColor(Constant.DEFAULT_FILL_COLOR, fillColor);
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (selectedShapes.isEmpty()) {
      fillColor = chosenColor;
    } else {
      selectedShapes.forEach(shape -> shape.setFillColor(chosenColor));
      repaint();
    }
  }

  public void updateOutlineSize(int outlineSize) {
    setIDLEMode();
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (selectedShapes.isEmpty()) {
      this.outlineSize = outlineSize;
    } else {
      selectedShapes.forEach(shape -> {
        shape.setOutlineSize(outlineSize);
        shape.setStroke();
      });
      repaint();
    }
  }

  public void updateDashSize(int dashSize) {
    setIDLEMode();
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (selectedShapes.isEmpty()) {
      this.dashSize = dashSize;
    } else {
      selectedShapes.forEach(shape -> {
        shape.setDashSize(dashSize);
        shape.setStroke();
      });
      repaint();
    }
  }

  public void setSpinnerValue(int outlineSize, int dashSize) {
    OutlineSizeSpinner outlineSizeSpinner = OutlineSizeSpinner.getInstance();
    DashSizeSpinner dashSizeSpinner = DashSizeSpinner.getInstance();
    outlineSizeSpinner.setValue(outlineSize);
    dashSizeSpinner.setValue(dashSize);
  }

  @Override
  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
    if (pageIndex > 0) {
      return NO_SUCH_PAGE;
    }
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
    shapes.forEach(shape -> shape.draw(graphics2D));
    return PAGE_EXISTS;
  }

  public void undo() {
    if (undoManager.canUndo() && isCurrentMode(Mode.IDLE)) {
      undoManager.undo();
      setUpdate(true);
      setIDLEMode();
    }
  }

  public void redo() {
    if (undoManager.canRedo() && isCurrentMode(Mode.IDLE)) {
      undoManager.redo();
      setUpdate(true);
      setIDLEMode();
    }
  }

  private void registerClipboard(DrawShape shape) {
    DrawShape copiedShape = shape.clone();
    copiedShape.setSelected(true);
    clipboard.add(copiedShape);
  }

  public void cut() {
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      selectedShapes.forEach(shape -> {
        shapes.remove(shape);
        registerClipboard(shape);
      });
      setUpdate(true);
      repaint();
    }
  }

  public void copy() {
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      selectedShapes.forEach(this::registerClipboard);
    }
  }

  public void paste() {
    if (!clipboard.isEmpty()) {
      clearSelected();
      clipboard.paste(bufferedImageGraphics2D).forEach(shape -> {
        shape.setSelected(true);
        undoManager.undoableEditHappened(
            new UndoableEditEvent(this, new UndoablePanel(shape)));
        shapes.add(shape);
      });
      setUpdate(true);
      repaint();
    }
  }

  public void group() {
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      DrawGroup group = new DrawGroup();
      selectedShapes.forEach(childShape -> {
        shapes.remove(childShape);
        childShape.setSelected(false);
        group.addChildShape(childShape);
      });
      shapes.add(group);
      group.setSelected(true);
      setUpdate(true);
      repaint();
    }
  }

  public void ungroup() {
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      AtomicBoolean childExist = new AtomicBoolean(false);
      List<DrawShape> temp = new ArrayList<>();
      selectedShapes.forEach(shape -> {
        if (shape instanceof DrawGroup) {
          ((DrawGroup) shape).getChildShapes().forEach(childShape -> {
            childShape.setSelected(true);
            temp.add(childShape);
          });
          shapes.remove(shape);
          childExist.set(true);
        }
      });
      if (childExist.get()) {
        shapes.addAll(temp);
        setUpdate(true);
        repaint();
      }
    }
  }

  public void bringForward() {
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      int maxIndex = selectedShapes.stream().mapToInt(shape -> shapes.indexOf(shape)).max()
          .orElse(shapes.size() - 1);

      if (maxIndex < shapes.size() - 1) {
        selectedShapes.forEach(shape -> shapes.remove(shape));
        shapes.addAll(maxIndex, selectedShapes);
        setUpdate(true);
        repaint();
      }
    }
  }

  public void sendBackward() {
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      int minIndex = selectedShapes.stream().mapToInt(shape -> shapes.indexOf(shape)).min()
          .orElse(-1);

      if (minIndex > 0) {
        selectedShapes.forEach(shape -> shapes.remove(shape));
        shapes.addAll(minIndex - 1, selectedShapes);
        setUpdate(true);
        repaint();
      }
    }
  }

  public void bringToFront() {
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      selectedShapes.forEach(shape -> {
        shapes.remove(shape);
        shapes.add(shape);
      });
      setUpdate(true);
      repaint();
    }
  }

  public void sendToBack() {
    List<DrawShape> selectedShapes = getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      List<DrawShape> selectedList = new ArrayList<>();
      selectedShapes.forEach(shape -> {
        shapes.remove(shape);
        selectedList.add(shape);
      });

      List<DrawShape> temp = new ArrayList<>(shapes);
      shapes.clear();
      shapes.addAll(selectedList);
      shapes.addAll(temp);
      setUpdate(true);
      repaint();
    }
  }

  private class UndoablePanel extends AbstractUndoableEdit {

    private static final long serialVersionUID = 1L;

    private final DrawShape shape;

    public UndoablePanel(DrawShape shape) {
      this.shape = shape;
    }

    public void undo() {
      super.undo();
      clearSelected();
      shapes.remove(shape);
      if (!shapes.isEmpty()) {
        shapes.get(shapes.size() - 1).setSelected(true);
      }

    }

    public void redo() {
      super.redo();
      clearSelected();
      shapes.add(shape);
      shape.setSelected(true);
    }
  }

  private class MouseHandler extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON3 && e.isPopupTrigger()) {
        showPanelPopup(e.getPoint());
      } else if (isCurrentMode(Mode.IDLE)) {
        clearSelected();
        setSpinnerValue(outlineSize, dashSize);
        if (isCurrentShapeClass(DrawSelection.class)) {
          Optional.ofNullable(getSelectedShape(e.getPoint())).ifPresentOrElse(selectedShape -> {
            selectedShape.setSelected(true);
            setSpinnerValue(selectedShape.getOutlineSize(),
                selectedShape.getDashSize());
            if (selectedShape.getCurrentAnchor(e.getPoint()) == null) {
              setCurrentMode(Mode.MOVE);
              setTransformer(new Mover(selectedShape));
            } else if (selectedShape.isCurrentAnchor(Anchor.RR)) {
              setCurrentMode(Mode.ROTATE);
              setTransformer(new Rotator(selectedShape));
            } else {
              setCurrentMode(Mode.RESIZE);
              setTransformer(new Resizer(selectedShape));
            }
          }, () -> {
            setCurrentMode(Mode.SELECTION);
            setCurrentShape(newShapeInstance());
            currentShape.setOutlineSize(1);
            currentShape.setDashSize(5);
            currentShape.setStroke();
            setTransformer(new Selector(currentShape));
          });
        } else {
          setCurrentMode(isCurrentShapeClass(DrawPolygon.class) ? Mode.DRAW_POLYGON
              : Mode.DRAW_NORMAL);
          setCurrentShape(newShapeInstance());
          setShapeAttributes(currentShape);
          setTransformer(new Drawer(currentShape));
        }
        getTransformer().ifPresent(transformer -> transformer.setPoint(e.getPoint()));
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      if (isCurrentMode(Mode.IDLE)) {
        if (isCurrentShapeClass(DrawSelection.class)) {
          updateCursorStyle(e.getPoint());
        }
      } else if (isCurrentMode(Mode.DRAW_POLYGON)) {
        getTransformer().ifPresent(
            transformer -> {
              transformer.transform(bufferedImageGraphics2D, e.getPoint());
              getGraphics().drawImage(bufferedImage, 0, 0, DrawingPanel.getInstance());
            });
      }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if (!isCurrentMode(Mode.IDLE)) {
        getTransformer().ifPresent(
            transformer -> {
              transformer.transform(bufferedImageGraphics2D, e.getPoint());
              getGraphics().drawImage(bufferedImage, 0, 0, DrawingPanel.getInstance());
            });
        if (isCurrentMode(Mode.MOVE, Mode.RESIZE, Mode.ROTATE)) {
          repaint();
        }
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (isCurrentMode(Mode.DRAW_NORMAL)) {
        getTransformer().ifPresent(
            transformer -> ((Drawer) transformer).finishTransform(shapes));
        finishDraw();
      } else if (isCurrentMode(Mode.SELECTION)) {
        getTransformer().ifPresent(
            transformer -> ((Selector) transformer).finishTransform(shapes));
        setIDLEMode();
      } else if (isCurrentMode(Mode.MOVE, Mode.RESIZE, Mode.ROTATE)) {
        setUpdate(true);
        setIDLEMode();
      }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {
        if (isCurrentMode(Mode.DRAW_POLYGON)) {
          if (e.getClickCount() == 1) {
            getTransformer().ifPresent(
                transformer -> ((Drawer) transformer).keepTransform(e.getPoint()));
          } else if (e.getClickCount() >= 2) {
            getTransformer().ifPresent(
                transformer -> ((Drawer) transformer).finishTransform(shapes));
            finishDraw();
          }
        }
      }
    }
  }
}
