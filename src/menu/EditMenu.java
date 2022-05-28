package menu;

import global.Constant;
import global.menu.EditMenuItem;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final EditMenu INSTANCE = new EditMenu();
  }

  private final EditMenuHandler editMenuHandler;

  private EditMenu() {
    super(Constant.EDIT_MENU_TITLE);
    editMenuHandler = EditMenuHandler.getInstance();
  }

  public static EditMenu getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void initialize() {
    createEditMenuItems();
  }

  private void createEditMenuItems() {
    Arrays.stream(EditMenuItem.values()).forEach(editMenuItem -> {
      JMenuItem menuItem = new JMenuItem(editMenuItem.getMenuName());
      menuItem.setActionCommand(editMenuItem.name());
      menuItem.addActionListener(editMenuHandler);
      menuItem.setAccelerator(editMenuItem.getKeyStroke());
      this.add(menuItem);
      if (editMenuItem.hasSeparator()) {
        this.addSeparator();
      }
    });
  }
}
