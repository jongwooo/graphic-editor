package frame;

import global.Constant;
import menu.MenuBar;
import panel.DrawingPanel;
import tool.ToolBar;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private MenuBar menuBar;
    private ToolBar toolBar;
    private DrawingPanel drawingPanel;

    public MainFrame() {
        super(Constant.MAINFRAME_TITLE);

        setLayout(new BorderLayout());

        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        toolBar = new ToolBar();
        this.add(BorderLayout.NORTH, toolBar);

        drawingPanel = new DrawingPanel();
        this.add(BorderLayout.CENTER, drawingPanel);
    }

    public void initialize() {
        menuBar.initialize(drawingPanel);
        toolBar.initialize(drawingPanel);

        setSize(Constant.MAINFRAME_WIDTH, Constant.MAINFRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
