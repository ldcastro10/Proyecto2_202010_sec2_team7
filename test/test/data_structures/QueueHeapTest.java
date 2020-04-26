package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.QueueHeap;

/**
 * Clase de prueba con los metodos necesarios para probar cualquier tipo de lista.
 * Esta clase de pruebas no tiene en cuenta las particularidades de cada tipo de lista.
 *
 */
//TODO
public class QueueHeapTest 
{
	/**
	 * Lista sobre la que se realizaran las pruebas.
	 */
	protected QueueHeap<Integer> lista;
	
	/**
	 * Arreglo con los elementos del escenario 2 (sirve para realizar pruebas exhaustivas).
	 */
	protected static final int[] ARREGLO_ESCENARIO_2 = {350, 383, 105, 233, 140, 266, 356, 236, 80, 360, 221, 241, 130, 244, 352, 446, 18, 98, 97, 396};
	
	/**
	 * Crea el escenario 1. Un escenario vacio.
	 */
	@Before
	public void setupEscenario1()
	{
		lista = new QueueHeap<Integer>();
		
	}
	
	/**
	 * Crea el escenario 2 (sin numeros repetidos) agregando los siguientes numeros (en este orden):
	 * 
	 * 350, 383, 105, 233, 140, 266, 356, 236, 80, 360, 221, 241, 130, 244, 352, 446, 18, 98, 97, 396
	 */
	public void setupEscenario2()
	{
		for(int actual: ARREGLO_ESCENARIO_2)
		{
			lista.enqueue(actual);
			
		}
	}
	
	/**
	 * Prueba que revisa el metodo size.
	 */
	@Test
	public void testSize()
	{
		//Prueba la lista vacia.
		assertEquals("El tamano de la lista vacia no es correcto", 0, lista.size());
		
		//Prueba la lista con dos elementos
		
		lista.enqueue(5);
		lista.enqueue(30);
		
		assertEquals("El tamano de la lista con dos elementos no es correcto", 2, lista.size());
		
		//Prueba vaciando la lista
		lista.clear();
		assertEquals("El tamano de la lista vacia no es correcto", 0, lista.size());
		
		//Prueba la lista con 20 elementos
		setupEscenario2();
		
		assertEquals("El tamano de la lista con 20 elementos no es correcto", ARREGLO_ESCENARIO_2.length, lista.size());
		
		//Agrega dos elementos mas y prueba
		
		lista.enqueue(5);
		lista.enqueue(30);
		
		assertEquals("El tamano de la lista con 22 elementos no es correcto", ARREGLO_ESCENARIO_2.length + 2, lista.size());
	}
	
	/**
	 * Prueba que revisa el metodo isEmpty
	 */
	@Test
	public void testIsEmpty()
	{
		//Prueba la lista vacia.
		assertTrue("Al inicio la lista deberia estar vacia", lista.isEmpty());
		
		//Prueba la lista con dos elementos.
		lista.enqueue(5);
		lista.enqueue(30);
		
		assertFalse("Al agregar elementos la lista no deberia estar vacia", lista.isEmpty());
		
		//Prueba la lista despues de vaciarla.
		lista.clear();
		
		assertTrue("Al remover todos los elementos de la lista deberia estar vacia", lista.isEmpty());
		
		//Prueba la lista con 20 elementos.
		setupEscenario2();
		
		assertFalse("Al agregar 20 elementos, la lista no deberia estar vacia", lista.isEmpty());
	}
	
	/**
	 * Prueba que revisa el metodo contains
	 */
	@Test
	public void testContains()
	{
		//Prueba con la lista vacia.
		assertFalse("Al inicio no deberia encontrar ningun elemento", lista.contains(80));
		assertFalse("Al inicio no deberia encontrar ningun elemento", lista.contains(new Double(80)));
		
		//Prueba con dos elementos
		lista.enqueue(5);
		lista.enqueue(30);
		
		assertTrue("Deberia contener el elemento", lista.contains(5));
		assertTrue("Deberia contener el elemento", lista.contains(30));
		
		//Vacia la lista y prueba.
		lista.clear();
		
		assertFalse("No deberia encontrar ningun elemento", lista.contains(30));
		assertFalse("No deberia encontrar ningun elemento", lista.contains(new Double(5)));
		
		// Prueba con 20 elementos.
		setupEscenario2();
		
		for(int actual: ARREGLO_ESCENARIO_2)
		{
			assertTrue("El elemento "+ actual +" deberia encontrarse en la lista", lista.contains(actual));
		}
		
	}
	
	/**
	 * Prueba que verifica el iterador que recibe.
	 */
	@Test
	public void testIterator()
	{
		//Prueba el iterador de la lista vacia.
		Iterator<Integer> iterador = lista.iterator();
		assertNotNull("Con la lista vacia retorna un iterador vacio", iterador);
		assertFalse("Con la lista vacia dice que puede avanzar al siguiente elemento", iterador.hasNext());
		try
		{
			iterador.next();
			fail("Si no tiene elementos, no deberia avanzar.");
		}
		catch(NoSuchElementException e)
		{
			//No avanzo porque no hay elementos.
		}
		
		//Prueba el iterador con dos elementos.
		lista.enqueue(5);
		lista.enqueue(30);
		
		iterador = lista.iterator();
		assertNotNull("No deberia retornar un iterador vacio", iterador);
		assertTrue("Con elementos el la lista dice que no puede avanzar al siguiente elemento", iterador.hasNext());
		try
		{
			Integer siguiente = iterador.next();
			assertTrue("Estando en el primer elemento de la lista, dice que no puede avanzar al segundo", iterador.hasNext());
			assertNotNull(siguiente);
			siguiente = iterador.next();
		}
		catch(NoSuchElementException e)
		{
			fail("No avanzo el iterador, dice que no hay mas elementos.");
		}
		
		assertFalse("Estando en el ultimo elemento de la lista dice que no puede avanzar.", iterador.hasNext());
		
		try
		{
			iterador.next();
			fail("Si esta en el ultimo elemento, no deberia avanzar.");
		}
		catch(NoSuchElementException e)
		{
			//No avanzo proque no hay mas elementos.
		}
		
		//Prueba el iterador con 20 elementos
		
		lista.clear();
		
		setupEscenario2();
		
		iterador = lista.iterator();
		assertNotNull("No deberia retornar un iterador vacio", iterador);
		assertTrue("Con elementos el la lista dice que no puede avanzar al siguiente elemento", iterador.hasNext());
		
		int indiceActual = 0;
		while(iterador.hasNext())
		{
			try
			{
				Integer actual = iterador.next();
				assertNotNull(actual);
				indiceActual++;
			}
			catch(NoSuchElementException e)
			{
				fail("Indica que puede avanzar, pero lanza excepcion al avanzar.");
			}
		}
		
		assertEquals("El iterador no recorrio toda la lista", indiceActual, lista.size());
		
	}
	
	/**
	 * Metodo que prueba el primer toarray
	 */
	@Test
	public void testToArray1()
	{
		//Prueba con la lista vacia.
		Object[] arreglo = lista.toArray();
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El arreglo con la lista vacia no esta vacio", 0, arreglo.length);
		
		//Prueba la lista con dos elementos.
		lista.enqueue(5);
		lista.enqueue(30);
		arreglo = lista.toArray();
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El arreglo con la lista vacia no esta vacio", 2, arreglo.length);
		
		//Prueba la lista con 20 elementos.
		lista.clear();
		setupEscenario2();
		arreglo = lista.toArray();
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El tamano del arreglo no es el esperado", ARREGLO_ESCENARIO_2.length, arreglo.length);	
	}
	
	/**
	 * Prueba que revisa que se agreguen los elementos sin tener en cuenta repetidos, ni el orden.
	 */
	@Test
	public void testAdd()
	{
		//Prueba la lista vacia.
		assertTrue("Al principio la lista esta vacia", lista.isEmpty());
		assertEquals("El tamano de la lista al principio no es 0", 0, lista.size());

		//Agrega dos elementos.
		assertTrue("No agrega el elemento", lista.enqueue(5));
		assertTrue("No agrega el elemento", lista.enqueue(30));
		assertFalse("La lista no deberia estar vacia", lista.isEmpty());
		assertEquals("La lista deberia tener 2 elementos", 2, lista.size());
		assertTrue("El elemento en la posicion 1 no es el correcto",(lista.get(0).compareTo(30))==0);
		assertTrue("La lista no contiene 5", lista.contains(5));
		assertTrue("La lista no contiene 30", lista.contains(30));
		
		//Agrega 20 elementos.
		lista.clear();
		
		setupEscenario2();
		
		assertEquals("La lista deberia tener 20 elementos", ARREGLO_ESCENARIO_2.length, lista.size());
		assertFalse("La lista no deberia estar vacia", lista.isEmpty());
		assertTrue("El elemento en la posicion 1 no es el correcto",(lista.get(0).compareTo(446))==0);
		assertTrue("El elemento en la posicion 2 no es el correcto",(lista.get(1).compareTo(396))==0);


	}
	
	/**
	 * Prueba el metodo remove que recibe como parametro un objeto.
	 */
	@Test
	public void testDequeueMax()
	{
		//Prueba con una lista vacia.
		assertEquals("No deberia eliminar elementos porque la lista esta vacia", null , lista.dequeueMax());
		
		//Prueba con una lista de 3 elementos.
		lista.enqueue(3);
		lista.enqueue(40);
		lista.enqueue(30);
		assertEquals("El tamano del arreglo no es el esperado", 3, lista.size());
				
		assertEquals("Deberia poder eliminar el elemento de la lista", new Integer(40) , lista.dequeueMax());
		assertFalse("No se elimino el elemento", lista.contains(new Integer(40)));
		assertEquals("El tamano de la lista no es el esperado", 2, lista.size());
				
		assertEquals("Deberia poder eliminar el elemento de la lista", new Integer(30), lista.dequeueMax());
		assertFalse("No se elimino el elemento", lista.contains(new Integer(30)));
		assertEquals("El tamano de la lista no es el esperado", 1, lista.size());
		
		assertEquals("Deberia poder eliminar el elemento de la lista", new Integer(3) , lista.dequeueMax());
		assertFalse("No se elimino el elemento", lista.contains(new Integer(3)));
		assertEquals("El tamano de la lista no es el esperado", 0, lista.size());
		
		//Prueba con una lista de 20 elementos.
		setupEscenario2();

		assertEquals("Deberia poder eliminar el elemento de la lista", new Integer(446),lista.dequeueMax());
		assertFalse("No se elimino el elemento", lista.contains(new Integer(446)));
		assertEquals("El tamano de la lista no es el esperado", 19, lista.size());
		
		assertEquals("Deberia poder eliminar el elemento de la lista", new Integer(396),lista.dequeueMax());
		assertFalse("No se elimino el elemento", lista.contains(new Integer(396)));
		assertEquals("El tamano de la lista no es el esperado", 18, lista.size());
		
		assertEquals("Deberia poder eliminar el elemento de la lista", new Integer(383),lista.dequeueMax());
		assertFalse("No se elimino el elemento", lista.contains(new Integer(383)));
		assertEquals("El tamano de la lista no es el esperado", 17, lista.size());
		
		assertEquals("Deberia poder eliminar el elemento de la lista", new Integer(360),lista.dequeueMax());
		assertFalse("No se elimino el elemento", lista.contains(new Integer(360)));
		assertEquals("El tamano de la lista no es el esperado", 16, lista.size());
		
		assertFalse("La lista no deberia tener elementos repetidos", hayRepetidos());
	}
	
	
	/**
	 * Prueba que revisa el metodo clear.
	 */
	@Test
	public void testClear()
	{
		//Prueba la lista vacia.
		lista.clear();
		assertEquals("La lista sigue vacia", 0, lista.size());
		
		//Prueba con 20 elementos
		setupEscenario2();
		assertEquals("La lista no tiene 20 elementos, hay que revisar el metodo add", 20, lista.size());
		lista.clear();
		assertEquals("La lista no quedo vacia", 0, lista.size());
		try
		{
			lista.get(0);
			fail("No deberia poder recuperar el primer elemento de la lista");
		}
		catch (IndexOutOfBoundsException e) 
		{
			//Deberia lanzar la excepcion.
		}
	}
	
	@Test
	public void testGetMax()
	{
		//Prueba con una lista vacia.
		assertEquals("No deberia eliminar elementos porque la lista esta vacia", null , lista.getMax());

		//Prueba con una lista de 3 elementos.
		lista.enqueue(3);
		lista.enqueue(40);
		lista.enqueue(30);
		assertEquals("El tamano del arreglo no es el esperado", 3, lista.size());

		assertEquals("El elemento mayor no es el esperado", new Integer(40) , lista.getMax());
		assertTrue("No se deberia eliminar el elemento", lista.contains(new Integer(40)));

		//Prueba con una lista de 20 elementos.
		setupEscenario2();

		assertEquals("El elemento mayor no es el esperado", new Integer(446) , lista.getMax());
		assertTrue("No se deberia eliminar el elemento", lista.contains(new Integer(446)));
	}
	
	/**
	 * Prueba que revisa el metodo get.
	 */
	@Test
	public void testGet()
	{
		//Revisa la lista vacia.
		try
		{
			lista.get(0);
			fail("Deberia lanzar excepcion porque la lista esta vacia.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deberia lanzar la excepcion
		}
		
		try
		{
			lista.get(-1);
			fail("Deberia lanzar excepcion porque el indice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deberia lanzar la excepcion
		}
		
		try
		{
			lista.get(-50);
			fail("Deberia lanzar excepcion porque el indice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deberia lanzar la excepcion
		}
		
		//Revisa la lista con 20 elementos.
		setupEscenario2();
		
		try
		{
			lista.get(-1);
			fail("Deberia lanzar excepcion porque el indice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deberia lanzar la excepcion
		}
		
		try
		{
			lista.get(-50);
			fail("Deberia lanzar excepcion porque el indice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deberia lanzarla
		}
		
		try
		{
			lista.get(500);
			fail("Deberia lanzar excepcion porque el indice esta por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deberia lanzarla
		}
		
		try
		{
			lista.get(lista.size());
			fail("Deberia lanzar excepcion porque el indice esta por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deberia lanzarla
		}
		
		for(int i = 0; i < ARREGLO_ESCENARIO_2.length; i++)
		{
			try
			{
				Integer elemento = lista.get(i);
				assertNotNull("Los elementos recuperados no pueden ser nulos", elemento);
			}
			catch(IndexOutOfBoundsException e)
			{
				fail("El elemento se encuentra dentro del rango, no deberia lanzar excepcion");
			}
		}
	}
	
	/**
	 * Prueba del metodo remove
	 */
	@Test
	public void testRemoveIndex()
	{
		//Prueba con la lista vacia.
		try
		{
			lista.remove(0);
			fail("No deberia dejar eliminar elementos porque la lista esta vacia");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		try
		{
			lista.remove(-1);
			fail("No deberia eliminar porque el indice es negativo");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		//Prueba con 20 elementos
		setupEscenario2();
		try
		{
			lista.remove(-1);
			fail("No deberia dejar eliminar porque el indice es negativo");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		try
		{
			lista.remove(lista.size());
			fail("No deberia dejar eliminar porque el indice esta por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		
		try
		{
			Integer paraEliminar = lista.get(0);
			assertEquals("No elimino el elemento esperado", paraEliminar, lista.remove(0));
			assertEquals("La lista no tiene el tamano esperado", 19, lista.size());
			assertFalse("No elimino el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(lista.size() - 1);
			assertEquals("No elimino el elemento esperado", paraEliminar, lista.remove(lista.size() - 1));
			assertEquals("La lista no tiene el tamano esperado", 18, lista.size());
			assertFalse("No elimino el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(3);
			assertEquals("No elimino el elemento esperado", paraEliminar, lista.remove(3));
			assertEquals("La lista no tiene el tamano esperado", 17, lista.size());
			assertFalse("No elimino el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(15);
			assertEquals("No elimino el elemento esperado", paraEliminar, lista.remove(15));
			assertEquals("La lista no tiene el tamano esperado", 16, lista.size());
			assertFalse("No elimino el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(10);
			assertEquals("No elimino el elemento esperado", paraEliminar, lista.remove(10));
			assertEquals("La lista no tiene el tamano esperado", 15, lista.size());
			assertFalse("No elimino el elemento", lista.contains(paraEliminar));
			
			assertFalse("La lista no deberia tener elementos repetidos", hayRepetidos());
			
			
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("Deberia dejar realizar la operacion porque el indice se encuentra en el rango");
		}
	}
	
	/**
	 * Prueba del metodo indexOf.
	 */
	@Test
	public void testIndexOf()
	{
		//Prueba con la lista vacia
		assertEquals("Como no hay elementos en la lista, el resultado deberia ser -1", -1, lista.indexOf(50));
		
		//Prueba con 20 elementos
		setupEscenario2();
		
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(10));
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(248));
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(448));
		assertEquals("El elemento no se encuentra en la lista", -1, lista.indexOf(366));
	}
	
	/**
	 * Prueba del metodo subList
	 */
	@Test
	public void testSubList()
	{
		//Prueba con la lista vacia.
		try
		{
			lista.subList(0, 1);
			fail("La lista esta vacia, no deberia dejar crear sublistas.");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		//Prueba con 20 elementos
		setupEscenario2();
		
		try
		{
			lista.subList(15, 21);
			fail("El indice se encuentra fuera de la lista, deberia fallar.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Correcto
		}
		
		try
		{
			lista.subList(-1, 15);
			fail("El indice se encuentra fuera de la lista, deberia fallar.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Correcto
		}
		
		try
		{
			lista.subList(0, 20);
			
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("El indice se encuentra dentro de la lista, no deberia fallar.");
		}
	}

	/**
	 * Revisa si hay numeros repetidos en la lista.
	 * @return True si esta repetido, false en caso contrario.
	 */
	protected boolean hayRepetidos()
	{
		for(int i = 0; i < lista.size(); i++)
		{
			for(int j = i + 1; j < lista.size(); j++)
			{
				if(lista.get(i).intValue() == lista.get(j).intValue())
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Revisa si un numero esta en el arreglo del escenario.
	 * @param numero Numero que se quiere buscar
	 * @return True si se encuentra, false de lo contrario.
	 */
	protected boolean estaEnArreglo(int numero)
	{
		for(int actual: ARREGLO_ESCENARIO_2)
		{
			if(actual == numero)
				return true;
		}
		return false;
	}
}