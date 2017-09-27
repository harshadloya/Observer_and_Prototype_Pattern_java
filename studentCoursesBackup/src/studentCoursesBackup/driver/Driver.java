package studentCoursesBackup.driver;

import studentCoursesBackup.util.TreeBuilder;

public class Driver 
{
	public static void main(String[] args) 
	{
		String inputFilePath = "";
		String deleteFilePath = "";
		String outputFile1Path = "";
		String outputFile2Path = "";
		String outputFile3Path = "";

		if (args.length == 5)
		{
			inputFilePath = args[0];
			deleteFilePath = args[1];
			outputFile1Path	= args[2];
			outputFile2Path = args[3];
			outputFile3Path = args[4];
			
			TreeBuilder tb = new TreeBuilder(inputFilePath, outputFile1Path, outputFile2Path, outputFile3Path);
		}
		else
		{
			System.out.println("Invalid number of arguments, please recheck");
		}
		
	}
}