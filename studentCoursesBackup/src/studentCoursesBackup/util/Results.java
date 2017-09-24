package studentCoursesBackup.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface
{
	
	private String[] resultSetStrings;
	private String outputFilePath;
	
	public Results() 
	{
		super();
		setResultSetStrings(new String[11]);
	}
	
	public Results(String outputPath)
	{
		this();
		setOutputFilePath(outputPath);
	}

	@Override
	public void writeToStdout(String s) 
	{
		
	}

	@Override
	public void writeToFile() 
	{
		try 
		{
			String path = getOutputFilePath();
			String[] temp = path.split("output.tx");
			
			File file = new File(temp[0]);
			boolean check = file.mkdirs();
			
			if(check == true || file.exists())
			{
				FileWriter writer = new FileWriter(path, true);
				for(int i = 0; i < getResultSetStrings().length; i++)
				{
					if(getResultSetStrings()[i] != null)
						writer.write(getResultSetStrings()[i]);
				}
				writer.flush();
				writer.close();
			}
			else
			{
				System.err.println("\nThe folders given in the path do not exist and cannot be created");
			}
		} 
		catch (IOException e) 
		{
			System.err.println("\nFile write cannot be completed");
			e.printStackTrace();
		}
		
	}
	
	public void storeNewResult(String s)
	{
		String temp[] = getResultSetStrings();
		
		for(int i = 0; i < temp.length; i++)
		{
			if (temp[i]==null)
			{
				temp[i] = s;
				break;
			}
		}
		
		setResultSetStrings(temp);
	}

	public String[] getResultSetStrings() {
		return resultSetStrings;
	}

	public void setResultSetStrings(String[] resultSetStrings) {
		this.resultSetStrings = resultSetStrings;
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

}
