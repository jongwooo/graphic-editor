package global;

public class Constant {
    public static final int MAINFRAME_WIDTH = 800;
    public static final int MAINFRAME_HEIGHT = 600;

    public static final String MAINFRAME_TITLE = "Graphic Editor";
    public static final String TOOLBAR_TITLE = "Tools";

    public static final String FILE_MENU_TITLE = "File";
    public static final String EDIT_MENU_TITLE = "Edit";

    public interface MenuItem {
        public String getMenuName();
        public boolean separated();
    }

    public enum FileMenuItem implements MenuItem {
        newFile("New"),
        openFile("Open File..."),
        closeEditor("Close Editor", true),
        saveFile("Save"),
        saveFileAs("Save As..."),
        printFile("Print...", true),
        quitEditor("Quit");

        private String menuName;
        private boolean isSeparated;

        private FileMenuItem(String menuName) {
            this.menuName = menuName;
        }

        private FileMenuItem(String menuName, boolean isSeparated) {
            this(menuName);
            this.isSeparated = isSeparated;
        }

        public String getMenuName() {
            return this.menuName;
        }

        public boolean separated() {
            return this.isSeparated;
        }
    }

    public enum EditMenuItem implements MenuItem {
        undo("Undo"),
        redo("Redo", true),
        cutItem("Cut"),
        copyItem("Copy"),
        pasteItem("Paste", true),
        groupItem("Group"),
        ungroupItem("Ungroup");

        private String menuName;
        private boolean isSeparated;

        private EditMenuItem(String menuName) {
            this.menuName = menuName;
        }

        private EditMenuItem(String menuName, boolean isSeparated) {
            this(menuName);
            this.isSeparated = isSeparated;
        }

        public String getMenuName() {
            return this.menuName;
        }

        public boolean separated() {
            return this.isSeparated;
        }
    }

    public enum ShapeToolItem {
        cursorBtn("cursor", true),
        drawRectangleBtn("rectangle"),
        drawOvalBtn("oval"),
        drawLineBtn("line"),
        drawPolygonBtn("polygon");

        private String toolName;
        private boolean isSelected;

        private ShapeToolItem(String toolName) {
            this.toolName = toolName;
        }

        private ShapeToolItem(String toolName, boolean isSelected) {
            this(toolName);
            this.isSelected = isSelected;
        }

        public String getToolName() {
            return this.toolName;
        }

        public boolean selected() {
            return this.isSelected;
        }
    }
}
