package popup;

import global.menu.EditMenuItem;
import java.util.Arrays;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import panel.DrawingPanel;

public class PanelPopup extends JPopupMenu {

    private static final long serialVersionUID = 1L;

    private static final PanelPopup PANEL_POPUP = new PanelPopup();

    private final PanelPopupHandler panelPopupHandler;

    private PanelPopup() {
        panelPopupHandler = PanelPopupHandler.getInstance();
    }

    public static PanelPopup getInstance() {
        return PANEL_POPUP;
    }

    public void associate(DrawingPanel drawingPanel) {
        panelPopupHandler.associate(drawingPanel);
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
