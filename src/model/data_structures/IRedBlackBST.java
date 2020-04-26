package model.data_structures;

import java.util.Iterator;

public interface IRedBlackBST<K extends Comparable<K>, V > 
{
	/**
	 * Retorna el numero de parejas key-value en la tabla de hash.
	 * @return numero de parejas key-value en la tabla de hash.
	 */
	public int size();
	/**
	 * Verifica si la tabla de hash esta vacia		
	 * @return True si la tabla esta vacia, false de lo contrario.
	 */
	public boolean isEmpty();	
	/**
	 * Valor asociado a la llave que entra como parametro
	 * @param key Llave
	 * @return Valor asociado a la llave
	 */
	public V get(K key);
	/**
	 * Retorna la altura del camino para llegar a la llave key
	 * @param key Llave del nodo a buscar
	 * @return  Altura del camino para llegar a la key. –1 si la llave No existe
	 */
	int getHeight(K key);
	/**
	 * Verifica si la llave esta contenida en el arbol
	 * @param key a verificar
	 * @return True si la llave esta en la tabla false de lo contrario
	 */
	public boolean contains(K key);
	/**
	 * Agrega una dupla (K,V) al arbol.
	 * @param key Llave 
	 * @param value Valores asociados a la llave
	 */
	public void putInSet(K key, V value);
	/**
	 * Retorna la llave mas pequena del arbol
	 * @return La llave mas peuena del arbol, null encaso de que el arbol este vacio
	 */
	K min();
	/**
	 * Retorna la llave mas grande del arbol
	 * @return La llave mas grande del arbol, null encaso de que el arbol este vacio
	 */
	K max();
	/**
	 * Verifica que el arbol se encuentre balanceado y ordenado de forma adecuado.
	 * Verifica que el hijo derecho de cualquier nodo no sea un enlace ROJO,
	 * Verifica que los nodos no tenga dos enlaces ROJOS.
	 * Verifica que todos los nodos tenga la misma cantidad de enlaces NEGROS 
	 * @return True en caso de que cumpla todos los requerimeintos False en caso contrario.
	 */
	boolean check();
	/**
	 * Retorna el conjunto de llaves del arbol.
	 * @return Conjunto de llaves del arbol.
	 */
	Iterator<K> keys();
	/**
	 * Retorna un conjunto de valores  que se encuntre en el rango de llaves
	 * @param init Inicio del rango de llaves
	 * @param end Fin del rango de llaves
	 * @return Conjunto de valores que se encuentren en el rango de llaves
	 */
	Iterator<V> valuesInRange(K init, K end);
	/**
	 * Retorna un conjunto de llaves que se encuntre en el rango de llaves
	 * @param init Inicio del rango de llaves
	 * @param end Fin del rango de llaves
	 * @return Conjunto de llaves que se encuentren en el rango de llaves
	 */
	Iterator<K> keysInRange(K init, K end);
	/**
	 * Retorna la altura del alborl
	 * @return Altura del arbol
	 */
	int height();
}
