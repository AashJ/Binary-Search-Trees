/**
 * A MapEntry class to be used for the TreeMap class. Has
 * getter and setter methods for the keys and values.
 * 
 * @author Aashish
 *
 * @param <K> Binds this class to operate on K objects
 * @param <V> Binds this class to operate on V objects
 */
public class MapEntry<K, V> implements Comparable
{
	//the instance field for key
	private K key;
	//the instance field for value 
	private V value;
	
	/**
	 * A constructor for a mapEntry
	 * @param k the value to set key to
	 * @param v the value to set value to
	 */
	public MapEntry (K k, V v)
	{
		key = k;
		value = v;
	}
	
	/**
	 * Getter method for the key
	 * 
	 * @return Returns the key of this object
	 */
	public K getKey()
	{
		return key;
	}
	
	/**
	 * Getter method for the value
	 * 
	 * @return Returns the value of this object
	 */
	public V getValue()
	{
		return value;
	}
	
	/**
	 * Sets this object's key
	 * 
	 * @param k The new key of this objects
	 */
	public void setKey(K k)
	{
		key = k;
	}
	
	/**
	 * Sets this objects value to v (parameter).
	 * 
	 * @param v the new value to be set
	 */
	public void setValue(V v)
	{
		value = v;
	}

	@Override
	/**
	 * The override of the compareTo method so that
	 * it compares the the keys of this object.
	 */
	public int compareTo(Object o) 
	{
		MapEntry compareValue = (MapEntry) o;
		return ((Comparable) getKey()).compareTo(compareValue.getKey());
	}
	
	
}
