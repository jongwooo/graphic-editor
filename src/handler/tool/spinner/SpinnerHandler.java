package handler.tool.spinner;

import global.tool.SpinnerModels;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import components.panel.DrawingPanel;

public class SpinnerHandler implements ChangeListener {

  private static class InstanceHolder {

    private static final SpinnerHandler INSTANCE = new SpinnerHandler();
  }

  public static SpinnerHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSpinner currentSpinner = (JSpinner) e.getSource();
    SpinnerModel currentSpinnerModel = currentSpinner.getModel();
    int currentSize = (int) currentSpinner.getValue();

    DrawingPanel drawingPanel = DrawingPanel.getInstance();
    if (SpinnerModels.outlineSizeModel.isCurrentModel(currentSpinnerModel)) {
      drawingPanel.updateOutlineSize(currentSize);
    } else if (SpinnerModels.dashSizeModel.isCurrentModel(currentSpinnerModel)) {
      drawingPanel.updateDashSize(currentSize);
    }
  }
}
