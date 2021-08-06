package in.ac.uecu.interfaces;

import in.ac.uecu.interfaces.*;
import javax.swing.*;
import javax.swing.undo.*;
import java.awt.*;
import java.awt.event.*;

public interface Editor
{
public void actionPerformed(ActionEvent actionEvent);
public FileMenuExecutor getFileMenuActionExecutor();
public EditMenuExecutor getEditMenuActionExecutor();
public JMenu getFileMenu();
public JMenu getEditMenu();
public JMenu getFormatMenu();
public JMenu getColorMenu();
public Container getContentPane();
public JTextArea getTextArea();
public UndoManager getUndoManager();
public void setTitle(String title);
public JFrame getFrame();
public JMenuItem getWordWrapMenuItem();
public void setWordWrapStatus(boolean wordWrapStatus);
public boolean getWordWrapStatus();
}