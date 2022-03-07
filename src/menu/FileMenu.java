package menu;

import global.Constant;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenu extends JMenu {
    public FileMenu() {
        super(Constant.FILE_MENU_TITLE);

        JMenuItem newFile = new JMenuItem("New");
        this.add(newFile);

        JMenuItem openFile = new JMenuItem("Open File...");
        this.add(openFile);

        this.addSeparator();

        JMenuItem saveFile = new JMenuItem("Save");
        this.add(saveFile);

        JMenuItem saveFileAs = new JMenuItem("Save As...");
        this.add(saveFileAs);

        this.addSeparator();

        JMenuItem quitEditor = new JMenuItem("Quit");
        quitEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(quitEditor);
    }
}
