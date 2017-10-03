package studentCoursesBackup.myTree;

/**
 * Interface that will make a node as Subject of some Observers 
 * @author hloya
 *
 */
public interface SubjectI 
{
	/**
	 * Method used to add new Observers to the Subject
	 * @param obsNode - will contain the node that needs to be added
	 */
	public void registerObserver(Node obsNode);
	
	/**
	 * Method used to remove Observers from the Subject
	 * @param obsNode - will contain the node that needs to be removed
	 */
	public void removeObserver(Node obsNode);
	
	/**
	 * Method used to notify all the observers when change occurs
	 */
	public void notifyObservers(); 
}
