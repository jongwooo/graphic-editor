package handler.tool.button;

import handler.ActionHandler;
import java.lang.reflect.InvocationTargetException;
import components.panel.DrawingPanel;

public class DrawingToolHandler extends ActionHandler {

  private static class InstanceHolder {

    private static final DrawingToolHandler INSTANCE = new DrawingToolHandler();
  }

  public static DrawingToolHandler getInstance() {
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
