package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import panel.DrawingPanel;

public class EditMenuHandler implements ActionListener {

  private static class InstanceHolder {

    private static final EditMenuHandler INSTANCE = new EditMenuHandler();
  }

  public static EditMenuHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private void invokeMethod(String methodName) {
    try {
      DrawingPanel drawingPanel = DrawingPanel.getInstance();
      drawingPanel.getClass().getMethod(methodName).invoke(drawingPanel);
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
