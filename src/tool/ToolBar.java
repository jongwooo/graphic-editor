package tool;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;

    private JButton drawRectangleBtn;
    private JButton drawOvalBtn;
    private JButton drawLineBtn;
    private JButton drawPolygonBtn;

    public ToolBar(String toolBarTitle) {
        super(toolBarTitle);
        this.setRollover(true);

        this.drawRectangleBtn = new JButton(new ImageIcon("src/image/rectangle.png"));
        this.add(this.drawRectangleBtn);

        this.drawOvalBtn = new JButton(new ImageIcon("src/image/oval.png"));
        this.add(this.drawOvalBtn);

        this.drawLineBtn = new JButton(new ImageIcon("src/image/line.png"));
        this.add(this.drawLineBtn);

        this.drawPolygonBtn = new JButton(new ImageIcon("src/image/polygon.png"));
        this.add(this.drawPolygonBtn);
    }
}
