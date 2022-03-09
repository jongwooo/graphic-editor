package menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    private JMenuItem undo;
    private JMenuItem redo;
    private JMenuItem cutItem;
    private JMenuItem copyItem;
    private JMenuItem pasteItem;

    public EditMenu(String menuTitle) {
        super(menuTitle);

        this.undo = new JMenuItem("Undo");
        this.add(this.undo);

        this.redo = new JMenuItem("Redo");
        this.add(this.redo);

        this.addSeparator();

        this.cutItem = new JMenuItem("Cut");
        this.add(this.cutItem);

        this.copyItem = new JMenuItem("Copy");
        this.add(this.copyItem);

        this.pasteItem = new JMenuItem("Paste");
        this.add(this.pasteItem);
    }
}
