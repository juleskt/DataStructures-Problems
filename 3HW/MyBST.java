package bst;
import java.util.ArrayList;
import java.io.*;
import bst.BST.Rotation;


/* NAME: Julian K Trinh
 * ID: U22431781
 * Collaboration with Christine Duong
 * Disclaimer, both files need to contain the same numbers and should not include
 * and extra lines.
 */
public class MyBST
{
	public static class BSTrotations extends BST 
	{
		// Constructor for BSTrotations; inherits from parent class
		public BSTrotations(Integer num) 
		{
			super(num);
	    	}
		
		// Override of transform function
		public static ArrayList<Rotation> transform(BST first, BST second) 
		{
			ArrayList<Rotation> values = new ArrayList<Rotation>();
		    	first = rotateBST(first, second, values);
		    	
		    	//From BST.java code that was given to us
		    	//Prints the two BSTs in the desired format
		    	String val = first.print();
		    	String val1 = second.print();
		    	System.out.printf("New First\n%s\n\nSecond\n%s\n", val,val1);
		    	
		    	return values;
		}
		
		public static BST rotateBST(BST first, BST second, ArrayList<Rotation> values)
		{
			//Find the root of [second] in [first]
		    	BST current = findNode(first,second.key);
		    	while (first.key != second.key) 
		    	{
		    		//While the root of [first] does not equal the root of [second]
		    		//Get the parent...
		    		BST parent = findParentofNode(current, first);
		    		
		    		//And move the node of [first] by rotating it in the right direction
		    		//Towards the position it is in [second]
		    		if (parent == first && parent.right == current)
		    		{
		    			first = rotateRight(parent);
		    			values.add(new Rotation(current.key, Rotation.RotationType.ZAG));
		    		}
		    		else if(parent == first && parent.left == current)
		    		{
		    			first = rotateLeft(parent);
		    			values.add(new Rotation(current.key, Rotation.RotationType.ZIG));
		    		}
		    		if(parent.right == current)
		    		{
		    			findParentofNode(parent, first).right = rotateRight(parent);
		    			values.add(new Rotation(current.key,Rotation.RotationType.ZAG));
		    		}
		    		else if(parent.left == current)
		    		{
		    			findParentofNode(parent, first).right = rotateLeft(parent);
		    			values.add(new Rotation(current.key, Rotation.RotationType.ZIG));
	    		}
	    	}
	    	
	    	//Print out how the tree looks at this point.
	    	String val = first.print();
	    	String val1 = second.print();
	    	System.out.printf("New First\n%s\n\nSecond\n%s\n", val, val1);
	    	
	    	//Keep rotating the children according
	    	if(first.left != null && second.left != null)
	    	{
	    		first.left = rotateBST(first.left, second.left, values);
	    	}
	    	if(first.right != null)
	    	{
	    		first.right = rotateBST(first.right, second.right, values);
	    	}
	    	
	    	return first;
	    }
		
		//ZAG
		static BST rotateLeft(BST Q)
	    	{
			BST P = Q.left;
			Q.left = P.right;
			P.right = Q;
			return P;
		}
	    
		//Zig
	    	static BST rotateRight(BST P)
	    	{
			BST Q = P.right;
			P.right = Q.left;
			Q.left = P;
			return Q;
		}
	    
	    //Suggested function in BST.java
	    public static BST findNode(BST node, Integer keyValue)
	    {
	    	// A useful function...finds the node with a key value.
	    	if(node.key == keyValue)
	    	{
	    		return node;
	    	}
	    	if(node.left == null && node.right == null)
	    	{
	    		return null; 	
	    	}
	    	else if(node.key > keyValue && node.left != null)
	    	{
	    		return findNode(node.left, keyValue);
	    	}
	    	else if(node.key < keyValue && node.right != null)
	    	{
	    		return findNode(node.right, keyValue);
	    	}
	    	
	    	return node;
	    }
	    
	    //Suggested function in BST.java
	    public static BST findParentofNode(BST node,BST parent)
	    {
	    	//Another useful function
	    	if(parent == node)
	    	{
	    		return parent;
	    	}
	    	if(parent.left == node || parent.right == node)
	    	{
	    		return parent;
	    	}
	    	if(parent.left == null && parent.right == null)
	    	{
	    		return null;
	    	}
	    	else if(node.key > parent.key && parent.right != null)
	    	{
	    		return findParentofNode(node, parent.right);
	    	}
	    	else if(node.key < parent.key && parent.left != null)
	    	{
	    		return findParentofNode(node, parent.left);
	    	}
	    	return null;
	    }
	}
	
	public static void main(String[] args) throws IOException 
	{ 
		BufferedReader tree1 = null;
		BufferedReader tree2 = null;
		ArrayList<Integer> T1num = new ArrayList<Integer>();
	 	ArrayList<Integer> T2num = new ArrayList<Integer>();

		try 
		{
			//Try to read in values from two text files.
			tree1 = new BufferedReader(new FileReader("T1.txt"));
			tree2 = new BufferedReader(new FileReader("T2.txt"));
			String l;

			while ((l = tree1.readLine()) != null) 
			{
			//Add the numbers from the first text file into T1num
			T1num.add(Integer.parseInt(l));
			}
			while ((l = tree2.readLine()) != null) 
			{
			//And add the numbers from the second text file into T2num
			T2num.add(Integer.parseInt(l));
			}
		} 
		finally 
		{
			//Close the files
		    	if (tree1 != null) 
		    	{
				tree1.close();
		    	}
		    	if (tree2 != null) 
		    	{
				tree2.close();
		    	}
		}
		
		//Make two BSTs!
		BST T1 = new BSTrotations(T1num.get(0));
	 	BST T2 = new BSTrotations(T2num.get(0));
	 	
	 	//Insert the numbers into the BST
	 	for(int ii=1;ii<T1num.size();ii++)
	 	{
	 		T1.insert(T1num.get(ii));
	 		T2.insert(T2num.get(ii));
	 	}
	 	
	 	//Print out our resulting BSTs 
	    	String value = T1.print();
	    	System.out.printf("Orig First\n%s\n", value);
	    	String value1 = T2.print();
		System.out.printf("Orig Second\n%s\n", value1);

		//Transform our first BST into the second
	    	ArrayList<Rotation> values = BSTrotations.transform(T1, T2);
	    	//Print out the needed Zigs and Zags
	    	for(int ii=0;ii<values.size();ii++)
	    	{	
	    		value = values.get(ii).print();
	    		System.out.printf("%s\n",value);
	    	}
	}
}

