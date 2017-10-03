package studentCoursesBackup.util;

/**
 * Interface used provide functionality of writing output to a file
 * @author hloya
 *
 */
public interface FileDisplayInterface 
{
	/**
	 * Method that should be overriden to write to some file
	 * @param fileNumber - Used to denote a number to the file if there are similar files created by this method
	 */
	public void writeToFile(int fileNumber);
}
