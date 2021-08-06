package in.ac.uecu.executor;

import in.ac.uecu.interfaces.*;

import javax.swing.*;
import java.awt.*;

public class ColorMenuActionExecutor implements ColorMenuExecutor
{
private Editor textEditor;
public ColorMenuActionExecutor(Editor textEditor)
{
this.textEditor=textEditor;
}
public void changeColor(String color)
{
switch(color)
{
case "White":
this.textEditor.getContentPane().setBackground(Color.white);
this.textEditor.getTextArea().setBackground(Color.white);
this.textEditor.getTextArea().setForeground(Color.black);
break;
case "Black":
this.textEditor.getContentPane().setBackground(Color.black);
this.textEditor.getTextArea().setBackground(Color.black);
this.textEditor.getTextArea().setForeground(Color.white);
break;
case "Blue":
this.textEditor.getContentPane().setBackground(Color.blue);
this.textEditor.getTextArea().setBackground(Color.blue);
this.textEditor.getTextArea().setForeground(Color.white);
break;
}
}
}