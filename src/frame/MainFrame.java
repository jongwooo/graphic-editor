package frame;

import global.Constant;
import menu.MenuBar;
import panel.DrawingPanel;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    public MainFrame() {
        super(Constant.MAINFRAME_TITLE);

        MenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar);

        DrawingPanel drawingPanel = new DrawingPanel();
        this.add(drawingPanel);
    }

    public void initialize() {
        setSize(Constant.MAINFRAME_WIDTH, Constant.MAINFRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
