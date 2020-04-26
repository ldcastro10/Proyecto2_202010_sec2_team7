package model.data_structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public interface IQueueHeap<E> {

	/**
	 *
	 * @return
	 */
	public int size();
	
	/**
	 * 
	 * @param index
	 * @param element
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException;
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	public boolean contains(Object o);
	
	/**
	 * 
	 * @return
	 */
	public Iterator<E> iterator();
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	public int indexOf(Object o);
	
	/**
	 * 
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * 
	 */
	public void clear();
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	public boolean enqueue(E e);
	
	/**
	 * 
	 * @return
	 */
	public E getMax();
	
	/**
	 * 
	 * @param index
	 * @param element
	 */
	public void add(int index, E element);
	
	/**
	 * 
	 * @return
	 */
	public E dequeueMax();
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public E remove(int pos);
	
	/**
	 * 	
	 * @param inicio
	 * @param fin
	 * @return
	 */
	public List<E> subList(int inicio, int fin);

	/**
	 * 
	 * @param elemento
	 * @param comparator
	 * @return
	 */
	boolean enqueue(E elemento, Comparator<E> comparator);	
}
