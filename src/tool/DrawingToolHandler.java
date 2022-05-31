package tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import panel.DrawingPanel;

public class DrawingToolHandler implements ActionListener {

  private static class InstanceHolder {

    private static final DrawingToolHandler INSTANCE = new DrawingToolHandler();
  }

  public static DrawingToolHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private void invokeMethod(String actionCommand) {
    try {
      DrawingPanel drawingPanel = DrawingPanel.getInstance();
      drawingPanel.getClass().getMethod(actionCommand).invoke(drawingPanel);
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
