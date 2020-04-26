package model.data_structures;

import java.util.Iterator;
import java.util.List;

public interface IQueue<E> {

	/**
	 *
	 * @return
	 */
	//TODO
	public int size();
	
	/**
	 * 
	 * @param index
	 * @param element
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	//TODO
	public E set(int index, E element) throws IndexOutOfBoundsException;
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	//TODO
	public boolean contains(Object o);
	
	/**
	 * 
	 * @return
	 */
	//TODO
	public Iterator<E> iterator();
	
	/**
	 * 
	 * @return
	 */
	//TODO
	public boolean isEmpty();
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	//TODO
	public int indexOf(Object o);
	
	/**
	 * 
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	//TODO
	public E get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * 
	 */
	//TODO
	public void clear();
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	//TODO
	public boolean enqueue(E e);
	
	/**
	 * 
	 * @param index
	 * @param element
	 */
	//TODO
	public void add(int index, E element);
	
	/**
	 * 
	 * @return
	 */
	//TODO
	public E dequeue();
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	//TODO
	public E remove(int pos);
	
	/**
	 * 	
	 * @param inicio
	 * @param fin
	 * @return
	 */
	//TODO
	public List<E> subList(int inicio, int fin);	
}
