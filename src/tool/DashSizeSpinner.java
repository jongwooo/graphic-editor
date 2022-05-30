package tool;

import global.Constant;
import global.tool.SpinnerModels;

public class DashSizeSpinner extends SizeSpinner {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final DashSizeSpinner INSTANCE = new DashSizeSpinner();
  }

  private final SpinnerHandler spinnerHandler;

  private DashSizeSpinner() {
    super(SpinnerModels.dashSizeModel.getModel());
    spinnerHandler = SpinnerHandler.getInstance();
  }

  public static DashSizeSpinner getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void initialize() {
    createDashSizeSpinner();
  }

  private void createDashSizeSpinner() {
    setToolTipText(Constant.DASH_SPINNER_TITLE.toLowerCase().concat(" size"));
    setMaximumSize(getPreferredSize());
    addChangeListener(spinnerHandler);
  }
}