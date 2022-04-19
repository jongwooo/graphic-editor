package global.menu;

public enum EditMenuItem implements MenuItem {
    undo("Undo"), redo("Redo", true), cut("Cut"), copy("Copy"), paste("Paste", true), group(
            "Group"), ungroup("Ungroup");

    private final String menuName;
    private boolean separated;

    EditMenuItem(String menuName) {
        this.menuName = menuName;
    }

    EditMenuItem(String menuName, boolean separated) {
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