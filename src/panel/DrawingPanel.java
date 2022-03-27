package panel;

import draw.DrawPolygon;
import draw.DrawShape;
import global.Constant;
import global.draw.DrawMode;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
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

        shapes = new ArrayList<DrawShape>();
        outlineColor = Constant.DEFAULT_OUTLINE_COLOR;
        fillColor = Constant.DEFAULT_FILL_COLOR;
        drawMode = DrawMode.CURSOR;

        mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void setCurrentShape(DrawShape currentShape) {
        stopDraw();
        this.currentShape = currentShape;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        shapes.forEach(shape -> {
            shape.draw(graphics2D);
        });
    }

    private void startDraw(Point startPoint) {
        currentShape = currentShape.newShape();
        currentShape.startDraw(startPoint);
        currentShape.setOutlineColor(outlineColor);
        currentShape.setFillColor(fillColor);
    }

    private void draw(Point point) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setXORMode(graphics2D.getBackground());
        currentShape.draw(graphics2D);
        currentShape.setCoordinate(point);
        currentShape.draw(graphics2D);
    }

    private void keepDraw(Point currentPoint) {
        ((DrawPolygon) currentShape).keepDraw(currentPoint);
    }

    public void stopDraw() {
        drawMode = DrawMode.CURSOR;
        repaint();
    }

    private void finishDraw() {
        shapes.add(currentShape);
        stopDraw();
    }

    public void eraseShape() {
        if (shapes.size() >= 1) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }

    public void cleanPanel() {
        shapes.clear();
        repaint();
    }

    private Color setColor(Color defaultColor) {
        return JColorChooser.showDialog(null, Constant.COLOR_CHOOSER_TITLE, defaultColor);
    }

    public void setOutlineColor() {
        stopDraw();
        outlineColor = setColor(Constant.DEFAULT_OUTLINE_COLOR);
    }

    public void setFillColor() {
        stopDraw();
        fillColor = setColor(Constant.DEFAULT_FILL_COLOR);
    }

    private class MouseHandler extends MouseInputAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (drawMode == DrawMode.POLYGON) {
                    if (e.getClickCount() == 1) {
                        keepDraw(e.getPoint());
                    } else if (e.getClickCount() >= 2) {
                        finishDraw();
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (drawMode == DrawMode.CURSOR) {
                if (currentShape != null) {
                    startDraw(e.getPoint());
                    drawMode = currentShape instanceof DrawPolygon ? DrawMode.POLYGON : DrawMode.GENERAL;
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (drawMode == DrawMode.GENERAL) {
                draw(e.getPoint());
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (drawMode == DrawMode.POLYGON) {
                draw(e.getPoint());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (drawMode == DrawMode.GENERAL) {
                finishDraw();
            }
        }
    }
}
