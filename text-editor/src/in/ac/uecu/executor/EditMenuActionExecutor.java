package in.ac.uecu.executor;

import in.ac.uecu.interfaces.*;

import javax.swing.*;
import javax.swing.undo.*;

public class EditMenuActionExecutor implements EditMenuExecutor
{
private Editor textEditor;
public EditMenuActionExecutor(Editor textEditor)
{
this.textEditor=textEditor;
}
public void undo()
{
UndoManager undoManager=this.textEditor.getUndoManager();
if(undoManager.canUndo()) undoManager.undo();
}
public void redo()
{
UndoManager undoManager=this.textEditor.getUndoManager();
if(undoManager.canRedo()) undoManager.redo();
}
}
