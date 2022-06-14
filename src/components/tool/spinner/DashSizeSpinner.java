package components.tool.spinner;

import global.Constant;
import global.tool.SpinnerModels;
import handlers.tool.spinner.SpinnerHandler;

public class DashSizeSpinner extends SizeSpinner {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final DashSizeSpinner INSTANCE = new DashSizeSpinner();
  }

  private DashSizeSpinner() {
    super(SpinnerModels.dashSizeModel.getModel());

    SpinnerHandler spinnerHandler = SpinnerHandler.getInstance();
    addChangeListener(spinnerHandler);
  }

  public static DashSizeSpinner getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void initialize() {
    createDashSizeSpinner();
  }

  private void createDashSizeSpinner() {
    setToolTipText(Constant.DASH_SPINNER_TITLE.toLowerCase().concat(" size"));
  }
}
