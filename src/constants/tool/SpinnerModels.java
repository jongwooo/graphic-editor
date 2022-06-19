package constants.tool;

import constants.Constant;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public enum SpinnerModels {
  outlineSizeModel(Constant.OUTLINE_SIZE_MODEL),
  dashSizeModel(Constant.DASH_SIZE_MODEL);

  private final SpinnerNumberModel spinnerNumberModel;

  SpinnerModels(SpinnerNumberModel spinnerNumberModel) {
    this.spinnerNumberModel = spinnerNumberModel;
  }

  public SpinnerNumberModel getModel() {
    return spinnerNumberModel;
  }

  public boolean isCurrentModel(SpinnerModel spinnerModel) {
    return this.spinnerNumberModel == spinnerModel;
  }
}
