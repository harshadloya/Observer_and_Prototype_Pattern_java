package studentCoursesBackup.myTree;

import java.util.ArrayList;

import studentCoursesBackup.util.RedBlackTreeContantsIfc;

public class Node implements SubjectI, ObserverI, Cloneable, RedBlackTreeContantsIfc
{
	private int bNumber;
	private ArrayList<String> courses;
	private Node left, right;
	private int color;
	private ArrayList<Node> observers;

	public Node()
	{
		bNumber = 0;
		courses = new ArrayList<String>();
		left = right = null;
		color = RED;
		observers = new ArrayList<Node>();
	}
	
	public Node(Node node)
	{
		bNumber = node.getbNumber();
		courses = node.getCourses();
		left = node.getLeft();
		right = node.getRight();
		color = node.getColor();
		observers = node.getObservers();
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
	
	public ArrayList<Node> getObservers() {
		return observers;
	}

	//Observer Method
	@SuppressWarnings("unchecked")
	@Override
	public void update(Object updateValues) 
	{
		setCourses((ArrayList<String>) updateValues);
	}

	//Subject Methods
	@Override
	public void registerObserver(Node obsNode) 
	{
		observers.add(obsNode);
	}

	@Override
	public void removeObserver(Node obsNode) 
	{
		observers.remove(obsNode);
	}
	
	public void removeAllObservers() 
	{
		observers.clear();
	}

	@Override
	public void notifyObservers() 
	{
		for (Node obsNode : observers)
		{
			obsNode.update(getCourses());
		}
	}

	@Override
	public Node clone() throws CloneNotSupportedException
	{
		Node clone =  (Node) super.clone();
		clone.bNumber = new Integer(bNumber);
		if(null != left)
		{
			clone.left = new Node(left);
		}
		else
		{
			clone.left = null;
		}
		
		if(null != right)
		{
			clone.right = new Node(right);
		}
		else
		{
			clone.right = null;
		}
		
		clone.color = new Integer(color);
		clone.courses = new ArrayList<String>(courses);
		clone.observers = new ArrayList<Node>(observers);
		return clone;
	}

	@Override
	public String toString() {
		return "Node : bNumber = " + bNumber + ", courses = " + courses;
	}
	
	
}
