package main;

import frame.MainFrame;
import global.Constant;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame =  new MainFrame(Constant.MAINFRAME_TITLE);
        mainFrame.setVisible(true);
    }
}
