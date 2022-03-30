package tool;

import global.tool.ShapeToolItem;
import panel.DrawingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class ToolBarHandler implements ActionListener {
    private DrawingPanel drawingPanel;

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public void cursor() {
        drawingPanel.setCurrentShape(ShapeToolItem.cursor.getShape());
    }

    public void rectangle() {
        drawingPanel.setCurrentShape(ShapeToolItem.rectangle.getShape());
    }

    public void ellipse() {
        drawingPanel.setCurrentShape(ShapeToolItem.ellipse.getShape());
    }

    public void line() {
        drawingPanel.setCurrentShape(ShapeToolItem.line.getShape());
    }

    public void polygon() {
        drawingPanel.setCurrentShape(ShapeToolItem.polygon.getShape());
    }

    public void erase() {
        drawingPanel.eraseShape();
    }

    public void clean() {
        drawingPanel.cleanPanel();
    }

    public void outline() {
        drawingPanel.setOutlineColor();
    }

    public void fill() {
        drawingPanel.setFillColor();
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
