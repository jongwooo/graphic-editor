package panel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
    public DrawingPanel() {
        this.setBackground(Color.white);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawRect(100, 100, 50, 50);
        g.drawOval(200, 100, 50, 50);
    }
}
