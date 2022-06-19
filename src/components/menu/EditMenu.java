package components.menu;

import constants.Constant;
import constants.menu.EditMenuItem;
import constants.menu.submenu.BringToFrontMenuItem;
import constants.menu.submenu.SendToBackMenuItem;
import handlers.menu.EditMenuHandler;
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
    createBringToFrontMenu();
    createSendToBackMenuMenu();
  }

  private void createEditMenuItems() {
    Arrays.stream(EditMenuItem.values()).forEach(item -> {
      JMenuItem menuItem = new JMenuItem(item.getMenuName());
      menuItem.setActionCommand(item.name());
      menuItem.addActionListener(editMenuHandler);
      menuItem.setAccelerator(item.getKeyStroke());
      this.add(menuItem);
      if (item.hasSeparator()) {
        this.addSeparator();
      }
    });
  }

  private void createBringToFrontMenu() {
    JMenu bringToFrontMenu = new JMenu(Constant.BRING_TO_FRONT_MENU_TITLE);
    Arrays.stream(BringToFrontMenuItem.values()).forEach(item -> {
      JMenuItem menuItem = new JMenuItem(item.getMenuName());
      menuItem.setActionCommand(item.name());
      menuItem.addActionListener(editMenuHandler);
      menuItem.setAccelerator(item.getKeyStroke());
      bringToFrontMenu.add(menuItem);
    });
    this.add(bringToFrontMenu);
  }

  private void createSendToBackMenuMenu() {
    JMenu sendToBackMenu = new JMenu(Constant.SEND_TO_BACK_MENU_TITLE);
    Arrays.stream(SendToBackMenuItem.values()).forEach(item -> {
      JMenuItem menuItem = new JMenuItem(item.getMenuName());
      menuItem.setActionCommand(item.name());
      menuItem.addActionListener(editMenuHandler);
      menuItem.setAccelerator(item.getKeyStroke());
      sendToBackMenu.add(menuItem);
    });
    this.add(sendToBackMenu);
  }
}
