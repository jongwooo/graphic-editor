package global.menu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public enum EditMenuItem implements MenuItem {
    undo("Undo", KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK),
    redo("Redo", KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK, true),
    cut("Cut", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK),
    copy("Copy", KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK),
    paste("Paste", KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK, true),
    group("Group", KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK),
    ungroup("Ungroup", KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK);

    private final String menuName;
    private final int keyCode, modifiers;
    private boolean separated;

    EditMenuItem(String menuName, int keyCode, int modifiers) {
        this.menuName = menuName;
        this.keyCode = keyCode;
        this.modifiers = modifiers;
    }

    EditMenuItem(String menuName, int keyCode, int modifiers, boolean separated) {
        this(menuName, keyCode, modifiers);
        this.separated = separated;
    }

    public String getMenuName() {
        return menuName;
    }

    public KeyStroke getKeyStroke() {
        return KeyStroke.getKeyStroke(keyCode, modifiers);
    }

    public boolean hasSeparator() {
        return separated;
    }
}
