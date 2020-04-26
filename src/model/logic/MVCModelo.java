package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.HashTableSeparateChaining;
import model.data_structures.QueueHeap;
import model.data_structures.RedBlackBST; 
/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo 
{ 

	/**
	 * Atributo de la lista comparendos
	 */
	private ArregloDinamico<Comparendo> listaComparendos;


	private Comparendo mayoId;


	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{

		listaComparendos = new ArregloDinamico<>(1); 
	}

	public void cargarArchivoJSON(String ruta) throws FileNotFoundException
	{
		JsonReader reader;
		try 
		{
			reader = new JsonReader(new FileReader(ruta));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray array = elem.getAsJsonObject().get("features").getAsJsonArray();
			listaComparendos = new ArregloDinamico<>(array.size());

			SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

			int i = 0;
			for(JsonElement e: array) 
			{
				Long OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsLong();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				String MUNICIPIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("MUNICIPIO").getAsString();

				Long longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsLong();

				Long latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsLong();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, DES_INFRAC, LOCALIDAD, MUNICIPIO, latitud, longitud);

				if(listaComparendos.darElemento(i) != null)
				{
					if(listaComparendos.darElemento(i).getId() < c.getId());
					mayoId = c;
				}
				else
					mayoId = c;


				listaComparendos.agregar(c);
				i++;
			}

		} 
		catch (FileNotFoundException | ParseException e) 
		{
			e.printStackTrace();
		}

	}	

	public int cantidadComparendos()
	{
		return listaComparendos.darTamano();
	}

	public Comparendo darComparendoMayorID()
	{
		return mayoId;
	}



	//A

	public ArregloDinamico<Comparendo> obtnerLosMComparendosConMayorGravedad(int m)
	{
		QueueHeap<Comparendo> queue = new QueueHeap<>();
		Comparator<Comparendo> com = new ComparadorGravedad();

		ArregloDinamico<Comparendo> result = new ArregloDinamico<>(1);

		for (int i = 0; i < listaComparendos.darTamano(); i++) 
		{
			Comparendo c = listaComparendos.darElemento(i);

			queue.enqueue(c, com);			
		}

		for (int i = 0; i < m; i++) 
		{
			Comparendo c = queue.dequeueMax();

			result.agregar(c);			
		}

		return result;
	}


	public ArregloDinamico<Comparendo> buscarComparendosPorMesYDiaSemana(int mes, String dia)
	{
		ArregloDinamico<Comparendo> result = new ArregloDinamico<>(1);
		HashTableSeparateChaining<String, Comparendo> hash = new HashTableSeparateChaining<>(1);

		Calendar calendar = Calendar.getInstance();

		for (int i = 0; i < listaComparendos.darTamano(); i++) 
		{
			Comparendo com = listaComparendos.darElemento(i);

			calendar.setTime(com.getFecha());

			int mesComp = calendar.get(Calendar.MONTH)+1;

			int diaComp = calendar.get(Calendar.DAY_OF_WEEK);

			String key = mesComp + "-" + diaComp;

			hash.putInSet(key, com);			
		}

		int diaNum = 0;

		switch (dia) 
		{
		case "L":
			diaNum = 1;
			break;
		case "M":
			diaNum = 2;
			break;
		case "I":
			diaNum = 3;
			break;
		case "J":
			diaNum = 4;
			break;
		case "V":
			diaNum = 5;
			break;
		case "S":
			diaNum = 6;
			break;
		case "D":
			diaNum = 7;
			break;
		}

		Iterator<Comparendo> it = hash.getSet(mes + "-" + diaNum);
		if(it != null)
		{
			while(it.hasNext())
				result.agregar(it.next());			
		}

		return result;	
	}


	public ArregloDinamico<Comparendo> buscarComparendosPorRangoDeFechaYLocalidad(String fecha1, String fecha2, String localidad) throws ParseException
	{
		RedBlackBST<Date, Comparendo> arbol = new RedBlackBST<>();
		ArregloDinamico<Comparendo> result = new ArregloDinamico<>(1);

		SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");

		Date date1 = spf.parse(fecha1);
		Date date2 = spf.parse(fecha2);

		for (int i = 0; i < listaComparendos.darTamano(); i++) 
		{
			Comparendo comp = listaComparendos.darElemento(i);
			Date date = comp.getFecha();

			arbol.putInSet(date, comp);
		}

		Iterator<Comparendo> it = arbol.valuesInRange(date1, date2);

		while(it.hasNext())
		{   
			Comparendo comp = it.next();

			if(comp.getLocalidad().equals(localidad))
				result.agregar(comp);
		}

		return result;
	}


	//C

	public ArregloDinamico<Integer> mostrartablaASCII(int dias) 
	{
		RedBlackBST<Date, Comparendo> arbol = new RedBlackBST<>();
		ArregloDinamico<Integer> result = new ArregloDinamico<>(1);


		for (int i = 0; i < listaComparendos.darTamano(); i++) 
		{
			Comparendo comp = listaComparendos.darElemento(i);
			Date fecha = comp.getFecha();

			arbol.putInSet(fecha, comp);
		}

		try
		{

			SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd");

			Date date1 = spf.parse("2018/01/01");

			while (date1.compareTo(spf.parse("2019/01/01")) <= 0)
			{

				Calendar c = Calendar.getInstance();
				c.setTime(date1);

				c.add(Calendar.DAY_OF_MONTH, dias);

				Date date2 = c.getTime();

				Iterator<Comparendo> it = arbol.valuesInRange(date1, date2);

				int cant = 0;

				while(it.hasNext())
				{   
					it.next();	
					cant++;
				}

				result.agregar(cant);

				date1 = date2;
			}
		}
		catch (ParseException e) 
		{		
			e.printStackTrace();
		}

		return result;
	}


	/*
	 * Todos los datos de la parte se se guardan en un arreglo dinamico
	 */
	public ArregloDinamico Parte2C()
	{
		ArregloDinamico resultado = new ArregloDinamico<>(3);		


		Comparable<Comparendo>[] comparendosOrdenados = listaComparendos.toArray();
		sortMerge(comparendosOrdenados);		

		int costoTotal = 0;	

		int promedioEspera = 0;

		ArregloDinamico<ArregloDinamico<Integer>> datos = new ArregloDinamico<>(365);

		ArregloDinamico<Integer> tiempos400 = new ArregloDinamico<>(3);
		int tiempoMin400 = Integer.MAX_VALUE;
		int tiempoMax400 = 0;
		int tiempoPro400 = 0;

		ArregloDinamico<Integer> tiempos40 = new ArregloDinamico<>(3);
		int tiempoMin40 = Integer.MAX_VALUE;
		int tiempoMax40 = 0;
		int tiempoPro40 = 0;

		ArregloDinamico<Integer> tiempos4 = new ArregloDinamico<>(3);
		int tiempoMin4 = Integer.MAX_VALUE;
		int tiempoMax4 = 0;
		int tiempoPro4 = 0;

		ArregloDinamico<ArregloDinamico<Integer>> datos2 = new ArregloDinamico<>(3);


		try
		{			
			int numeroDia = 0; 

			SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd");
			Date date1 = spf.parse("2018/01/01");

			int index = 0;
			int compEnEspera = 0;

			while(date1.compareTo(spf.parse("2019/01/01")) <= 0 && comparendosOrdenados.length != index)
			{
				int compRevisados = 0;


				Calendar c = Calendar.getInstance();
				c.setTime(date1);
				int dia = c.get(Calendar.DAY_OF_YEAR);				

				Comparendo comp = (Comparendo) comparendosOrdenados[index];
				Date fechaComp = comp.getFecha();
				c.setTime(fechaComp);

				int diaComp = c.get(Calendar.DAY_OF_YEAR);							
				int diferencia = diaComp-dia;

				while(compRevisados < 1500 && diferencia == 0) 
				{	
					compRevisados++;
					index++;
					if(index == comparendosOrdenados.length)
						break;
					
					if(diferencia != 0)
						compEnEspera--;

					comp = (Comparendo) comparendosOrdenados[index];
					fechaComp = comp.getFecha();
					c.setTime(fechaComp);

					diaComp = c.get(Calendar.DAY_OF_YEAR);
					diferencia = diaComp-dia;

				}	

				int indexEspera = index;
				if(index != comparendosOrdenados.length)
				{
					Comparendo compEspera = (Comparendo) comparendosOrdenados[indexEspera];
					Date fechaCompEpera = comp.getFecha();
					c.setTime(fechaComp);

					int diaCompEspera = c.get(Calendar.DAY_OF_YEAR);							
					int diferencia2 = diaComp-dia;

					while(diferencia2 == 0)
					{
						//Calcular penalizaciones

						String descripcion = comp.getDescripcionInfraccion();

						if(descripcion.contains("SERA INMOVILIZADO") || descripcion.contains("SERÁ INMOVILIZADO"))	
						{
							costoTotal += 400;

							if(tiempoMax400 < diferencia2)
								tiempoMax400 = diferencia2;
							if(tiempoMin400 > diferencia2)
								tiempoMin400 = diferencia2;

							tiempoPro400 += diferencia2;

						}
						else if(descripcion.contains("LICENCIA DE CONDUCCIÓN"))
						{
							costoTotal += diferencia2*40;

							if(tiempoMax40 < diferencia2)
								tiempoMax40 = diferencia2;
							if(tiempoMin40 > diferencia2)
								tiempoMin40 = diferencia2;

							tiempoPro40 += diferencia2;
						}
						else
						{
							costoTotal += diferencia2*4;

							if(tiempoMax4 < diferencia2)
								tiempoMax4 = diferencia2;
							if(tiempoMin4 > diferencia2)
								tiempoMin4 = diferencia2;

							tiempoPro4 += diferencia2;
						}

						promedioEspera += diferencia2;

						compEnEspera++;
						indexEspera++;
						if(indexEspera == comparendosOrdenados.length)
							break;

						compEspera = (Comparendo) comparendosOrdenados[indexEspera];
						fechaCompEpera = comp.getFecha();
						c.setTime(fechaComp);

						diaCompEspera = c.get(Calendar.DAY_OF_YEAR);
						diferencia2 = diaComp-dia;
					}
				}

				c.setTime(date1);
				c.add(Calendar.DAY_OF_MONTH, 1);

				date1 = c.getTime();

				//Cantidad de comparendos revisados 
				datos.agregar(new ArregloDinamico<Integer>(2));
				datos.darElemento(numeroDia).agregar(compRevisados);

				//Cantidad de comparendos en espera 
				datos.agregar(new ArregloDinamico<Integer>(2));
				datos.darElemento(numeroDia).agregar(compEnEspera);				

				numeroDia++;
			}

			tiempos400.agregar(tiempoMin400);
			tiempos400.agregar(tiempoMax400);
			tiempos400.agregar(tiempoPro400);

			tiempos40.agregar(tiempoMin40);
			tiempos40.agregar(tiempoMax40);
			tiempos40.agregar(tiempoPro40);

			tiempos4.agregar(tiempoMin4);
			tiempos4.agregar(tiempoMax4);
			tiempos4.agregar(tiempoPro4);

			datos2.agregar(tiempos400);
			datos2.agregar(tiempos40);
			datos2.agregar(tiempos4);

			promedioEspera = promedioEspera/comparendosOrdenados.length;	


		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}

		resultado.agregar(costoTotal);

		resultado.agregar(promedioEspera);

		resultado.agregar(datos);

		resultado.agregar(datos2);


		return resultado;
	}


	public void sortMerge(Comparable<Comparendo>[] a)
	{
		Comparendo[] aux = new Comparendo[a.length];
		sortMerge2(a, aux, 0, a.length-1);		
	} 

	private void sortMerge2(Comparable<Comparendo>[] a, Comparable<Comparendo>[] aux, int lo, int hi) 
	{
		if (hi <= lo) 
			return;
		int mid = lo + (hi - lo) / 2;
		sortMerge2(a, aux, lo, mid);
		sortMerge2(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);

	}

	public void merge(Comparable<Comparendo>[] a, Comparable<Comparendo>[] aux, int lo, int mid, int hi)
	{
		for (int k = lo; k <= hi; k++) 
		{
			aux[k] = a[k]; 
		}

		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) 
		{
			if      (i > mid)              
				a[k] = aux[j++];
			else if (j > hi)              
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else                           
				a[k] = aux[i++];
		}
	}

	private boolean less(Comparable<Comparendo> v, Comparable<Comparendo> w)
	{
		return v.compareTo((Comparendo) w)<0;
	}


}	