package menu;

import panel.DrawingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import util.file.FileControl;

public class MenuBarHandler implements ActionListener {

    private static final MenuBarHandler MENU_BAR_HANDLER = new MenuBarHandler();

    private DrawingPanel drawingPanel;
    private FileControl fileControl;

    public static MenuBarHandler createMenuBarHandler() {
        return MENU_BAR_HANDLER;
    }

    public void associate(DrawingPanel drawingPanel, FileControl fileControl) {
        this.drawingPanel = drawingPanel;
        this.fileControl = fileControl;
    }

    public void newFile() {
        fileControl.newFile();
    }

    public void openFile() {
        fileControl.openFile();
    }

    public void saveFile() {
        fileControl.saveFile();
    }

    public void saveFileAs() {
        fileControl.saveFileAs();
    }

    public void print() {
        fileControl.print();
    }

    public void quitEditor() {
        fileControl.quitEditor();
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
        } catch (InvocationTargetException | IllegalAccessException |
                 NoSuchMethodException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        invokeMethod(e.getActionCommand());
    }
}
