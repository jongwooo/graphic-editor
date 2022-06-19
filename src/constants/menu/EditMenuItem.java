package constants.menu;

import constants.Constant;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public enum EditMenuItem implements MenuItem {
  undo("Undo", KeyEvent.VK_Z, Constant.CMD_MASK),
  redo("Redo", KeyEvent.VK_Z, Constant.CMD_MASK + InputEvent.SHIFT_DOWN_MASK, true),
  cut("Cut", KeyEvent.VK_X, Constant.CMD_MASK),
  copy("Copy", KeyEvent.VK_C, Constant.CMD_MASK),
  paste("Paste", KeyEvent.VK_V, Constant.CMD_MASK),
  delete("Delete", KeyEvent.VK_D, Constant.CMD_MASK, true),
  group("Group", KeyEvent.VK_G, Constant.CMD_MASK),
  ungroup("Ungroup", KeyEvent.VK_G, Constant.CMD_MASK + InputEvent.SHIFT_DOWN_MASK, true);

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

  @Override
  public String getMenuName() {
    return menuName;
  }

  @Override
  public KeyStroke getKeyStroke() {
    return KeyStroke.getKeyStroke(keyCode, modifiers);
  }

  public boolean hasSeparator() {
    return separated;
  }
}
