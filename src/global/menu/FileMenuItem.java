package global.menu;

public enum FileMenuItem implements MenuItem {
    newFile("New"), openFile("Open File...", true), saveFile("Save"), saveFileAs(
            "Save As..."), print("Print...", true), quitEditor("Quit");

    private final String menuName;
    private boolean separated;

    FileMenuItem(String menuName) {
        this.menuName = menuName;
    }

    FileMenuItem(String menuName, boolean separated) {
        this(menuName);
        this.separated = separated;
    }

    public String getMenuName() {
        return menuName;
    }

    public boolean hasSeparator() {
        return separated;
    }
}