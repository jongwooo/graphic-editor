package panel;

import draw.DrawPolygon;
import draw.DrawShape;
import global.Constant;
import global.draw.DrawMode;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final DrawingPanel DRAWING_PANEL = new DrawingPanel();

    private DrawMode drawMode;
    private ArrayList<DrawShape> shapes;
    private DrawShape currentShape;
    private Color outlineColor, fillColor;
    private int outlineSize, dashSize;
    private MouseHandler mouseHandler;

    private DrawingPanel() {
        setBackground(Color.WHITE);

        drawMode = DrawMode.CURSOR;
        shapes = new ArrayList<DrawShape>();
        outlineColor = Constant.DEFAULT_OUTLINE_COLOR;
        fillColor = Constant.DEFAULT_FILL_COLOR;
        outlineSize = Constant.DEFAULT_OUTLINE_SIZE;
        dashSize = Constant.DEFAULT_DASH_SIZE;
        mouseHandler = MouseHandler.createMouseHandler();
    }

    public static DrawingPanel createDrawingPanel() {
        return DRAWING_PANEL;
    }

    public void associate() {
        mouseHandler.associate(this);
    }

    public void initialize() {
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public boolean isCurrentDrawMode(DrawMode drawMode) {
        return this.drawMode == drawMode;
    }

    public void updateCurrentDrawMode(DrawMode drawMode) {
        this.drawMode = drawMode;
    }

    public boolean isDrawPolygon() {
        return currentShape instanceof DrawPolygon;
    }

    public boolean isCurrentShape(DrawShape shape) {
        return currentShape == shape;
    }

    public void updateCurrentShape(DrawShape currentShape) {
        this.currentShape = currentShape;
    }

    public void updateCursorStyle() {
        setCursor(isCurrentShape(null) ? Constant.DEFAULT_STYLE_CURSOR : Constant.CROSSHAIR_STYLE_CURSOR);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        shapes.forEach(shape -> {
            shape.draw(graphics2D);
        });
    }

    @Override
    public void repaint() {
        super.repaint();
        updateCurrentDrawMode(DrawMode.CURSOR);
    }

    public void startDraw(Point startPoint) {
        updateCurrentShape(currentShape.newShape());
        currentShape.updateShapeAttributes(outlineColor, fillColor, outlineSize, dashSize);
        currentShape.startDraw(startPoint);
    }

    public void draw(Point point) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setXORMode(graphics2D.getBackground());
        currentShape.draw(graphics2D);
        currentShape.setCoordinate(point);
        currentShape.draw(graphics2D);
    }

    public void keepDraw(Point currentPoint) {
        ((DrawPolygon) currentShape).keepDraw(currentPoint);
    }

    public void finishDraw() {
        shapes.add(currentShape);
        repaint();
    }

    public void eraseShape() {
        if (shapes.size() >= 1 && !isCurrentDrawMode(DrawMode.POLYGON)) {
            shapes.remove(shapes.size() - 1);
        }
        repaint();
    }

    public void clearShapes() {
        shapes.clear();
        repaint();
    }

    private Color chooseColor(Color defaultColor, Color currentColor) {
        Color chosenColor = JColorChooser.showDialog(null, Constant.COLOR_CHOOSER_TITLE, defaultColor);
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
}
