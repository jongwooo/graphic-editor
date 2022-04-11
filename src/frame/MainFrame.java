package frame;

import global.Constant;
import menu.MenuBar;
import panel.DrawingPanel;
import tool.ToolBar;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final MainFrame MAIN_FRAME = new MainFrame();

    private MenuBar menuBar;
    private ToolBar toolBar;
    private DrawingPanel drawingPanel;

    private MainFrame() {
        super(Constant.MAINFRAME_TITLE);
        setSize(Constant.MAINFRAME_WIDTH, Constant.MAINFRAME_HEIGHT);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = MenuBar.createMenuBar();
        toolBar = ToolBar.createToolBar();
        drawingPanel = DrawingPanel.createDrawingPanel();
    }

    public static MainFrame createMainFrame() {
        return MAIN_FRAME;
    }

    public void associate() {
        menuBar.associate(drawingPanel);
        toolBar.associate(drawingPanel);
        drawingPanel.associate();
    }

    public void initialize() {
        menuBar.initialize();
        this.setJMenuBar(menuBar);

        toolBar.initialize();
        this.add(BorderLayout.NORTH, toolBar);

        drawingPanel.initialize();
        this.add(BorderLayout.CENTER, drawingPanel);
    }
}
