package main;

import frame.MainFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.associate();
        mainFrame.initialize();
        mainFrame.setVisible(true);
    }
}
