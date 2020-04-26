package model.data_structures;

import java.util.Iterator;

import javax.swing.plaf.basic.BasicTreeUI.NodeDimensionsHandler;

/**
 *	Tomado de Algorithms 4th edition 
 */
public class RedBlackBST<K extends Comparable<K>,V> implements IRedBlackBST<K, V> 
{
	private class NodoBST_RB implements Comparable<NodoBST_RB>
	{		
		/**
		 * Identificador del nodo 
		 */
		private K key;
		
	    /**
	     * Elemento que almacena el nodo
	     */
		private V value;
		
		/**
		 * Hijo izquierdo del nodo
		 */
		private NodoBST_RB izq;

		/**
		 * Hijo derecho del nodo
		 */
		private NodoBST_RB der;
		
		/**
		 * Color del nodo
		 */
		private boolean color;
		
		/**
		 * El tamano del subarbol
		 */
		private int subtree;
		
		
		public NodoBST_RB(K key, V value) 
		{
			this.key = key;
			this.value = value;
			
			izq = null;
			der = null;
			color = RED;
			subtree = 1;
		}


		@Override
		public int compareTo(RedBlackBST<K, V>.NodoBST_RB arg0)
		{
			return 0;
		}
		
	}
	
	/**
	 * Constante que representa el color Rojo del nodo
	 */
	public static final boolean RED = true;
	
	/**
	 * Constatnte que representa el color Negro del nodo
	 */
	public static final boolean BLACK = false;	
	
	/**
	 * Raiz del arbol
	 */
	private NodoBST_RB root;
	
	/**
	 * Construlle un arbol vacio
	 */
	public RedBlackBST() 
	{
		root = null;
	}

	@Override
	public int size() 
	{		
		return size(root);
	}
	
	/**
	 * Retorna el tamano arbol generado del nodo que se le pasa por parametro
	 * @param node Nodo a mirar su tamano
	 * @return Tamano del arbol
	 */
	private int size(NodoBST_RB node) 
	{	
		if(node == null)
			return 0;
		return node.subtree;
	}

	@Override
	public boolean isEmpty() 
	{		
		return root == null;
	}

	@Override
	public V get(K key) 
	{
		if(root == null)
			return null;
		return get(root, key);
	}

	/**
	 * Retorna el valor asociado a la llave en el nodo que se le pasa por parametro
	 * @param node Arbol en donde se va a buscar la llave
	 * @param key Llave a buscar
	 * @return Valor asociado a la llave
	 */
	private V get(NodoBST_RB node, K key) 
	{
		while(node != null)
		{
			int cmp = key.compareTo(node.key);
			if(cmp < 0)
				node = node.izq;
			else if (cmp > 0)
				node = node.der;
			else 
				return node.value;
		}
		return null;
	}

	@Override
	public int getHeight(K key) 
	{
		int height = 0;
		NodoBST_RB node = root;
		
		while(node != null)
		{
			int cmp = key.compareTo(node.key);
			if(cmp < 0)
			{
				node = node.izq;
				height++;
			}
			else if (cmp > 0)
			{
				node = node.der;
				height++;
			}
			else
				return height;
		}
		return -1;
	}

	@Override
	public boolean contains(K key) 
	{
		return get(key) != null;
	}

	@Override
	public void putInSet(K key, V value) 
	{
		root = put(root, key, value);
		root.color = BLACK;
	}
	
	/**
	 * 
	 * @param nodo
	 * @param key
	 * @param value
	 * @return
	 */
	private NodoBST_RB put(NodoBST_RB nodo, K key, V value)
	{
		if(nodo == null)
		{
			return new NodoBST_RB(key, value);
		}
		
		int comparacion = key.compareTo(nodo.key);
		
		if(comparacion < 0)		
			nodo.izq = put(nodo.izq,key,value);
		
		else if(comparacion > 0)			
			nodo.der = put(nodo.der,key,value);				
		
		else
			nodo.value = value;
		
		
		if(isRed(nodo.der) && !isRed(nodo.izq))
		{
			nodo = rotateLeft(nodo);
		}
		if(isRed(nodo.izq) && isRed(nodo.izq.izq))
		{
			nodo = rotateRight(nodo);
		}
		if(isRed(nodo.izq) && isRed(nodo.der))
		{
			flipColors(nodo);
		}
		
		nodo.subtree = size(nodo.der) + size(nodo.izq) + 1;
		
		return nodo;		
	}
	
	/**
	 * Invierte los colores del nodo y de sus dos hijos
	 * @param nodo Nodo a invertir
	 */
	private void flipColors(RedBlackBST<K, V>.NodoBST_RB nodo) 
	{
		nodo.color = !nodo.color;
		nodo.izq.color = !nodo.izq.color;
		nodo.der.color = !nodo.der.color;
	}

	/**
	 * 
	 * @param nodo
	 * @return
	 */
	private boolean isRed(NodoBST_RB nodo)
	{
		if(nodo == null)		
			return false;
		
		return nodo.color;
	}
	
	@Override
	public K min() 
	{
		if(isEmpty())
		{
			return null;
		}
		
		NodoBST_RB node = root; 

		while(node.izq != null)
		{
			node = node.izq;
		}				
		
		return node.key;
	}

	@Override
	public K max() 
	{
		if(isEmpty())
		{
			return null;
		}
		
		NodoBST_RB node = root;

		while(node.der != null)
		{
			node = node.der;
		}				
		
		return node.key;
	}

	@Override
	public boolean check() 
	{
		return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isBST()
	{
		return isBST(root, null, null);
	}
	
	/**
	 * 
	 * @param nodo
	 * @param keyMin
	 * @param keyMax
	 * @return
	 */
	private boolean isBST(NodoBST_RB nodo, K keyMin, K keyMax )
	{
		if(nodo==null)
		{
			return true;
		}
		if(keyMin != null && nodo.key.compareTo(keyMin)<=0)
		{
			return false;
		}
		if(keyMax != null && nodo.key.compareTo(keyMax)>=0 )
		{
			return false;
		}
		return isBST(nodo.izq, keyMin, nodo.key) && isBST(nodo.der, nodo.key, keyMax);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isSizeConsistent()
	{
		return isSizeConsistent(root);
	}
	
	/**
	 * 
	 * @param nodo
	 * @return
	 */
	private boolean isSizeConsistent(NodoBST_RB nodo)
	{
		if(nodo==null)
			return true;
		if(nodo.subtree != (size(nodo.izq) + size(nodo.der)+1))
		{
			return false;
		}
		
		return isSizeConsistent(nodo.izq) && isSizeConsistent(nodo.der);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isRankConsistent()
	{
		for (int i = 0; i < size(); i++) 
		{
			if(i!= rank(select(i)))
			{
				return false;
			}
		}
		Iterator<K> it = (IteradorArreglo<K>) keys();
		while(it.hasNext())
		{
			K actual= it.next();
			if(actual.compareTo(select(rank(actual)))!= 0)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isBalanced()
	{
		int blackLinks=0;
		NodoBST_RB nodo= root;
		while(nodo!=null)
		{
			if(!isRed(nodo))
			{
				blackLinks++;
			}
			nodo= nodo.izq;
		}
		return isBalanced(root, blackLinks);
	}
	
	/**
	 * 
	 * @param nodo
	 * @param blackLinks
	 * @return
	 */
	private boolean isBalanced(NodoBST_RB nodo, int blackLinks)
	{
		if(nodo==null)
		{
			return blackLinks==0;
		}
		if(!isRed(nodo))
		{
			blackLinks--;
		}
		return isBalanced(nodo.izq, blackLinks) && isBalanced(nodo.der, blackLinks);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean is23()
	{
		return is23(root);
	}
	
	/**
	 * 
	 * @param nodo
	 * @return
	 */
	private boolean is23(NodoBST_RB nodo)
	{
		if(nodo==null)
			return true;
		if(isRed(nodo.der))
			return false;
		if(nodo!= root && isRed(nodo) && isRed(nodo.izq))
			return false;
		
		return is23(nodo.izq) && is23(nodo.der);
	}
	
	/**
	 * 
	 * @param k
	 * @return
	 */
	public K select(int k)
	{
		NodoBST_RB nodo=select(root, k);
		return nodo.key;
	}
	
	/**
	 * 
	 * @param nodo
	 * @param k
	 * @return
	 */
	private NodoBST_RB select(NodoBST_RB nodo, int k)
	{
		int s = size(nodo.izq);
		if(s>k)
		{
			return select(nodo.izq,k);
		}
		else if(s<k)
		{
			return select(nodo.der,k-s-1);
		}
		else
			return nodo;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public int rank(K key)
	{
		return rank(key,root);
	}
	
	/**
	 * 
	 * @param key
	 * @param nodo
	 * @return
	 */
	private int rank(K key, NodoBST_RB nodo)
	{
		if(nodo==null)
		{
			return 0;
		}
		int c= key.compareTo(nodo.key);
		if(c<0)
		{
			return rank(key,nodo.izq);
		}
		else if(c>0)
		{
			return 1+ size(nodo.izq)+ rank(key,nodo.der);
		}
		else 
			return size(nodo.izq);
	}
	
	@Override
	public Iterator<K> keys() 
	{	
		if (root == null) 
            return null; 
		
		Comparable<K>[] arr = new Comparable[size()];
        
		Stack<NodoBST_RB> s = new Stack<NodoBST_RB>();        
        NodoBST_RB act = root;   
        int i = 0;
        
        while (act != null || s.size() > 0) 
        { 
            while (act !=  null) 
            { 
                s.push(act); 
                act = act.izq; 
            } 
            act = s.pop(); 
            arr[i] = act.key;
            act = act.der; 
            i++;
        } 
        IteradorArreglo<K> it = (IteradorArreglo<K>) new IteradorArreglo<>(arr);
        return it;
	}

	@Override
	public Iterator<V> valuesInRange(K init, K end) 
	{
		IteradorSencillo<V> it = null;
		Queue<V> queue = new Queue<V>();
		valuesInRange(root, queue, init, end);		
		
		it = (IteradorSencillo<V>) queue.iterator();
		return it;		
	}
	  
	/**
	 * 
	 * @param nodo
	 * @param s
	 * @param init
	 * @param end
	 */
	private void valuesInRange(NodoBST_RB nodo, Queue<V> s, K init, K end)
	{
		if(nodo != null)
		{
			int compInit = init.compareTo(nodo.key);
			int compEnd = end.compareTo(nodo.key);
			if(compInit<0)
			{
				valuesInRange(nodo.izq, s, init, end);
			}
			if(compInit<=0 && compEnd>=0)
			{
				s.enqueue(nodo.value);
			}
			if(compEnd>0)
			{
				valuesInRange(nodo.der, s, init, end);
			}
		}
	}

	@Override
	public Iterator<K> keysInRange(K init, K end) 
	{
		IteradorSencillo<K> it= null;
		Queue<K> queue = new Queue<K>();
		keysInRange(root, queue, init, end);
		it = (IteradorSencillo<K>) queue.iterator();
		return it;
	}
	    
	/**
	 * 
	 * @param nodo
	 * @param s
	 * @param init
	 * @param end
	 */
	private void keysInRange(NodoBST_RB nodo, Queue<K> s, K init, K end)
	{
		if(nodo != null)
		{
			int compInit = init.compareTo(nodo.key);
			int compEnd = end.compareTo(nodo.key);
			if(compInit<0)
			{
				keysInRange(nodo.izq, s, init, end);
			}
			if(compInit<=0 && compEnd>=0)
			{
				s.enqueue(nodo.key);
			}
			if(compEnd>0)
			{
				keysInRange(nodo.der, s, init, end);
			}
		}
	}
	
	@Override
	public int height()
	{
		if(root!=null)
		{
			return height(root);	
		}
		else return -1;
	}
	private int height(NodoBST_RB nodo)
	{
		if(nodo.der==null&& nodo.izq==null)
		{
			return 0;        
		}
		else
		{
			int hDer=0;
			int hIzq=0;
			if(nodo.der!=null)
			{
				hDer=height(nodo.der);
			}
			if(nodo.izq!=null)
			{
				hIzq=height(nodo.izq);
			}
			if(hDer>hIzq)
			{
				return hDer+1;
			}
			else
				return hIzq+1;
			
		}
	}
	public double darAlturaPromedio()
	{
		double h=0;
		int cant=0;
		NodoBST_RB nodo=root;
		ArregloDinamico<NodoBST_RB>arr= new ArregloDinamico<>(1);
		if(nodo!=null)
		{
			darAlturaPromedio(nodo,arr); 
		}
		else
			return 0;
		if(arr.darTamano()!=0)
		{
			for (int i = 0; i < arr.darTamano(); i++) 
			{
				NodoBST_RB act= arr.darElemento(i); 
				h+=getHeight(act.key);
				cant++;
			}
		}
		if(cant>0)
		{
			return h/cant;
		}
		else 
			return 0;
		
		
	}
	private void darAlturaPromedio(NodoBST_RB nodo,ArregloDinamico<NodoBST_RB>arr)
	{
		
		if(nodo.der==null && nodo.izq==null)
		{
			arr.agregar(nodo);
		}
		else
		{
			if(nodo.der!=null)
			{
				darAlturaPromedio(nodo.der,arr);
			}
			if(nodo.izq!=null)
			{
				darAlturaPromedio(nodo.izq,arr);
			}
		}
		
	}
	
	/**
	 * 
	 * @param nodo
	 * @return
	 */
	private NodoBST_RB rotateRight(NodoBST_RB nodo)
	{
		NodoBST_RB node = nodo.izq;
		nodo.izq = node.der;
		node.der = nodo;
		node.color = node.der.color;
		node.der.color = RED;
		node.subtree = nodo.subtree;
		nodo.subtree = size(nodo.izq) + size(nodo.der) + 1;
		
		return node;
	}
	
	/**
	 * 
	 * @param nodo
	 * @return
	 */
	private NodoBST_RB rotateLeft(NodoBST_RB nodo)
	{
		NodoBST_RB node = nodo.der;
		nodo.der = node.izq;
		node.izq = nodo;
		node.color = node.izq.color;
		node.izq.color = RED;
		node.subtree = nodo.subtree;
		nodo.subtree = size(nodo.izq) + size(nodo.der) + 1;
		
		return node;
	}

}
