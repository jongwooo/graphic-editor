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
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (drawingPanel.getDrawMode() == DrawMode.POLYGON) {
                if (e.getClickCount() == 1) {
                    drawingPanel.keepDraw(e.getPoint());
                } else if (e.getClickCount() >= 2) {
                    drawingPanel.finishDraw();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (drawingPanel.getDrawMode() == DrawMode.CURSOR) {
            if (drawingPanel.getCurrentShape() != null) {
                drawingPanel.startDraw(e.getPoint());
                drawingPanel.setDrawMode(drawingPanel.getCurrentShape() instanceof DrawPolygon ? DrawMode.POLYGON : DrawMode.GENERAL);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (drawingPanel.getDrawMode() == DrawMode.GENERAL) {
            drawingPanel.draw(e.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (drawingPanel.getDrawMode() == DrawMode.POLYGON) {
            drawingPanel.draw(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (drawingPanel.getDrawMode() == DrawMode.GENERAL) {
            drawingPanel.finishDraw();
        }
    }
}
