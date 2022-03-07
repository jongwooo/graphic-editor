package menu;

import global.Constant;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.Arrays;

public class FileMenu extends JMenu {
    public FileMenu() {
        super(Constant.FILE_MENU_TITLE);

        String[] fileMenuItems = Constant.FILE_MENU_ITEMS;
        Arrays.stream(fileMenuItems).forEach(menuItems -> {
           JMenuItem menuItem = new JMenuItem(menuItems);
           this.add(menuItem);
        });

        this.addSeparator();
    }
}
