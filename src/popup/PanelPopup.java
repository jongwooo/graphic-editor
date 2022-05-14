package popup;

import global.menu.EditMenuItem;
import java.util.Arrays;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PanelPopup extends JPopupMenu {

    private static final long serialVersionUID = 1L;

    private static class InstanceHolder {

        private static final PanelPopup INSTANCE = new PanelPopup();
    }

    private final PanelPopupHandler panelPopupHandler;

    private PanelPopup() {
        panelPopupHandler = PanelPopupHandler.getInstance();
    }

    public static PanelPopup getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void associate() {
        panelPopupHandler.associate();
    }

    public void initialize() {
        createPanelPopupItems();
    }

    private void createPanelPopupItems() {
        Arrays.stream(EditMenuItem.values()).forEach(editMenuItem -> {
            JMenuItem menuItem = new JMenuItem(editMenuItem.getMenuName());
            menuItem.setActionCommand(editMenuItem.name());
            menuItem.addActionListener(panelPopupHandler);
            this.add(menuItem);
            if (editMenuItem.hasSeparator()) {
                this.addSeparator();
            }
        });
    }
}
