package model.data_structures;

import java.util.Iterator;

/**
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa *
 */
public class ArregloDinamico<T extends Comparable<T>> implements IArregloDinamico<T>, Comparable<ArregloDinamico<T>> 
{
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = (T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	public void agregar( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = (int) Math.ceil((double) (1.1 * tamanoMax));
			T [ ] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;
	}

	public int darCapacidad() {
		return tamanoMax;
	}

	public int darTamano() {
		return tamanoAct;
	}

	public T darElemento(int i) 
	{								
		return elementos[i];
	}

	public T buscar(T dato) 
	{
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		for(T elemento: elementos)
		{
			if(elemento != null && dato.compareTo(elemento) == 0)
				return elemento;
		}

		return null;
	}

	public T eliminar(T dato) 
	{			
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		T eliminar = null;

		for(int i = 0; i < tamanoMax; i++)
		{
			T elemento = elementos[i];
			if(dato.compareTo(elemento) == 0)
			{
				eliminar = elemento;
				T [] nuevo = (T[]) new Comparable [tamanoMax-1]; 
				for(int j = 0 ; j < tamanoMax-1; j++)
				{
					if(i == j)
						j++;
					nuevo[j] = elementos[j];
				}
				elementos = nuevo;
				tamanoAct--;
				break;
			}
		}

		return eliminar;
	}

	public Iterator<T> iterator() 
	{			
		return new IteradorArreglo<>(elementos);
	}

	public Comparable<T>[] toArray()
	{
		Comparable<T>[] arr = (T[]) new Comparable[tamanoAct];
		for (int i = 0; i < tamanoAct; i++) 
			arr[i] = elementos[i];
		return arr;
	}

	@Override
	public int compareTo(ArregloDinamico<T> a) 
	{
		if(this.tamanoAct > a.tamanoAct)
			return 1;
		else if(this.tamanoAct < a.tamanoAct)
			return -1;
		else
			return 0;
	}

}
