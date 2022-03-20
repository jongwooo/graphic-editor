package menu;

import global.menu.FileMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    private ActionHandler actionHandler;

    public FileMenu(String menuTitle) {
        super(menuTitle);
        actionHandler = new ActionHandler();

        Arrays.stream(FileMenuItem.values()).forEach(fileMenuItem -> {
            JMenuItem menuItem = new JMenuItem(fileMenuItem.getMenuName());
            menuItem.setActionCommand(fileMenuItem.name());
            menuItem.addActionListener(actionHandler);
            this.add(menuItem);

            if(fileMenuItem.separated()) this.addSeparator();
        });
    }

    public void newFile() {

    }

    public void openFile() {

    }

    public void closeEditor() {

    }

    public void saveFile() {

    }

    public void saveFileAs() {

    }

    public void printFile() {

    }

    public void quitEditor() {
        System.exit(0);
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
