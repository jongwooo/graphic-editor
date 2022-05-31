package tool;

import draw.DrawShape;
import global.tool.ShapeToolItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import panel.DrawingPanel;

public class ShapeToolHandler implements ActionListener {

  private static class InstanceHolder {

    private static final ShapeToolHandler INSTANCE = new ShapeToolHandler();
  }

  public static ShapeToolHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private void invokeMethod(String actionCommand) {
    try {
      DrawingPanel drawingPanel = DrawingPanel.getInstance();
      drawingPanel.getClass().getMethod("updateCurrentShape", DrawShape.class)
          .invoke(drawingPanel, ShapeToolItem.valueOf(actionCommand).newShape());
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
