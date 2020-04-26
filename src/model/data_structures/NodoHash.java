package model.data_structures;

/**
 * Representa un nodo dentro de la lista. Este nodo almacena un elemento.
 *
 * @param <K> Tipo de elemento que se esta almacenando dentro de los nodos.
 */
public class NodoHash<K extends Comparable<K>,V extends Comparable<V>>
{
	
	/**
	 * Llave almacenado en el nodo.
	 */
	private K key;
	
	/**
	 * 
	 */
	private ArregloDinamico<V> values;
	
	/**
	 * Siguiente nodo.
	 */
	private NodoHash<K,V> siguiente;
	
	/**
	 * Constructor  del Nodo
	 * @param key Llave
	 * @param value Valor
	 */
	public NodoHash(K key, V value)
	{
		this.key = key;
		values = new ArregloDinamico<>(1);
		addValue(value);
	}
	
	/**
	 * Metodo que  retorna la llave almacenada en el nodo.
	 * @return La llave almacenada en el nodo.
	 */
	public K darKey()
	{
		return key;
	}
	
	/**
	 * Metodo que retorna  los valores almacenados en el nodo
	 * @return Los valores almacenados en el nodo.
	 */
	public ArregloDinamico<V> darValues()
	{
		return values;
	}
	
	/**
	 * Anade un  value al arreglo de values
	 * @param value Dato ha agregar.
	 */
	public void addValue(V value)
	{
		values.agregar(value);
	}
	
	/**
	 * Metodo que  retorna el siguiente nodo.
	 * @return Siguiente nodo
	 */
	public NodoHash<K,V> darSiguiente()
	{
		return siguiente;
	}
	
	/**
	 * Metodo que  cambia el siguiente nodo.
	 * <b>post: </b> Se ha  cambiado el siguiente nodo
	 * @param siguiente El  nuevo siguiente nodo
	 */
	public void cambiarSiguiente(NodoHash<K,V> siguiente)
	{
		this.siguiente = siguiente;
	}

}
