package test.data_structures;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.HashTableSeparateChaining;
import model.data_structures.ListaHash;

public class HashTableSeparateChainingTest 
{

	private HashTableSeparateChaining<String, Integer> chaining;


	@Before
	public void setUpEscenerio1()
	{
		chaining= new HashTableSeparateChaining<>(11);
	}

	public void setUpEscenario2()
	{
		chaining.putInSet("A", 1);
		chaining.putInSet("B", 2);
		chaining.putInSet("C", 3);
		chaining.putInSet("D", 4);
		chaining.putInSet("E", 5);
		chaining.putInSet("F", 6);
		chaining.putInSet("G", 7);
		chaining.putInSet("H", 8);
		chaining.putInSet("I", 9);
		chaining.putInSet("J", 10);
		chaining.putInSet("K", 11);
	}
	public void setUpEscenario3()
	{
		chaining= new HashTableSeparateChaining<>(1);
		chaining.putInSet("A", 1);
		chaining.putInSet("B", 2);
		chaining.putInSet("C", 3);
		chaining.putInSet("D", 4);
		chaining.putInSet("E", 5);
	}
	@Test
	public void testSize()
	{
		//Prueba la tabla vacia.
		assertEquals("El tamanio de la tabla vacia no es correcto", 0, chaining.size());
		//Prueba la tabla con dos elementos

		chaining.putInSet("X", 11);
		chaining.putInSet("Y", 12);

		assertEquals("El tamanio de la tabla con dos elementos no es el correcto", 2, chaining.size());
		//Prueba vaciando la tabla

		chaining.deleteSet("X");
		chaining.deleteSet("Y");


		// Prueba con 11 elementos

		setUpEscenario2();

		assertEquals("El tamanio de la tabla con 10 elementos no es el correcto", 11, chaining.size());

		//Agrega dos elementos mas

		chaining.putInSet("P", 13);
		chaining.putInSet("Q", 14);

		assertEquals("El tamanio de la tabla no es el correcto", 13, chaining.size());

	}
	@Test
	public void testPut()
	{
		//Prueba la lista vacia.
		assertTrue("Al principio la tabla esta vacia", chaining.isEmpty());

		assertEquals("El tamanio de la tabla al principio no es 0", 0, chaining.size());

		//Agrega dos elementos

		chaining.putInSet("V", 14);
		chaining.putInSet("W", 15);


		assertTrue("El elemento no se agrego",chaining.contains("V"));
		assertTrue("El elemento no se agrego",chaining.contains("W"));
		assertFalse("La tabla no deberia estar vacia", chaining.isEmpty());
		assertEquals("La tabla deberia tener 2 elementos",2,chaining.size());

		// Vaciar tabla

		chaining.deleteSet("V");
		chaining.deleteSet("W");

		//Agregar 11 elementos
		setUpEscenario2();

		assertFalse("La tabla no deberia estas vacia", chaining.isEmpty());
		assertEquals("La tabla deberia tener 11 elementos",11,chaining.size());


	}
	@Test
	public void testDelete()
	{
		// Prueba con la tabla vacia
		try
		{
			chaining.deleteSet("A");
			fail("Deberia arrojar una excepcion");			
		}
		catch(NoSuchElementException e)
		{
			
		}

		//Prueba con dos elementos
		chaining.putInSet("K", 16);
		chaining.putInSet("L", 17);
		assertEquals("El size de la tabla no es el esperado", 2, chaining.size());
		Iterator<Integer> it= chaining.deleteSet("K");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(16), actual);

		}
		assertFalse("No se elimino el elemento", chaining.contains("K"));
		assertEquals("El tamanio de la tabla no es correcto",1,chaining.size());

		it= chaining.deleteSet("L");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(17), actual);

		}
		assertFalse("No se elimino el elemento", chaining.contains("L"));
		assertTrue("La tabla deberia estar vacia", chaining.isEmpty());

		//Prueba 11 elementos
		setUpEscenario2();

		it = chaining.deleteSet("A");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(1), actual);

		}
		assertFalse("No se elimino el elemento",chaining.contains("A"));
		assertEquals("El tamanio de la tabla no es el correcto", 10, chaining.size());



		it = chaining.deleteSet("B");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(2), actual);

		}
		assertFalse("No se elimino el elemento",chaining.contains("B"));
		assertEquals("El tamanio de la tabla no es el esperado", 9, chaining.size());

		it = chaining.deleteSet("C");
		while(it.hasNext())
		{
			Integer actual = it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(3), actual);

		}
		assertFalse("No se elimino el elemento",chaining.contains("C"));
		assertEquals("El tamanio de la tabla no es el esperado", 8, chaining.size());

		it = chaining.deleteSet("D");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(4), actual);

		}
		assertFalse("No se elimino el elemento",chaining.contains("D"));
		assertEquals("El tamanio de la tabla no es el esperado", 7, chaining.size());


	}

	@Test
	public void testGet()
	{
		assertNull("No hay valores asociados",chaining.getSet("N"));

		//Prueba dos elementos
		chaining.putInSet("H", 23);

		chaining.putInSet("R", 18);

		assertNotNull("Deberia retornar los valores asociados",chaining.getSet("R"));
		Iterator<Integer> it= chaining.getSet("R");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(18), actual);

		}
		assertNotNull("Deberia retornar los valores asociados",chaining.getSet("H"));
		it = chaining.getSet("H");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(23), actual);

		}

		//Prueba 11 elementos
		chaining.clean();
		setUpEscenario2();

		assertNotNull("Deberia retornar los valores asociados",chaining.getSet("A"));
		it = chaining.getSet("A");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(1), actual);

		}
		assertNotNull("Deberia retornar los valores asociados",chaining.getSet("B"));
		it = chaining.getSet("B");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(2), actual);

		}
		assertNotNull("Deberia retornar los valores asociados",chaining.getSet("C"));
		it = chaining.getSet("C");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento eliminado no es el correcto", new Integer(3), actual);

		}
		assertNotNull("Deberia retornar los valores asociados",chaining.getSet("D"));

		
		//Prueba elementos con la misma key
		chaining.putInSet("A", 10);

		chaining.putInSet("B", 20);

		assertEquals("El tamanio de la tabla no es el esperado", 11, chaining.size());

		assertNotNull("Deberia retornar los valores asociados",chaining.getSet("A"));
		it = chaining.getSet("A");
		int i = 0;
		while(it.hasNext())
		{
			Integer actual = it.next();
			if(i == 0)
				assertEquals("El elemento no es el esperado", new Integer(1), actual);
			else
				assertEquals("El elemento no es el esperado", new Integer(10), actual);
			i++;
		}

		assertNotNull("Deberia retornar los valores asociados",chaining.getSet("B"));
		it = chaining.getSet("B");
		int j = 0;
		while(it.hasNext())
		{
			Integer actual= it.next();
			if(j == 0)
				assertEquals("El elemento no es el esperado", new Integer(2), actual);
			else
				assertEquals("El elemento no es el esperado", new Integer(20), actual);
			j++;
		}

		//Prueba elementos con el mismo hash
		chaining.clean();
		
		chaining.putInSet("FB", 10);

		chaining.putInSet("Ea", 20);
		
		assertEquals("El tamanio de la tabla no es el esperado", 2, chaining.size());
		
		assertNotNull("Deberia retornar los valores asociados",chaining.getSet("FB"));
		it = chaining.getSet("FB");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento no es el esperado", new Integer(10), actual);
		}
		
		assertNotNull("Deberia retornar los valores asociados",chaining.getSet("FB"));
		it = chaining.getSet("Ea");
		while(it.hasNext())
		{
			Integer actual= it.next();
			assertEquals("El elemento no es el esperado", new Integer(20), actual);
		}
		
		
		Iterator<String> it2 = chaining.keys();
		int z = 0;
		while(it.hasNext())
		{
			String actual= it2.next();
			if(z == 0)
				assertEquals("El elemento no es el esperado", "FB", actual);
			else
				assertEquals("El elemento no es el esperado", "Ea", actual);
			z++;
		}
		

	}
	@Test
	public void testIsEmpty()
	{
		//Prueba la lista vacia.
		assertTrue("Al inicio la tabla deberia estar vacia", chaining.isEmpty());

		//Prueba la lista con dos elementos.
		chaining.putInSet("T", 22);
		chaining.putInSet("O", 24);

		assertFalse("Al agregar elementos la tabla no deberia estar vacia", chaining.isEmpty());

		//Prueba la lista despues de vaciarla.
		chaining.deleteSet("T");
		chaining.deleteSet("O");

		assertTrue("Al remover todos los elementos de la tabla deberia estar vacia", chaining.isEmpty());

		//Prueba la lista con 11 elementos.
		setUpEscenario2();

		assertFalse("Al agregar 11 elementos, la tabla no deberia estar vacia", chaining.isEmpty());



	}

	@Test
	public void testContains()
	{

		//Prueba con la lista vacia.
		assertFalse("Al inicio no deberia encontrar ningun elemento", chaining.contains("A"));
		assertFalse("Al inicio no deberia encontrar ningun elemento", chaining.contains("B"));

		//Prueba con dos elementos
		chaining.putInSet("A", 23);
		chaining.putInSet("B", 26);
		assertTrue("Deberia contener el elemento", chaining.contains("A"));
		assertTrue("Deberia contener el elemento", chaining.contains("B"));

		//Vacia la lista y prueba.
		chaining.deleteSet("A");
		chaining.deleteSet("B");

		assertFalse("No deberia encontrar ningun elemento", chaining.contains("A"));
		assertFalse("No deberia encontrar ningun elemento", chaining.contains("B"));

		// Prueba con 11 elementos.
		setUpEscenario2();

		assertTrue("Deberia contener el elemento", chaining.contains("A"));
		assertTrue("Deberia contener el elemento", chaining.contains("B"));
		assertTrue("Deberia contener el elemento", chaining.contains("C"));
		assertTrue("Deberia contener el elemento", chaining.contains("D"));




	}
	@Test
	public void keysTest()
	{
		Iterator<String> it = chaining.keys();
		try
		{
			it.next();
			fail("Deberia arrojar una excepcion");
		}
		catch(NoSuchElementException e)
		{
			
		}
		//Prueba dos elementos
		chaining.putInSet("H", 23);

		chaining.putInSet("R", 18);

		assertNotNull("Deberia retornar los valores asociados",chaining.keys());
		it = chaining.keys();
		while(it.hasNext())
		{
			String actual= it.next();
			assertNotNull("el elemento deberia encontrarse en la tabla",actual);
		}
		chaining.deleteSet("H");
		chaining.deleteSet("R");
		
		//Prueba con 11 elementos
		setUpEscenario2();
		it = chaining.keys();
		int i = 0;
		while(it.hasNext())
		{
			String actual = it.next();
			assertNotNull("el elemento deberia encontrarse en la tabla",actual);
			
			if(i == 0)
				assertEquals("No es el elemnto esperado", "B", actual);
			else if(i == 1)
				assertEquals("No es el elemnto esperado", "C", actual);
			else if(i == 2)
				assertEquals("No es el elemnto esperado", "D", actual);
			else if(i == 3)
				assertEquals("No es el elemnto esperado", "E", actual);
			else if(i == 4)
				assertEquals("No es el elemnto esperado", "F", actual);
			else if(i == 5)
				assertEquals("No es el elemnto esperado", "G", actual);
			else if(i == 6)
				assertEquals("No es el elemnto esperado", "H", actual);
			else if(i == 7)
				assertEquals("No es el elemnto esperado", "I", actual);
			else if(i == 8)
				assertEquals("No es el elemnto esperado", "J", actual);
			else if(i == 9)
				assertEquals("No es el elemnto esperado", "K", actual);
			else if(i == 10)
				assertEquals("No es el elemnto esperado", "A", actual);
			i++;
		}


	}
	@Test
	public void testResize()
	{
		
		setUpEscenario3();
		
		int hash = chaining.hash("A");
		ListaHash<String, Integer> lista = chaining.darListaEnPos(hash);
		assertTrue("Deberia haber encontrado la llave", lista.contains("A") != -1);
		
		int hash1= chaining.hash("B");
		ListaHash<String, Integer> lista1 = chaining.darListaEnPos(hash1);
		assertTrue("Deberia haber encontrado la llave", lista1.contains("B") != -1);
		
		int hash2= chaining.hash("C");
		ListaHash<String, Integer> lista2 = chaining.darListaEnPos(hash2);
		assertTrue("Deberia haber encontrado la llave", lista2.contains("C") != -1);
		
		int hash3= chaining.hash("D");
		ListaHash<String, Integer> lista3 = chaining.darListaEnPos(hash3);
		assertTrue("Deberia haber encontrado la llave", lista3.contains("D") != -1);
		
		int hash4= chaining.hash("E");
		ListaHash<String, Integer> lista4 = chaining.darListaEnPos(hash4);
		assertTrue("Deberia haber encontrado la llave", lista4.contains("E") != -1);

		chaining.putInSet("F", 6);
		
		int reHash= chaining.hash("A");
		ListaHash<String, Integer> reLista= chaining.darListaEnPos(reHash);
		assertTrue("Deberia haber encontrado la llave", reLista.contains("A") != -1);
		
		int reHash1= chaining.hash("B");
		ListaHash<String, Integer> reLista1 = chaining.darListaEnPos(reHash1);
		assertTrue("Deberia haber encontrado la llave", reLista1.contains("B") != -1);
		
		int reHash2= chaining.hash("C");
		ListaHash<String, Integer> reLista2 = chaining.darListaEnPos(reHash2);
		assertTrue("Deberia haber encontrado la llave", reLista2.contains("C") != -1);
		
		int reHash3= chaining.hash("D");
		ListaHash<String, Integer> reLista3 = chaining.darListaEnPos(reHash3);
		assertTrue("Deberia haber encontrado la llave", reLista3.contains("D") != -1);
		
		int reHash4= chaining.hash("E");
		ListaHash<String, Integer> reLista4 = chaining.darListaEnPos(reHash4);
		assertTrue("Deberia haber encontrado la llave", reLista4.contains("E") != -1);
		
		int reHash5= chaining.hash("F");
		ListaHash<String, Integer> reLista5= chaining.darListaEnPos(reHash5);
		assertTrue("Deberia haber encontrado la llave", reLista5.contains("F") != -1);
		
	}
}
