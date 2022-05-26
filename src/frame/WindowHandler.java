package frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import util.file.FileControl;

public class WindowHandler implements WindowListener {

  private static class InstanceHolder {

    private static final WindowHandler INSTANCE = new WindowHandler();
  }

  private FileControl fileControl;

  public static WindowHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void associate() {
    fileControl = FileControl.getInstance();
  }

  @Override
  public void windowOpened(WindowEvent e) {

  }

  @Override
  public void windowClosing(WindowEvent e) {
    fileControl.quitEditor();
  }

  @Override
  public void windowClosed(WindowEvent e) {

  }

  @Override
  public void windowIconified(WindowEvent e) {

  }

  @Override
  public void windowDeiconified(WindowEvent e) {

  }

  @Override
  public void windowActivated(WindowEvent e) {

  }

  @Override
  public void windowDeactivated(WindowEvent e) {

  }
}
