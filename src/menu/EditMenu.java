package menu;

import global.Constant;
import global.menu.EditMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.Arrays;

public class EditMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public EditMenu(MenuBarHandler menuBarHandler) {
        super(Constant.EDIT_MENU_TITLE);

        Arrays.stream(EditMenuItem.values()).forEach(editMenuItem -> {
            JMenuItem menuItem = new JMenuItem(editMenuItem.getMenuName());
            menuItem.setActionCommand(editMenuItem.name());
            menuItem.addActionListener(menuBarHandler);
            this.add(menuItem);

            if (editMenuItem.separated()) this.addSeparator();
        });
    }
}
