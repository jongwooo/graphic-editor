package handlers.frame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import utils.file.FileControl;

public class WindowHandler extends WindowAdapter {

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
  public void windowClosing(WindowEvent e) {
    fileControl.quitEditor();
  }
}
