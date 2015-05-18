/**
 * A Utility class that operates on a binary search tree. This class 
 * contains the methods: "contains, insert, deleteNode, and delete".
 * (Notes on these methods are above the methods). 
 * 
 * @author Aashish Jain
 * @version December 8, 2014
 */
import java.util.*;

public abstract class BSTUtilities
{
	/**
	 * Checks if the given binary search tree contains a value and returns true 
	 * if it does, or false if it does not. A recursive method that runs until
	 * the node is null or the match is found. If it is not found, the method runs
	 * again on the tree to the left or right depending on the value that
	 * is being searched for compared to the current tree node value. 
	 * 
	 * @param t The current treeNode.
	 * @param x The value that is being searched for. 
	 * @param display The display that helps with testing.
	 * 
	 * @return Returns true if the value is found in the tree, and false otherwise.
	 */
	public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
	{
		display.visit(t);
		if (t == null) return false;
		else if (t.getValue().equals(x)) return true;
		else
		{
			if (((Comparable) t.getValue()).compareTo(x) > 0) return contains(t.getLeft(),
					x, display);
			else return contains(t.getRight(), x, display);
		}
	}

	/**
	 * Inserts a given value x into a treeNode t. This method is recursive and runs until
	 * the tree is at the bottom (t is null) or the value already exists. If it is neither, 
	 * it sets t's right or left (depending on the value compared to t's value) to a recursive
	 * call of insert. 
	 * 
	 * @param t The TreeNode that the value is inserted in. 
	 * @param x The value that is inserted
	 * @param display The display that helps with testing
	 * @return A pointer to the resulting tree. 
	 */
	public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
	{
		display.visit(t);
		if (t == null) return new TreeNode(x);
		int compareValue = x.compareTo(t.getValue());
		if (compareValue < 0)
		{
			t.setLeft(insert(t.getLeft(), x, display));
		}
		if (compareValue > 0) 
		{
			t.setRight(insert(t.getRight(), x, display));
		}
		return t;
	}

	/**
	 * Deletes the treeNode t. This method first finds the successor and 
	 * does one of three cases based on that. If the successor is null,
	 * the program returns a pointer to the left subtree. If the successor
	 * is t's right child, the program splices out t, and replaces it with 
	 * the successor (Note: this case only manipulates pointers and values).
	 * 
	 * @param t The TreeNode that is to be deleted. 
	 * @param display The display that helps with testing. 
	 * @return A pointer to the resulting tree. 
	 */
	private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
	{
		/**
		//First find the successor
		TreeNode successor = findSuccessor(t);
		//case 1: successor is null - return pointer to left subtree
		if (successor == null) 
		{
			return null;
		}
		//case 2: successor is t's right child - splice node and delete duplicate
		else
		{
			t.setValue(successor.getValue());
			return deleteNode(successor, display);
		}
		*/
		
		if (t == null) return null;
		if (t.getRight() == null) return t.getLeft();
		else if (t.getRight().getLeft() == null) 
		{
			t.getRight().setLeft(t.getLeft());
			return t.getRight();
		}
		else
		{
			TreeNode temp = t.getRight();
			TreeNode tempParent = t.getRight();
			while (temp.getLeft() != null)
			{
				temp = temp.getLeft();
			}
			while (tempParent.getLeft().getLeft() != null)
			{
				tempParent = tempParent.getLeft();
			}
			t.setValue(temp.getValue());
			tempParent.setLeft(temp.getRight());
		}
		return t;
	}
	
	/**
	 * Finds the inorder successor of a given node. Does so by
	 * getting the leftmost value to the right of the node. 
	 * 
	 * @param t The TreeNode of which the successor is found. 
	 * @return A pointer to the successor treeNode. 
	 */
	public static TreeNode findSuccessor(TreeNode t)
	{
		if (t.getRight() != null)
		{
			t = t.getRight();
		}
		else
		{
			return null;
		}
		while (t.getLeft() != null)
		{
			t = t.getLeft();
		}
		return t;
	}
	
	/** 
	 * Deletes a value (x) from the TreeNode (t). Does so by calling
	 * the deleteNode method on the node that needs deleting. Does so
	 * by navigating the BST based on the comparable value and the current
	 * value. 
	 * 
	 * @param t The root of the treeNode (The value to be deleted
	 * 			is null or in here). 
	 * @param x The value to be deleted. 
	 * @param display A display that helps with testing. 
	 * 
	 * @return A pointer to the resulting treeNode. 
	 */
	public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
	{
		display.visit(t);
		if (t == null) return null;
		else if (t.getValue().equals(x)) return deleteNode(t, display);
		int compareValue = x.compareTo(t.getValue());
		if (compareValue > 0) t.setRight(delete(t.getRight(), x, display));
		else t.setLeft(delete(t.getLeft(), x, display));
		return t;
	}

}