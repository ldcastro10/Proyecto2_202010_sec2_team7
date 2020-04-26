package model.data_structures;

import java.util.Iterator;

public interface IListaEncadenada<E> {

	/**
	 * Consultar la cantidad de elementos de la lista
	 * @return la cantidad de elementos de la lista
	 */
	public int size();
	
	/**
	 * En la posicion que entra por parametro se agrega el elemento que entra por parametro
	 * @param index indice de la lista donde va el elemento
	 * @param element elemento que se va a agregar
	 * @return el elemento que estaba en esa posicion
	 * @throws IndexOutOfBoundsException
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException;
	
	/**
	 * Verifica si contiene el objeto que entra por parametro
	 * @param o objeto a buscar
	 * @return True en caso de estar contenido, false de lo contrario
	 */
	public boolean contains(Object o);
	
	/**
	 * Iterador sencillo
	 * @return iterador
	 */
	public Iterator<E> iterator();
	
	/**
	 * Verfica si la lista esta vacia
	 * @return True si la lista esta vacia, false de lo contrario
	 */
	public boolean isEmpty();
	
	/**
	 * Consulta la posicion del objeto que entra por parametro
	 * @param o objeto a buscar
	 * @return la posicion del objeto
	 */
	public int indexOf(Object o);
	
	/**
	 * Consulta el elemento en una posicion que entra por parametro
	 * @param index indice del elemento	
	 * @return el elemento en esa posicion
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Vacia la lista
	 */
	public void clear();
	
	/**
	 * Adicionar un elemento en la ultima posicion
	 * @param e elemento a adicionar
	 * @return true en caso de agregarlo, false de lo contrario
	 */
	public boolean add(E e);
	
	/**
	 * Adicionar en un posicion que entra por parametro	
	 * @param index indice donde se debe adicionar
	 * @param element elemento a adicionar
	 */
	public void add(int index, E element);
	
	/**
	 * Eliminar el objeto
	 * @param o objeto a eliminar
	 * @return true si lo elimino, false de lo contrario
	 */
	public boolean remove(Object o);
	
	/**
	 * Elimina el objeto en una posicion
	 * @param pos posicion en la lista
	 * @return el elemento eliminado 
	 */
	public E remove(int pos);
	
	/**
	 * Retorna un arreglo con todos los tados de la lista
	 * @return arreglo de tipo E
	 */
	public Object[] toArray();
}
