package in.ac.uecu.executor;

import in.ac.uecu.interfaces.*;

import javax.swing.*;
import java.awt.*;

public class FormatMenuActionExecutor implements FormatMenuExecutor
{
private Editor textEditor;
private Font arial,comicSansMS,timesNewRoman,selectedFont;
public FormatMenuActionExecutor(Editor textEditor)
{
this.textEditor=textEditor;
this.selectedFont=new Font("Arial", Font.PLAIN,20);
}
public void wordWrap()
{
if(this.textEditor.getWordWrapStatus()==false)
{
this.textEditor.setWordWrapStatus(true);
this.textEditor.getTextArea().setLineWrap(true);
this.textEditor.getTextArea().setWrapStyleWord(true);
this.textEditor.getWordWrapMenuItem().setText("Word Wrap : On");
}
else if(this.textEditor.getWordWrapStatus()==true)
{
this.textEditor.setWordWrapStatus(false);
this.textEditor.getTextArea().setLineWrap(false);
this.textEditor.getTextArea().setWrapStyleWord(false);
this.textEditor.getWordWrapMenuItem().setText("Word Wrap : Off");
}
}
public void setSelectedFont(Font font)
{
this.selectedFont=font;
this.textEditor.getTextArea().setFont(this.selectedFont);
}
public Font getSelectedFont()
{
return this.selectedFont;
}
public void setSelectedFontSize(int size)
{
this.selectedFont=new Font(this.selectedFont.getFontName(),this.selectedFont.getStyle(),size);
this.textEditor.getTextArea().setFont(this.selectedFont);
}
public int getSelectedFontSize()
{
return this.selectedFont.getSize();
}
}