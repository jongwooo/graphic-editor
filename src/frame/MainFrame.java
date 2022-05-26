package frame;

import global.Constant;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import menu.MenuBar;
import panel.DrawingPanel;
import tool.ToolBar;
import util.file.FileControl;

public class MainFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final MainFrame INSTANCE = new MainFrame();
  }

  private final MenuBar menuBar;
  private final ToolBar toolBar;
  private final DrawingPanel drawingPanel;
  private final FileControl fileControl;
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
    fileControl = FileControl.getInstance();
    windowHandler = WindowHandler.getInstance();
  }

  public static MainFrame getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void associate() {
    menuBar.associate();
    toolBar.associate();
    drawingPanel.associate();
    fileControl.associate();
    windowHandler.associate();
  }

  public void initialize() {
    menuBar.initialize();
    this.setJMenuBar(menuBar);

    toolBar.initialize();
    this.add(BorderLayout.NORTH, toolBar);

    drawingPanel.initialize();
    this.add(BorderLayout.CENTER, drawingPanel);

    addWindowListener(windowHandler);
  }

  public void setDefaultTitle() {
    setTitle(Constant.MAINFRAME_TITLE);
  }
}
