package studentCoursesBackup.myTree;

import java.util.ArrayList;

public class Node implements SubjectI, ObserverI, Cloneable
{
	private int bNumber;
	private ArrayList<String> courses;
	//private HashMap<Integer, String> courses;
	Node left, right;

	public Node()
	{
		bNumber = 0;
		courses = new ArrayList<String>();
		//courses = new HashMap<Integer, String>();
		left = right = null;
	}

	public int getbNumber() {
		return bNumber;
	}

	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}

	public ArrayList<String> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}

	/*	public HashMap<Integer, String> getCourses() {
		return courses;
	}

	public void setCourses(HashMap<Integer, String> courses) {
		this.courses = courses;
	}
	 */
	//Observer Method
	@Override
	public void update(Object updateValues) 
	{
	}

	//Subject Methods
	@Override
	public void registerObserver(ObserverI o) 
	{
	}

	@Override
	public void removeObserver(ObserverI o) 
	{
	}

	@Override
	public void notifyObservers() 
	{	
	}

	@Override
	protected Object clone() throws CloneNotSupportedException 
	{
		return super.clone();
	}


}
