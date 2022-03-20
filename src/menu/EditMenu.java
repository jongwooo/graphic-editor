package menu;

import global.menu.EditMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class EditMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    private ActionHandler actionHandler;

    public EditMenu(String menuTitle) {
        super(menuTitle);
        actionHandler = new ActionHandler();

        Arrays.stream(EditMenuItem.values()).forEach(editMenuItem -> {
            JMenuItem menuItem = new JMenuItem(editMenuItem.getMenuName());
            menuItem.setActionCommand(editMenuItem.name());
            menuItem.addActionListener(actionHandler);
            this.add(menuItem);

            if (editMenuItem.separated()) this.addSeparator();
        });
    }

    public void undo() {

    }

    public void redo() {

    }

    public void cutItem() {

    }

    public void copyItem() {

    }

    public void pasteItem() {

    }

    public void groupItem() {

    }

    public void ungroupItem() {

    }

    public void invokeMethod(String methodName) {
        try {
            this.getClass().getMethod(methodName).invoke(this);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            invokeMethod(e.getActionCommand());
        }
    }
}
