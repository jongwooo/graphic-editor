package menu;

import global.Constant;
import panel.DrawingPanel;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;

    private FileMenu fileMenu;
    private EditMenu editMenu;
    private ActionHandler actionHandler;

    public MenuBar() {
        actionHandler = new ActionHandler();

        fileMenu = new FileMenu(Constant.FILE_MENU_TITLE, actionHandler);
        this.add(fileMenu);

        editMenu = new EditMenu(Constant.EDIT_MENU_TITLE, actionHandler);
        this.add(editMenu);
    }

    public void initialize(DrawingPanel drawingPanel) {
        actionHandler.initialize(drawingPanel);
    }
}
