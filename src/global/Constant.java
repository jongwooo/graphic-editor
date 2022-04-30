package global;

import draw.stroke.CustomStroke;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SpinnerNumberModel;

public class Constant {

    public static final int MAINFRAME_WIDTH = 800;
    public static final int MAINFRAME_HEIGHT = 600;

    public static final String MAINFRAME_TITLE = "Graphic Editor";
    public static final String TOOLBAR_TITLE = "Tools";
    public static final String COLOR_CHOOSER_TITLE = "Select a color";

    public static final String FILE_MENU_TITLE = "File";
    public static final String EDIT_MENU_TITLE = "Edit";

    public static final String OUTLINE_SPINNER_TITLE = "Outline";
    public static final String DASH_SPINNER_TITLE = "Dash";

    public static final String IMAGE_ICON_PATH = "src/image/";
    public static final String IMAGE_ICON_EXTENSION = ".png";

    public static final String FILE_EXTENSION = "graphic";
    public static final String FILE_EXTENSION_DESCRIPTION = "Graphic (." + FILE_EXTENSION + ")";
    public static final String DEFAULT_FILE_NAME = "//Untitled." + FILE_EXTENSION;

    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final Color DEFAULT_OUTLINE_COLOR = Color.BLACK;
    public static final Color DEFAULT_FILL_COLOR = new Color(0, 0, 0, 0);

    public static final SpinnerNumberModel OUTLINE_SIZE_MODEL = new SpinnerNumberModel(1, 1, 10, 1);
    public static final SpinnerNumberModel DASH_SIZE_MODEL = new SpinnerNumberModel(0, 0, 10, 1);

    public static final CustomStroke DEFAULT_STROKE = new CustomStroke();
    public static final int DEFAULT_OUTLINE_SIZE = 1;
    public static final int DEFAULT_DASH_SIZE = 0;

    public static final Cursor DEFAULT_STYLE_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
    public static final Cursor HAND_STYLE_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor CROSSHAIR_STYLE_CURSOR = new Cursor(Cursor.CROSSHAIR_CURSOR);
}
