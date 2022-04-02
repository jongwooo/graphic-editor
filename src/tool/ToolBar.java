package tool;

import global.Constant;
import global.tool.ShapeToolItem;
import global.tool.StateToolItem;
import panel.DrawingPanel;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import java.util.Arrays;
import java.util.Locale;

public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;
    private static final ToolBar TOOL_BAR = new ToolBar();

    private ButtonHandler buttonHandler;
    private ButtonGroup shapeToolBtnGroup;
    private SpinnerHandler spinnerHandler;
    private SpinnerNumberModel outlineSizeModel, dashSizeModel;

    private ToolBar() {
        super(Constant.TOOLBAR_TITLE);
        setBorderPainted(true);
        setFloatable(false);
        createSpinnerModels();

        buttonHandler = ButtonHandler.createButtonHandler();
        spinnerHandler = SpinnerHandler.createSpinnerHandler();
        shapeToolBtnGroup = new ButtonGroup();
    }

    public static ToolBar createToolBar() {
        return TOOL_BAR;
    }

    public void associate(DrawingPanel drawingPanel) {
        buttonHandler.associate(drawingPanel);
        spinnerHandler.associate(drawingPanel, outlineSizeModel, dashSizeModel);
    }

    public void initialize() {
        createShapeToolButtons();
        createStateToolButtons();
        createOutlineSizeSpinner();
        createDashSizeSpinner();
        setDefaultButton();
    }

    private void createShapeToolButtons() {
        Arrays.stream(ShapeToolItem.values()).forEach(shapeToolItem -> {
            JRadioButton shapeToolBtn = new JRadioButton();
            shapeToolBtn.setToolTipText(shapeToolItem.name());
            shapeToolBtn.setActionCommand(shapeToolItem.name());
            shapeToolBtn.addActionListener(buttonHandler);
            shapeToolBtn.setIcon(new ImageIcon("src/image/" + shapeToolItem.name() + ".png"));
            shapeToolBtn.setSelectedIcon(new ImageIcon("src/image/selected_" + shapeToolItem.name() + ".png"));
            shapeToolBtnGroup.add(shapeToolBtn);
            this.add(shapeToolBtn);
        });
        this.addSeparator();
    }

    private void createStateToolButtons() {
        Arrays.stream(StateToolItem.values()).forEach(stateToolItem -> {
            JButton stateToolBtn = new JButton();
            stateToolBtn.setToolTipText(stateToolItem.name());
            stateToolBtn.setActionCommand(stateToolItem.name());
            stateToolBtn.addActionListener(buttonHandler);
            stateToolBtn.setIcon(new ImageIcon("src/image/" + stateToolItem.name() + ".png"));
            stateToolBtn.setBorderPainted(false);
            stateToolBtn.setFocusPainted(false);
            stateToolBtn.setContentAreaFilled(false);
            this.add(stateToolBtn);
        });
        this.addSeparator();
    }

    private void setDefaultButton() {
        JRadioButton defaultBtn = (JRadioButton) this.getComponent(ShapeToolItem.rectangle.ordinal());
        defaultBtn.doClick();
    }

    private void createSpinnerModels() {
        outlineSizeModel = new SpinnerNumberModel(1, 1, 10, 1);
        dashSizeModel = new SpinnerNumberModel(0, 0, 10, 1);
    }

    private void createOutlineSizeSpinner() {
        JSpinner outlineSizeSpinner = new JSpinner(outlineSizeModel);
        outlineSizeSpinner.addChangeListener(spinnerHandler);
        outlineSizeSpinner.setMaximumSize(outlineSizeSpinner.getPreferredSize());
        outlineSizeSpinner.setToolTipText(Constant.OUTLINE_SPINNER_TITLE.toLowerCase(Locale.ROOT));
        this.add(new JLabel(Constant.OUTLINE_SPINNER_TITLE));
        this.add(outlineSizeSpinner);
        this.addSeparator();
    }

    private void createDashSizeSpinner() {
        JSpinner dashSizeSpinner = new JSpinner(dashSizeModel);
        dashSizeSpinner.addChangeListener(spinnerHandler);
        dashSizeSpinner.setMaximumSize(dashSizeSpinner.getPreferredSize());
        dashSizeSpinner.setToolTipText(Constant.DASH_SPINNER_TITLE.toLowerCase(Locale.ROOT));
        this.add(new JLabel(Constant.DASH_SPINNER_TITLE));
        this.add(dashSizeSpinner);
        this.addSeparator();
    }
}
