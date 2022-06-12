package frame;

import components.menu.MenuBar;
import components.panel.DrawingPanel;
import components.tool.ToolBar;
import global.Constant;
import handlers.frame.WindowHandler;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final MainFrame INSTANCE = new MainFrame();
  }

  private final MenuBar menuBar;
  private final ToolBar toolBar;
  private final DrawingPanel drawingPanel;
  private final WindowHandler windowHandler;

  private MainFrame() {
    super(Constant.MAINFRAME_TITLE);
    setSize(Constant.MAINFRAME_WIDTH, Constant.MAINFRAME_HEIGHT);
    setLayout(new BorderLayout());
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    menuBar = MenuBar.getInstance();
    toolBar = ToolBar.getInstance();
    drawingPanel = DrawingPanel.getInstance();
    windowHandler = WindowHandler.getInstance();
    this.setJMenuBar(menuBar);
    this.add(BorderLayout.NORTH, toolBar);
    this.add(BorderLayout.CENTER, drawingPanel);
  }

  public static MainFrame getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void initialize() {
    menuBar.initialize();
    toolBar.initialize();
    drawingPanel.initialize();
    addWindowListener(windowHandler);
  }
}
