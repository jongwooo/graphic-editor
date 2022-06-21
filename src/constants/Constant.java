package constants;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.SpinnerNumberModel;
import utils.stroke.CustomStroke;

public class Constant {

  public static final int MAINFRAME_WIDTH = 800;
  public static final int MAINFRAME_HEIGHT = 600;

  public static final String MAINFRAME_TITLE = "Graphic Editor";
  public static final String TOOLBAR_TITLE = "Tools";
  public static final String COLOR_CHOOSER_TITLE = "Select a color";

  public static final String FILE_MENU_TITLE = "File";
  public static final String EDIT_MENU_TITLE = "Edit";
  public static final String BRING_TO_FRONT_MENU_TITLE = "Bring to front";
  public static final String SEND_TO_BACK_MENU_TITLE = "Send to Back";

  public static final String OUTLINE_SPINNER_TITLE = "Outline";
  public static final String DASH_SPINNER_TITLE = "Dash";

  public static final String IMAGE_ICON_PATH = "static/image/button/";
  public static final String IMAGE_ICON_EXTENSION = ".png";

  public static final String FILE_EXTENSION = "graphic";
  public static final String FILE_EXTENSION_DESCRIPTION = "Graphic (." + FILE_EXTENSION + ")";
  public static final String DEFAULT_FILE_NAME = "//Untitled." + FILE_EXTENSION;

  public static final int ANCHOR_WIDTH = 10;
  public static final int ANCHOR_HEIGHT = 10;
  public static final int ROTATE_BAR_HEIGHT = 40;

  public static final Color BACKGROUND_COLOR = Color.WHITE;
  public static final Color DEFAULT_OUTLINE_COLOR = Color.BLACK;
  public static final Color DEFAULT_FILL_COLOR = new Color(0, 0, 0, 0);

  public static final SpinnerNumberModel OUTLINE_SIZE_MODEL = new SpinnerNumberModel(1, 1, 10, 1);
  public static final SpinnerNumberModel DASH_SIZE_MODEL = new SpinnerNumberModel(0, 0, 10, 1);
  public static final int DEFAULT_OUTLINE_SIZE = 1;
  public static final int DEFAULT_DASH_SIZE = 0;
  public static final CustomStroke DEFAULT_STROKE = new CustomStroke(DEFAULT_OUTLINE_SIZE);

  public static final Toolkit DEFAULT_TOOLKIT = Toolkit.getDefaultToolkit();
  public static final boolean IS_MAC_OS =
      System.getProperty("os.name").toLowerCase().contains("mac");

  public static final int CMD_KEY =
      IS_MAC_OS ? 157 : KeyEvent.VK_CONTROL;
  public static final int CMD_MASK =
      IS_MAC_OS ? DEFAULT_TOOLKIT.getMenuShortcutKeyMaskEx() : KeyEvent.CTRL_DOWN_MASK;

  public static final Cursor DEFAULT_STYLE_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
  public static final Cursor HAND_STYLE_CURSOR = new Cursor(Cursor.HAND_CURSOR);
  public static final Cursor CROSSHAIR_STYLE_CURSOR = new Cursor(Cursor.CROSSHAIR_CURSOR);
  public static final Cursor NW_CURSOR = new Cursor(Cursor.NW_RESIZE_CURSOR);
  public static final Cursor WW_CURSOR = new Cursor(Cursor.W_RESIZE_CURSOR);
  public static final Cursor SW_CURSOR = new Cursor(Cursor.SW_RESIZE_CURSOR);
  public static final Cursor SS_CURSOR = new Cursor(Cursor.S_RESIZE_CURSOR);
  public static final Cursor SE_CURSOR = new Cursor(Cursor.SE_RESIZE_CURSOR);
  public static final Cursor EE_CURSOR = new Cursor(Cursor.E_RESIZE_CURSOR);
  public static final Cursor NE_CURSOR = new Cursor(Cursor.NE_RESIZE_CURSOR);
  public static final Cursor NN_CURSOR = new Cursor(Cursor.N_RESIZE_CURSOR);
  public static final Cursor ROTATE_CURSOR = DEFAULT_TOOLKIT.createCustomCursor(
      DEFAULT_TOOLKIT.getImage("static/image/cursor/rotate.png"), new Point(8, 8), "Rotate");
}
