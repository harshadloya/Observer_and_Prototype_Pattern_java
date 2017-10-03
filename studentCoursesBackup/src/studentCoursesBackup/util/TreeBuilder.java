package studentCoursesBackup.util;

import java.util.ArrayList;
import java.util.Iterator;

import studentCoursesBackup.myTree.Node;

/**
 * Class that builds and does insert, search, delete operation on the built tree
 * @author hloya
 *
 */
public class TreeBuilder 
{
	/**
	 * Data member that is used for reading from a file
	 */
	private FileProcessor fileProcessor;

	/**
	 * Data member that holds the original tree
	 */
	private RedBlackTree tree_orig;

	/**
	 * Data member that holds the backup tree 1
	 */
	private RedBlackTree backup_Tree_1;

	/**
	 * Data member that holds the backup tree 2
	 */
	private RedBlackTree backup_Tree_2;

	/**
	 * Data member that holds the path of input file
	 */
	private String inputFile;

	/**
	 * Data member that holds the path of delete file
	 */
	private String deleteFile;

	/**
	 * Data member that holds the path of output file 1
	 */
	private String output1File;

	/**
	 * Data member that holds the path of output file 2
	 */
	private String output2File;

	/**
	 * Data member that holds the path of output file 3
	 */
	private String output3File;

	/**
	 * Default Constructor that initializes the 3 tree references.
	 */
	public TreeBuilder()
	{
		tree_orig = new RedBlackTree();
		backup_Tree_1 = new RedBlackTree();
		backup_Tree_2 = new RedBlackTree();
	}

	/**
	 * Parameterized constructor that sets the data members to the passed parameter values.
	 * @param inputFilePath - contains path of input file
	 * @param deleteFilePath - contains path of delete file
	 * @param outputFile1Path - contains path of output file 1
	 * @param outputFile2Path - contains path of output file 2
	 * @param outputFile3Path - contains path of output file 3
	 */
	public TreeBuilder(String inputFilePath, String deleteFilePath, String outputFile1Path, String outputFile2Path, String outputFile3Path)
	{
		this();
		inputFile = inputFilePath;
		deleteFile = deleteFilePath;
		output1File = outputFile1Path;
		output2File = outputFile2Path;
		output3File = outputFile3Path;		
	}

	/**
	 * Method that reads the bNumber and Course Names from the input file one at a time, validates it and creates a node for them, if one already doesn't exists in the tree for that bNumber.
	 * <br>
	 * Then adds the node to the original tree.
	 * <br>
	 * If the node already exists, then just the new course read is added to that node.
	 * <br>
	 * If the course read already exists for that bNumber, that input line is skipped.
	 * <br>
	 * Two Backup Trees are also created and the original node is cloned to be added to respective backup tree.
	 */
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

			
			int bNo = 0;
			String course = "";

			Node node_orig = null;
			Node backup_Node_1 = null;
			Node backup_Node_2 = null;
			ArrayList<String> tempCourses = null;

			try
			{
				//checking the input bNumber
				bNumberCheck = validateBNumber(temp[0].trim());

				if(!bNumberCheck)
				{
					continue;
				}
				bNo = Integer.parseInt(temp[0].trim());

				//checking the input course
				courseCheck = validateCourse(temp[1].trim());

				if(!courseCheck)
				{
					continue;
				}
				course = temp[1].trim();

				//check if node with that bNumber already exists
				node_orig = tree_orig.search(tree_orig.getRoot(), bNo);
				tempCourses = node_orig.getCourses();
			}
			catch (ArrayIndexOutOfBoundsException e) 
			{
				continue;
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

					//add observers only if original node has no observers
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

	/**
	 * Method that reads the bNumber and Course Names from the delete file one at a time, validates it and deletes the respective course for that node from the original tree.
	 * <br>
	 * If the course for that node is already deleted, then just skip that line.
	 * <br>
	 * Then, it notifies the observers for that node of the change, so that the backup trees also get updated.
	 */
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

			Node node_orig = null;
			ArrayList<String> tempCourses = null;
			int bNo = 0;
			String course = "";

			try
			{
				//checking the input bNumber
				bNumberCheck = validateBNumber(temp[0].trim());

				if(!bNumberCheck)
				{
					continue;
				}
				bNo = Integer.parseInt(temp[0].trim());

				//checking the input course
				courseCheck = validateCourse(temp[1].trim());

				if(!courseCheck)
				{
					continue;
				}
				course = temp[1].trim();

				//find node with that bNumber from the tree
				node_orig = tree_orig.search(tree_orig.getRoot(), bNo);
				tempCourses = node_orig.getCourses();
			}
			catch (ArrayIndexOutOfBoundsException e) 
			{
				continue;
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

	/**
	 * This method 
	 * <br>
	 * 1. Stores the bNumber and Courses of all the nodes of all 3 trees.
	 * 2. Calls method that will write these result values in a file
	 */
	public void print()
	{
		Results tree_orig_result = new Results(output1File);
		tree_orig.printNodes(tree_orig_result, tree_orig.getRoot());
		tree_orig_result.writeToFile(1);

		Results backup_Tree_1_result = new Results(output2File);
		backup_Tree_1.printNodes(backup_Tree_1_result, backup_Tree_1.getRoot());
		backup_Tree_1_result.writeToFile(2);

		Results backup_Tree_2_result = new Results(output3File);
		backup_Tree_2.printNodes(backup_Tree_2_result, backup_Tree_2.getRoot());
		backup_Tree_2_result.writeToFile(3);
	}

	/**
	 * Method used to check if bNumber read from file is proper 4 digit number.
	 * @param bNo - a string value that contains the bNumber read from the input/delete file.
	 * @return true if bNumber read is 4 digit number.
	 */
	private boolean validateBNumber(String bNo) 
	{
		if(bNo.matches("[1-9][0-9][0-9][0-9]"))
			return true;
		return false;
	}

	/**
	 * Method used to check if course read from file matches specification of being within alphabets A - K.
	 * @param course - a string value that contains the course read from input/delete file.
	 * @return true if course read is within specifications.
	 */
	private boolean validateCourse(String course) 
	{
		if (course.matches("[A-K]"))
			return true;
		return false;	
	}

}
