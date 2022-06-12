package utils.file;

import components.panel.DrawingPanel;
import frames.MainFrame;
import global.Constant;
import global.dialog.FileDialog;
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

public class FileControl {

  private static class InstanceHolder {

    private static final FileControl INSTANCE = new FileControl();
  }

  private String filePath;
  private final JFileChooser fileChooser;
  private final FileFactory fileFactory;

  private FileControl() {
    filePath = null;
    fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter(Constant.FILE_EXTENSION_DESCRIPTION,
        Constant.FILE_EXTENSION));
    fileChooser.setMultiSelectionEnabled(false);
    fileFactory = FileFactory.getInstance();
  }

  public static FileControl getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private boolean isNewFile() {
    return filePath == null;
  }

  private void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  private boolean checkOtherOptions(int dialogOption) {
    return dialogOption != JOptionPane.CLOSED_OPTION
        && dialogOption != JOptionPane.CANCEL_OPTION;
  }

  private boolean checkSave() {
    DrawingPanel drawingPanel = DrawingPanel.getInstance();

    if (drawingPanel.checkUpdate()) {
      int dialogOption = JOptionPane.showConfirmDialog(drawingPanel,
          FileDialog.SAVE_CONFIRM_DIALOG_MESSAGE, FileDialog.SAVE_CONFIRM_DIALOG_TITLE,
          JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (dialogOption == JOptionPane.YES_OPTION) {
        saveFile();
      }
      return checkOtherOptions(dialogOption);
    }
    return true;
  }

  private boolean checkExtension(File currentFile) {
    return currentFile.getName().contains("." + Constant.FILE_EXTENSION);
  }

  private void showFileExtensionErrorDialog() {
    JOptionPane.showMessageDialog(fileChooser, FileDialog.FILE_DIALOG_ERROR_MESSAGE,
        FileDialog.FILE_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
  }

  public void newFile() {
    MainFrame mainFrame = MainFrame.getInstance();
    DrawingPanel drawingPanel = DrawingPanel.getInstance();

    if (checkSave()) {
      int dialogOption = JOptionPane.showConfirmDialog(drawingPanel,
          FileDialog.NEW_FILE_CONFIRM_DIALOG_MESSAGE,
          FileDialog.NEW_FILE_CONFIRM_DIALOG_TITLE, JOptionPane.YES_NO_CANCEL_OPTION,
          JOptionPane.QUESTION_MESSAGE);

      if (checkOtherOptions(dialogOption)) {
        setFilePath(null);
        mainFrame.setTitle(Constant.MAINFRAME_TITLE);
        drawingPanel.setSpinnerValue(Constant.DEFAULT_OUTLINE_SIZE, Constant.DEFAULT_DASH_SIZE);
        drawingPanel.clearShapes();
        drawingPanel.setUpdate(false);
        drawingPanel.repaint();

        if (dialogOption == JOptionPane.YES_OPTION) {
          saveFileAs();
        }
      }
    }
  }

  public void openFile() {
    DrawingPanel drawingPanel = DrawingPanel.getInstance();

    if (checkSave()) {
      int dialogOption = fileChooser.showOpenDialog(drawingPanel);

      if (dialogOption == JFileChooser.APPROVE_OPTION) {
        File currentFile = fileFactory.getFile(
            fileChooser.getSelectedFile().getAbsolutePath());
        if (checkExtension(currentFile)) {
          readShapeObject(currentFile);
          drawingPanel.setSpinnerValue(Constant.DEFAULT_OUTLINE_SIZE, Constant.DEFAULT_DASH_SIZE);
          drawingPanel.setUpdate(false);
          drawingPanel.repaint();
        } else {
          showFileExtensionErrorDialog();
        }
      }
    }
  }

  public void saveFile() {
    if (isNewFile()) {
      saveFileAs();
    } else {
      writeShapeObject(fileFactory.getFile(filePath));
    }
  }

  public void saveFileAs() {
    DrawingPanel drawingPanel = DrawingPanel.getInstance();

    fileChooser.setSelectedFile(fileFactory.getFile(
        fileChooser.getCurrentDirectory() + Constant.DEFAULT_FILE_NAME));
    int dialogOption = fileChooser.showSaveDialog(drawingPanel);

    if (dialogOption == JFileChooser.APPROVE_OPTION) {
      File currentFile = fileFactory.getFile(fileChooser.getSelectedFile().getAbsolutePath());
      if (checkExtension(currentFile)) {
        writeShapeObject(currentFile);
      } else {
        showFileExtensionErrorDialog();
        saveFileAs();
      }
    }
  }

  public void print() {
    DrawingPanel drawingPanel = DrawingPanel.getInstance();

    PrinterJob printerJob = PrinterJob.getPrinterJob();
    printerJob.setPrintable(drawingPanel);
    boolean isPrintable = printerJob.printDialog();

    try {
      if (isPrintable) {
        printerJob.print();
      }
    } catch (PrinterException exception) {
      JOptionPane.showMessageDialog(drawingPanel, FileDialog.PRINT_DIALOG_ERROR_MESSAGE,
          FileDialog.PRINT_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
    }
  }

  public void quitEditor() {
    DrawingPanel drawingPanel = DrawingPanel.getInstance();

    if (checkSave()) {
      int dialogOption = JOptionPane.showConfirmDialog(drawingPanel,
          FileDialog.QUIT_CONFIRM_DIALOG_MESSAGE, FileDialog.QUIT_CONFIRM_DIALOG_TITLE,
          JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

      if (dialogOption == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
    }
  }

  private void readShapeObject(File currentFile) {
    MainFrame mainFrame = MainFrame.getInstance();
    DrawingPanel drawingPanel = DrawingPanel.getInstance();

    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(
          new BufferedInputStream(new FileInputStream(currentFile)));
      drawingPanel.setShapes(objectInputStream.readObject());
      drawingPanel.setSpinnerValue(Constant.DEFAULT_OUTLINE_SIZE, Constant.DEFAULT_DASH_SIZE);
      mainFrame.setTitle(currentFile.getName());
      setFilePath(currentFile.getAbsolutePath());
      objectInputStream.close();
    } catch (IOException | ClassNotFoundException exception) {
      JOptionPane.showMessageDialog(drawingPanel, FileDialog.OPEN_DIALOG_ERROR_MESSAGE,
          FileDialog.OPEN_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
    }
  }

  private void writeShapeObject(File currentFile) {
    MainFrame mainFrame = MainFrame.getInstance();
    DrawingPanel drawingPanel = DrawingPanel.getInstance();

    try {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(
          new BufferedOutputStream(new FileOutputStream(currentFile)));
      drawingPanel.clearSelectedShapes();
      objectOutputStream.writeObject(drawingPanel.getShapes());
      drawingPanel.setSpinnerValue(Constant.DEFAULT_OUTLINE_SIZE, Constant.DEFAULT_DASH_SIZE);
      drawingPanel.setUpdate(false);
      mainFrame.setTitle(currentFile.getName());
      setFilePath(currentFile.getAbsolutePath());
      objectOutputStream.close();
    } catch (IOException exception) {
      JOptionPane.showMessageDialog(drawingPanel, FileDialog.SAVE_DIALOG_ERROR_MESSAGE,
          FileDialog.SAVE_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
    }
  }
}
