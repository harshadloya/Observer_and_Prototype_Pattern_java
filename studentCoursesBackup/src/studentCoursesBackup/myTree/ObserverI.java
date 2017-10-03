package studentCoursesBackup.myTree;
/**
 * Interface that will make a node as Observer of some Subject
 * @author hloya
 *
 */
public interface ObserverI 
{
	/**
	 * Method that receives values from the subject that the Observer needs to Update in itself
	 * @param updateValues - value that the Subject sends over to be updated
	 */
	public void update(Object updateValues);
}
