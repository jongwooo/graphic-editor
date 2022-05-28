package panel;

import draw.DrawPolygon;
import draw.DrawShape;
import global.Constant;
import global.draw.Anchor;
import global.transformer.Mode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import popup.PanelPopup;
import tool.DashSizeSpinner;
import tool.OutlineSizeSpinner;
import transformer.Drawer;
import transformer.Mover;
import transformer.Resizer;
import transformer.Rotator;
import transformer.Transformer;

public class DrawingPanel extends JPanel implements Printable {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final DrawingPanel INSTANCE = new DrawingPanel();
  }

  private boolean update;
  private Mode mode;
  private List<DrawShape> shapes;
  private final UndoManager undoManager;
  private final MouseHandler mouseHandler;
  private Transformer transformer;
  private DrawShape currentShape, selectedShape;
  private Color outlineColor, fillColor;
  private int outlineSize, dashSize;
  private final PanelPopup panelPopup;

  private DrawingPanel() {
    setBackground(Constant.BACKGROUND_COLOR);

    update = false;
    mode = Mode.IDLE;
    shapes = new ArrayList<>();
    undoManager = new UndoManager();
    mouseHandler = new MouseHandler();
    transformer = null;
    currentShape = null;
    selectedShape = null;
    outlineColor = Constant.DEFAULT_OUTLINE_COLOR;
    fillColor = Constant.DEFAULT_FILL_COLOR;
    outlineSize = Constant.DEFAULT_OUTLINE_SIZE;
    dashSize = Constant.DEFAULT_DASH_SIZE;
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

  private boolean exists(Object object) {
    return object != null;
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

  private boolean isCurrentShape(Class<?>... classes) {
    return Arrays.stream(classes).anyMatch(aClass -> aClass.isInstance(currentShape));
  }

  public void setCurrentShape(DrawShape shape) {
    this.currentShape = shape;
  }

  public void updateCurrentShape(DrawShape currentShape) {
    setCurrentShape(currentShape);
    updateCursorStyle();
    setIDLEMode();
  }

  private boolean checkSelectedShape() {
    return shapes.stream().anyMatch(DrawShape::isSelected);
  }

  private DrawShape getSelectedShape(Point currentPoint) {
    List<DrawShape> temp = new ArrayList<>(shapes);
    Collections.reverse(temp);
    return temp.stream().filter(shape -> shape.contains(currentPoint)).findFirst().orElse(null);
  }

  private void setSelectedShape(DrawShape shape) {
    this.selectedShape = shape;
    if (exists(shape)) {
      shape.setSelected(true);
    }
  }

  public void clearSelectedShapes() {
    shapes.forEach(shape -> shape.setSelected(false));
    setSelectedShape(null);
    repaint();
  }

  private boolean isCursorOnShape(Point currentPoint) {
    return shapes.stream().anyMatch(shape -> shape.contains(currentPoint));
  }

  public void updateCursorStyle() {
    setCursor(exists(currentShape) ? Constant.CROSSHAIR_STYLE_CURSOR
        : Constant.DEFAULT_STYLE_CURSOR);
  }

  private void updateCursorStyle(Point currentPoint) {
    setCursor(isCursorOnShape(currentPoint) ? Constant.HAND_STYLE_CURSOR
        : Constant.DEFAULT_STYLE_CURSOR);
    if (exists(selectedShape)) {
      Anchor currentAnchor = selectedShape.getCurrentAnchor(currentPoint);
      if (exists(currentAnchor)) {
        setCursor(currentAnchor.getCursorStyle());
      }
    }
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
    Graphics2D graphics2D = (Graphics2D) graphics;
    shapes.forEach(shape -> shape.draw(graphics2D));
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
  }

  private void finishDraw() {
    undoManager.undoableEditHappened(
        new UndoableEditEvent(this, new UndoablePanel(currentShape)));
    setSelectedShape(currentShape);
    setUpdate(true);
    setIDLEMode();
  }

  public void clearShapes() {
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
    if (checkSelectedShape()) {
      selectedShape.setOutlineColor(chosenColor);
      repaint();
    } else {
      outlineColor = chosenColor;
    }
  }

  public void chooseFillColor() {
    Color chosenColor = chooseColor(Constant.DEFAULT_FILL_COLOR, fillColor);
    if (checkSelectedShape()) {
      selectedShape.setFillColor(chosenColor);
      repaint();
    } else {
      fillColor = chosenColor;
    }
  }

  public void updateOutlineSize(int outlineSize) {
    setIDLEMode();
    if (checkSelectedShape()) {
      selectedShape.setOutlineSize(outlineSize);
    } else {
      this.outlineSize = outlineSize;
    }
  }

  public void updateDashSize(int dashSize) {
    setIDLEMode();
    if (checkSelectedShape()) {
      selectedShape.setDashSize(dashSize);
    } else {
      this.dashSize = dashSize;
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

  public void cut() {

  }

  public void copy() {

  }

  public void paste() {

  }

  public void group() {

  }

  public void ungroup() {

  }

  public void bringForward() {
    if (exists(selectedShape)) {
      int shapeIndex = shapes.indexOf(selectedShape);
      if (shapeIndex < shapes.size() - 1) {
        Collections.swap(shapes, shapeIndex, shapeIndex + 1);
        repaint();
      }
    }
  }

  public void sendBackward() {
    if (exists(selectedShape)) {
      int shapeIndex = shapes.indexOf(selectedShape);
      if (shapeIndex > 0) {
        Collections.swap(shapes, shapeIndex - 1, shapeIndex);
        repaint();
      }
    }
  }

  public void bringToFront() {
    if (exists(selectedShape)) {
      shapes.remove(selectedShape);
      shapes.add(selectedShape);
      repaint();
    }
  }

  public void sendToBack() {
    if (exists(selectedShape)) {
      shapes.remove(selectedShape);
      List<DrawShape> temp = new ArrayList<>(shapes);
      shapes.clear();
      shapes.add(selectedShape);
      shapes.addAll(temp);
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
      clearSelectedShapes();
      shapes.remove(shape);
      if (!shapes.isEmpty()) {
        setSelectedShape(shapes.get(shapes.size() - 1));
      }

    }

    public void redo() {
      super.redo();
      clearSelectedShapes();
      shapes.add(shape);
      setSelectedShape(shape);
    }
  }

  private class MouseHandler extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON3 && e.isPopupTrigger()) {
        showPanelPopup(e.getPoint());
      } else if (isCurrentMode(Mode.IDLE)) {
        clearSelectedShapes();
        setSpinnerValue(outlineSize, dashSize);
        if (exists(currentShape)) {
          setCurrentMode(isCurrentShape(DrawPolygon.class) ? Mode.DRAW_POLYGON
              : Mode.DRAW_NORMAL);
          setCurrentShape(currentShape.newShape());
          setShapeAttributes(currentShape);
          setTransformer(new Drawer(currentShape));
        } else {
          setSelectedShape(getSelectedShape(e.getPoint()));
          if (exists(selectedShape)) {
            setSpinnerValue(selectedShape.getOutlineSize(),
                selectedShape.getDashSize());
            if (!exists(selectedShape.getCurrentAnchor(e.getPoint()))) {
              setCurrentMode(Mode.MOVE);
              setTransformer(new Mover(selectedShape));
            } else if (selectedShape.isCurrentAnchor(Anchor.RR)) {
              setCurrentMode(Mode.ROTATE);
              setTransformer(new Rotator(selectedShape));
            } else {
              setCurrentMode(Mode.RESIZE);
              setTransformer(new Resizer(selectedShape));
            }
          }
        }
        getTransformer().ifPresent(transformer -> transformer.setPoint(e.getPoint()));
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      if (isCurrentMode(Mode.IDLE)) {
        if (!exists(currentShape)) {
          updateCursorStyle(e.getPoint());
        }
      } else if (isCurrentMode(Mode.DRAW_POLYGON)) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        getTransformer().ifPresent(
            transformer -> transformer.transform(graphics2D, e.getPoint()));
      }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if (!isCurrentMode(Mode.IDLE)) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        getTransformer().ifPresent(
            transformer -> transformer.transform(graphics2D, e.getPoint()));
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
