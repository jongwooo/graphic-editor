package menu;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        FileMenu fileMenu = new FileMenu();
        this.add(fileMenu);

        EditMenu editMenu = new EditMenu();
        this.add(editMenu);
    }
}
