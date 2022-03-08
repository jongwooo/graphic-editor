package frame;

import global.Constant;
import menu.MenuBar;
import tool.ToolBar;
import panel.DrawingPanel;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    public MainFrame() {
        super(Constant.MAINFRAME_TITLE);

        this.setLayout(new BorderLayout());

        MenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar);

        ToolBar toolBar = new ToolBar();
        this.getContentPane().add(toolBar, BorderLayout.NORTH);

        DrawingPanel drawingPanel = new DrawingPanel();
        this.add(drawingPanel);
    }

    public void initialize() {
        setSize(Constant.MAINFRAME_WIDTH, Constant.MAINFRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
