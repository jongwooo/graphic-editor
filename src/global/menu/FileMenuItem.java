package global.menu;

public enum FileMenuItem implements MenuItem {
    newFile("New"),
    openFile("Open File..."),
    closeWindow("Close Window", true),
    saveFile("Save"),
    saveFileAs("Save As..."),
    print("Print...", true),
    quitEditor("Quit");

    private String menuName;
    private boolean separated;

    private FileMenuItem(String menuName) {
        this.menuName = menuName;
    }

    private FileMenuItem(String menuName, boolean separated) {
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