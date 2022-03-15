package panel;

import global.Constant.ShapeToolItem;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class DrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private ShapeToolItem shapeTool;

    public DrawingPanel() {
        this.setBackground(Color.WHITE);

        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    public void setShapeTool(ShapeToolItem shapeTool) {
        this.shapeTool = shapeTool;
    }

    private class MouseHandler extends MouseInputAdapter {
        public void mousePressed(MouseEvent e) {

        }

        public void mouseDragged(MouseEvent e) {

        }
    }
}
