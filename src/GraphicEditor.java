import frame.MainFrame;

public class GraphicEditor {

  public static void main(String[] args) {
    MainFrame mainFrame = MainFrame.getInstance();
    mainFrame.setVisible(true);
    mainFrame.initialize();
  }
}
