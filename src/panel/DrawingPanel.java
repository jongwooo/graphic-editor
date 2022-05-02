package panel;

import draw.DrawPolygon;
import draw.DrawShape;
import global.Constant;
import global.draw.DrawMode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import popup.PanelPopup;
import transformer.Drawer;
import transformer.Transformer;

public class DrawingPanel extends JPanel implements Printable {

    private static final long serialVersionUID = 1L;
    private static final DrawingPanel DRAWING_PANEL = new DrawingPanel();

    private boolean update;
    private DrawMode drawMode;
    private ArrayList<DrawShape> shapes;
    private DrawShape currentShape;
    private Color outlineColor, fillColor;
    private int outlineSize, dashSize;
    private Transformer transformer;
    private final UndoManager undoManager;
    private final MouseHandler mouseHandler;
    private final PanelPopup panelPopup;

    private DrawingPanel() {
        setBackground(Constant.BACKGROUND_COLOR);

        update = false;
        drawMode = DrawMode.IDLE;
        shapes = new ArrayList<>();
        transformer = null;
        undoManager = new UndoManager();
        mouseHandler = new MouseHandler();
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

    public void setTransformer(Transformer transformer) {
        this.transformer = transformer;
    }

    public boolean isCurrentDrawMode(DrawMode drawMode) {
        return this.drawMode == drawMode;
    }

    public void setCurrentDrawMode(DrawMode drawMode) {
        this.drawMode = drawMode;
    }

    public boolean isDrawPolygon() {
        return currentShape instanceof DrawPolygon;
    }

    public boolean isCurrentShape(DrawShape shape) {
        return currentShape == shape;
    }

    public void setCurrentShape(DrawShape currentShape) {
        this.currentShape = currentShape;
    }

    public void updateCursorStyle() {
        setCursor(isCurrentShape(null) ? Constant.DEFAULT_STYLE_CURSOR
                : Constant.CROSSHAIR_STYLE_CURSOR);
    }

    public void updateCursorStyle(boolean cursorOnShape) {
        setCursor(cursorOnShape ? Constant.HAND_STYLE_CURSOR : Constant.DEFAULT_STYLE_CURSOR);
    }

    public boolean isCursorOnShape(Point currentPoint) {
        return shapes.stream().anyMatch(shape -> shape.isContainCurrentPoint(currentPoint));
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        shapes.forEach(shape -> shape.draw(graphics2D));
    }

    @Override
    public void repaint() {
        super.repaint();
        setCurrentDrawMode(DrawMode.IDLE);
    }

    public void finishDraw() {
        undoManager.undoableEditHappened(
                new UndoableEditEvent(this, new UndoablePanel(currentShape)));
        setTransformer(null);
        setUpdate(true);
        repaint();
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

    public void clearShapes() {
        shapes.clear();
        setUpdate(true);
        repaint();
    }

    private Color chooseColor(Color defaultColor, Color currentColor) {
        Color chosenColor = JColorChooser.showDialog(this, Constant.COLOR_CHOOSER_TITLE,
                defaultColor);
        return chosenColor != null ? chosenColor : currentColor;
    }

    public void chooseOutlineColor() {
        repaint();
        outlineColor = chooseColor(Constant.DEFAULT_OUTLINE_COLOR, outlineColor);
    }

    public void chooseFillColor() {
        repaint();
        fillColor = chooseColor(Constant.DEFAULT_FILL_COLOR, fillColor);
    }

    public void updateOutlineSize(int outlineSize) {
        repaint();
        this.outlineSize = outlineSize;
    }

    public void updateDashSize(int dashSize) {
        repaint();
        this.dashSize = dashSize;
    }

    public void showPanelPopup(Point currentPoint) {
        panelPopup.show(this, currentPoint.x, currentPoint.y);
        repaint();
    }

    public void undo() {
        if (undoManager.canUndo() && isCurrentDrawMode(DrawMode.IDLE)) {
            undoManager.undo();
            setUpdate(true);
            repaint();
        }
    }

    public void redo() {
        if (undoManager.canRedo() && isCurrentDrawMode(DrawMode.IDLE)) {
            undoManager.redo();
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
            shapes.remove(shape);
        }

        public void redo() {
            super.redo();
            shapes.add(shape);
        }
    }

    private class MouseHandler extends MouseInputAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isCurrentDrawMode(DrawMode.POLYGON) && e.getButton() == MouseEvent.BUTTON1) {
                if (e.getClickCount() == 1) {
                    ((Drawer) transformer).keepTransform(e.getPoint());
                } else if (e.getClickCount() >= 2) {
                    transformer.finishTransform(shapes);
                    finishDraw();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3 && e.isPopupTrigger()) {
                showPanelPopup(e.getPoint());
            } else if (isCurrentDrawMode(DrawMode.IDLE) && !isCurrentShape(null)) {
                setCurrentDrawMode(isDrawPolygon() ? DrawMode.POLYGON : DrawMode.NORMAL);
                setCurrentShape(currentShape.newShape());
                setTransformer(new Drawer(currentShape));
                currentShape.updateShapeAttributes(outlineColor, fillColor, outlineSize, dashSize);
                transformer.startTransform(e.getPoint());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (isCurrentDrawMode(DrawMode.NORMAL)) {
                transformer.transform((Graphics2D) getGraphics(), e.getPoint());
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (isCurrentDrawMode(DrawMode.IDLE) && isCurrentShape(null)) {
                updateCursorStyle(isCursorOnShape(e.getPoint()));
            } else if (isCurrentDrawMode(DrawMode.POLYGON)) {
                transformer.transform((Graphics2D) getGraphics(), e.getPoint());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (isCurrentDrawMode(DrawMode.NORMAL)) {
                transformer.finishTransform(shapes);
                finishDraw();
            }
        }
    }
}
