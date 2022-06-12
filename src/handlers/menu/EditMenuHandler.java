package handlers.menu;

import components.panel.DrawingPanel;
import handlers.ActionHandler;
import java.lang.reflect.InvocationTargetException;

public class EditMenuHandler extends ActionHandler {

  private static class InstanceHolder {

    private static final EditMenuHandler INSTANCE = new EditMenuHandler();
  }

  public static EditMenuHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void invokeMethod(String actionCommand) {
    try {
      DrawingPanel drawingPanel = DrawingPanel.getInstance();
      drawingPanel.getClass().getMethod(actionCommand).invoke(drawingPanel);
    } catch (InvocationTargetException | IllegalAccessException |
             NoSuchMethodException exception) {
      exception.printStackTrace();
    }
  }
}
