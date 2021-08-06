package in.ac.uecu.executor;

import in.ac.uecu.interfaces.*;
import in.ac.uecu.utility.*;
import in.ac.uecu.exception.*;

import javax.swing.*;
import java.awt.*;

public class FileMenuActionExecutor implements FileMenuExecutor
{
private Editor textEditor;
private String fileName,fileDirectory;
public FileMenuActionExecutor(Editor textEditor)
{
this.textEditor=textEditor;
}
public void createNewFile()
{
this.textEditor.getTextArea().setText("");
this.textEditor.setTitle("New");
this.fileName=null;
this.fileDirectory=null;
}
public void openFile()
{
FileDialog fileDialog=new FileDialog(this.textEditor.getFrame(),"Open",FileDialog.LOAD);
fileDialog.setVisible(true);
if(fileDialog.getFile()!=null)
{
this.fileName=fileDialog.getFile();
this.fileDirectory=fileDialog.getDirectory();
this.textEditor.setTitle(this.fileName);
}
try
{
String text=FileUtility.readFile(this.fileDirectory+this.fileName);
JTextArea textArea=this.textEditor.getTextArea();
textArea.setText(text);
}catch(FileUtilityException fileUtilityException)
{
System.out.println(fileUtilityException);
}
}
public void saveFile()
{
if(this.fileName==null)
{
this.saveFileAs();
return;
}
String text=this.textEditor.getTextArea().getText();
try
{
FileUtility.writeFile(this.fileDirectory+this.fileName,text);
}catch(FileUtilityException fileUtilityException)
{
System.out.println(fileUtilityException);
}
this.textEditor.setTitle(this.fileName);
}
public void saveFileAs()
{
FileDialog fileDialog=new FileDialog(this.textEditor.getFrame(),"Save As",FileDialog.SAVE);
fileDialog.setVisible(true);
if(fileDialog.getFile()==null) return;
this.fileName=fileDialog.getFile();
this.fileDirectory=fileDialog.getDirectory();
this.textEditor.setTitle(this.fileName);
JTextArea textArea=this.textEditor.getTextArea();
String text=textArea.getText();
try
{
FileUtility.writeFile(this.fileDirectory+this.fileName,text);
}catch(FileUtilityException fileUtilityException)
{
System.out.println(fileUtilityException);
}
}
public void exit()
{
System.exit(0);
}
}