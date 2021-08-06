package in.ac.uecu.editor;

import in.ac.uecu.utility.*;
import in.ac.uecu.executor.*;
import in.ac.uecu.handler.*;
import in.ac.uecu.interfaces.*;

import javax.swing.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class TextEditor extends JFrame implements Editor,ActionListener
{
private JMenuBar menuBar;
private JMenu fileMenu,editMenu,formatMenu,colorMenu; // Menus
private JMenuItem newMenuItem,openMenuItem,saveMenuItem,saveAsMenuItem,exitMenuItem; // File Menu Items
private JMenuItem undoMenuItem,redoMenuItem; // Edit Menu Items
private boolean wordWrapStatus;
private JMenuItem wordWrapMenuItem; // Format Menu Items
private JMenu fontMenu,fontSizeMenu; // Format Menu Items
private JMenuItem arialFontMenuItem,comicSansMonoSpaceFontMenuItem,timesNewRomanFontMenuItem,verdanaFontMenuItem,consolasFontMenuItem,lucidaConsoleFontMenuItem; // Font Menu Items
private JMenuItem eightFontSizeMenuItem,twelveFontSizeMenuItem,sixteenFontSizeMenuItem,twentyFontSizeMenuItem,twentyFourFontSizeMenuItem,twentyEigthFontSizeMenuItem,thirtyFontSizeMenuItem,thirtyTwoFontSizeMenuItem; // Font Menu Items
private JMenuItem whiteColorMenuItem,blackColorMenuItem,blueColorMenuItem; // Color Menu Items
private JTextArea textArea;
private JScrollPane scrollPane;
private UndoManager undoManager;

// Action Executors
private FileMenuExecutor fileMenuExecutor;
private EditMenuExecutor editMenuExecutor;
private FormatMenuExecutor formatMenuExecutor;
private ColorMenuExecutor colorMenuExecutor;

// Key Handler
private KeyHandler keyHandler;

public TextEditor()
{
this.keyHandler=new KeyHandler(this);
this.setFrameAppearance();
this.initializeAllComponents();
}
private void setFrameAppearance()
{
this.setTitle("Text Editor");
this.setSize(1500,900);
Image image = new ImageIcon(this.getClass().getResource("/resources/logo.png"),"Logo").getImage();
this.setIconImage(image);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setVisible(true);
}
private void initializeAllComponents()
{
initializeExecutors();
initializeTextArea();
initializeMenuBar();
}
private void initializeExecutors()
{
this.fileMenuExecutor=new FileMenuActionExecutor(this);
this.editMenuExecutor=new EditMenuActionExecutor(this);
this.formatMenuExecutor=new FormatMenuActionExecutor(this);
this.colorMenuExecutor=new ColorMenuActionExecutor(this);
}
private void initializeMenuBar()
{
this.menuBar=new JMenuBar();
this.fileMenu=new JMenu("File");
this.fileMenu.setMnemonic(KeyEvent.VK_F);
initializeFileMenu();
this.editMenu=new JMenu("Edit");
this.editMenu.setMnemonic(KeyEvent.VK_E);
initializeEditMenu();
this.formatMenu=new JMenu("Format");
this.formatMenu.setMnemonic(KeyEvent.VK_O);
initializeFormatMenu();
this.colorMenu=new JMenu("Color");
this.colorMenu.setMnemonic(KeyEvent.VK_C);
initializeColorMenu();
this.menuBar.add(this.fileMenu);
this.menuBar.add(this.editMenu);
this.menuBar.add(this.formatMenu);
this.menuBar.add(this.colorMenu);
this.setJMenuBar(this.menuBar);
}
private void initializeFileMenu()
{
this.newMenuItem=new JMenuItem("New");
this.newMenuItem.addActionListener(this);
this.newMenuItem.setActionCommand(String.valueOf(Command.NEW_FILE));
this.openMenuItem=new JMenuItem("Open");
this.openMenuItem.addActionListener(this);
this.openMenuItem.setActionCommand(String.valueOf(Command.OPEN_FILE));
this.saveMenuItem=new JMenuItem("Save");
this.saveMenuItem.addActionListener(this);
this.saveMenuItem.setActionCommand(String.valueOf(Command.SAVE_FILE));
this.saveAsMenuItem=new JMenuItem("Save As");
this.saveAsMenuItem.addActionListener(this);
this.saveAsMenuItem.setActionCommand(String.valueOf(Command.SAVE_FILE_AS));
this.exitMenuItem=new JMenuItem("Exit");
this.exitMenuItem.addActionListener(this);
this.exitMenuItem.setActionCommand(String.valueOf(Command.EXIT));
this.fileMenu.add(this.newMenuItem);
this.fileMenu.add(this.openMenuItem);
this.fileMenu.add(this.saveMenuItem);
this.fileMenu.add(this.saveAsMenuItem);
this.fileMenu.add(this.exitMenuItem);
}
private void initializeEditMenu()
{
this.undoMenuItem=new JMenuItem("Undo");
this.undoMenuItem.addActionListener(this);
this.undoMenuItem.setActionCommand(String.valueOf(Command.UNDO));
this.redoMenuItem=new JMenuItem("Redo");
this.redoMenuItem.addActionListener(this);
this.redoMenuItem.setActionCommand(String.valueOf(Command.REDO));
this.editMenu.add(this.undoMenuItem);
this.editMenu.add(this.redoMenuItem);
}

private void initializeFormatMenu()
{
this.wordWrapMenuItem=new JMenuItem("Word Wrap : Off");
this.wordWrapMenuItem.addActionListener(this);
this.wordWrapMenuItem.setActionCommand(String.valueOf(Command.WORD_WRAP));
this.formatMenu.add(this.wordWrapMenuItem);
this.fontMenu=new JMenu("Font");
this.arialFontMenuItem=new JMenuItem("Arial");
this.arialFontMenuItem.addActionListener(this);
this.arialFontMenuItem.setActionCommand(String.valueOf(Command.FONT_ARIAL));
this.comicSansMonoSpaceFontMenuItem=new JMenuItem("Comic Sans MS");
this.comicSansMonoSpaceFontMenuItem.addActionListener(this);
this.comicSansMonoSpaceFontMenuItem.setActionCommand(String.valueOf(Command.FONT_COMIC_SANS_MONOSPACE));
this.timesNewRomanFontMenuItem=new JMenuItem("Times New Roman");
this.timesNewRomanFontMenuItem.addActionListener(this);
this.timesNewRomanFontMenuItem.setActionCommand(String.valueOf(Command.FONT_TIMES_NEW_ROMAN));
this.verdanaFontMenuItem=new JMenuItem("Verdana");
this.verdanaFontMenuItem.addActionListener(this);
this.verdanaFontMenuItem.setActionCommand(String.valueOf(Command.FONT_VERDANA));
this.consolasFontMenuItem=new JMenuItem("Consolas");
this.consolasFontMenuItem.addActionListener(this);
this.consolasFontMenuItem.setActionCommand(String.valueOf(Command.FONT_CONSOLAS));
this.lucidaConsoleFontMenuItem=new JMenuItem("Lucida Console");
this.lucidaConsoleFontMenuItem.addActionListener(this);
this.lucidaConsoleFontMenuItem.setActionCommand(String.valueOf(Command.FONT_LUCIDA_CONSOLE));
this.fontMenu.add(this.timesNewRomanFontMenuItem);
this.fontMenu.add(this.comicSansMonoSpaceFontMenuItem);
this.fontMenu.add(this.arialFontMenuItem);
this.fontMenu.add(this.verdanaFontMenuItem);
this.fontMenu.add(this.consolasFontMenuItem);
this.fontMenu.add(this.lucidaConsoleFontMenuItem);
this.formatMenu.add(this.fontMenu);
this.fontSizeMenu=new JMenu("Font Size");
this.eightFontSizeMenuItem=new JMenuItem("8");
this.eightFontSizeMenuItem.addActionListener(this);
this.eightFontSizeMenuItem.setActionCommand(String.valueOf(Command.FONT_SIZE_8));
this.twelveFontSizeMenuItem=new JMenuItem("12");
this.twelveFontSizeMenuItem.addActionListener(this);
this.twelveFontSizeMenuItem.setActionCommand(String.valueOf(Command.FONT_SIZE_12));
this.sixteenFontSizeMenuItem=new JMenuItem("16");
this.sixteenFontSizeMenuItem.addActionListener(this);
this.sixteenFontSizeMenuItem.setActionCommand(String.valueOf(Command.FONT_SIZE_16));
this.twentyFontSizeMenuItem=new JMenuItem("20");
this.twentyFontSizeMenuItem.addActionListener(this);
this.twentyFontSizeMenuItem.setActionCommand(String.valueOf(Command.FONT_SIZE_20));
this.twentyFourFontSizeMenuItem=new JMenuItem("24");
this.twentyFourFontSizeMenuItem.addActionListener(this);
this.twentyFourFontSizeMenuItem.setActionCommand(String.valueOf(Command.FONT_SIZE_24));
this.twentyEigthFontSizeMenuItem=new JMenuItem("28");
this.twentyEigthFontSizeMenuItem.addActionListener(this);
this.twentyEigthFontSizeMenuItem.setActionCommand(String.valueOf(Command.FONT_SIZE_28));
this.thirtyFontSizeMenuItem=new JMenuItem("30");
this.thirtyFontSizeMenuItem.addActionListener(this);
this.thirtyFontSizeMenuItem.setActionCommand(String.valueOf(Command.FONT_SIZE_30));
this.thirtyTwoFontSizeMenuItem=new JMenuItem("32");
this.thirtyTwoFontSizeMenuItem.addActionListener(this);
this.thirtyTwoFontSizeMenuItem.setActionCommand(String.valueOf(Command.FONT_SIZE_32));
this.fontSizeMenu.add(this.thirtyTwoFontSizeMenuItem);
this.fontSizeMenu.add(this.thirtyFontSizeMenuItem);
this.fontSizeMenu.add(this.twentyEigthFontSizeMenuItem);
this.fontSizeMenu.add(this.twentyFourFontSizeMenuItem);
this.fontSizeMenu.add(this.twentyFontSizeMenuItem);
this.fontSizeMenu.add(this.sixteenFontSizeMenuItem);
this.fontSizeMenu.add(this.twelveFontSizeMenuItem);
this.fontSizeMenu.add(this.eightFontSizeMenuItem);
this.formatMenu.add(this.fontSizeMenu);
}
private void initializeColorMenu()
{
this.colorMenuExecutor.changeColor("White");
this.whiteColorMenuItem=new JMenuItem("White");
this.whiteColorMenuItem.addActionListener(this);
this.whiteColorMenuItem.setActionCommand(String.valueOf(Command.COLOR_WHITE));
this.blackColorMenuItem=new JMenuItem("Black");
this.blackColorMenuItem.addActionListener(this);
this.blackColorMenuItem.setActionCommand(String.valueOf(Command.COLOR_BLACK));
this.blueColorMenuItem=new JMenuItem("Blue");
this.blueColorMenuItem.addActionListener(this);
this.blueColorMenuItem.setActionCommand(String.valueOf(Command.COLOR_BLUE));
this.colorMenu.add(this.whiteColorMenuItem);
this.colorMenu.add(this.blackColorMenuItem);
this.colorMenu.add(this.blueColorMenuItem);
}
private void initializeTextArea()
{
this.undoManager=new UndoManager();
this.textArea=new JTextArea();
this.textArea.setFont(formatMenuExecutor.getSelectedFont());
this.textArea.addKeyListener(this.keyHandler);
this.textArea.getDocument().addUndoableEditListener((UndoableEditEvent undoableEditEvent)->{ undoManager.addEdit(undoableEditEvent.getEdit());});
this.scrollPane=new JScrollPane(this.textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
this.add(this.scrollPane);
}
public void actionPerformed(ActionEvent actionEvent)
{
Command command=Command.valueOf(actionEvent.getActionCommand());
switch(command)
{
case NEW_FILE: fileMenuExecutor.createNewFile(); break;
case OPEN_FILE: fileMenuExecutor.openFile(); break;
case SAVE_FILE: fileMenuExecutor.saveFile(); break;
case SAVE_FILE_AS: fileMenuExecutor.saveFileAs(); break;
case EXIT: fileMenuExecutor.exit(); break;
case UNDO: editMenuExecutor.undo(); break;
case REDO: editMenuExecutor.redo(); break;
case WORD_WRAP: formatMenuExecutor.wordWrap(); break;
case FONT_ARIAL: formatMenuExecutor.setSelectedFont(new Font("Arial", Font.PLAIN,formatMenuExecutor.getSelectedFontSize())); break;
case FONT_COMIC_SANS_MONOSPACE: formatMenuExecutor.setSelectedFont(new Font("Comic Sans MS", Font.PLAIN,formatMenuExecutor.getSelectedFontSize())); break;
case FONT_TIMES_NEW_ROMAN: formatMenuExecutor.setSelectedFont(new Font("Times New Roman", Font.PLAIN,formatMenuExecutor.getSelectedFontSize())); break;
case FONT_VERDANA: formatMenuExecutor.setSelectedFont(new Font("Verdana", Font.PLAIN,formatMenuExecutor.getSelectedFontSize())); break;
case FONT_CONSOLAS: formatMenuExecutor.setSelectedFont(new Font("Consolas", Font.PLAIN,formatMenuExecutor.getSelectedFontSize())); break;
case FONT_LUCIDA_CONSOLE: formatMenuExecutor.setSelectedFont(new Font("Lucida Console", Font.PLAIN,formatMenuExecutor.getSelectedFontSize())); break;
case FONT_SIZE_8: formatMenuExecutor.setSelectedFontSize(8); break;
case FONT_SIZE_12: formatMenuExecutor.setSelectedFontSize(12); break;
case FONT_SIZE_16: formatMenuExecutor.setSelectedFontSize(16); break;
case FONT_SIZE_20: formatMenuExecutor.setSelectedFontSize(20); break;
case FONT_SIZE_24: formatMenuExecutor.setSelectedFontSize(24); break;
case FONT_SIZE_28: formatMenuExecutor.setSelectedFontSize(28); break;
case FONT_SIZE_30: formatMenuExecutor.setSelectedFontSize(30); break;
case FONT_SIZE_32: formatMenuExecutor.setSelectedFontSize(32); break;
case COLOR_WHITE: colorMenuExecutor.changeColor("White"); break;
case COLOR_BLACK: colorMenuExecutor.changeColor("Black"); break;
case COLOR_BLUE: colorMenuExecutor.changeColor("Blue"); break;
}
}

public FileMenuExecutor getFileMenuActionExecutor()
{
return this.fileMenuExecutor;
}
public EditMenuExecutor getEditMenuActionExecutor()
{
return this.editMenuExecutor;
}

public JMenu getFileMenu()
{
return this.fileMenu;
}
public JMenu getEditMenu()
{
return this.editMenu;
}
public JMenu getFormatMenu()
{
return this.formatMenu;
}
public JMenu getColorMenu()
{
return this.colorMenu;
}
public Container getContentPane()
{
return super.getContentPane();
}
public JTextArea getTextArea()
{
return this.textArea;
}
public UndoManager getUndoManager()
{
return this.undoManager;
}
public void setTitle(String title)
{
super.setTitle(title);
}
public JFrame getFrame()
{
return this;
}
public JMenuItem getWordWrapMenuItem()
{
return this.wordWrapMenuItem;
}
public void setWordWrapStatus(boolean wordWrapStatus)
{
this.wordWrapStatus=wordWrapStatus;
}
public boolean getWordWrapStatus()
{
return this.wordWrapStatus;
}
}