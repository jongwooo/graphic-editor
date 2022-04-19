package panel;

import global.draw.DrawMode;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

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
        if (drawingPanel.isCurrentDrawMode(DrawMode.POLYGON)
                && e.getButton() == MouseEvent.BUTTON1) {
            if (e.getClickCount() == 1) {
                drawingPanel.keepDraw(e.getPoint());
            } else if (e.getClickCount() >= 2) {
                drawingPanel.finishDraw();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (drawingPanel.isCurrentDrawMode(DrawMode.CURSOR) && !drawingPanel.isCurrentShape(null)) {
            drawingPanel.setCurrentDrawMode(
                    drawingPanel.isDrawPolygon() ? DrawMode.POLYGON : DrawMode.NORMAL);
            drawingPanel.startDraw(e.getPoint());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (drawingPanel.isCurrentDrawMode(DrawMode.NORMAL)) {
            drawingPanel.draw(e.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (drawingPanel.isCurrentDrawMode(DrawMode.CURSOR) && drawingPanel.isCurrentShape(null)) {
            drawingPanel.updateCursorStyle(drawingPanel.isCursorOnShape(e.getPoint()));
        } else if (drawingPanel.isCurrentDrawMode(DrawMode.POLYGON)) {
            drawingPanel.draw(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (drawingPanel.isCurrentDrawMode(DrawMode.NORMAL)) {
            drawingPanel.finishDraw();
        }
    }
}
