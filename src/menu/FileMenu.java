package menu;

import global.menu.FileMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.Arrays;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public FileMenu(String menuTitle) {
        super(menuTitle);

        Arrays.stream(FileMenuItem.values()).forEach(fileMenuItem -> {
            JMenuItem menuItem = new JMenuItem(fileMenuItem.getMenuName());
            this.add(menuItem);

            if(fileMenuItem.separated()) this.addSeparator();
        });
    }
}
