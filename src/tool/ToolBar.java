package tool;

import global.Constant;

import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import java.util.Arrays;

public class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;

    public ToolBar(String toolBarTitle) {
        super(toolBarTitle);
        this.setBorderPainted(true);

        ButtonGroup toolBtnGroup = new ButtonGroup();

        Arrays.stream(Constant.ShapeToolItem.values()).forEach(shapeToolItem -> {
            JRadioButton radioButton = new JRadioButton();
            radioButton.setIcon(new ImageIcon("src/image/" + shapeToolItem.getToolName() + ".png"));
            radioButton.setSelectedIcon(new ImageIcon("src/image/selected_" + shapeToolItem.getToolName() + ".png"));
            toolBtnGroup.add(radioButton);
            if(shapeToolItem.selected()) toolBtnGroup.setSelected(radioButton.getModel(), true);
            this.add(radioButton);
        });
    }
}
