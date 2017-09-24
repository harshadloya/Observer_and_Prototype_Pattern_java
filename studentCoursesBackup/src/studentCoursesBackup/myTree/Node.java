package studentCoursesBackup.myTree;

import java.util.HashMap;

public class Node 
{
	private int bNumber;
//	private List courses;
	private HashMap<Integer, String> courses;
	//private HashMap<?,?> node;
	
	public Node()
	{
		bNumber = 0;
		//courses = new ArrayList<String>();
		courses = new HashMap<Integer, String>();
		//node = new HashMap<Integer, String>();
	}

	public int getbNumber() {
		return bNumber;
	}

	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}
/*
	public List getCourses() {
		return courses;
	}

	public void setCourses(List courses) {
		this.courses = courses;
	}
*/

	public HashMap<Integer, String> getCourses() {
		return courses;
	}

	public void setCourses(HashMap<Integer, String> courses) {
		this.courses = courses;
	}	
	

}
