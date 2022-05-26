package tool;

import global.Constant;
import global.tool.DrawingToolItem;
import global.tool.ShapeToolItem;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

  private static final long serialVersionUID = 1L;

  private static class InstanceHolder {

    private static final ToolBar INSTANCE = new ToolBar();
  }

  private final ButtonHandler buttonHandler;
  private final ButtonGroup shapeToolBtnGroup;
  private final SpinnerHandler spinnerHandler;
  private final OutlineSizeSpinner outlineSizeSpinner;
  private final DashSizeSpinner dashSizeSpinner;

  private ToolBar() {
    super(Constant.TOOLBAR_TITLE);
    setBorderPainted(true);
    setFloatable(false);

    shapeToolBtnGroup = new ButtonGroup();
    buttonHandler = ButtonHandler.getInstance();
    spinnerHandler = SpinnerHandler.getInstance();
    outlineSizeSpinner = OutlineSizeSpinner.getInstance();
    dashSizeSpinner = DashSizeSpinner.getInstance();
  }

  public static ToolBar getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void associate() {
    buttonHandler.associate();
    spinnerHandler.associate();
    outlineSizeSpinner.associate();
    dashSizeSpinner.associate();
  }

  public void initialize() {
    createShapeToolButtons();
    createDrawingToolButtons();
    setDefaultButton();

    outlineSizeSpinner.initialize();
    this.add(new JLabel(Constant.OUTLINE_SPINNER_TITLE));
    this.add(outlineSizeSpinner);
    this.addSeparator();

    dashSizeSpinner.initialize();
    this.add(new JLabel(Constant.DASH_SPINNER_TITLE));
    this.add(dashSizeSpinner);
    this.addSeparator();
  }

  private ImageIcon createImageIcon(String imageName, boolean selected) {
    return new ImageIcon(
        Constant.IMAGE_ICON_PATH.concat(selected ? "selected_" : "").concat(imageName)
            .concat(Constant.IMAGE_ICON_EXTENSION));
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
    JRadioButton defaultBtn = (JRadioButton) this.getComponent(
        ShapeToolItem.rectangle.ordinal());
    defaultBtn.doClick();
  }
}
