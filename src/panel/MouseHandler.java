package panel;

import draw.DrawPolygon;
import global.draw.DrawMode;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseInputAdapter {
    private static final MouseHandler MOUSE_HANDLER = new MouseHandler();

    private DrawingPanel drawingPanel;

    public static MouseHandler createMouseHandler() {
        return MOUSE_HANDLER;
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (drawingPanel.compareCurrentDrawMode(DrawMode.POLYGON) && e.getButton() == MouseEvent.BUTTON1) {
            if (e.getClickCount() == 1) {
                drawingPanel.keepDraw(e.getPoint());
            } else if (e.getClickCount() >= 2) {
                drawingPanel.finishDraw();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (drawingPanel.compareCurrentDrawMode(DrawMode.CURSOR) && drawingPanel.getCurrentShape() != null) {
            drawingPanel.setDrawMode(drawingPanel.getCurrentShape() instanceof DrawPolygon ? DrawMode.POLYGON : DrawMode.GENERAL);
            drawingPanel.startDraw(e.getPoint());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (drawingPanel.compareCurrentDrawMode(DrawMode.GENERAL)) {
            drawingPanel.draw(e.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (drawingPanel.compareCurrentDrawMode(DrawMode.POLYGON)) {
            drawingPanel.draw(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (drawingPanel.compareCurrentDrawMode(DrawMode.GENERAL)) {
            drawingPanel.finishDraw();
        }
    }
}
