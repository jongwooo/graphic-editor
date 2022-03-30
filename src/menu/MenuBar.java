package menu;

import panel.DrawingPanel;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;

    private FileMenu fileMenu;
    private EditMenu editMenu;
    private MenuBarHandler menuBarHandler;

    public MenuBar() {
        menuBarHandler = new MenuBarHandler();

        fileMenu = new FileMenu(menuBarHandler);
        this.add(fileMenu);

        editMenu = new EditMenu(menuBarHandler);
        this.add(editMenu);
    }

    public void associate(DrawingPanel drawingPanel) {
        menuBarHandler.associate(drawingPanel);
    }
}
