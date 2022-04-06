package tool;

import draw.DrawShape;
import global.tool.ShapeToolItem;
import panel.DrawingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class ButtonHandler implements ActionListener {
    private static final ButtonHandler BUTTON_HANDLER = new ButtonHandler();

    private DrawingPanel drawingPanel;

    public static ButtonHandler createButtonHandler() {
        return BUTTON_HANDLER;
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public void cursor() {
        setCurrentShape(ShapeToolItem.cursor.getShape());
    }

    public void rectangle() {
        setCurrentShape(ShapeToolItem.rectangle.getShape());
    }

    public void ellipse() {
        setCurrentShape(ShapeToolItem.ellipse.getShape());
    }

    public void line() {
        setCurrentShape(ShapeToolItem.line.getShape());
    }

    public void polygon() {
        setCurrentShape(ShapeToolItem.polygon.getShape());
    }

    public void erase() {
        drawingPanel.eraseShape();
    }

    public void clear() {
        drawingPanel.clearShapes();
    }

    public void outline() {
        drawingPanel.setOutlineColor();
    }

    public void fill() {
        drawingPanel.setFillColor();
    }

    private void setCurrentShape(DrawShape currentShape) {
        drawingPanel.setCurrentShape(currentShape);
        drawingPanel.repaint();
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
