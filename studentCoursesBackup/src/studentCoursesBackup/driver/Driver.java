package studentCoursesBackup.driver;

import studentCoursesBackup.util.TreeBuilder;

/**
 * Class containing the main method which is the starting point of code execution
 * @author hloya
 *
 */
public class Driver 
{
	/**
	 * Main method responsible for creating the tree and then calling the delete function
	 * @param args - Input given from command line which contains location of Input file, Delete file, and the Output Files for the 3 trees
	 */
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
			
			TreeBuilder buildTree = new TreeBuilder(inputFilePath, deleteFilePath, outputFile1Path, outputFile2Path, outputFile3Path);
			buildTree.createTree();
			buildTree.delete();
			buildTree.print();
		}
		else
		{
			System.out.println("Invalid number of arguments, please recheck");
		}
		
	}
}