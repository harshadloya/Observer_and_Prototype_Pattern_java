package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;

public class BSTTree 
{
	Node root;
	
	public BSTTree()
	{
		root = new Node();
	}
	
	public Node search(int key)
	{
		return root;
	}
	
	public void insert(Node node)
	{
		root = node;
	}

}