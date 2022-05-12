package tool;

import global.tool.SpinnerModels;
import panel.DrawingPanel;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerHandler implements ChangeListener {

    private static class InstanceHolder {

        private static final SpinnerHandler INSTANCE = new SpinnerHandler();
    }

    private DrawingPanel drawingPanel;

    public static SpinnerHandler getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner currentSpinner = (JSpinner) e.getSource();
        SpinnerModel currentSpinnerModel = currentSpinner.getModel();
        int currentSize = (int) currentSpinner.getValue();

        if (SpinnerModels.outlineSizeModel.isCurrentModel(currentSpinnerModel)) {
            drawingPanel.updateOutlineSize(currentSize);
        } else if (SpinnerModels.dashSizeModel.isCurrentModel(currentSpinnerModel)) {
            drawingPanel.updateDashSize(currentSize);
        }
    }
}
