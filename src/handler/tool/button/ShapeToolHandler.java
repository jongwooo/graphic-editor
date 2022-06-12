package handler.tool.button;

import components.panel.DrawingPanel;
import global.tool.ShapeToolItem;
import handler.ActionHandler;
import java.lang.reflect.InvocationTargetException;
import util.draw.DrawShape;

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
      drawingPanel.getClass().getMethod("updateCurrentShape", DrawShape.class)
          .invoke(drawingPanel, ShapeToolItem.valueOf(actionCommand).newShape());
    } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception) {
      exception.printStackTrace();
    }
  }
}
