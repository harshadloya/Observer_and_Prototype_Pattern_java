/*
 * Referenced from http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
 */

package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;

public class RedBlackTree extends BSTTree implements RedBlackTreeContantsIfc
{
	public RedBlackTree()
	{
		super();
	}

	public void insert(Node node)
	{
		super.insert(node);
		getRoot().setColor(BLACK);
	}

	public Node insertRecursive(Node recursionRoot, Node node)
	{
		recursionRoot = super.insertRecursive(recursionRoot, node);

		//Rotations to maintain RedBlackTree Policy
		if (isRed(recursionRoot.getRight()) && !isRed(recursionRoot.getLeft()))
			recursionRoot = rotateLeft(recursionRoot);
		if (isRed(recursionRoot.getLeft())  &&  isRed(recursionRoot.getLeft().getLeft()))
			recursionRoot = rotateRight(recursionRoot);
		if (isRed(recursionRoot.getLeft())  &&  isRed(recursionRoot.getRight()))
			flipColors(recursionRoot);

		return recursionRoot;
	}

	//flip colors of the node and its two children
	private void flipColors(Node node) 
	{
		// node must have opposite color of its two children
		if ((!isRed(node) &&  isRed(node.getLeft()) &&  isRed(node.getRight()))  || (isRed(node)  && !isRed(node.getLeft()) && !isRed(node.getRight())))
		{
			if(isRed(node))
				node.setColor(BLACK);
			else
				node.setColor(RED);

			if(isRed(node.getLeft()) && isRed(node.getRight()))
			{
				node.getLeft().setColor(BLACK);
				node.getRight().setColor(BLACK);
			}
			else
			{
				node.getLeft().setColor(RED);
				node.getRight().setColor(RED);
			}
		}	
	}

	private Node rotateRight(Node node) 
	{
		// assert (h != null) && isRed(h.left);
		Node rotatedNode = node.getLeft();
		node.setLeft(rotatedNode.getRight());
		rotatedNode.setRight(node);
		rotatedNode.setColor(rotatedNode.getRight().getColor());
		rotatedNode.getRight().setColor(RED);
		return rotatedNode;
	}

	private Node rotateLeft(Node node) 
	{
		// assert (h != null) && isRed(h.right);
		Node rotatedNode = node.getRight();
		node.setRight(rotatedNode.getLeft());
		rotatedNode.setLeft(node);
		rotatedNode.setColor(rotatedNode.getLeft().getColor());
		rotatedNode.getLeft().setColor(RED);
		return rotatedNode;
	}

	private boolean isRed(Node nodeToCheck)
	{
		if (nodeToCheck != null && RED == nodeToCheck.getColor())
			return true;
		return false;
	}

}
