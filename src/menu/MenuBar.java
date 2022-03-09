package menu;

import global.Constant;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;

    private FileMenu fileMenu;
    private EditMenu editMenu;

    public MenuBar() {
        this.fileMenu = new FileMenu(Constant.FILE_MENU_TITLE);
        this.add(this.fileMenu);

        this.editMenu = new EditMenu(Constant.EDIT_MENU_TITLE);
        this.add(this.editMenu);
    }
}
