package handlers.tool.button;

import components.panel.DrawingPanel;
import global.tool.ShapeToolItem;
import handlers.ActionHandler;
import java.lang.reflect.InvocationTargetException;

public class ShapeToolHandler extends ActionHandler {

  private static class InstanceHolder {

    private static final ShapeToolHandler INSTANCE = new ShapeToolHandler();
  }

  public static ShapeToolHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void invokeMethod(String actionCommand) {
    try {
      DrawingPanel drawingPanel = DrawingPanel.getInstance();
      drawingPanel.getClass().getMethod("updateShapeClass", Class.class)
          .invoke(drawingPanel, ShapeToolItem.valueOf(actionCommand).getShapeClass());
    } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception) {
      exception.printStackTrace();
    }
  }
}
