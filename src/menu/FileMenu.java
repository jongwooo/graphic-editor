package menu;

import global.Constant;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.Arrays;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    public FileMenu(String menuTitle) {
        super(menuTitle);

        Arrays.stream(Constant.FileMenuItem.values()).forEach(fileMenuItem -> {
            JMenuItem menuItem = new JMenuItem(fileMenuItem.getMenuName());
            if(fileMenuItem.separated()) this.addSeparator();
            this.add(menuItem);
        });
    }
}
