package global.tool;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public enum SpinnerModels {
    outlineSizeModel(new SpinnerNumberModel(1, 1, 10, 1)),
    dashSizeModel(new SpinnerNumberModel(0, 0, 10, 1));

    private SpinnerNumberModel currentSpinnerModel;

    private SpinnerModels(SpinnerNumberModel currentSpinnerModel) {
        this.currentSpinnerModel = currentSpinnerModel;
    }

    public SpinnerNumberModel getModel() {
        return currentSpinnerModel;
    }

    public boolean isCurrentModel(SpinnerModel currentSpinnerModel) {
        return this.currentSpinnerModel == currentSpinnerModel;
    }
}
