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
    private boolean isSeparated;

    private FileMenuItem(String menuName) {
        this.menuName = menuName;
    }

    private FileMenuItem(String menuName, boolean isSeparated) {
        this(menuName);
        this.isSeparated = isSeparated;
    }

    public String getMenuName() {
        return menuName;
    }

    public boolean separated() {
        return isSeparated;
    }
}