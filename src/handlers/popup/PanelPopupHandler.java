package handlers.popup;

import handlers.menu.EditMenuHandler;

public class PanelPopupHandler extends EditMenuHandler {

  private static class InstanceHolder {

    private static final PanelPopupHandler INSTANCE = new PanelPopupHandler();
  }

  public static PanelPopupHandler getInstance() {
    return InstanceHolder.INSTANCE;
  }
}
