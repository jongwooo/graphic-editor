package constants.menu.submenu;

import constants.Constant;
import constants.menu.MenuItem;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public enum SendToBackMenuItem implements MenuItem {
  sendToBack("Send to Back", KeyEvent.VK_B,
      Constant.CMD_MASK + InputEvent.SHIFT_DOWN_MASK),
  sendBackward("Send Backward", KeyEvent.VK_B,
      Constant.CMD_MASK + InputEvent.SHIFT_DOWN_MASK + InputEvent.ALT_DOWN_MASK);

  private final String menuName;
  private final int keyCode, modifiers;

  SendToBackMenuItem(String menuName, int keyCode, int modifiers) {
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
