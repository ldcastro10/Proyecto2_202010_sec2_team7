package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Stack<E> implements IStack<E>
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
	 * Construye la pila vacia.
	 * <b>post: </b> Se ha inicializado el primer nodo en null
	 */
	public Stack() 
	{
		primerNodo = null;
		
	}

	/**
	 * Se construye una nueva pila cuyo primer nodo  guardara al elemento que llega por parametro. Actualiza el numero de elementos.
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public Stack(E nPrimero) throws NullPointerException
	{
		if(nPrimero != null)
		{
			Nodo<E> temp = new Nodo<E>(nPrimero); 
			primerNodo = temp;
			
			cantidadElementos++;
		}
		else
			throw new NullPointerException();
	}

	@SuppressWarnings("unchecked")
	public Object[] toArray() 
	{
		E[] arreglo = (E[]) new Object[size()];
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
		
		cantidadElementos = 0;
	}
	
	@Override
	public E remove(int pos) 
	{
		Nodo<E> actual= primerNodo;
		Nodo<E>anterior= null;
		int index=0;
		while(actual!=null&&index!=pos)
		{
			anterior=actual;
			actual=actual.darSiguiente();
			index++;
		}
		if(actual==null || cantidadElementos==0)
		{
			throw new IndexOutOfBoundsException();
		}

		if(pos!=0)
		{
			anterior.cambiarSiguiente(actual.darSiguiente());



		}
		else if(pos==0)
		{
			if(cantidadElementos==1)
			{
				primerNodo=null;
			}
			else 
				primerNodo=actual.darSiguiente();
		}
		cantidadElementos--;

		return actual.darElemento();	}

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
				boolean anadir = false;					

				while(inicio < fin)
				{
					if(pos == inicio)
						anadir = true;
					if(anadir)
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

	
	public E set(int index, E element) throws IndexOutOfBoundsException 
	{
		Nodo<E> actual= primerNodo;
		E temp= null;
		int pos=0;

		if(index<0|| index>=size())
		{
			throw new IndexOutOfBoundsException();
		}
		while(actual.darSiguiente()!=null && index!=pos)
		{
			pos++;
			actual=actual.darSiguiente();

		}
		if(actual!=null)
		{
			temp=  actual.darElemento();
			actual.cambiarElemento(element);

		}

		return temp;

	}
			
	

	@Override
	public boolean push(E elemento) 
	{
		boolean agrego= false;
		if(primerNodo!=null)
		{
			Nodo<E> top= primerNodo;
			primerNodo= new Nodo<E>(elemento);
			primerNodo.cambiarSiguiente(top);
			cantidadElementos++;
			agrego=true;			
		}
		else
		{
			primerNodo= new Nodo<E>(elemento);
			cantidadElementos++;
			agrego=true;
			
		}
		return agrego;
		
		
		
	}

	@Override
	public E pop() 
	{
		E elim=null;
		if(primerNodo!=null)
		{
			elim= primerNodo.darElemento();
			
			primerNodo=primerNodo.darSiguiente();
			cantidadElementos--;
			
			
		}
		return elim;
	}	
}
