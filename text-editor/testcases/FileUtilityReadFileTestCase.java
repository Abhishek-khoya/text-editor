package testcases;

import in.ac.uecu.utility.*;
import in.ac.uecu.exception.*;

public class FileUtilityReadFileTestCase
{
public static void main(String arguments[])
{
try
{
String fileName=arguments[0];
String fileData=FileUtility.readFile(fileName);
System.out.println("TestCase Passed");
System.out.println("File Data : ");
System.out.println(fileData);
}catch(FileUtilityException fileUtilityException)
{
System.out.print("TestCase Failed : ");
System.out.println(fileUtilityException);
}
}
}