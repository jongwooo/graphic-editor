package menu;

import panel.DrawingPanel;

import javax.swing.JMenuBar;
import util.file.FileControl;

public class MenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;
    private static final MenuBar MENU_BAR = new MenuBar();

    private final FileMenu fileMenu;
    private final EditMenu editMenu;
    private final MenuBarHandler menuBarHandler;

    private MenuBar() {
        menuBarHandler = MenuBarHandler.getInstance();
        fileMenu = FileMenu.getInstance();
        editMenu = EditMenu.getInstance();
    }

    public static MenuBar getInstance() {
        return MENU_BAR;
    }

    public void associate(DrawingPanel drawingPanel, FileControl fileControl) {
        menuBarHandler.associate(drawingPanel, fileControl);
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
