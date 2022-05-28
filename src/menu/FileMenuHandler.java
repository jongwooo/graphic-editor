package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import util.file.FileControl;

public class FileMenuHandler implements ActionListener {

  private static class InstanceHolder {

    private static final FileMenuHandler INSTANCE = new FileMenuHandler();
  }

  public static FileMenuHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private void invokeMethod(String methodName) {
    try {
      FileControl fileControl = FileControl.getInstance();
      fileControl.getClass().getMethod(methodName).invoke(fileControl);
    } catch (InvocationTargetException | IllegalAccessException |
             NoSuchMethodException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    invokeMethod(e.getActionCommand());
  }
}
