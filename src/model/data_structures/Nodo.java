package model.data_structures;

/**
 * Representa un nodo dentro de la lista. Este nodo almacena un elemento.
 *
 * @param <E> Tipo de elemento que se esta almacenando dentro de los nodos.
 */
public class Nodo<E>
{
	
	/**
	 * Elemento almacenado en el nodo.
	 */
	private E elemento;
	
	/**
	 * Siguiente nodo.
	 */
	private Nodo<E> siguiente;
	
	/**
	 * Constructor del nodo.
	 * @param elemento El elemento que se almacenara en el nodo. elemento != null
	 */
	public Nodo(E elemento)
	{
		this.elemento = elemento;
	}
	
	/**
	 * Metodo que cambia el siguiente nodo.
	 * <b>post: </b> Se ha cambiado el siguiente nodo
	 * @param siguiente El nuevo siguiente nodo
	 */
	public void cambiarSiguiente(Nodo<E> siguiente)
	{
		this.siguiente = siguiente;
	}
	
	/**
	 * Metodo que retorna el elemento almacenado en el nodo.
	 * @return El elemento almacenado en el nodo.
	 */
	public E darElemento()
	{
		return elemento;
	}
	
	/**
	 * Cambia el elemento almacenado en el nodo.
	 * @param elemento El nuevo elemento que se almacenara en el nodo.
	 */
	public void cambiarElemento(E elemento)
	{
		this.elemento = elemento;
	}
	
	
	/**
	 * Metodo que retorna el siguiente nodo.
	 * @return Siguiente nodo
	 */
	public Nodo<E> darSiguiente()
	{
		return siguiente;
	}

}
