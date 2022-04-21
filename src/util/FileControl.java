package util;

import frame.MainFrame;
import global.Constant;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import panel.DrawingPanel;

public class FileControl {

    private static final FileControl FILE_CONTROL = new FileControl();

    private MainFrame mainFrame;
    private DrawingPanel drawingPanel;
    private final JFileChooser fileChooser;
    private String filePath;

    private FileControl() {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(Constant.FILE_EXTENSION_DESCRIPTION,
                Constant.FILE_EXTENSION));
        fileChooser.setMultiSelectionEnabled(false);
        filePath = null;
    }

    public static FileControl createFileControl() {
        return FILE_CONTROL;
    }

    public void associate(MainFrame mainFrame, DrawingPanel drawingPanel) {
        this.mainFrame = mainFrame;
        this.drawingPanel = drawingPanel;
    }

    private boolean isNewFile() {
        return filePath == null;
    }

    private void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private boolean checkSave() {
        if (drawingPanel.checkUpdate()) {
            int dialogOption = JOptionPane.showConfirmDialog(drawingPanel,
                    Constant.SAVE_CONFIRM_DIALOG_MESSAGE, Constant.SAVE_CONFIRM_DIALOG_TITLE,
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialogOption == JOptionPane.YES_OPTION) {
                saveFile();
            }
            return dialogOption != JOptionPane.CLOSED_OPTION;
        }
        return true;
    }

    public void newFile() {
        if (checkSave()) {
            setFilePath(null);
            mainFrame.setDefaultTitle();
            drawingPanel.clearShapes();
            drawingPanel.setUpdate(false);
            drawingPanel.repaint();
        }
    }

    public void openFile() {
        if (checkSave()) {
            int dialogOption = fileChooser.showOpenDialog(drawingPanel);

            if (dialogOption == JFileChooser.APPROVE_OPTION) {
                File currentFile = fileChooser.getSelectedFile();
                readShapeObject(currentFile);
                drawingPanel.setUpdate(false);
                drawingPanel.repaint();
            }
        }
    }

    public void saveFile() {
        if (isNewFile()) {
            saveFileAs();
        } else {
            writeShapeObject(new File(filePath));
        }
    }

    public void saveFileAs() {
        int dialogOption = fileChooser.showSaveDialog(drawingPanel);

        if (dialogOption == JFileChooser.APPROVE_OPTION) {
            File currentFile = fileChooser.getSelectedFile();
            if (currentFile.getName().contains("." + Constant.FILE_EXTENSION)) {
                writeShapeObject(currentFile);
            } else {
                JOptionPane.showMessageDialog(fileChooser, Constant.FILE_DIALOG_ERROR_MESSAGE,
                        Constant.FILE_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
                saveFileAs();
            }
        }
    }

    public void print() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(drawingPanel);
        boolean isPrintable = printerJob.printDialog();

        try {
            if (isPrintable) {
                printerJob.print();
            }
        } catch (PrinterException exception) {
            JOptionPane.showMessageDialog(drawingPanel, Constant.PRINT_DIALOG_ERROR_MESSAGE,
                    Constant.PRINT_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void quitEditor() {
        if (checkSave()) {
            int dialogOption = JOptionPane.showConfirmDialog(drawingPanel,
                    Constant.QUIT_CONFIRM_DIALOG_MESSAGE, Constant.QUIT_CONFIRM_DIALOG_TITLE,
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (dialogOption == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    private void readShapeObject(File currentFile) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream(currentFile)));
            drawingPanel.setShapes(objectInputStream.readObject());
            mainFrame.setTitle(currentFile.getName());
            setFilePath(currentFile.getAbsolutePath());
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException exception) {
            JOptionPane.showMessageDialog(drawingPanel, Constant.OPEN_DIALOG_ERROR_MESSAGE,
                    Constant.OPEN_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void writeShapeObject(File currentFile) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(currentFile)));
            objectOutputStream.writeObject(drawingPanel.getShapes());
            drawingPanel.setUpdate(false);
            mainFrame.setTitle(currentFile.getName());
            setFilePath(currentFile.getAbsolutePath());
            objectOutputStream.close();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(drawingPanel, Constant.SAVE_DIALOG_ERROR_MESSAGE,
                    Constant.SAVE_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }
}
