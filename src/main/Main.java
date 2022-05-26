package main;

import frame.MainFrame;

public class Main {

  public static void main(String[] args) {
    MainFrame mainFrame = MainFrame.getInstance();
    mainFrame.associate();
    mainFrame.initialize();
    mainFrame.setVisible(true);
  }
}
