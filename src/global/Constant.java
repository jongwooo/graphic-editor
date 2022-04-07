package global;

import java.awt.Color;
import java.awt.Cursor;

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

    public static final Color DEFAULT_OUTLINE_COLOR = Color.BLACK;
    public static final Color DEFAULT_FILL_COLOR = Color.WHITE;

    public static final int DEFAULT_OUTLINE_SIZE = 1;
    public static final int DEFAULT_DASH_SIZE = 0;

    public static final Cursor DEFAULT_STYLE_CURSOR = Cursor.getDefaultCursor();
    public static final Cursor CROSSHAIR_STYLE_CURSOR = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
}
