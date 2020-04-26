package model.data_structures;

import java.util.Iterator;

public class ListaEncadenada<E extends Comparable<E>> implements IListaEncadenada<E>
{

	/**
	 * Atributo que indica la cantidad de elementos que han sido almacenados en la lista.
	 */
	private int cantidadElementos;

	/**
	 * Primer nodo de la lista.
	 */
	private Nodo<E> primerNodo;

	/**
	 * Ultimo nodo de la lista.
	 */
	private Nodo<E> ultimoNodo;


	/**
	 * Construye la lista vacia.
	 * <b>post: </b> Se ha inicializado el primer nodo en null
	 */
	public ListaEncadenada() 
	{
		primerNodo = null;
		ultimoNodo = null;
	}

	/**
	 * Se construye una nueva lista cuyo primer nodo  guardara al elemento que llega por parametro. Actualiza el numero de elementos.
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public ListaEncadenada(E nPrimero) throws NullPointerException
	{
		if(nPrimero != null)
		{
			Nodo<E> temp = new Nodo<E>(nPrimero); 
			primerNodo = temp;
			ultimoNodo = temp;
			cantidadElementos++;
		}
		else
			throw new NullPointerException();
	}

	public int size() 
	{
		return cantidadElementos;
	}

	public E set(int index, E element) throws IndexOutOfBoundsException 
	{
		E elemento = null;
		if(primerNodo != null)
		{		
			int posicion = 0;
			Nodo<E> actual = primerNodo;
			elemento = primerNodo.darElemento();
			while(index != posicion)
			{
				actual = actual.darSiguiente();
				if(actual != null)
				{
					elemento = actual.darElemento();
					posicion++;
				}
				else
					throw new IndexOutOfBoundsException();
			}

			actual.cambiarElemento(element);
		}
		else
			throw new IndexOutOfBoundsException();
		return elemento;
	}

	public Iterator<E> iterator() 
	{
		return new IteradorSencillo<E>(primerNodo);
	}

	public boolean isEmpty() 
	{
		if(primerNodo == null)
			return true;
		else
			return false;
	}

	public int indexOf(Object o) 
	{
		int index = -1;
		if(primerNodo != null)
		{
			Nodo<E> actual = primerNodo;
			E elemento = primerNodo.darElemento();
			index++;
			while(!o.equals(elemento))
			{
				actual = actual.darSiguiente();
				if(actual != null)
				{
					elemento = actual.darElemento();
					index++;	
				}
				else
					return -1;
			}
		}

		return index;
	}

	public E get(int index) throws IndexOutOfBoundsException
	{
		E elemento = null;
		if(primerNodo != null)
		{		
			int posicion = 0;
			Nodo<E> actual = primerNodo;
			elemento = primerNodo.darElemento();
			while(index != posicion)
			{
				actual = actual.darSiguiente();
				if(actual != null)
				{
					elemento = actual.darElemento();
					posicion++;
				}
				else
					throw new IndexOutOfBoundsException();
			}
		}
		else
			throw new IndexOutOfBoundsException();
		return elemento;
	}

	public void clear() 
	{
		primerNodo = null;
		ultimoNodo = null;
		cantidadElementos = 0;
	}


	@Override
	public boolean add(E elemento) 
	{
		Nodo<E> nuevo = new Nodo<E>(elemento);

		if(ultimoNodo != null )
		{
			ultimoNodo.cambiarSiguiente(nuevo);
			ultimoNodo = nuevo;
		}
		else
		{
			Nodo<E> temp = new Nodo<E>(elemento); 
			primerNodo = temp;
			ultimoNodo = temp;
		}
		cantidadElementos++;

		return true;

	}

	@Override
	public void add(int index, E elemento) 
	{
		if(primerNodo != null)
		{
			int indice = 0;
			Nodo<E> anterior = null;
			Nodo<E> actual = primerNodo;
			while(index != indice && actual != null)
			{
				anterior = actual;
				actual = actual.darSiguiente();
				indice++;
			}
			if(indice == index)
			{
				Nodo<E> nuevo = new Nodo<E>(elemento);
				if(anterior != null)
				{
					anterior.cambiarSiguiente(nuevo);
					nuevo.cambiarSiguiente(actual);
					if(actual == null)
						ultimoNodo = nuevo;
					cantidadElementos++;
				}
				else
				{
					primerNodo = nuevo;
					ultimoNodo = actual;
					nuevo.cambiarSiguiente(actual);
					cantidadElementos++;
				}
			}
			else
				throw new IndexOutOfBoundsException();
		}
		else if(index == 0)
		{
			primerNodo = new Nodo<E>(elemento);
			cantidadElementos++;
		}
		else
			throw new IndexOutOfBoundsException();

	}

	@Override
	public boolean remove(Object objeto) 
	{
		if(primerNodo != null)
		{
			Nodo<E> anterior = null;
			Nodo<E> actual = primerNodo;
			while(actual != null && !objeto.equals(actual.darElemento()))
			{
				anterior = actual;
				actual = actual.darSiguiente();				
			}
			if(actual == null)
				return false;
			else if(anterior != null)
			{
				anterior.cambiarSiguiente(actual.darSiguiente());
				ultimoNodo = anterior;
			}
			else
			{
				Nodo<E> siguiente = actual.darSiguiente();
				primerNodo = siguiente;
				if(siguiente == null)
					ultimoNodo = null;
			}
			cantidadElementos--;
			return true;
		}
		return false;
	}

	@Override
	public E remove(int pos) 
	{
		E remove = null;
		if(primerNodo != null)
		{
			int indice = 0;
			Nodo<E> anterior = null;
			Nodo<E> actual = primerNodo;
			while(pos != indice && actual != null)
			{
				anterior = actual;
				actual = actual.darSiguiente();
				indice++;
			}
			if(actual != null)
			{
				remove = actual.darElemento();
				if(actual.darSiguiente() != null && anterior != null)
					anterior.cambiarSiguiente(actual.darSiguiente());

				else if(anterior == null)
				{
					primerNodo = actual.darSiguiente();
					if(primerNodo == null)
						ultimoNodo = null;					
				}

				else
				{
					anterior.cambiarSiguiente(null);	
					ultimoNodo = anterior;
				}
				cantidadElementos--;
			}
			else
				throw new IndexOutOfBoundsException();
		}
		else
			throw new IndexOutOfBoundsException();
		return remove;
	}

	@Override
	public boolean contains(Object o) 
	{
		if(primerNodo != null)
		{
			Nodo<E> actual = primerNodo;
			E elemento = primerNodo.darElemento();
			while(!o.equals(elemento))
			{
				actual = actual.darSiguiente();
				if(actual != null)
					elemento = actual.darElemento();				
				else
					return false;
			}
			return true;			
		}
		return false;
	}

	@Override
	public Comparable<E>[] toArray() 
	{
		Comparable<E>[] arreglo = new Comparable[size()];
		Nodo<E> actual = primerNodo;
		int pos = 0;
		while(actual != null)
		{
			arreglo[pos] = actual.darElemento();
			actual = actual.darSiguiente();
			pos ++;
		}
		return arreglo;
	}


}
