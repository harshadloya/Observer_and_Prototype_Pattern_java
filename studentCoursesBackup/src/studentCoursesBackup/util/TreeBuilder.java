package studentCoursesBackup.util;

import java.util.ArrayList;
import java.util.Iterator;

import studentCoursesBackup.myTree.Node;

public class TreeBuilder 
{
	private FileProcessor fileProcessor;
	//private BSTTree tree1;
	private RedBlackTree tree_orig;
	private RedBlackTree backup_Tree_1;
	private RedBlackTree backup_Tree_2;
	private String inputFile;
	private String deleteFile;
	private String output1File;
	private String output2File;
	private String output3File;

	public TreeBuilder()
	{
		//tree1 = new BSTTree();
		tree_orig = new RedBlackTree();
		backup_Tree_1 = new RedBlackTree();
		backup_Tree_2 = new RedBlackTree();
	}

	public TreeBuilder(String inputFilePath, String deleteFilePath, String outputFile1Path, String outputFile2Path, String outputFile3Path)
	{
		this();
		inputFile = inputFilePath;
		deleteFile = deleteFilePath;
		output1File = outputFile1Path;
		output2File = outputFile2Path;
		output3File = outputFile3Path;		
	}
	
	public void createTree()
	{
		String line = "";
		String temp[];
		fileProcessor = new FileProcessor(inputFile);


		while((line = fileProcessor.readLine(inputFile)) != null)
		{
			boolean bNumberCheck = false;
			boolean courseCheck = false;
			boolean courseAlreadyExists = false;

			//remove leading or trailing whitespaces if any
			line = line.trim();
			temp = line.split(":");

			//checking the input bNumber
			bNumberCheck = validateBNumber(temp[0].trim());

			if(!bNumberCheck)
			{
				continue;
			}
			int bNo = Integer.parseInt(temp[0].trim());

			//checking the input course
			courseCheck = validateCourse(temp[1].trim());

			if(!courseCheck)
			{
				continue;
			}
			String course = temp[1].trim();


			Node node_orig = null;
			Node backup_Node_1 = null;
			Node backup_Node_2 = null;
			ArrayList<String> tempCourses = null;

			try
			{
				//check if node with that bNumber already exists
				node_orig = tree_orig.search(tree_orig.getRoot(), bNo);
				tempCourses = node_orig.getCourses();
			}
			catch(IndexOutOfBoundsException | NullPointerException e)
			{
				//else create a new node
				node_orig = new Node();
				tempCourses = new ArrayList<String>();
			}
			
			node_orig.setbNumber(bNo);

			if(null != tempCourses && !tempCourses.isEmpty())
			{
				Iterator<String> arrayListIter = tempCourses.iterator();
				while(arrayListIter.hasNext())
				{
					if(arrayListIter.next().toString().compareTo(course)==0)
					{
						courseAlreadyExists = true;
						break;
					}
				}
			}
			else
			{
				tempCourses = new ArrayList<String>();
			}
			
			if(courseAlreadyExists)
			{
				continue;
			}
			
			tempCourses.add(course);
			node_orig.setCourses(tempCourses);
			try
			{
				if(node_orig instanceof Cloneable)
				{
					backup_Node_1 = node_orig.clone();
					backup_Node_2 = node_orig.clone();
					
					if(null == node_orig.getObservers() || node_orig.getObservers().isEmpty())
					{
						node_orig.registerObserver(backup_Node_1);
						node_orig.registerObserver(backup_Node_2);
					}
					
				}
			}
			catch(CloneNotSupportedException ce)
			{
				System.err.println("Cloning not supported, please check if the class implements Cloneable");
				ce.printStackTrace();
				System.exit(0);
			}

			tree_orig.insert(node_orig);
			backup_Tree_1.insert(backup_Node_1);
			backup_Tree_2.insert(backup_Node_2);
		}

		//close the open file in the end of reading
		fileProcessor.closeFile();
	}
	
	public void delete()
	{
		String line = "";
		String temp[];
		fileProcessor = new FileProcessor(deleteFile);

		while((line = fileProcessor.readLine(deleteFile)) != null)
		{
			boolean bNumberCheck = false;
			boolean courseCheck = false;

			//remove leading or trailing whitespaces if any
			line = line.trim();
			temp = line.split(":");

			//checking the input bNumber
			bNumberCheck = validateBNumber(temp[0].trim());

			if(!bNumberCheck)
			{
				continue;
			}
			int bNo = Integer.parseInt(temp[0].trim());

			//checking the input course
			courseCheck = validateCourse(temp[1].trim());

			if(!courseCheck)
			{
				continue;
			}
			String course = temp[1].trim();


			Node node_orig = null;
			ArrayList<String> tempCourses = null;

			try
			{
				//find node with that bNumber from the tree
				node_orig = tree_orig.search(tree_orig.getRoot(), bNo);
				tempCourses = node_orig.getCourses();
			}
			catch(IndexOutOfBoundsException | NullPointerException e)
			{
				System.err.println("Course to Delete does not exists in the Node of the Tree");
				e.printStackTrace();
				continue;
			}
			
			if(null != tempCourses && !tempCourses.isEmpty())
			{
				Iterator<String> arrayListIter = tempCourses.iterator();
				while(arrayListIter.hasNext())
				{
					if(arrayListIter.next().toString().compareTo(course)==0)
					{
						tempCourses.remove(course);
						node_orig.setCourses(tempCourses);
						tree_orig.insert(node_orig);
						
						node_orig.notifyObservers();
						break;
					}
				}
			}
		}

		//close the open file in the end of reading
		fileProcessor.closeFile();
	}

	private boolean validateBNumber(String bNo) 
	{
		if(bNo.matches("[0-9][0-9][0-9][1-9]"))
			return true;
		return false;
	}

	private boolean validateCourse(String course) 
	{
		if (course.matches("[A-K]"))
			return true;
		return false;	
	}

}
