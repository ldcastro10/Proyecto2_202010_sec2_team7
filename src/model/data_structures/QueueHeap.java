package model.data_structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class QueueHeap<E extends Comparable<E>> implements IQueueHeap<E>
{

	/**
	 * Atributo que indica la cantidad de elementos que han sido almacenados en la cola.
	 */
	private int cantidadElementos;

	/**
	 * Primer nodo de la cola.
	 */
	private Nodo<E> primerNodo;

	/**
	 * Ultimo nodo de la cola.
	 */
	private Nodo<E> ultimoNodo;


	/**
	 * Construye la cola vacia.
	 * <b>post: </b> Se ha inicializado el primer nodo en null
	 */
	public QueueHeap() 
	{
		primerNodo = null;
		ultimoNodo = null;
	}

	/**
	 * Se construye una nueva cola cuyo primer nodo  guardara al elemento que llega por parametro. Actualiza el numero de elementos.
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public QueueHeap(E nPrimero) throws NullPointerException
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

	@SuppressWarnings("unchecked")
	public Object[] toArray() 
	{
		Object[] arreglo = new Object[size()];
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

	/**
	 * Devuelve el nodo de la posicion dada
	 * @param pos la posicion  buscada
	 * @return el nodo en la posicion dada 
	 * @throws IndexOutOfBoundsException si index < 0 o index >= size()
	 */
	public Nodo<E> darNodo(int index)
	{
		if(index < 0 || index > cantidadElementos)
		{
			throw new IndexOutOfBoundsException("Se esta pidiendo el indice: " + index + " y el tamano de la lista es de " + cantidadElementos);
		}

		Nodo<E> actual = primerNodo;
		int posActual = 0;
		while(actual != null && posActual < index)
		{
			actual = actual.darSiguiente();
			posActual ++;
		}

		return actual;
	}

	public void clear() 
	{
		primerNodo = null;
		ultimoNodo = null;
		cantidadElementos = 0;
	}


	@Override
	public boolean enqueue(E elemento) 
	{
		Nodo<E> nodo = new Nodo<E>(elemento);
		
		if(primerNodo != null)
		{
			Nodo<E> anterior = null;
			Nodo<E> actual = primerNodo;
			Comparable<E> elem = actual.darElemento();
			int comp=elem.compareTo(elemento);
			
			while(actual != null && comp>= 0)
			{
				anterior = actual;
				actual = actual.darSiguiente();				
				
				if(actual != null) {
				elem=actual.darElemento();
				comp=elem.compareTo(elemento);
				}
			}
			
			if(actual == null)
			{
				anterior.cambiarSiguiente(nodo);
				ultimoNodo = nodo;
			}
			else if(anterior == null)
			{
				primerNodo = nodo;
				primerNodo.cambiarSiguiente(actual);
			}
			else
			{
				anterior.cambiarSiguiente(nodo);
				nodo.cambiarSiguiente(actual);
			}
			
		}
		else
		{
			primerNodo = nodo;
			ultimoNodo = nodo;			
		}
		cantidadElementos++;
		return true;

	}
	
	@Override
	public boolean enqueue(E elemento, Comparator<E> comparator) 
	{
		Nodo<E> nodo = new Nodo<E>(elemento);
		
		if(primerNodo != null)
		{
			Nodo<E> anterior = null;
			Nodo<E> actual = primerNodo;
			Comparable<E> elem = actual.darElemento();
			int comp = comparator.compare((E) elem, elemento);
			
			while(actual != null && comp>= 0)
			{
				anterior = actual;
				actual = actual.darSiguiente();				
				
				if(actual != null) {
				elem=actual.darElemento();
				comp=comparator.compare((E) elem, elemento);
				}
			}
			
			if(actual == null)
			{
				anterior.cambiarSiguiente(nodo);
				ultimoNodo = nodo;
			}
			else if(anterior == null)
			{
				primerNodo = nodo;
				primerNodo.cambiarSiguiente(actual);
			}
			else
			{
				anterior.cambiarSiguiente(nodo);
				nodo.cambiarSiguiente(actual);
			}
			
		}
		else
		{
			primerNodo = nodo;
			ultimoNodo = nodo;			
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
	public E dequeueMax() 
	{
		//TODO
		if(primerNodo != null)
		{
			Nodo<E> actual = primerNodo;
			Nodo<E> siguiente = actual.darSiguiente();			
			primerNodo = siguiente;
			ultimoNodo = siguiente != null ? ultimoNodo:null;
			cantidadElementos--;
			return actual.darElemento();
		}
		return null;
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
	public List<E> subList(int inicio, int fin) 
	{
		ArrayList<E> list = new ArrayList<>();
		if(primerNodo != null)
		{
			if(fin <= cantidadElementos && inicio >= 0)
			{
				int pos = 0;
				Nodo<E> actual = primerNodo;
				boolean add = false;					

				while(inicio < fin)
				{
					if(pos == inicio)
						add = true;
					if(add)
					{		
						list.add(actual.darElemento());
						inicio++;
					}
					else
					{
						actual = actual.darSiguiente();
						pos++;
					}
				}
			}
			else
				throw new IndexOutOfBoundsException();					
		}
		else
			throw new IndexOutOfBoundsException();		

		return list;
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
	public E getMax() 
	{		
		return primerNodo != null ? primerNodo.darElemento(): null;
	}


}
