package panel;

import draw.DrawShape;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

public class DrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private DrawShape currentShape;
    private MouseHandler mouseHandler;
    private Vector<DrawShape> shapes;

    public DrawingPanel() {
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);

        shapes = new Vector<DrawShape>();

        mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.addMouseWheelListener(mouseHandler);
    }

    public void setCurrentShape(DrawShape currentShape) {
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
    }

    private void draw(Point point) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setXORMode(graphics2D.getBackground());
        currentShape.draw(graphics2D);
        currentShape.setCoordinate(point);
        currentShape.draw(graphics2D);
    }

    private void finishDraw() {
        shapes.add(currentShape);
        repaint();
    }

    private class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            startDraw(e.getPoint());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            draw(e.getPoint());
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            finishDraw();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }
    }
}
