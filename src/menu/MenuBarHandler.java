package menu;

import panel.DrawingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class MenuBarHandler implements ActionListener {
    private static final MenuBarHandler MENU_BAR_HANDLER = new MenuBarHandler();

    private DrawingPanel drawingPanel;

    public static MenuBarHandler createMenuBarHandler() {
        return MENU_BAR_HANDLER;
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public void newFile() {

    }

    public void openFile() {

    }

    public void closeWindow() {

    }

    public void saveFile() {

    }

    public void saveFileAs() {

    }

    public void print() {

    }

    public void quitEditor() {
        System.exit(0);
    }

    public void undo() {

    }

    public void redo() {

    }

    public void cut() {

    }

    public void copy() {

    }

    public void paste() {

    }

    public void group() {

    }

    public void ungroup() {

    }

    private void invokeMethod(String methodName) {
        try {
            this.getClass().getMethod(methodName).invoke(this);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        invokeMethod(e.getActionCommand());
    }
}
