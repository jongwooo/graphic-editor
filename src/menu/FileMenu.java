package menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    private JMenuItem saveFileAs;
    private JMenuItem quitEditor;

    public FileMenu(String menuTitle) {
        super(menuTitle);

        this.newFile = new JMenuItem("New");
        this.add(this.newFile);

        this.openFile = new JMenuItem("Open File...");
        this.add(this.openFile);

        this.addSeparator();

        this.saveFile = new JMenuItem("Save");
        this.add(this.saveFile);

        this.saveFileAs = new JMenuItem("Save As...");
        this.add(this.saveFileAs);

        this.addSeparator();

        this.quitEditor = new JMenuItem("Quit");
        this.quitEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(this.quitEditor);
    }
}
