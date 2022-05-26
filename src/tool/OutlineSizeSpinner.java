package tool;

import global.Constant;
import global.tool.SpinnerModels;
import javax.swing.JSpinner;

public class OutlineSizeSpinner extends JSpinner {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final OutlineSizeSpinner INSTANCE = new OutlineSizeSpinner();
  }

  private SpinnerHandler spinnerHandler;

  private OutlineSizeSpinner() {
    super(SpinnerModels.outlineSizeModel.getModel());
  }

  public static OutlineSizeSpinner getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void associate() {
    spinnerHandler = SpinnerHandler.getInstance();
  }

  public void initialize() {
    createOutlineSizeSpinner();
  }

  private void createOutlineSizeSpinner() {
    setToolTipText(Constant.OUTLINE_SPINNER_TITLE.toLowerCase().concat(" size"));
    setMaximumSize(getPreferredSize());
    addChangeListener(spinnerHandler);
  }
}
