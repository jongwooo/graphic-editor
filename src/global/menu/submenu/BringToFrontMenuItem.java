package global.menu.submenu;

import global.Constant;
import global.menu.MenuItem;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public enum BringToFrontMenuItem implements MenuItem {
  bringToFront("Bring to front", KeyEvent.VK_F,
      Constant.CMD_MASK + InputEvent.SHIFT_DOWN_MASK),
  bringForward("Bring forward", KeyEvent.VK_F,
      Constant.CMD_MASK + InputEvent.SHIFT_DOWN_MASK + InputEvent.ALT_DOWN_MASK);

  private final String menuName;
  private final int keyCode, modifiers;

  BringToFrontMenuItem(String menuName, int keyCode, int modifiers) {
    this.menuName = menuName;
    this.keyCode = keyCode;
    this.modifiers = modifiers;
  }

  @Override
  public String getMenuName() {
    return menuName;
  }

  @Override
  public KeyStroke getKeyStroke() {
    return KeyStroke.getKeyStroke(keyCode, modifiers);
  }
}
