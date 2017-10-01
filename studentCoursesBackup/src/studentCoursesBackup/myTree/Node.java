package studentCoursesBackup.myTree;

import java.util.ArrayList;

import studentCoursesBackup.util.RedBlackTreeContantsIfc;

public class Node implements SubjectI, ObserverI, Cloneable, RedBlackTreeContantsIfc
{
	private int bNumber;
	private ArrayList<String> courses;
	//private HashMap<Integer, String> courses;
	private Node left, right;
	private int color;

	public Node()
	{
		bNumber = 0;
		courses = new ArrayList<String>();
		//courses = new HashMap<Integer, String>();
		left = right = null;
		color = RED;
	}
	
	public Node(Node node)
	{
		bNumber = node.getbNumber();
		courses = node.getCourses();
		left = node.getLeft();
		right = node.getRight();
		color = node.getColor();
	}

	public int getbNumber() 
	{
		return bNumber;
	}

	public void setbNumber(int bNumber) 
	{
		this.bNumber = bNumber;
	}

	public ArrayList<String> getCourses() 
	{
		return new ArrayList<String>(courses);
	}

	public void setCourses(ArrayList<String> courses) 
	{
		this.courses = courses;
	}
	
	public Node getLeft() 
	{
		return left;
	}

	public void setLeft(Node left) 
	{
		this.left = left;
	}

	public Node getRight() 
	{
		return right;
	}

	public void setRight(Node right) 
	{
		this.right = right;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
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
