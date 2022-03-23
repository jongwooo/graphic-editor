package menu;

import panel.DrawingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class MenuBarHandler implements ActionListener {
    private DrawingPanel drawingPanel;

    public void initialize(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        invokeMethod(e.getActionCommand());
    }
}
