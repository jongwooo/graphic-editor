package menu;

import panel.DrawingPanel;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;
    private static final MenuBar MENU_BAR = new MenuBar();

    private final FileMenu fileMenu;
    private final EditMenu editMenu;
    private final MenuBarHandler menuBarHandler;

    private MenuBar() {
        menuBarHandler = MenuBarHandler.createMenuBarHandler();
        fileMenu = FileMenu.createFileMenu();
        editMenu = EditMenu.createEditMenu();
    }

    public static MenuBar createMenuBar() {
        return MENU_BAR;
    }

    public void associate(DrawingPanel drawingPanel) {
        menuBarHandler.associate(drawingPanel);
        fileMenu.associate(menuBarHandler);
        editMenu.associate(menuBarHandler);
    }

    public void initialize() {
        fileMenu.initialize();
        this.add(fileMenu);

        editMenu.initialize();
        this.add(editMenu);
    }
}
