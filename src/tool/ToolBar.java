package tool;

import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;

    private JRadioButton drawRectangleBtn;
    private JRadioButton drawOvalBtn;
    private JRadioButton drawLineBtn;
    private JRadioButton drawPolygonBtn;

    public ToolBar(String toolBarTitle) {
        super(toolBarTitle);
        ButtonGroup toolBtnGroup = new ButtonGroup();

        this.drawRectangleBtn = new JRadioButton();
        this.drawRectangleBtn.setIcon(new ImageIcon("src/image/rectangle.png"));
        toolBtnGroup.add(drawRectangleBtn);
        this.add(this.drawRectangleBtn);

        this.drawOvalBtn = new JRadioButton();
        this.drawOvalBtn.setIcon(new ImageIcon("src/image/oval.png"));
        toolBtnGroup.add(drawOvalBtn);
        this.add(this.drawOvalBtn);

        this.drawLineBtn = new JRadioButton();
        this.drawLineBtn.setIcon(new ImageIcon("src/image/line.png"));
        toolBtnGroup.add(drawLineBtn);
        this.add(this.drawLineBtn);

        this.drawPolygonBtn = new JRadioButton();
        this.drawPolygonBtn.setIcon(new ImageIcon("src/image/polygon.png"));
        toolBtnGroup.add(drawPolygonBtn);
        this.add(this.drawPolygonBtn);
    }
}
