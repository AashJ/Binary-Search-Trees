/**
 * The MyTreeSet class creates a set from a BST. This class
 * contains the methods: "size, contains, add, and remove".
 * 
 * @author Aashish Jain
 *
 * @param <E> Binds the MyTreeSet to only operate on objects
 * @version December 15, 2014
 */
public class MyTreeSet<E>
{
	//the root of this tree
	private TreeNode root;
	//the size of this set
	private int size;
	//a display that helps with testing
	private TreeDisplay display;

	/**
	 * A constructor that sets the root to null, the size to 0
	 * and creates a new TreeDisplay.
	 */
	public MyTreeSet()
	{
		root = null;
		size = 0;
		display = new TreeDisplay();

		//wait 1 millisecond when visiting a node
		display.setDelay(1);
	}

	/**
	 * Returns the size of this MyTreeSet.
	 * 
	 * @return Integer value of the size. 
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Checks if this MyTreeSet has an obj. If it does, this method
	 * return true, else returns false. Does so by calling the BSTUtilities
	 * contains method. 
	 * 
	 * @param obj The object to be checked. 
	 * 
	 * @return A boolean based on whether or not the obj is found. 
	 */
	public boolean contains(Object obj)
	{
		return BSTUtilities.contains(root, (Comparable) obj, display);
	}

	/**
	 * Adds an obj to this MyTreeSet. Changes the size. Does so by
	 * calling the BSTUtilities insert method on the root. 
	 * 
	 * @param obj The obj to be added 
	 * 
	 * @return returns false if the obj is already contained
	 * 		or true if the object was successfully inserted.
	 */
	public boolean add(E obj)
	{
		if (contains(obj))
		{
			return false;
		}
		else
		{
			root = BSTUtilities.insert(root, (Comparable) obj, display);
			size++;
			return true;
		}
	}

	/**
	 * Removes the obj from this set by using the BSTUtilities
	 * .delete method. 
	 * 
	 * @param obj The obj to be removed
	 * 
	 * @return True if the object can be successfully removed
	 * returns false otherwise.
	 */
	public boolean remove(Object obj)
	{
		if (contains(obj))
		{
			root = BSTUtilities.delete(root, (Comparable) obj, display);
			size--;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Converts this treeNode into a string. 
	 */
	public String toString()
	{
		return toString(root);
	}

	/**
	 * Converts this treeNode to a string by printing
	 * in an inOrder succession.
	 * 
	 * @param t The tree to print
	 * 
	 * @return returns the string of this root. 
	 */
	private String toString(TreeNode t)
	{
		if (t == null)
			return " ";
		return toString(t.getLeft()) + t.getValue() + "," + toString(t.getRight());
	}
} 