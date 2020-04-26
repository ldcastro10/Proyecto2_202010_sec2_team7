package test.data_structures;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.RedBlackBST;

public class RedBlackBSTTest 
{
	private RedBlackBST<String, Integer> arbol;
	
	@Before
	public void setUpEscenario1()
	{
		arbol= new RedBlackBST<String, Integer>();
	}
	
	public void setUpEscenario2()
	{
		arbol.putInSet("A", 1);
		arbol.putInSet("B", 2);
		arbol.putInSet("C", 3);
		arbol.putInSet("D", 4);
		arbol.putInSet("E", 5);
		arbol.putInSet("F", 6);
		arbol.putInSet("G", 7);
		arbol.putInSet("H", 8);
	}

	public void setUpEscenario3()
	{
		arbol.putInSet("H", 8);
		arbol.putInSet("G", 7);
		arbol.putInSet("F", 6);
		arbol.putInSet("E", 5);
		arbol.putInSet("D", 4);
		arbol.putInSet("C", 3);
		arbol.putInSet("B", 2);
		arbol.putInSet("A", 1);
	}
	
	public void setUpEscenario4()
	{
		arbol.putInSet("B", 2);
		arbol.putInSet("F", 6);
		arbol.putInSet("G", 7);
		arbol.putInSet("C", 3);
		arbol.putInSet("H", 8);
		arbol.putInSet("A", 1);
		arbol.putInSet("E", 5);
		arbol.putInSet("D", 4);
	}
	@Test
	public void testSize()
	{
		assertEquals("El arbol esta vacio",0,arbol.size());
		
		//agrega 2 elementos
		
		arbol.putInSet("X", 10);
		arbol.putInSet("Y", 11);
		
		assertEquals("El arbol debe tener dos elementos", 2, arbol.size());
		
		//agrega otro elemento
		
		arbol.putInSet("Z", 12);
		
		assertEquals("El arbol debe tener tres elementos",3, arbol.size());
		
		//agrega 8 elementos mas
		
		setUpEscenario2();
		
		assertEquals("El arbol de tener once elementos",11, arbol.size());		
	}
	
	@Test 
	public void testIsEmpty()
	{
		assertTrue("Debe estar vacio el arbol", arbol.isEmpty());
		
		//agrega un elemento
		arbol.putInSet("A", 1);
		
		assertFalse("El arbol no esta vacio", arbol.isEmpty());		
	}
	
	@Test
	public void testGet()
	{
		assertNull("El arbol esta vacio", arbol.get("A"));
		
		arbol.putInSet("R",13);
		
		assertEquals("El valor no corresponde",new Integer(13) ,arbol.get("R"));
		
		setUpEscenario2();
		
		assertEquals("El valor no corresponde",new Integer(1) ,arbol.get("A"));
		assertEquals("El valor no corresponde",new Integer(2) ,arbol.get("B"));
		assertEquals("El valor no corresponde",new Integer(3) ,arbol.get("C"));
		assertEquals("El valor no corresponde",new Integer(4) ,arbol.get("D"));
		assertEquals("El valor no corresponde",new Integer(5) ,arbol.get("E"));
		assertEquals("El valor no corresponde",new Integer(6) ,arbol.get("F"));
		assertEquals("El valor no corresponde",new Integer(7) ,arbol.get("G"));
		assertEquals("El valor no corresponde",new Integer(8) ,arbol.get("H"));		
	}
	
	@Test
	public void testGetHeight()
	{
		assertEquals("La altura no es correcta el arbol esta vacio",-1, arbol.getHeight("B"));

		setUpEscenario2();
		
		assertEquals("La altura no es correcta",0, arbol.getHeight("D"));
		assertEquals("La altura no es correcta",1, arbol.getHeight("B"));
		assertEquals("La altura no es correcta",1, arbol.getHeight("F"));
		assertEquals("La altura no es correcta",2, arbol.getHeight("A"));
		assertEquals("La altura no es correcta",2, arbol.getHeight("C"));
		assertEquals("La altura no es correcta",2, arbol.getHeight("H"));
		assertEquals("La altura no es correcta",-1, arbol.getHeight("X"));		
	}
	
	@Test
	public void testContains()
	{
		assertFalse("El elemento no existe", arbol.contains("A"));
		
		arbol.putInSet("X", 11);
		
		assertTrue("El elemento deberia estar contenido", arbol.contains("X"));
		
		setUpEscenario2();
		
		assertTrue("El elemento deberia estar contenido", arbol.contains("A"));
		assertTrue("El elemento deberia estar contenido", arbol.contains("B"));
		assertTrue("El elemento deberia estar contenido", arbol.contains("C"));
		assertFalse("El elemento no existe", arbol.contains("R"));

	}
	
	@Test
	public void testPut1()
	{
		arbol.putInSet("R", 22);
		assertTrue("Deberia contener el elemento", arbol.contains("R"));
		assertTrue("Deberia estar balaciado el arbol", arbol.check());
		assertEquals("Deberia tener 1 elemento el arbol",1, arbol.size());
		
		arbol.putInSet("S", 23);
		assertTrue("Deberia contener el elemento", arbol.contains("S"));
		assertTrue("Deberia estar balaciado el arbol", arbol.check());
		assertEquals("Deberia tener 2 elemento el arbol",2, arbol.size());
		assertEquals("El menor elemento no concuerda","R", arbol.min());
		assertEquals("El mayor elemento no concuerda","S", arbol.max());
		
		arbol.putInSet("M", 24);
		assertTrue("Deberia contener el elemento", arbol.contains("M"));
		assertTrue("Deberia estar balaciado el arbol", arbol.check());
		assertEquals("Deberia tener 3 elemento el arbol",3, arbol.size());
		assertEquals("El menor elemento no concuerda","M", arbol.min());
		assertEquals("El mayor elemento no concuerda","S", arbol.max());
		
		arbol.putInSet("O", 25);
		assertTrue("Deberia contener el elemento", arbol.contains("O"));
		assertTrue("Deberia estar balaciado el arbol", arbol.check());
		assertEquals("Deberia tener 4 elemento el arbol",4, arbol.size());
		assertEquals("El menor elemento no concuerda","M", arbol.min());
		assertEquals("El mayor elemento no concuerda","S", arbol.max());		
	}
	@Test 
	public void testPut2()
	{
		setUpEscenario2();
		
		assertTrue("Deberia estar balaciado el arbol", arbol.check());
		assertEquals("Deberia tener 8 elemento el arbol",8, arbol.size());
		assertEquals("El menor elemento no concuerda","A", arbol.min());
		assertEquals("El mayor elemento no concuerda","H", arbol.max());		
	}
	@Test 
	public void testPut3()
	{
		setUpEscenario3();
		
		assertTrue("Deberia estar balaciado el arbol", arbol.check());
		assertEquals("Deberia tener 8 elemento el arbol",8, arbol.size());
		assertEquals("El menor elemento no concuerda","A", arbol.min());
		assertEquals("El mayor elemento no concuerda","H", arbol.max());		
	}
	@Test 
	public void testPut4()
	{
		setUpEscenario4();
		
		assertTrue("Deberia estar balaciado el arbol", arbol.check());
		assertEquals("Deberia tener 8 elemento el arbol",8, arbol.size());
		assertEquals("El menor elemento no concuerda","A", arbol.min());
		assertEquals("El mayor elemento no concuerda","H", arbol.max());		
	}
	@Test
	public void testHeight()
	{
		assertEquals("El arbol esta vacio",-1,arbol.height());
		
		arbol.putInSet("R", 22);
		assertEquals("La altura no coincide",0,arbol.height());
		
		arbol.putInSet("Q", 23);
		assertEquals("La altura no coincide",1,arbol.height());
		
		arbol.putInSet("S", 25);
		assertEquals("La altura no coincide",1,arbol.height());   
		
		setUpEscenario2();
		
		assertEquals("La altura no coincide",3,arbol.height());		
	}
	@Test
	public void testMin()
	{
		arbol.putInSet("R", 22);
		assertEquals("Deberia tener 1 elemento el arbol",1, arbol.size());
		assertEquals("El menor elemento no concuerda","R", arbol.min());

		
		arbol.putInSet("S", 23);
		assertEquals("Deberia tener 2 elemento el arbol",2, arbol.size());
		assertEquals("El menor elemento no concuerda","R", arbol.min());
		
		arbol.putInSet("M", 24);
		assertEquals("Deberia tener 3 elemento el arbol",3, arbol.size());
		assertEquals("El menor elemento no concuerda","M", arbol.min());
		
		arbol.putInSet("O", 25);
		assertEquals("Deberia tener 4 elemento el arbol",4, arbol.size());
		assertEquals("El menor elemento no concuerda","M", arbol.min());
		
		setUpEscenario2();
		
		assertEquals("Deberia tener 12 elemento el arbol",12, arbol.size());
		assertEquals("El menor elemento no concuerda","A", arbol.min());
		
	}
	@Test
	public void testMax()
	{
		arbol.putInSet("R", 22);
		
		assertEquals("Deberia tener 1 elemento el arbol",1, arbol.size());
		assertEquals("El mayor elemento no concuerda","R", arbol.max());

		arbol.putInSet("S", 23);
		
		assertEquals("Deberia tener 2 elemento el arbol",2, arbol.size());
		assertEquals("El mayor elemento no concuerda","S", arbol.max());
		
		arbol.putInSet("M", 24);
		
		assertEquals("Deberia tener 3 elemento el arbol",3, arbol.size());
		assertEquals("El mayor elemento no concuerda","S", arbol.max());
		
		arbol.putInSet("O", 25);
		
		assertEquals("Deberia tener 4 elemento el arbol",4, arbol.size());
		assertEquals("El mayor elemento no concuerda","S", arbol.max());
		
		setUpEscenario2();
		assertEquals("Deberia tener 12 elemento el arbol",12, arbol.size());
		assertEquals("El mayor elemento no concuerda","S", arbol.max());
		
	}
	@Test
	public void testCheck1()
	{
		arbol.putInSet("R", 22);
		assertTrue("Deberia estar balaciado el arbol", arbol.check());

		arbol.putInSet("S", 23);
		assertTrue("Deberia estar balaciado el arbol", arbol.check());

		arbol.putInSet("M", 24);
		assertTrue("Deberia estar balaciado el arbol", arbol.check());

		arbol.putInSet("O", 25);
		assertTrue("Deberia estar balaciado el arbol", arbol.check());
		

	}
	@Test
	public void testCheck2()
	{
		setUpEscenario2();
		assertTrue("Deberia estar balaciado el arbol", arbol.check());

	}
	@Test
	public void testCheck3()
	{
		setUpEscenario3();
		assertTrue("Deberia estar balaciado el arbol", arbol.check());

	}
	@Test
	public void testCheck4()
	{
		setUpEscenario4();
		assertTrue("Deberia estar balaciado el arbol", arbol.check());

	}
	@Test 
	public void testKeys1()
	{
		Iterator<String> it1 = arbol.keys();

		try
		{
			it1.next();
			fail("No deberia haber llaves asociados");
		}
		catch (NullPointerException e) 
		{
			
		}		

		//Prueba dos elementos
		arbol.putInSet("H", 23);

		arbol.putInSet("R", 18);

		assertNotNull("Deberia retornar los valores asociados",arbol.keys()); 
		Iterator<String>it=arbol.keys();
		int i = 0;
		while(it.hasNext())
		{
			String actual= it.next();
			assertNotNull("el elemento deberia encontrarse en el arbol",actual);
			if(i == 0)
				assertEquals("El elemento deberia coincidir", "H" ,actual);
			else
				assertEquals("El elemento deberia coincidir", "R" ,actual);
			i++;
		}
		
		
	}
	@Test 
	public void testKeys2()
	{
		setUpEscenario2();
		Iterator<String>it = arbol.keys();
		int i = 0;
		while(it.hasNext())
		{
			String actual = it.next();
			assertNotNull("el elemento deberia encontrarse en la tabla",actual);

			if(i == 0)
				assertEquals("No es el elemnto esperado", "A", actual);
			else if(i == 1)
				assertEquals("No es el elemnto esperado", "B", actual);
			else if(i == 2)
				assertEquals("No es el elemnto esperado", "C", actual);
			else if(i == 3)
				assertEquals("No es el elemnto esperado", "D", actual);
			else if(i == 4)
				assertEquals("No es el elemnto esperado", "E", actual);
			else if(i == 5)
				assertEquals("No es el elemnto esperado", "F", actual);
			else if(i == 6)
				assertEquals("No es el elemnto esperado", "G", actual);
			else if(i == 7)
				assertEquals("No es el elemnto esperado", "H", actual);
			
			i++;
		}		
	}
	@Test
	public void testValuesInRange()
	{		
		setUpEscenario2();
		
		Iterator<Integer> it= arbol.valuesInRange("B", "F");
		
		int i = 0;
		while(it.hasNext())
		{
			Integer actual = it.next();

			if(i == 0)
				assertEquals("No es el elemnto esperado", new Integer(2), actual);
			else if(i == 1)
				assertEquals("No es el elemnto esperado", new Integer(3), actual);
			else if(i == 2)
				assertEquals("No es el elemnto esperado", new Integer(4), actual);
			else if(i == 3)
				assertEquals("No es el elemnto esperado",new Integer(5), actual);
			else if(i == 4)
				assertEquals("No es el elemnto esperado", new Integer(6), actual);
			
			i++;
		}			
	}
	
	@Test
	public void testKeysInRange()
	{		
		setUpEscenario2();
		
		Iterator<String> it= arbol.keysInRange("B", "F");
		
		int i = 0;
		while(it.hasNext())
		{
			String actual = it.next();

			if(i == 0)
				assertEquals("No es el elemnto esperado", "B", actual);
			else if(i == 1)
				assertEquals("No es el elemnto esperado", "C", actual);
			else if(i == 2)
				assertEquals("No es el elemnto esperado", "D", actual);
			else if(i == 3)
				assertEquals("No es el elemnto esperado","E", actual);
			else if(i == 4)
				assertEquals("No es el elemnto esperado", "F", actual);
			
			i++;
		}		
	}	
}
