package components.tool;

import components.tool.spinner.DashSizeSpinner;
import components.tool.spinner.OutlineSizeSpinner;
import components.tool.spinner.SizeSpinner;
import global.Constant;
import global.tool.DrawingToolItem;
import global.tool.ShapeToolItem;
import handlers.tool.button.DrawingToolHandler;
import handlers.tool.button.ShapeToolHandler;
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

  private final ShapeToolHandler shapeToolHandler;
  private final DrawingToolHandler drawingToolHandler;
  private final ButtonGroup shapeToolBtnGroup;
  private final OutlineSizeSpinner outlineSizeSpinner;
  private final DashSizeSpinner dashSizeSpinner;

  private ToolBar() {
    super(Constant.TOOLBAR_TITLE);
    setBorderPainted(true);
    setFloatable(false);

    shapeToolBtnGroup = new ButtonGroup();
    shapeToolHandler = ShapeToolHandler.getInstance();
    drawingToolHandler = DrawingToolHandler.getInstance();
    outlineSizeSpinner = OutlineSizeSpinner.getInstance();
    dashSizeSpinner = DashSizeSpinner.getInstance();

    createShapeToolButtons();
    createDrawingToolButtons();
    addSizeSpinner(Constant.OUTLINE_SPINNER_TITLE, outlineSizeSpinner);
    addSizeSpinner(Constant.DASH_SPINNER_TITLE, dashSizeSpinner);
    setDefaultButton();
  }

  public static ToolBar getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void initialize() {
    outlineSizeSpinner.initialize();
    dashSizeSpinner.initialize();
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
      shapeToolBtn.addActionListener(shapeToolHandler);
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
      drawingToolBtn.setActionCommand(drawingToolItem.getMethodName());
      drawingToolBtn.addActionListener(drawingToolHandler);
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

  private void addSizeSpinner(String label, SizeSpinner spinner) {
    spinner.setMaximumSize();
    this.add(new JLabel(label));
    this.add(spinner);
    this.addSeparator();
  }
}
