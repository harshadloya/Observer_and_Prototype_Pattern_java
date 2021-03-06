package studentCoursesBackup.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class that stores the result of operations performed on the referencer of this class.
 * <br>
 * This class also implements two interfaces used for displaying content in some file or on standard output
 * @author hloya
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface
{
	/**
	 * Data member that stores the path of the output file in which results(data) stored in this class will be written
	 */
	private String outputFilePath;
	
	/**
	 * Data member that stores the results(data) passed to this class
	 */
	private HashMap<Integer, String> resultSetStrings;
	
	/**
	 * Counter used for naming similar files - here output(counter) .txt
	 */
	private int i;
	
	/**
	 * Default Constructor that initializes the data members
	 */
	public Results() 
	{
		super();
		resultSetStrings = new HashMap<Integer, String>();
		i = 0;
	}
	
	/**
	 * Parameterized constructor that initializes the data member to the specified paramter value.
	 * @param outputPath - provides the path of the output file in which results (data) will be written.
	 */
	public Results(String outputPath)
	{
		this();
		setOutputFilePath(outputPath);
	}

	@Override
	public void writeToStdout(String s) 
	{
		//not using this method
	}

	@Override
	public void writeToFile(int fileNumber) 
	{
		try 
		{
			String path = getOutputFilePath();
			String[] temp = path.split("output"+ fileNumber +".tx");
			
			File file = new File(temp[0]);
			boolean check = file.mkdirs();
			File outputFile = new File(path);
			boolean checkFileAlreadyExists = false;
			
			if(check == true || file.exists())
			{
				if(outputFile.exists())
				{
					checkFileAlreadyExists = true;
				}
				
				FileWriter writer = new FileWriter(path, true);
				if(checkFileAlreadyExists)
				{
					writer.write("\n\n");
					checkFileAlreadyExists = false;
				}
				
				for(int i = 0; i < resultSetStrings.size()-1; i++)
				{
					if(resultSetStrings.get(i) != null)
						writer.write(resultSetStrings.get(i) +"\n");
				}
				
				if(resultSetStrings.get(resultSetStrings.size()-1) != null)
					writer.write(resultSetStrings.get(resultSetStrings.size()-1));
				
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
	
	/**
	 * Stores the result string passed into a data structure
	 * @param s - contains the bNumber and Courses of the node for which this method is called
	 */
	public void storeNewResult(String s)
	{
		resultSetStrings.put(i, s);
		i++;
	}

	/**
	 * Accessor for output file's path
	 * @return path of output file
	 */
	public String getOutputFilePath() {
		return outputFilePath;
	}

	/**
	 * Mutator for output file's path
	 * @param outputFilePath - specifies the path of the output file
	 */
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}


}
