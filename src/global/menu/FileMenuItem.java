package global.menu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public enum FileMenuItem implements MenuItem {
    newFile("New", KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK),
    openFile("Open File...", KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK, true),
    saveFile("Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK),
    saveFileAs("Save As...", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK),
    print("Print...", KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK, true),
    quitEditor("Quit", KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK);

    private final String menuName;
    private final int keyCode, modifiers;
    private boolean separated;

    FileMenuItem(String menuName, int keyCode, int modifiers) {
        this.menuName = menuName;
        this.keyCode = keyCode;
        this.modifiers = modifiers;
    }

    FileMenuItem(String menuName, int keyCode, int modifiers, boolean separated) {
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
