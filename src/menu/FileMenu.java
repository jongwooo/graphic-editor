package menu;

import global.Constant;
import global.menu.FileMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.Arrays;

public class FileMenu extends JMenu {

    private static final long serialVersionUID = 1L;

    private static class InstanceHolder {

        private static final FileMenu INSTANCE = new FileMenu();
    }

    private MenuBarHandler menuBarHandler;

    private FileMenu() {
        super(Constant.FILE_MENU_TITLE);
    }

    public static FileMenu getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void associate(MenuBarHandler menuBarHandler) {
        this.menuBarHandler = menuBarHandler;
    }

    public void initialize() {
        createFileMenuItems();
    }

    private void createFileMenuItems() {
        Arrays.stream(FileMenuItem.values()).forEach(fileMenuItem -> {
            JMenuItem menuItem = new JMenuItem(fileMenuItem.getMenuName());
            menuItem.setActionCommand(fileMenuItem.name());
            menuItem.addActionListener(menuBarHandler);
            menuItem.setAccelerator(fileMenuItem.getKeyStroke());
            this.add(menuItem);
            if (fileMenuItem.hasSeparator()) {
                this.addSeparator();
            }
        });
    }
}
