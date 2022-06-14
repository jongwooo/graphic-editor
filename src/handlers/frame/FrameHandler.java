package handlers.frame;

import components.panel.DrawingPanel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FrameHandler extends ComponentAdapter {

  private static class InstanceHolder {

    private static final FrameHandler INSTANCE = new FrameHandler();
  }

  private final DrawingPanel drawingPanel;

  private FrameHandler() {
    drawingPanel = DrawingPanel.getInstance();
  }

  public static FrameHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void componentResized(ComponentEvent e) {
    drawingPanel.createBufferedImage();
  }
}
