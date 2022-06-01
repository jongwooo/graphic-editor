package components.tool.spinner;

import global.Constant;
import global.tool.SpinnerModels;
import handler.tool.spinner.SpinnerHandler;

public class OutlineSizeSpinner extends SizeSpinner {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final OutlineSizeSpinner INSTANCE = new OutlineSizeSpinner();
  }

  private final SpinnerHandler spinnerHandler;

  private OutlineSizeSpinner() {
    super(SpinnerModels.outlineSizeModel.getModel());
    spinnerHandler = SpinnerHandler.getInstance();
  }

  public static OutlineSizeSpinner getInstance() {
    return InstanceHolder.INSTANCE;
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
