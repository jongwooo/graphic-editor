package components.menu;

import global.Constant;
import global.menu.FileMenuItem;
import handlers.menu.FileMenuHandler;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final FileMenu INSTANCE = new FileMenu();
  }

  private final FileMenuHandler fileMenuHandler;

  private FileMenu() {
    super(Constant.FILE_MENU_TITLE);
    fileMenuHandler = FileMenuHandler.getInstance();
  }

  public static FileMenu getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void initialize() {
    createFileMenuItems();
  }

  private void createFileMenuItems() {
    Arrays.stream(FileMenuItem.values()).forEach(fileMenuItem -> {
      JMenuItem menuItem = new JMenuItem(fileMenuItem.getMenuName());
      menuItem.setActionCommand(fileMenuItem.name());
      menuItem.addActionListener(fileMenuHandler);
      menuItem.setAccelerator(fileMenuItem.getKeyStroke());
      this.add(menuItem);
      if (fileMenuItem.hasSeparator()) {
        this.addSeparator();
      }
    });
  }
}
