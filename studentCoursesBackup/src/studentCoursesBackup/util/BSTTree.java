package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;

public class BSTTree 
{
	private Node root;

	public BSTTree()
	{
		root = null;
	}

	public Node search(Node recursionRoot, int bNumber)
	{
		if(null == recursionRoot || bNumber == recursionRoot.getbNumber())
		{
			return new Node(recursionRoot);
		}
		
		if(bNumber < recursionRoot.getbNumber())
		{
			return search(recursionRoot.getLeft(), bNumber);
		}
		
		return search(recursionRoot.getRight(), bNumber);
	}

	public void insert(Node node)
	{
		root = insertRecursive(root, node);
	}

	public Node insertRecursive(Node rootRecursive, Node node)
	{
		if(null == rootRecursive)
		{
			rootRecursive = node;
			return rootRecursive;
		}

		if(node.getbNumber() < rootRecursive.getbNumber())
		{
			rootRecursive.setLeft(insertRecursive(rootRecursive.getLeft(), node));
		}
		else if (node.getbNumber() > rootRecursive.getbNumber())
		{
			rootRecursive.setRight(insertRecursive(rootRecursive.getRight(), node));
		}
		else if (node.getbNumber() == rootRecursive.getbNumber())
		{
			rootRecursive.setCourses(node.getCourses());
		}
		
		return rootRecursive;
	}

	public Node getRoot() 
	{
		return root;
	}
	
	public void printNodes(Results r, Node root, int fileNumber)
	{
		if(root != null)
		{
			printNodes(r, root.getLeft(), fileNumber);
			r.writeToFile(root.toString(), fileNumber);
			printNodes(r, root.getRight(), fileNumber);
		}
	}

}