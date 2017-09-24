package studentCoursesBackup.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import studentCoursesBackup.myTree.Node;

public class TreeBuilder 
{
	private FileProcessor fileProcessor;
	private HashMap<Integer, Node> tree;

	public TreeBuilder()
	{
		tree = new HashMap<Integer, Node>();
	}

	public TreeBuilder(String inputFilePath, String outputFilePath)
	{
		this();
		fileProcessor = new FileProcessor(inputFilePath);

		String line = "";
		String temp[];

		while((line = fileProcessor.readLine(inputFilePath)) != null)
		{
			//remove leading or trailing whitespaces if any
			line = line.trim();
			temp = line.split(":");
			int bNo = Integer.parseInt(temp[0].trim());
			String course = temp[1].trim();
			Node node;
			HashMap<Integer, String> tempCourses = null;

			try
			{
				node = tree.get(bNo);
				tempCourses = tree.get(bNo).getCourses();
			}
			catch(IndexOutOfBoundsException | NullPointerException e)
			{
				node = new Node();
				tempCourses = new HashMap<Integer, String>();
			}			

			node.setbNumber(bNo);

			if(null != tempCourses && !tempCourses.isEmpty())
			{
				String bNoCoursesCheck = (String) tempCourses.get(bNo);

				if(!(0 == bNoCoursesCheck.compareTo("")) || !(null == bNoCoursesCheck))
				{
					course = bNoCoursesCheck + "," + course;
				}

				tempCourses.replace(bNo, course);
				node.setCourses(tempCourses);
			}
			else
			{
				tempCourses.put(bNo, course);
				node.setCourses(tempCourses);
			}

			tree.put(bNo, node);
		}

		//close the open file in the end of reading
		fileProcessor.closeFile();
	}

}
