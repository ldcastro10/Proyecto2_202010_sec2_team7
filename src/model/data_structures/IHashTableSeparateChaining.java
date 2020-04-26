package model.data_structures;

import java.util.Iterator;

public interface IHashTableSeparateChaining <K extends Comparable<K>,V extends Comparable<V>> 
{
	/**
	 * 
	 */
	public int size();
	/**
	 * 
	 */
	public int hash(K k);
	/**
	 * 
	 */
	public boolean isEmpty();	
	/**
	 * 
	 */
	public void clean();
	/**
	 * 
	 */
	public boolean contains(K k) throws IllegalAccessException;
	/**
	 * 
	 */
	public Iterator<V> getSet(K k);
	/**
	 * 
	 */
	public void putInSet(K k, V v);
	/**
	 * 
	 */
	public Iterator<V> deleteSet(K k);
	/**
	 * 
	 */
	public Iterator<K> keys();
		
	/**
	 * 
	 */
	int sizeArr();
	/**
	 * 
	 */
	double factorCarga();
	
}
