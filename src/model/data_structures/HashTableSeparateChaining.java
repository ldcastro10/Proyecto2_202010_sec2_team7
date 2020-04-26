package model.data_structures;

import java.util.Iterator;

public class HashTableSeparateChaining<K extends Comparable<K>,V extends Comparable<V>> implements IHashTableSeparateChaining<K, V>
{
	/**
	 * 
	 */
	private ListaHash<K,V>[] listas;
	
	/**
	 * 
	 */
	private int numeroParejas;
	
	/**
	 * 
	 */
	private int size;
	
	/**
	 * 
	 * @param s
	 */
	public HashTableSeparateChaining(int s)
	{
		size = s;
		numeroParejas = 0;
		listas= new ListaHash[s];
		for (int i = 0; i < size; i++) 
		{
			listas[i]= new ListaHash<>();
		}
	}
		
	@Override
	public int size() 
	{		
		return numeroParejas;
	}
	
	@Override
	public int sizeArr()
	{
		return size;
	}
	
	@Override
	public double factorCarga()
	{
		return (numeroParejas/ new Double( size ));
	}

	@Override
	public int hash(K k) 
	{		
		return (k.hashCode()& 0x7fffffff)%size;
	}

	@Override
	public boolean isEmpty() 
	{
		boolean empty = true;
		for (ListaHash<K, V> listaEncadenada : listas) 		
			if(listaEncadenada != null && listaEncadenada.size() != 0)
			{
				empty = false;
				break;
			}				
		return empty;
	}
	@Override
	public void clean()
	{
		numeroParejas = 0;
		listas= new ListaHash[size];
		for (int i = 0; i < size; i++) 
		{
			listas[i]= new ListaHash<>();
		}
	}

	@Override
	public boolean contains(K key)
	{		
		return getSet(key) != null;
	}

	@Override
	public Iterator<V> getSet(K key) 
	{
		ArregloDinamico<V> values = null;
		IteradorArreglo<V> it = null;
		int i= hash(key);
		boolean existe=false;
		ListaHash<K, V> lista=listas[i];
		for (int j = 0; j < lista.size() && !existe; j++) 
		{
			NodoHash<K, V> nodo = lista.getNodo(j); 
			if((nodo.darKey().compareTo(key)) == 0)
			{
				values = nodo.darValues();
				it = (IteradorArreglo<V>) values.iterator();
				existe = true;
			}
		}		
		return it;
	}

	@Override
	public void putInSet(K key, V value) 
	{
		int i = hash(key);
		if((numeroParejas/new Double(size)) >= 5.0)		
			resize(size*2);
		
		if(!contains(key))
		{
			listas[i].add(key, value);			
			numeroParejas++;
		}
		else
		{
			ListaHash<K, V> lista= listas[i];
			for (int j = 0; j < lista.size(); j++) 
			{
				NodoHash<K, V> nodo = lista.getNodo(j);
				if((nodo.darKey().compareTo(key)) == 0)
					nodo.addValue(value);
			}
		}		
		
	}

	@Override
	public Iterator<V> deleteSet(K key) 
	{
		int i = hash(key);
		IteradorArreglo<V> it = null;
		it = (IteradorArreglo<V>) listas[i].remove(key);
		numeroParejas--;
		return it;		
	}

	@Override
	public Iterator<K> keys() 
	{
		ArregloDinamico<K> lista = new ArregloDinamico<>(1);
		
		for (ListaHash<K, V> listaEncadenada : listas)					
			for (int i = 0; i < listaEncadenada.size(); i++)			
				lista.agregar(listaEncadenada.getNodo(i).darKey());		
		
		IteradorArreglo<K> it = (IteradorArreglo<K>) lista.iterator();
		return it;
	}
	private void resize(int size)
	{
		
		HashTableSeparateChaining<K, V> temp= new HashTableSeparateChaining<>(size);
		for (int i = 0; i <this.size ; i++) 
		{
			for (int j = 0; j < listas[i].size(); j++) 
			{
				NodoHash<K, V> nodo = listas[i].getNodo(j);
				K key = nodo.darKey();
				ArregloDinamico<V> values = nodo.darValues();
				for (int k = 0; k < values.darTamano(); k++) 
				{
					V value = values.darElemento(k);
					temp.putInSet(key, value);
				}
			} 
		}
		this.size=temp.size;
		this.numeroParejas=temp.numeroParejas;
		this.listas=temp.listas;
		
		
		
	}

}