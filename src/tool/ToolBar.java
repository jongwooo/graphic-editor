package tool;

import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;

    private JRadioButton cursorBtn;
    private JRadioButton drawRectangleBtn;
    private JRadioButton drawOvalBtn;
    private JRadioButton drawLineBtn;
    private JRadioButton drawPolygonBtn;

    public ToolBar(String toolBarTitle) {
        super(toolBarTitle);
        ButtonGroup toolBtnGroup = new ButtonGroup();

        this.cursorBtn = new JRadioButton();
        this.cursorBtn.setIcon(new ImageIcon("src/image/cursor.png"));
        this.cursorBtn.setSelectedIcon(new ImageIcon("src/image/selected_cursor.png"));
        toolBtnGroup.add(cursorBtn);
        this.add(this.cursorBtn);

        this.drawRectangleBtn = new JRadioButton();
        this.drawRectangleBtn.setIcon(new ImageIcon("src/image/rectangle.png"));
        this.drawRectangleBtn.setSelectedIcon(new ImageIcon("src/image/selected_rectangle.png"));
        toolBtnGroup.add(drawRectangleBtn);
        this.add(this.drawRectangleBtn);

        this.drawOvalBtn = new JRadioButton();
        this.drawOvalBtn.setIcon(new ImageIcon("src/image/oval.png"));
        this.drawOvalBtn.setSelectedIcon(new ImageIcon("src/image/selected_oval.png"));
        toolBtnGroup.add(drawOvalBtn);
        this.add(this.drawOvalBtn);

        this.drawLineBtn = new JRadioButton();
        this.drawLineBtn.setIcon(new ImageIcon("src/image/line.png"));
        this.drawLineBtn.setSelectedIcon(new ImageIcon("src/image/selected_line.png"));
        toolBtnGroup.add(drawLineBtn);
        this.add(this.drawLineBtn);

        this.drawPolygonBtn = new JRadioButton();
        this.drawPolygonBtn.setIcon(new ImageIcon("src/image/polygon.png"));
        this.drawPolygonBtn.setSelectedIcon(new ImageIcon("src/image/selected_polygon.png"));
        toolBtnGroup.add(drawPolygonBtn);
        this.add(this.drawPolygonBtn);
    }
}
