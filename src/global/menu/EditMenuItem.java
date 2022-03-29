package global.menu;

public enum EditMenuItem implements MenuItem {
    undo("Undo"),
    redo("Redo", true),
    cutItem("Cut"),
    copyItem("Copy"),
    pasteItem("Paste", true),
    groupItems("Group"),
    ungroupItems("Ungroup");

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
        return menuName;
    }

    public boolean separated() {
        return isSeparated;
    }
}