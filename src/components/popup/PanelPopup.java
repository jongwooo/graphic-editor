package components.popup;

import global.Constant;
import global.menu.EditMenuItem;
import global.menu.submenu.BringToFrontMenuItem;
import global.menu.submenu.SendToBackMenuItem;
import handler.popup.PanelPopupHandler;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PanelPopup extends JPopupMenu {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final PanelPopup INSTANCE = new PanelPopup();
  }

  private final JMenu bringToFrontMenu;
  private final JMenu sendToBackMenu;
  private final PanelPopupHandler panelPopupHandler;

  private PanelPopup() {
    bringToFrontMenu = new JMenu(Constant.BRING_TO_FRONT_MENU_TITLE);
    sendToBackMenu = new JMenu(Constant.SEND_TO_BACK_MENU_TITLE);
    panelPopupHandler = PanelPopupHandler.getInstance();
  }

  public static PanelPopup getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void initialize() {
    createPanelPopupItems();
    createBringToFrontMenu();
    createSendToBackMenuMenu();
  }

  private void createPanelPopupItems() {
    Arrays.stream(EditMenuItem.values()).forEach(item -> {
      JMenuItem menuItem = new JMenuItem(item.getMenuName());
      menuItem.setActionCommand(item.name());
      menuItem.addActionListener(panelPopupHandler);
      this.add(menuItem);
      if (item.hasSeparator()) {
        this.addSeparator();
      }
    });
  }

  private void createBringToFrontMenu() {
    Arrays.stream(BringToFrontMenuItem.values()).forEach(item -> {
      JMenuItem menuItem = new JMenuItem(item.getMenuName());
      menuItem.setActionCommand(item.name());
      menuItem.addActionListener(panelPopupHandler);
      bringToFrontMenu.add(menuItem);
    });
    this.add(bringToFrontMenu);
  }

  private void createSendToBackMenuMenu() {
    Arrays.stream(SendToBackMenuItem.values()).forEach(item -> {
      JMenuItem menuItem = new JMenuItem(item.getMenuName());
      menuItem.setActionCommand(item.name());
      menuItem.addActionListener(panelPopupHandler);
      sendToBackMenu.add(menuItem);
    });
    this.add(sendToBackMenu);
  }
}
