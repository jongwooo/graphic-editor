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
        updateCurrentShape(ShapeToolItem.cursor.newShape());
    }

    public void rectangle() {
        updateCurrentShape(ShapeToolItem.rectangle.newShape());
    }

    public void ellipse() {
        updateCurrentShape(ShapeToolItem.ellipse.newShape());
    }

    public void line() {
        updateCurrentShape(ShapeToolItem.line.newShape());
    }

    public void polygon() {
        updateCurrentShape(ShapeToolItem.polygon.newShape());
    }

    public void pencil() {
        updateCurrentShape(ShapeToolItem.pencil.newShape());
    }

    public void eraser() {
        updateCurrentShape(ShapeToolItem.eraser.newShape());
    }

    public void clear() {
        drawingPanel.clearShapes();
    }

    public void outline() {
        drawingPanel.chooseOutlineColor();
    }

    public void fill() {
        drawingPanel.chooseFillColor();
    }

    private void updateCurrentShape(DrawShape currentShape) {
        drawingPanel.setCurrentShape(currentShape);
        drawingPanel.updateCursorStyle();
        drawingPanel.repaint();
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
