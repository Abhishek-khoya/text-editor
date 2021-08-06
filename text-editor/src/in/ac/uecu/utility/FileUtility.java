package in.ac.uecu.utility;

import in.ac.uecu.exception.*;

import java.io.*;

public class FileUtility
{
private FileUtility() {}
public static String readFile(String file) throws FileUtilityException
{
if(file==null) return null;
StringBuilder text=new StringBuilder("");
BufferedReader bufferedReader=null;
try
{
bufferedReader=new BufferedReader(new FileReader(file));
String line=null;
while(bufferedReader.ready()) 
{
line=bufferedReader.readLine();
if(line==null) break;
text.append(line+"\n");
}
bufferedReader.close();
}catch(Exception exception)
{
throw new FileUtilityException("Unable to open file.");
}
return text.toString();
}
public static void writeFile(String file,String text) throws FileUtilityException
{
if(file==null || text==null) return;
FileWriter fileWriter=null;
try
{
fileWriter=new FileWriter(file);
fileWriter.write(text);
fileWriter.close();
}catch(Exception exception)
{
throw new FileUtilityException("Unable to write, File : "+file);
}
}
}