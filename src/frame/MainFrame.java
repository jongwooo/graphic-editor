package frame;

import constant.Constant;
import menu.MenuBar;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    public MainFrame() {
        super(Constant.MAINFRAME_TITLE);

        MenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar);
    }

    public void initialize() {
        setSize(Constant.MAINFRAME_WIDTH, Constant.MAINFRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
