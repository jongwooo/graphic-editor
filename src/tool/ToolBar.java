package tool;

import global.Constant.ShapeToolItem;
import panel.DrawingPanel;

import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;

    private DrawingPanel drawingPanel;

    public ToolBar(String toolBarTitle) {
        super(toolBarTitle);

        ActionHandler actionHandler = new ActionHandler();
        ButtonGroup toolBtnGroup = new ButtonGroup();

        Arrays.stream(ShapeToolItem.values()).forEach(shapeToolItem -> {
            JRadioButton radioButton = new JRadioButton();
            radioButton.setActionCommand(shapeToolItem.name());
            radioButton.addActionListener(actionHandler);
            radioButton.setIcon(new ImageIcon("src/image/" + shapeToolItem.name() + ".png"));
            radioButton.setSelectedIcon(new ImageIcon("src/image/selected_" + shapeToolItem.name() + ".png"));
            toolBtnGroup.add(radioButton);
            this.add(radioButton);
        });
    }

    public void initialize(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;

        setBorderPainted(true);
        setDefaultBtn();
    }

    private void setDefaultBtn() {
        JRadioButton defaultBtn = (JRadioButton) this.getComponent(ShapeToolItem.cursor.ordinal());
        defaultBtn.doClick();
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setShapeTool(ShapeToolItem.valueOf(e.getActionCommand()));
        }
    }
}
