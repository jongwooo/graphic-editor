package frame;

import global.Constant;
import menu.MenuBar;
import tool.ToolBar;
import panel.DrawingPanel;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private MenuBar menuBar;
    private ToolBar toolBar;
    private DrawingPanel drawingPanel;

    public MainFrame(String frameTitle) {
        super(frameTitle);

        this.menuBar = new MenuBar();
        this.setJMenuBar(this.menuBar);

        this.toolBar = new ToolBar(Constant.TOOLBAR_TITLE);
        this.add(BorderLayout.NORTH, this.toolBar);

        this.drawingPanel = new DrawingPanel();
        this.add(this.drawingPanel);
    }

    public void initialize() {
        toolBar.initialize(drawingPanel);

        setSize(Constant.MAINFRAME_WIDTH, Constant.MAINFRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
