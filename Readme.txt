Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: modified build file to compile when run command is executed

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=FIRST -Darg1=SECOND -Darg2=THIRD -Darg3=FOURTH -Darg4=FIFTH
eg: ant -buildfile src/build.xml run -Darg0=/import/linux/home1/hloya1/input_file/input.txt -Darg1=/import/linux/home1/hloya1/delete/delete.txt -Darg2=/import/linux/home1/hloya1/output_file/output1.txt -Darg3=/import/linux/home1/hloya1/output_file/output2.txt -Darg4=/import/linux/home1/hloya1/output_file/output3.txt

#Note: 
1. Make sure input.txt and delete.txt is present at appropriate location
2. Output file location and existence doesn't matter but its name should be "output1.txt/output2.txt/output3.txt"
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.”

[Date: 10/03/2017]

-----------------------------------------------------------------------
I have used Red-Black Tree because since the tree is a balanced binary search tree:
1. Even in worst case its insert, search and delete time complexity is O(log n) where n is number of nodes in the tree.
2. Binary Search Trees are already sorted with all values smaller than root on left side of tree and all greater than root on right. This makes traversal easier.

Observer pattern Implementation:
The nodes can be both Subject as well as Observers.
Observers are created as backup nodes using Prototype Pattern.
The nodes contain a data structure (list) to hold the Observer references.
This data structure has values only for Subject Nodes, it is empty in Observer Nodes.
Whenever a change occurs, in this assignment deletion of course from Subject nodes, the subject nodes call notifyObservers() method.
The notifyObservers() method is responsible for calling update() method on each of observer passing the change as parameter to the method.
The update() method then, makes similar change in the Observer nodes.
Since, Observer nodes are references of nodes actually in the backup trees, the backup trees also get updated as a result.

So, whatever change is made in Subject - subject notifies its observers to make similar changes, so that they remain in sync. 
-----------------------------------------------------------------------

Implementation for Red-Black Tree was referred from http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html