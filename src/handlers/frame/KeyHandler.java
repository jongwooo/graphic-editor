package handlers.frame;

import components.panel.DrawingPanel;
import global.Constant;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

  private static class InstanceHolder {

    private static final KeyHandler INSTANCE = new KeyHandler();
  }

  private final DrawingPanel drawingPanel;

  private KeyHandler() {
    drawingPanel = DrawingPanel.getInstance();
  }

  public static KeyHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == Constant.CMD_KEY) {
      drawingPanel.setMultiple(true);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == Constant.CMD_KEY) {
      drawingPanel.setMultiple(false);
    }
  }

}
