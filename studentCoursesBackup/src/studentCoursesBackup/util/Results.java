package studentCoursesBackup.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface
{
	
	private String outputFilePath;
	
	public Results() 
	{
		super();
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
	public void writeToFile(String s, int fileNumber) 
	{
		try 
		{
			String path = getOutputFilePath();
			String[] temp = path.split("output"+ fileNumber +".tx");
			
			File file = new File(temp[0]);
			boolean check = file.mkdirs();
			
			if(check == true || file.exists())
			{
				FileWriter writer = new FileWriter(path, true);
				
				writer.write(s+"\n");
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

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

}
