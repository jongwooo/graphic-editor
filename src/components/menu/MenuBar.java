package components.menu;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final MenuBar INSTANCE = new MenuBar();
  }

  private final FileMenu fileMenu;
  private final EditMenu editMenu;

  private MenuBar() {
    fileMenu = FileMenu.getInstance();
    editMenu = EditMenu.getInstance();
    this.add(fileMenu);
    this.add(editMenu);
  }

  public static MenuBar getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void initialize() {
    fileMenu.initialize();
    editMenu.initialize();
  }
}
