package panel;

import draw.DrawPolygon;
import draw.DrawShape;
import global.draw.DrawMode;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class DrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private DrawShape currentShape;
    private MouseHandler mouseHandler;
    private Vector<DrawShape> shapes;
    private DrawMode drawMode;

    public DrawingPanel() {
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);

        shapes = new Vector<DrawShape>();
        drawMode = DrawMode.CURSOR;

        mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.addMouseWheelListener(mouseHandler);
    }

    public void setCurrentShape(DrawShape currentShape) {
        this.currentShape = currentShape;
        this.drawMode = DrawMode.CURSOR;
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

    private void finishDraw() {
        shapes.add(currentShape);
        drawMode = DrawMode.CURSOR;
        repaint();
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
                startDraw(e.getPoint());
                drawMode = currentShape instanceof DrawPolygon ? DrawMode.POLYGON : DrawMode.GENERAL;
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
