package menu;

import global.Constant;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.Arrays;

public class EditMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public EditMenu(String menuTitle) {
        super(menuTitle);

        Arrays.stream(Constant.EditMenuItem.values()).forEach(editMenuItem -> {
            JMenuItem menuItem = new JMenuItem(editMenuItem.getMenuName());
            this.add(menuItem);

            if (editMenuItem.separated()) this.addSeparator();
        });
    }
}
