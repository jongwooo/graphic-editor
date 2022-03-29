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

    private DrawShape currentShape;
    private MouseHandler mouseHandler;
    private DrawMode drawMode;
    private ArrayList<DrawShape> shapes;
    private Color outlineColor, fillColor;

    public DrawingPanel() {
        setBackground(Color.WHITE);

        drawMode = DrawMode.CURSOR;
        shapes = new ArrayList<DrawShape>();
        outlineColor = Constant.DEFAULT_OUTLINE_COLOR;
        fillColor = Constant.DEFAULT_FILL_COLOR;

        mouseHandler = new MouseHandler();
        mouseHandler.initialize(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public DrawMode getDrawMode() {
        return drawMode;
    }

    public void setDrawMode(DrawMode drawMode) {
        this.drawMode = drawMode;
    }

    public DrawShape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(DrawShape currentShape) {
        this.currentShape = currentShape;
        repaint();
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
        drawMode = DrawMode.CURSOR;
    }

    public void startDraw(Point startPoint) {
        currentShape = currentShape.newShape();
        currentShape.startDraw(startPoint);
        currentShape.setOutlineColor(outlineColor);
        currentShape.setFillColor(fillColor);
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
        if (shapes.size() >= 1 && drawMode != DrawMode.POLYGON) {
            shapes.remove(shapes.size() - 1);
        }
        repaint();
    }

    public void cleanPanel() {
        shapes.clear();
        repaint();
    }

    private Color setColor(Color defaultColor, Color currentColor) {
        Color selectedColor = JColorChooser.showDialog(null, Constant.COLOR_CHOOSER_TITLE, defaultColor);
        return selectedColor != null ? selectedColor : currentColor;
    }

    public void setOutlineColor() {
        outlineColor = setColor(Constant.DEFAULT_OUTLINE_COLOR, outlineColor);
        repaint();
    }

    public void setFillColor() {
        fillColor = setColor(Constant.DEFAULT_FILL_COLOR, fillColor);
        repaint();
    }
}
