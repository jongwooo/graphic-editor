package tool;

import panel.DrawingPanel;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerHandler implements ChangeListener {
    private static final SpinnerHandler SPINNER_HANDLER = new SpinnerHandler();

    private DrawingPanel drawingPanel;
    private SpinnerNumberModel outlineSizeModel, dashSizeModel;

    public static SpinnerHandler createSpinnerHandler() {
        return SPINNER_HANDLER;
    }

    public void associate(DrawingPanel drawingPanel, SpinnerNumberModel outlineSizeModel, SpinnerNumberModel dashSizeModel) {
        this.drawingPanel = drawingPanel;
        this.outlineSizeModel = outlineSizeModel;
        this.dashSizeModel = dashSizeModel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner currentSpinner = (JSpinner) e.getSource();
        SpinnerModel currentSpinnerModel = currentSpinner.getModel();
        int currentSize =  (int) currentSpinner.getValue();

        if (currentSpinnerModel == outlineSizeModel) {
            drawingPanel.setOutlineSize(currentSize);
        } else if (currentSpinnerModel == dashSizeModel) {
            drawingPanel.setDashSize(currentSize);
        }
    }
}
