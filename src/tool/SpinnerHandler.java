package tool;

import panel.DrawingPanel;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerHandler implements ChangeListener {
    private static final SpinnerHandler SPINNER_HANDLER = new SpinnerHandler();

    private ToolBar toolBar;
    private DrawingPanel drawingPanel;

    public static SpinnerHandler createSpinnerHandler() {
        return SPINNER_HANDLER;
    }

    public void associate(ToolBar toolBar, DrawingPanel drawingPanel) {
        this.toolBar = toolBar;
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner currentSpinner = (JSpinner) e.getSource();
        SpinnerModel currentSpinnerModel = currentSpinner.getModel();
        int currentSize = (int) currentSpinner.getValue();

        if (toolBar.isOutlineSizeModel(currentSpinnerModel)) {
            drawingPanel.updateOutlineSize(currentSize);
        } else if (toolBar.isDashSizeModel(currentSpinnerModel)) {
            drawingPanel.updateDashSize(currentSize);
        }
    }
}
