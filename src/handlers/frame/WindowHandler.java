package handlers.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import utils.file.FileControl;

public class WindowHandler implements WindowListener {

  private static class InstanceHolder {

    private static final WindowHandler INSTANCE = new WindowHandler();
  }

  private final FileControl fileControl;

  private WindowHandler() {
    fileControl = FileControl.getInstance();
  }

  public static WindowHandler getInstance() {
    return InstanceHolder.INSTANCE;
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
