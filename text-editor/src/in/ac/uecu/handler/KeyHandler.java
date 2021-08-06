package in.ac.uecu.handler;

import in.ac.uecu.interfaces.*;

import java.awt.event.*;

public class KeyHandler implements KeyListener
{
private Editor textEditor;
public KeyHandler(Editor textEditor)
{
this.textEditor=textEditor;
}
public void keyPressed(KeyEvent keyEvent)
{
if(keyEvent.isControlDown() && keyEvent.getKeyCode()==KeyEvent.VK_N)
{
this.textEditor.getFileMenuActionExecutor().createNewFile();
}
if(keyEvent.isControlDown() && keyEvent.getKeyCode()==KeyEvent.VK_O)
{
this.textEditor.getFileMenuActionExecutor().openFile();
}
if(keyEvent.isControlDown() && keyEvent.getKeyCode()==KeyEvent.VK_S)
{
this.textEditor.getFileMenuActionExecutor().saveFile();
}
if(keyEvent.isShiftDown() && keyEvent.isControlDown() && keyEvent.getKeyCode()==KeyEvent.VK_S)
{
this.textEditor.getFileMenuActionExecutor().saveFileAs();
}
if(keyEvent.isControlDown() && keyEvent.getKeyCode()==KeyEvent.VK_Z)
{
this.textEditor.getEditMenuActionExecutor().undo();
}
if(keyEvent.isControlDown() && keyEvent.getKeyCode()==KeyEvent.VK_Y)
{
this.textEditor.getEditMenuActionExecutor().redo();
}
if(keyEvent.isAltDown() && keyEvent.getKeyCode()==KeyEvent.VK_F)
{
this.textEditor.getFileMenu().doClick();
}
if(keyEvent.isAltDown() && keyEvent.getKeyCode()==KeyEvent.VK_E)
{
this.textEditor.getEditMenu().doClick();
}
if(keyEvent.isAltDown() && keyEvent.getKeyCode()==KeyEvent.VK_O)
{
this.textEditor.getFormatMenu().doClick();
}
if(keyEvent.isAltDown() && keyEvent.getKeyCode()==KeyEvent.VK_C)
{
this.textEditor.getColorMenu().doClick();
}
}
public void keyTyped(KeyEvent keyEvent) {}
public void keyReleased(KeyEvent keyEvent) {}
}