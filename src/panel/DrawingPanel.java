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
import java.util.Collections;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import popup.PanelPopup;
import transformer.Drawer;
import transformer.Mover;
import transformer.Transformer;

public class DrawingPanel extends JPanel implements Printable {

    private static final long serialVersionUID = 1L;
    private static final DrawingPanel DRAWING_PANEL = new DrawingPanel();

    private boolean update;
    private Mode mode;
    private ArrayList<DrawShape> shapes;
    private final UndoManager undoManager;
    private final MouseHandler mouseHandler;
    private Transformer transformer;
    private Anchor currentAnchor;
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
        currentAnchor = null;
        currentShape = null;
        selectedShape = null;
        outlineColor = Constant.DEFAULT_OUTLINE_COLOR;
        fillColor = Constant.DEFAULT_FILL_COLOR;
        outlineSize = Constant.DEFAULT_OUTLINE_SIZE;
        dashSize = Constant.DEFAULT_DASH_SIZE;
        panelPopup = PanelPopup.createPanelPopup();
    }

    public static DrawingPanel createDrawingPanel() {
        return DRAWING_PANEL;
    }

    public void associate() {
        panelPopup.associate(this);
    }

    public void initialize() {
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        panelPopup.initialize();
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
        this.shapes = (ArrayList<DrawShape>) shapeObject;
    }

    public boolean checkUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    private boolean isCurrentMode(Mode mode) {
        return this.mode == mode;
    }

    private void setCurrentMode(Mode mode) {
        this.mode = mode;
    }

    private boolean isDrawPolygon() {
        return currentShape instanceof DrawPolygon;
    }

    public void setCurrentShape(DrawShape shape) {
        this.currentShape = shape;
    }

    private boolean checkSelectedShape() {
        return shapes.stream().anyMatch(DrawShape::isSelected);
    }

    private DrawShape getSelectedShape(Point currentPoint) {
        ArrayList<DrawShape> temp = new ArrayList<>(shapes);
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

    private void setCurrentAnchor(Anchor anchor) {
        this.currentAnchor = anchor;
    }

    public void updateCursorStyle() {
        setCursor(exists(currentShape) ? Constant.CROSSHAIR_STYLE_CURSOR
                : Constant.DEFAULT_STYLE_CURSOR);
    }

    private void updateCursorStyle(Point currentPoint) {
        if (exists(selectedShape)) {
            setCurrentAnchor(selectedShape.getCurrentAnchor(currentPoint));
            setCursor(exists(currentAnchor) ? currentAnchor.getCursorStyle()
                    : isCursorOnShape(currentPoint) ? Constant.HAND_STYLE_CURSOR
                            : Constant.DEFAULT_STYLE_CURSOR);
        }
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
        shape.setStroke(outlineSize, dashSize);
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
        Color chosenColor = JColorChooser.showDialog(this, Constant.COLOR_CHOOSER_TITLE,
                defaultColor);
        return chosenColor != null ? chosenColor : currentColor;
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
        this.outlineSize = outlineSize;
        if (checkSelectedShape()) {
            selectedShape.setStroke(outlineSize, dashSize);
        }
    }

    public void updateDashSize(int dashSize) {
        setIDLEMode();
        this.dashSize = dashSize;
        if (checkSelectedShape()) {
            selectedShape.setStroke(outlineSize, dashSize);
        }
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

    private class UndoablePanel extends AbstractUndoableEdit {

        private static final long serialVersionUID = 1L;

        private final DrawShape shape;

        public UndoablePanel(DrawShape shape) {
            this.shape = shape;
        }

        public void undo() {
            super.undo();
            shapes.remove(shape);
        }

        public void redo() {
            super.redo();
            shapes.add(shape);
        }
    }

    private class MouseHandler extends MouseInputAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3 && e.isPopupTrigger()) {
                showPanelPopup(e.getPoint());
            } else if (isCurrentMode(Mode.IDLE)) {
                clearSelectedShapes();
                if (exists(currentShape)) {
                    setCurrentMode(isDrawPolygon() ? Mode.DRAW_POLYGON : Mode.DRAW_NORMAL);
                    setCurrentShape(currentShape.newShape());
                    setShapeAttributes(currentShape);
                    setTransformer(new Drawer(currentShape));
                    transformer.setPoint(e.getPoint());
                } else {
                    setSelectedShape(getSelectedShape(e.getPoint()));
                    if (exists(selectedShape)) {
                        setCurrentAnchor(selectedShape.getCurrentAnchor(e.getPoint()));
                        if (!exists(currentAnchor)) {
                            setCurrentMode(Mode.MOVE);
                            setTransformer(new Mover(selectedShape));
                            transformer.setPoint(e.getPoint());
                        }
                    }
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (isCurrentMode(Mode.IDLE)) {
                if (!exists(currentShape)) {
                    updateCursorStyle(e.getPoint());
                }
            } else if (isCurrentMode(Mode.DRAW_POLYGON)) {
                transformer.transform((Graphics2D) getGraphics(), e.getPoint());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!isCurrentMode(Mode.IDLE)) {
                transformer.transform((Graphics2D) getGraphics(), e.getPoint());
                if (isCurrentMode(Mode.MOVE)) {
                    repaint();
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (isCurrentMode(Mode.DRAW_NORMAL)) {
                ((Drawer) transformer).finishTransform(shapes);
                finishDraw();
            } else if (isCurrentMode(Mode.MOVE)) {
                setUpdate(true);
                setIDLEMode();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (isCurrentMode(Mode.DRAW_POLYGON)) {
                    if (e.getClickCount() == 1) {
                        ((Drawer) transformer).keepTransform(e.getPoint());
                    } else if (e.getClickCount() >= 2) {
                        ((Drawer) transformer).finishTransform(shapes);
                        finishDraw();
                    }
                }
            }
        }
    }
}
