package constants.menu;

import constants.Constant;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public enum FileMenuItem implements MenuItem {
  newFile("New", KeyEvent.VK_N, Constant.CMD_MASK),
  openFile("Open File...", KeyEvent.VK_O, Constant.CMD_MASK, true),
  saveFile("Save", KeyEvent.VK_S, Constant.CMD_MASK),
  saveFileAs("Save As...", KeyEvent.VK_S, Constant.CMD_MASK + InputEvent.SHIFT_DOWN_MASK),
  print("Print...", KeyEvent.VK_P, Constant.CMD_MASK, true),
  quitEditor("Quit", KeyEvent.VK_Q, Constant.CMD_MASK);

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
