package tool;

import global.Constant;
import global.tool.ShapeToolItem;
import global.tool.StateToolItem;
import panel.DrawingPanel;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import java.util.Arrays;

public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;
    private static final ToolBar TOOL_BAR = new ToolBar();

    private ToolBarHandler toolBarHandler;
    private ButtonGroup shapeToolBtnGroup;

    private ToolBar() {
        super(Constant.TOOLBAR_TITLE);
        setBorderPainted(true);

        toolBarHandler = ToolBarHandler.createToolBarHandler();
        shapeToolBtnGroup = new ButtonGroup();
    }

    public static ToolBar createToolBar() {
        return TOOL_BAR;
    }

    public void associate(DrawingPanel drawingPanel) {
        toolBarHandler.associate(drawingPanel);
    }

    public void initialize() {
        createShapeToolBtn();
        this.addSeparator();

        createStateToolBtn();
        this.addSeparator();

        setDefaultBtn();
    }

    private void createShapeToolBtn() {
        Arrays.stream(ShapeToolItem.values()).forEach(shapeToolItem -> {
            JRadioButton shapeToolBtn = new JRadioButton();
            shapeToolBtn.setToolTipText(shapeToolItem.name());
            shapeToolBtn.setActionCommand(shapeToolItem.name());
            shapeToolBtn.addActionListener(toolBarHandler);
            shapeToolBtn.setIcon(new ImageIcon("src/image/" + shapeToolItem.name() + ".png"));
            shapeToolBtn.setSelectedIcon(new ImageIcon("src/image/selected_" + shapeToolItem.name() + ".png"));
            shapeToolBtnGroup.add(shapeToolBtn);
            this.add(shapeToolBtn);
        });
    }

    private void createStateToolBtn() {
        Arrays.stream(StateToolItem.values()).forEach(stateToolItem -> {
            JButton stateToolBtn = new JButton();
            stateToolBtn.setToolTipText(stateToolItem.name());
            stateToolBtn.setActionCommand(stateToolItem.name());
            stateToolBtn.addActionListener(toolBarHandler);
            stateToolBtn.setIcon(new ImageIcon("src/image/" + stateToolItem.name() + ".png"));
            stateToolBtn.setBorderPainted(false);
            stateToolBtn.setFocusPainted(false);
            stateToolBtn.setContentAreaFilled(false);
            this.add(stateToolBtn);
        });
    }

    private void setDefaultBtn() {
        JRadioButton defaultBtn = (JRadioButton) this.getComponent(ShapeToolItem.rectangle.ordinal());
        defaultBtn.doClick();
    }
}
