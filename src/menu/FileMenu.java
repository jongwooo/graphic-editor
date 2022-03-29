package menu;

import global.Constant;
import global.menu.FileMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.Arrays;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public FileMenu(MenuBarHandler menuBarHandler) {
        super(Constant.FILE_MENU_TITLE);

        Arrays.stream(FileMenuItem.values()).forEach(fileMenuItem -> {
            JMenuItem menuItem = new JMenuItem(fileMenuItem.getMenuName());
            menuItem.setActionCommand(fileMenuItem.name());
            menuItem.addActionListener(menuBarHandler);
            this.add(menuItem);

            if (fileMenuItem.separated()) this.addSeparator();
        });
    }
}
