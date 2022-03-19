package panel;

import draw.DrawShape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class DrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private DrawShape currentShape;
    private Vector<DrawShape> shapes;

    public DrawingPanel() {
        this.setBackground(Color.WHITE);
        shapes = new Vector<DrawShape>();

        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
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

    private void draw(Point point) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setXORMode(graphics2D.getBackground());
        currentShape.draw(graphics2D);
        currentShape.setCoordinate(point);
        currentShape.draw(graphics2D);
    }

    private void startDraw(Point startPoint) {
        currentShape = currentShape.newShape();
        currentShape.startDraw(startPoint);
    }

    private void finishDraw(DrawShape currentShape) {
        shapes.add(currentShape);
        repaint();
    }

    private class MouseHandler extends MouseInputAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            startDraw(e.getPoint());
        }

        @Override
        public void mouseDragged(MouseEvent e) { draw(e.getPoint());}

        @Override
        public void mouseReleased(MouseEvent e) {
            finishDraw(currentShape);
        }
    }
}
