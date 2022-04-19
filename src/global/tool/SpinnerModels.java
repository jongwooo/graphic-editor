package global.tool;

import global.Constant;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public enum SpinnerModels {
    outlineSizeModel(Constant.OUTLINE_SIZE_MODEL), dashSizeModel(Constant.DASH_SIZE_MODEL);

    private final SpinnerNumberModel currentSpinnerModel;

    SpinnerModels(SpinnerNumberModel currentSpinnerModel) {
        this.currentSpinnerModel = currentSpinnerModel;
    }

    public SpinnerNumberModel getModel() {
        return currentSpinnerModel;
    }

    public boolean isCurrentModel(SpinnerModel currentSpinnerModel) {
        return this.currentSpinnerModel == currentSpinnerModel;
    }
}
