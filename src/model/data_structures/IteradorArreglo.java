package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteradorArreglo<E> implements Iterator<E> 
{

	/**
	 * El arreglo donde se encuentra el iterador.
	 */
	private E[] actual;
	
	/**
	 * La posicion en donde se encuentra el iterador.
	 */
	private int posicion;

	/**
	 * Constructor del iterador, iterador del arrelgo que llega como parametro.
	 * @param arreglo arreglo al que se va a iterar.
	 */
	public IteradorArreglo(E[] arreglo) 
	{
		actual = arreglo;
		posicion = 0;
	}
	
	@Override
	public boolean hasNext() 
	{
		try
		{
			return actual[posicion] != null;
		}
		catch(IndexOutOfBoundsException e)
		{
			return false;
		}
		
	}

	@Override
	public E next() 
	{
		if(actual[posicion] == null)
			throw new NoSuchElementException("Se ha alcanzado el final de la lista");
		E valor = actual[posicion];
		posicion++;
		return valor;
	}
}
