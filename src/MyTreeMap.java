/**
 * The MyTreeMap class is a map made out of a binary search tree. 
 * @author Aashish Jain
 *
 * @param <K> Binds the TreeMap to have K objects
 * @param <V> Binds the TreeMap to have V objects 
 * 
 * @version
 * 		December 15, 2014
 */
public class MyTreeMap<K,V> 
{
	//the root of this map
	private TreeNode root;
	//the sizd of this map
	private int size;
	//the display of this map
	private TreeDisplay display;
	
	/**
	 * A constructor used to create a tree map. Sets the root to null
	 * the size to 0, and creates a new TreeDisplay. 
	 */
	public MyTreeMap()
	{
		root = null;
		size = 0;
		display = new TreeDisplay();
	}
	
	/**
	 * Returns the size of this map
	 * @return and integer of the size of this map
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * Checks if the key is contained in this map. Uses the
	 * method from BSTUtilities. 
	 * 
	 * @param key The key to be checked for
	 * @return Returns a boolean based on whether the key is contained.
	 */
	public boolean containsKey(Object key)
	{
		return BSTUtilities.contains(root, new MapEntry<K, V>((K) key, null), display);
	}
	
	/**
	 * Puts a value with a key. If the key does not exist, creates a new Map
	 * Entry and puts it in the tree. If the key does exist, replaces the value.
	 * Uses the putHelper method. 
	 * 
	 * @param Key The Key to find
	 * @param Value The value to find
	 * 
	 * @return Returns the value that was replaced or null if the key
	 * did not exist.
	 */
	public V put(K Key, V Value)
	{
		if (containsKey(Key) == false)
		{
			BSTUtilities.insert(root, (Comparable) new TreeNode(new MapEntry<K,V> (Key, Value)), display);
			return null;
		}
		else
		{
			return putHelper(Key, Value, root);
		}
		
	}
	
	/**
	 * A helper method for the put. Finds the key value and
	 * replaces. Used for the put method. 
	 * 
	 * @precondition The key is contained in the tree
	 * 
	 * @param key The key to find
	 * @param value The new value, which replaces the old
	 * value
	 * @param t the treeNode to search
	 * @return Returns the replaced value. 
	 */
	private V putHelper (K key, V value, TreeNode t)
	{
		 int compareValue = (((Comparable) key).compareTo(
				 ((MapEntry) t.getValue()).getKey()));
		 if (compareValue == 0)
		 {
		     V alpha = (V)((MapEntry)t.getValue()).getValue(); 
		     ((MapEntry)t.getValue()).setValue(value);
		     return alpha;
		 }
		 else if (compareValue < 0)
		 {
		     return putHelper(key, value, t.getLeft());
		 }
		 else if (compareValue > 0)
		 {
		     return putHelper(key, value, t.getRight());
		 }
		 return null; 
	}
	
	/**
	 * Gets the value associated with the given key.
	 * Uses the getHelper method to do so. 
	 * 
	 * @param Key The key to find and get the value from
	 * 
	 * @return The value based on the key. 
	 */
	public V get(Object Key)
	{
		if (containsKey((K) Key) == false)
		{
			return null;
		}
		else
		{
			return getHelper((K) Key, root);
		}
	}
	
	/**
	 * Gets the value given a key and a treeNode.
	 * @param key The key to get the value from
	 * @param t The treeNode to search
	 * 
	 * @return returns the value with the treeNode
	 */
	private V getHelper(K key, TreeNode t)
	{
		MapEntry alpha = (MapEntry) t.getValue();
		int compareValue = ((Comparable) key).compareTo(alpha.getKey());
		if (compareValue == 0)
		{
			return (V) ((MapEntry) t.getValue()).getValue();
		}
		else if (compareValue > 0)
		{
			return getHelper(key, t.getRight());
		}
		else if (compareValue < 0)
		{
			return getHelper(key, t.getLeft());
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Removes the object with the given key. Using the
	 * BSTUtilities.delete method.
	 * @param key The key to remove the object from
	 * @return null if the key is not in the tree, or the removed
	 * value if the key is in the tree. 
	 */
	public V remove(Object key)
	{
		if (containsKey((K) key) == false)
		{
			return null;
		}
		else
		{
			MapEntry<K, V> alpha = new MapEntry((K) key, null);
			V answer = (V) get(key);
			root = BSTUtilities.delete(root, (Comparable) alpha, display);
			return answer;
		}
		
	}
	
}
