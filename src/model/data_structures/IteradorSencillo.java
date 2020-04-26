package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que representa el iterador sencillo (solo avanza hacia adelante).
 * @param <E> Tipo de informacion que almacena el iterador.
 */
public class IteradorSencillo<E> implements Iterator<E>
{

	
	/**
	 * El nodo donde se encuentra el iterador.
	 */
	private Nodo<E> actual;

	/**
	 * Constructor del iterador, inicia con el que llega como parametro.
	 * @param primerNodo Nodo desde el que se iniciara a recorrer.
	 */
	public IteradorSencillo(Nodo<E> primerNodo) 
	{
		actual = primerNodo;
	}
	
	@Override
	public boolean hasNext() 
	{
		return actual != null;
	}

	@Override
	public E next() 
	{
		if(actual == null)
			throw new NoSuchElementException("Se ha alcanzado el final de la lista");
		E valor = actual.darElemento();
		actual = actual.darSiguiente();
		return valor;
	}

}
