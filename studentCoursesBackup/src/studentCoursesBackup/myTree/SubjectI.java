package studentCoursesBackup.myTree;

public interface SubjectI 
{
	public void registerObserver(Node obsNode);
	public void removeObserver(Node obsNode);
	public void notifyObservers(); 
}
