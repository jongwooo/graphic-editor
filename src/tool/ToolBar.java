package tool;

import javax.swing.*;

public class ToolBar extends JToolBar {
    public ToolBar() {
        this.setRollover(true);

        JButton drawRectangleBtn = new JButton("Rectangle");
        this.add(drawRectangleBtn);

        JButton drawOvalBtn = new JButton("Oval");
        this.add(drawOvalBtn);
    }
}
