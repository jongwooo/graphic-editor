package tool;

import global.tool.ColorToolItem;
import global.tool.ShapeToolItem;
import panel.DrawingPanel;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import java.util.Arrays;

public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;

    private ToolBarHandler toolBarHandler;
    private ButtonGroup shapeToolBtnGroup;

    public ToolBar(String toolBarTitle) {
        super(toolBarTitle);
        toolBarHandler = new ToolBarHandler();
        shapeToolBtnGroup = new ButtonGroup();

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

        this.addSeparator();

        Arrays.stream(ColorToolItem.values()).forEach(colorToolItem -> {
            JButton colorToolBtn = new JButton();
            colorToolBtn.setToolTipText(colorToolItem.name());
            colorToolBtn.setActionCommand(colorToolItem.name());
            colorToolBtn.addActionListener(toolBarHandler);
            colorToolBtn.setIcon(new ImageIcon("src/image/" + colorToolItem.name() + ".png"));
            colorToolBtn.setBorderPainted(false);
            colorToolBtn.setFocusPainted(false);
            colorToolBtn.setContentAreaFilled(false);
            this.add(colorToolBtn);
        });
    }

    public void initialize(DrawingPanel drawingPanel) {
        toolBarHandler.initialize(drawingPanel);

        setBorderPainted(true);
        setDefaultBtn();
    }

    private void setDefaultBtn() {
        JRadioButton defaultBtn = (JRadioButton) this.getComponent(ShapeToolItem.cursor.ordinal());
        defaultBtn.doClick();
    }
}
