package tool;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;

    private JButton drawRectangleBtn;
    private JButton drawOvalBtn;

    public ToolBar() {
        this.setRollover(true);

        this.drawRectangleBtn = new JButton("Rectangle");
        this.add(this.drawRectangleBtn);

        this.drawOvalBtn = new JButton("Oval");
        this.add(this.drawOvalBtn);
    }
}
