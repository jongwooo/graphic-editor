package menu;

import panel.DrawingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import util.file.FileControl;

public class MenuBarHandler implements ActionListener {

    private static class InstanceHolder {

        private static final MenuBarHandler INSTANCE = new MenuBarHandler();
    }

    private DrawingPanel drawingPanel;
    private FileControl fileControl;

    public static MenuBarHandler getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void associate() {
        drawingPanel = DrawingPanel.getInstance();
        fileControl = FileControl.getInstance();
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
        drawingPanel.undo();
    }

    public void redo() {
        drawingPanel.redo();
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
