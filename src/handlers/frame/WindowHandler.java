package handlers.frame;

import components.panel.DrawingPanel;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import utils.file.FileControl;

public class WindowHandler implements WindowListener, ComponentListener {

  private static class InstanceHolder {

    private static final WindowHandler INSTANCE = new WindowHandler();
  }

  private final DrawingPanel drawingPanel;
  private final FileControl fileControl;

  private WindowHandler() {
    drawingPanel = DrawingPanel.getInstance();
    fileControl = FileControl.getInstance();
  }

  public static WindowHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void componentResized(ComponentEvent e) {
    drawingPanel.createBufferedImage();
  }

  @Override
  public void componentMoved(ComponentEvent e) {

  }

  @Override
  public void componentShown(ComponentEvent e) {

  }

  @Override
  public void componentHidden(ComponentEvent e) {

  }

  @Override
  public void windowOpened(WindowEvent e) {

  }

  @Override
  public void windowClosing(WindowEvent e) {
    fileControl.quitEditor();
  }

  @Override
  public void windowClosed(WindowEvent e) {

  }

  @Override
  public void windowIconified(WindowEvent e) {

  }

  @Override
  public void windowDeiconified(WindowEvent e) {

  }

  @Override
  public void windowActivated(WindowEvent e) {

  }

  @Override
  public void windowDeactivated(WindowEvent e) {

  }
}
