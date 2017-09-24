package studentCoursesBackup.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor 
{

	FileReader isr;
	BufferedReader br;

	public FileProcessor(String filePath)
	{
		try
		{
			isr = new FileReader(new File(filePath));
			br = new BufferedReader(isr);
		}
		catch (FileNotFoundException e) 
		{
			System.err.println("File Not Found, Please recheck the path specified in arguments");
			e.printStackTrace();
		}
	}
	

	//returns 1 line from the file whose path is specified by filePath on each call to the method
	public String readLine(String filePath)
	{
		String oneLineFromFile = "";
		try 
		{
			oneLineFromFile = br.readLine();
		} 
		catch (IOException e) 
		{
			System.err.println("Cannot Read from File");
			e.printStackTrace();
		}

		return oneLineFromFile;
	}
	
	
	//Closes the file opened for reading
	public void closeFile()
	{
		try
		{
			br.close();
		}
		catch (IOException e) 
		{
			System.err.println("File in Use, File cannot be closed");
			e.printStackTrace();
		}
	}

	/*
	 * Testing purpose
	 
	public static void main(String args[]) throws FileNotFoundException
	{
		String filePath = ".\\input.txt";
		FileProcessor fp = new FileProcessor(filePath);

		String abc = "";
		while((abc = fp.readLine(filePath)) != null)
		{			
			System.out.println(abc);
		}
	}
	*/

}
