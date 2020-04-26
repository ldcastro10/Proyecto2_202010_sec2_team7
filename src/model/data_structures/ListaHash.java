package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaHash<K extends Comparable<K>,V extends Comparable<V>> implements IListaHash<K, V>
{

	/**
	 * Atributo que indica la cantidad de elementos que han sido almacenados en la lista.
	 */
	private int cantidadElementos;

	/**
	 * Primer nodo de la lista.
	 */
	private NodoHash<K,V> primerNodo;

	/**
	 * Ultimo nodo de la lista.
	 */
	private NodoHash<K,V> ultimoNodo;


	/**
	 * Construye la lista vacia.
	 * <b>post: </b> Se ha inicializado el primer nodo en null
	 */
	public ListaHash() 
	{
		primerNodo = null;
		ultimoNodo = null;
	}

	/**
	 * Se construye una nueva lista cuyo primer nodo  guardara al elemento que llega por parametro. Actualiza el numero de elementos.
	 * @param key el elemento a guardar en el primer nodo
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public ListaHash(K key, V value) throws NullPointerException
	{
		if(key != null)
		{
			NodoHash<K,V> temp = new NodoHash<K,V>(key,value); 
			primerNodo = temp;
			ultimoNodo = temp;
			cantidadElementos++;
		}
		else
			throw new NullPointerException();
	}

	@Override
	public int size() 
	{
		return cantidadElementos;
	}
	
	@Override
	public Iterator<K> iteratorKeys() 
	{
		Comparable<K>[] keys = new Comparable[cantidadElementos];
		
		for(int i = 0; i < cantidadElementos; i++)
		{
			keys[i] = getNodo(i).darKey();
		}
		
		return (Iterator<K>) new IteradorArreglo<>(keys);
	}

	@Override
	public boolean isEmpty() 
	{
		if(primerNodo == null)
			return true;
		else
			return false;
	}
	
	@Override
	public NodoHash<K, V> getNodo(int index) throws IndexOutOfBoundsException
	{
		NodoHash<K, V> nodo = null;
		if(primerNodo != null)
		{		
			int posicion = 0;
			NodoHash<K,V> actual = primerNodo;
			nodo = primerNodo;
			while(index != posicion)
			{
				actual = actual.darSiguiente();
				if(actual != null)
				{
					nodo = actual;
					posicion++;
				}
				else
					throw new IndexOutOfBoundsException();
			}
		}
		else
			throw new IndexOutOfBoundsException();
		return nodo;
	}	
	@Override
	public Iterator<V> remove(K key)
	{		
		
		Iterator<V> it = null;
		if(primerNodo != null)
		{
			NodoHash<K,V> anterior = null;
			NodoHash<K,V> actual = primerNodo;
			while(actual != null && actual.darKey().compareTo(key) != 0)
			{
				anterior = actual;
				actual = actual.darSiguiente();
			}
			if(actual == null)
				throw new NoSuchElementException();
			else if(anterior != null)
			{
				anterior.cambiarSiguiente(actual.darSiguiente());
				ultimoNodo = anterior;
			}
			else
			{
				NodoHash<K,V> siguiente = actual.darSiguiente();
				primerNodo = siguiente;
				if(siguiente == null)
					ultimoNodo = null;
			}
			it = actual.darValues().iterator();
		}
		else 
			throw new NoSuchElementException();
		cantidadElementos--;
		return it;
	}
	
	@Override
	public void clear() 
	{
		primerNodo = null;
		ultimoNodo = null;
		cantidadElementos = 0;
	}


	@Override
	public boolean add(K key, V value) 
	{
		int posicion = contains(key);
		NodoHash<K, V> nodo = null;
		if(posicion != -1)
		{
			nodo = getNodo(posicion);
			nodo.addValue(value);
		}
		else
		{
			nodo = new NodoHash<K, V>(key, value);
			if(ultimoNodo != null)
			{
				ultimoNodo.cambiarSiguiente(nodo);
				ultimoNodo = nodo;
			}
			else
			{
				primerNodo = nodo;
				ultimoNodo = nodo;
			}
				
		}

		cantidadElementos++;

		return true;

	}

	@Override
	public int contains(K key) 
	{
		if(primerNodo != null)
		{
			int posicion = 0;
			NodoHash<K,V> actual = primerNodo;
			K elemento = primerNodo.darKey();
			while(key.compareTo(elemento) != 0)
			{
				actual = actual.darSiguiente();
				if(actual != null)
					elemento = actual.darKey();				
				else
					return -1;
				posicion++;
			}
			return posicion;			
		}
		return -1;
	}

	@Override
	public Comparable<K>[] toArrayKey() 
	{
		Comparable<K>[] arreglo = new Comparable[size()];
		NodoHash<K,V> actual = primerNodo;
		int pos = 0;
		while(actual != null)
		{
			arreglo[pos] = actual.darKey();
			actual = actual.darSiguiente();
			pos ++;
		}
		return arreglo;
	}

}
