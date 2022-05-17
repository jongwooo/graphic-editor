package tool;

import global.Constant;
import global.tool.SpinnerModels;
import javax.swing.JSpinner;

public class DashSizeSpinner extends JSpinner {

    private static final long serialVersionUID = 1L;

    private static class InstanceHolder {

        private static final DashSizeSpinner INSTANCE = new DashSizeSpinner();
    }

    private SpinnerHandler spinnerHandler;

    private DashSizeSpinner() {
        super(SpinnerModels.dashSizeModel.getModel());
    }

    public static DashSizeSpinner getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void associate() {
        spinnerHandler = SpinnerHandler.getInstance();
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
