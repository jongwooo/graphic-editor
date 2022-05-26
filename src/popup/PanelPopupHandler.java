package popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import panel.DrawingPanel;

public class PanelPopupHandler implements ActionListener {

  private static class InstanceHolder {

    private static final PanelPopupHandler INSTANCE = new PanelPopupHandler();
  }

  private DrawingPanel drawingPanel;

  public static PanelPopupHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void associate() {
    drawingPanel = DrawingPanel.getInstance();
  }

  public void undo() {
    drawingPanel.undo();
  }

  public void redo() {
    drawingPanel.redo();
  }

  public void cut() {

  }

  public void copy() {

  }

  public void paste() {

  }

  public void group() {

  }

  public void ungroup() {

  }

  private void invokeMethod(String methodName) {
    try {
      this.getClass().getMethod(methodName).invoke(this);
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
