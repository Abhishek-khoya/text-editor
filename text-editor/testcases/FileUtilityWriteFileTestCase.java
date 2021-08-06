package testcases;

import in.ac.uecu.utility.*;
import in.ac.uecu.exception.*;

class FileUtilityWriteFileTestCase
{
public static void main(String arguments[])
{
try
{
String fileName=arguments[0];
String fileData=arguments[1];
FileUtility.writeFile(fileName,fileData);
System.out.println("TestCase Passed");
}catch(FileUtilityException fileUtilityException)
{
System.out.print("TestCase Failed : ");
System.out.println(fileUtilityException);
}
}
}