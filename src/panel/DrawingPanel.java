package panel;

import draw.DrawShape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class DrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private DrawShape drawShape;

    public DrawingPanel() {
        this.setBackground(Color.WHITE);

        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    public void setDrawShape(DrawShape drawShape) {
        this.drawShape = drawShape;
    }

    private void startDraw(Point startPoint) {
        drawShape.startDraw(startPoint);
    }

    private void endDraw(Point endPoint) {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setXORMode(graphics2D.getBackground());
        drawShape.draw(graphics2D);
        drawShape.endDraw(endPoint);
        drawShape.draw(graphics2D);
    }

    private class MouseHandler extends MouseInputAdapter {
        public void mousePressed(MouseEvent e) {
            startDraw(e.getPoint());
        }
        public void mouseDragged(MouseEvent e) {
            endDraw(e.getPoint());
        }
    }
}
