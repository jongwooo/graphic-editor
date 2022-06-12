package handler.menu;

import handler.ActionHandler;
import java.lang.reflect.InvocationTargetException;
import utils.file.FileControl;

public class FileMenuHandler extends ActionHandler {

  private static class InstanceHolder {

    private static final FileMenuHandler INSTANCE = new FileMenuHandler();
  }

  public static FileMenuHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void invokeMethod(String actionCommand) {
    try {
      FileControl fileControl = FileControl.getInstance();
      fileControl.getClass().getMethod(actionCommand).invoke(fileControl);
    } catch (InvocationTargetException | IllegalAccessException |
             NoSuchMethodException exception) {
      exception.printStackTrace();
    }
  }
}
