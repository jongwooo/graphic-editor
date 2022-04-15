package tool;

import global.Constant;
import global.tool.DrawingToolItem;
import global.tool.ShapeToolItem;
import global.tool.SpinnerModels;
import panel.DrawingPanel;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.util.Arrays;

public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;
    private static final ToolBar TOOL_BAR = new ToolBar();

    private final ButtonHandler buttonHandler;
    private final ButtonGroup shapeToolBtnGroup;
    private final SpinnerHandler spinnerHandler;
    private final SpinnerNumberModel outlineSizeModel, dashSizeModel;

    private ToolBar() {
        super(Constant.TOOLBAR_TITLE);
        setBorderPainted(true);
        setFloatable(false);

        buttonHandler = ButtonHandler.createButtonHandler();
        spinnerHandler = SpinnerHandler.createSpinnerHandler();
        shapeToolBtnGroup = new ButtonGroup();
        outlineSizeModel = SpinnerModels.outlineSizeModel.getModel();
        dashSizeModel = SpinnerModels.dashSizeModel.getModel();
    }

    public static ToolBar createToolBar() {
        return TOOL_BAR;
    }

    public void associate(DrawingPanel drawingPanel) {
        buttonHandler.associate(drawingPanel);
        spinnerHandler.associate(drawingPanel);
    }

    public void initialize() {
        createShapeToolButtons();
        createDrawingToolButtons();
        createSizeSpinner(outlineSizeModel, Constant.OUTLINE_SPINNER_TITLE);
        createSizeSpinner(dashSizeModel, Constant.DASH_SPINNER_TITLE);
        setDefaultButton();
    }

    private ImageIcon createImageIcon(String imageName, boolean selected) {
        return new ImageIcon("src/image/".concat(selected ? "selected_" : "").concat(imageName).concat(".png"));
    }

    private void createShapeToolButtons() {
        Arrays.stream(ShapeToolItem.values()).forEach(shapeToolItem -> {
            JRadioButton shapeToolBtn = new JRadioButton();
            shapeToolBtn.setToolTipText(shapeToolItem.name());
            shapeToolBtn.setActionCommand(shapeToolItem.name());
            shapeToolBtn.addActionListener(buttonHandler);
            shapeToolBtn.setIcon(createImageIcon(shapeToolItem.name(), false));
            shapeToolBtn.setSelectedIcon(createImageIcon(shapeToolItem.name(), true));
            shapeToolBtnGroup.add(shapeToolBtn);
            this.add(shapeToolBtn);
        });
        this.addSeparator();
    }

    private void createDrawingToolButtons() {
        Arrays.stream(DrawingToolItem.values()).forEach(drawingToolItem -> {
            JButton drawingToolBtn = new JButton();
            drawingToolBtn.setToolTipText(drawingToolItem.name());
            drawingToolBtn.setActionCommand(drawingToolItem.name());
            drawingToolBtn.addActionListener(buttonHandler);
            drawingToolBtn.setIcon(createImageIcon(drawingToolItem.name(), false));
            drawingToolBtn.setBorderPainted(false);
            drawingToolBtn.setFocusPainted(false);
            drawingToolBtn.setContentAreaFilled(false);
            this.add(drawingToolBtn);
        });
        this.addSeparator();
    }

    private void setDefaultButton() {
        JRadioButton defaultBtn = (JRadioButton) this.getComponent(ShapeToolItem.rectangle.ordinal());
        defaultBtn.doClick();
    }

    private void createSizeSpinner(SpinnerModel spinnerModel, String spinnerName) {
        JSpinner sizeSpinner = new JSpinner(spinnerModel);
        sizeSpinner.setToolTipText(spinnerName.toLowerCase().concat(" size"));
        sizeSpinner.setMaximumSize(sizeSpinner.getPreferredSize());
        sizeSpinner.addChangeListener(spinnerHandler);
        this.add(new JLabel(spinnerName));
        this.add(sizeSpinner);
        this.addSeparator();
    }
}
