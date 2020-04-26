package model.data_structures;

import java.util.Iterator;

public interface IListaHash<K extends Comparable<K>, V extends Comparable<V>> {

	/**
	 * Consultar la cantidad de elementos de la lista
	 * @return la cantidad de elementos de la lista
	 */
	public int size();
	
	/**
	 * Verifica  si contiene la llave que entra por parametro
	 * @param key Llave a buscar
	 * @return la posicion en la que se encuentra la llave, -1 en caso que no se encuentre 
	 */
	public int contains(K key);
	
	/**
	 * Iterador  sencillo
	 * @return iterador
	 */
	public Iterator<K> iteratorKeys();
	
	/**
	 * Verfica si la  lista esta vacia
	 * @return True si la lista esta vacia, false de lo contrario
	 */
	public boolean isEmpty();
	
	/**
	 * Consulta el nodo en una  posicion que entra por parametro
	 * @param index indice del nodo	
	 * @return El nodo en esa posicion
	 * @throws IndexOutOfBoundsException
	 */
	public NodoHash<K, V> getNodo(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Elimina el nodo con la lleve que llega como parametro y  retorna un iterador con los valores del nodo
	 * @param key La llave del nodo a eliminar
	 * @return Iterador con los valores del nodo
	 */
	public Iterator<V> remove(K key);
	
	/**
	 * Vacia la lista
	 */
	public void clear();
	
	/**
	 * Adicionar un elemento en  la ultima posicion en caso de que la llave  no exista, si existe se le adicionan el value al nodo.
	 * @param key Llave de los values a anadir
	 * @param value Valores a andir a la llave
	 * @return True si se pudo anadir, false en caso contrario.
	 */
	public boolean add(K key, V value);
	
	/**
	 * Retorna un arreglo  con todas las llaves de la lista
	 * @return arreglo de tipo K
	 */
	public Comparable<K>[] toArrayKey();
}
