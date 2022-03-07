package menu;

import global.Constant;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu {
    public EditMenu() {
        super(Constant.EDIT_MENU_TITLE);

        JMenuItem undo = new JMenuItem("Undo");
        this.add(undo);

        JMenuItem redo = new JMenuItem("Redo");
        this.add(redo);

        this.addSeparator();

        JMenuItem cutItem = new JMenuItem("Cut");
        this.add(cutItem);

        JMenuItem copyItem = new JMenuItem("Copy");
        this.add(copyItem);

        JMenuItem pasteItem = new JMenuItem("Paste");
        this.add(pasteItem);
    }
}
