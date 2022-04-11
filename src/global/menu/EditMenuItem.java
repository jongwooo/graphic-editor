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
    private boolean separated;

    private EditMenuItem(String menuName) {
        this.menuName = menuName;
    }

    private EditMenuItem(String menuName, boolean separated) {
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