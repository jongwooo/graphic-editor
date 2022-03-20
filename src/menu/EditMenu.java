package menu;

import global.menu.EditMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.Arrays;

public class EditMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public EditMenu(String menuTitle, ActionHandler actionHandler) {
        super(menuTitle);

        Arrays.stream(EditMenuItem.values()).forEach(editMenuItem -> {
            JMenuItem menuItem = new JMenuItem(editMenuItem.getMenuName());
            menuItem.setActionCommand(editMenuItem.name());
            menuItem.addActionListener(actionHandler);
            this.add(menuItem);

            if (editMenuItem.separated()) this.addSeparator();
        });
    }
}
