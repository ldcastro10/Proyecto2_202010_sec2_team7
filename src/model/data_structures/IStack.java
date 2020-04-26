package model.data_structures;

import java.util.Iterator;
import java.util.List;

public interface IStack<E> 
{
	
	/**
	 *
	 * @return
	 */
	public int size();
	
	/**
	 * Coloca el elemento en el indice indicado
	 * @param index indice donde colocar al elemento
	 * @param element elemento a cambiar de posicion
	 * @return el elemento ingresado
	 * @throws IndexOutOfBoundsException
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException;
	
	/**
	 * verificar si el objeto esta contenido en la lista
	 * @param o objeto a verificar
	 * @return true si esta contenido false de lo contrario
	 */
	public boolean contains(Object o);
	
	/**
	 * Iterador sencillo
	 * @return iterador sencillo
	 */
	public Iterator<E> iterator();
	
	/**
	 * Verifica si la lista esta vacia
	 * @return true si esta vacia false de lo contrario
	 */
	public boolean isEmpty();
	
	/**
	 * retorna el indice del objeto
	 * @param o objeto a analizar
	 * @return retorna el indice del objeto
	 */
	public int indexOf(Object o);
	
	/**
	 * retorna el objeto en la posicion que entra por parametro
	 * @param index posicion del objeto
	 * @return el objeto en el indice
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * borrar todos los elemetos de la lista
	 */
	public void clear();
	
	
	
	/**
	 * adicionar un elemento a la lista en un indice especifico
	 * @param index indice donde agregar el elmento	
	 * @param element elemento a agregar
	 */
	public void add(int index, E element);
	
	
	
	/**
	 * Remover un elemento en una posicion	
	 * @param pos posicion del elemento a la eliminar
	 * @return elemento eliminado
	 */
	public E remove(int pos);
	
	/**
	 * 	crea una lista dentro de los limites
	 * @param inicio limite inicial
	 * @param fin limite final
	 * @return la lista dentro de los limites qeue entran por parametro
	 */
	public List<E> subList(int inicio, int fin);
	
	
	/**
	 * agregar un elemento al Stack
	 * @param elemento elemento a agregar
	 * @return true si lo agrego false de lo contrario
	 */
	public boolean push(E elemento);
	
	
	/**
	 * eliminar un elemento del Stack
	 * @return elemneto eliminado
	 */
	public E pop();
	
}
