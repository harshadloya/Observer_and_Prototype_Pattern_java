package studentCoursesBackup.util;

import java.util.ArrayList;
import java.util.Iterator;

import studentCoursesBackup.myTree.Node;

public class TreeBuilder 
{
	private FileProcessor fileProcessor;
	//private HashMap<Integer, Node> tree;
	//private BSTTree tree1;
	private RedBlackTree tree1;
	private String inputFile;
	private String deleteFile;
	private String output1File;
	private String output2File;
	private String output3File;

	public TreeBuilder()
	{
		//tree = new HashMap<Integer, Node>();
		//tree1 = new BSTTree();
		tree1 = new RedBlackTree();
	}

	public TreeBuilder(String inputFilePath, String deleteFilePath, String outputFile1Path, String outputFile2Path, String outputFile3Path)
	{
		this();
		fileProcessor = new FileProcessor(inputFilePath);
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


			Node node = null;
			//HashMap<Integer, String> tempCourses = null;
			ArrayList<String> tempCourses = null;

			try
			{
				//check if node with that bNumber already exists
				node = tree1.search(tree1.getRoot(), bNo);
				tempCourses = node.getCourses();
			}
			catch(IndexOutOfBoundsException | NullPointerException e)
			{
				//else create a new node
				node = new Node();
				tempCourses = new ArrayList<String>();
				//tempCourses = new HashMap<Integer, String>();
			}
			
			node.setbNumber(bNo);

			if(null != tempCourses && !tempCourses.isEmpty())
			{
				Iterator arrayListIter = tempCourses.iterator();
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
			node.setCourses(tempCourses);

			//tree.put(bNo, node);
			tree1.insert(node);
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
