package components.tool.spinner;

import global.Constant;
import global.tool.SpinnerModels;
import handlers.tool.spinner.SpinnerHandler;

public class OutlineSizeSpinner extends SizeSpinner {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final OutlineSizeSpinner INSTANCE = new OutlineSizeSpinner();
  }

  private OutlineSizeSpinner() {
    super(SpinnerModels.outlineSizeModel.getModel());

    SpinnerHandler spinnerHandler = SpinnerHandler.getInstance();
    addChangeListener(spinnerHandler);
  }

  public static OutlineSizeSpinner getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void initialize() {
    createOutlineSizeSpinner();
  }

  private void createOutlineSizeSpinner() {
    setToolTipText(Constant.OUTLINE_SPINNER_TITLE.toLowerCase().concat(" size"));
  }
}
