package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

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
	public ArregloDinamico Parte2CY3C(boolean ordenar)
	{
		ArregloDinamico resultado = new ArregloDinamico<>(4);
		ArregloDinamico<Comparendo> listaComparendos = this.listaComparendos;
		
		if(ordenar)
			listaComparendos = ordenarComparendos();	
		
		int costoTotal = 0;
		
		int promedioEspera = 0;
		
		ArregloDinamico<ArregloDinamico<Integer>> datos = new ArregloDinamico<>(365);
		
		ArregloDinamico<Integer> tiempos400 = new ArregloDinamico<>(3);
		int tiempoMin400 = 365;
		int tiempoMax400 = 0;
		int tiempoPro400 = 0;
		int cantNoProce400 = 0;

		ArregloDinamico<Integer> tiempos40 = new ArregloDinamico<>(3);
		int tiempoMin40 = 365;
		int tiempoMax40 = 0;
		int tiempoPro40 = 0;
		int cantNoProce40 = 0;

		ArregloDinamico<Integer> tiempos4 = new ArregloDinamico<>(3);
		int tiempoMin4 = 365;
		int tiempoMax4 = 0;
		int tiempoPro4 = 0;
		int cantNoProce4 = 0;

		ArregloDinamico<ArregloDinamico<Integer>> datos2 = new ArregloDinamico<>(3);
		double limite = listaComparendos.darTamano();			

		try 
		{
			int index = 0;
			
			int compEnEspera = (int) limite;
			
			SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd");
			Date date1 = spf.parse("2018/01/01");
			
			Calendar c = Calendar.getInstance();
			c.setTime(date1);
			int dia = c.get(Calendar.DAY_OF_YEAR);

			while(date1.compareTo(spf.parse("2019/01/01")) <= 0 && limite != index)
			{
				int compRevisados = 0;
				

				while(compRevisados < 1500 && limite != index)
				{
					Comparendo comp = listaComparendos.darElemento(index);
					Date fechaComp = comp.getFecha();
					c.setTime(fechaComp);
					
					int diaComp = c.get(Calendar.DAY_OF_YEAR);							
					int diferencia = diaComp-dia;
					
					if(diferencia > 0)
					{
						String descripcion = comp.getDescripcionInfraccion();

						if(descripcion.contains("SERA INMOVILIZADO") || descripcion.contains("SERÁ INMOVILIZADO"))	
						{
							costoTotal += diferencia*400;

							if(tiempoMax400 < diferencia)
								tiempoMax400 = diferencia;
							if(tiempoMin400 > diferencia)
								tiempoMin400 = diferencia;

							tiempoPro400 += diferencia;
							promedioEspera += diferencia;
							
							cantNoProce400++;

						}
						else if(descripcion.contains("LICENCIA DE CONDUCCIÓN"))
						{
							costoTotal += diferencia*40;

							if(tiempoMax40 < diferencia)
								tiempoMax40 = diferencia;
							if(tiempoMin40 > diferencia)
								tiempoMin40 = diferencia;

							tiempoPro40 += diferencia;
							promedioEspera += diferencia;
							
							cantNoProce40++;
						}
						else
						{
							costoTotal += diferencia*4;

							if(tiempoMax4 < diferencia)
								tiempoMax4 = diferencia;
							if(tiempoMin4 > diferencia)
								tiempoMin4 = diferencia;

							tiempoPro4 += diferencia;
							promedioEspera += diferencia;
							
							cantNoProce4++;
						}						
					}
					
					index++;
					compRevisados++;
				}
				
				compEnEspera -= compRevisados;				
				
				//Cantidad de comparendos revisados 
				datos.agregar(new ArregloDinamico<Integer>(2));
				datos.darElemento(dia-1).agregar(compRevisados);

				//Cantidad de comparendos en espera 
				datos.agregar(new ArregloDinamico<Integer>(2));
				datos.darElemento(dia-1).agregar(compEnEspera);	
						
				c.setTime(date1);
				c.add(Calendar.DAY_OF_MONTH, 1);
				date1 = c.getTime();
				dia = c.get(Calendar.DAY_OF_YEAR);

			}
			
			tiempoPro400 = tiempoPro400 / (cantNoProce400 != 0? cantNoProce400: Integer.MAX_VALUE);
			tiempoPro40 = tiempoPro40 / (cantNoProce40 != 0? cantNoProce40: Integer.MAX_VALUE);
			tiempoPro4 = tiempoPro4 / (cantNoProce4 != 0? cantNoProce4: Integer.MAX_VALUE);
			
			promedioEspera = promedioEspera / 365;
			
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
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
		
		
		
		resultado.agregar(costoTotal);

		resultado.agregar(promedioEspera);

		resultado.agregar(datos);

		resultado.agregar(datos2);
		
		resultado.agregar(limite);


		return resultado;
	}

	private ArregloDinamico<Comparendo> ordenarComparendos() 
	{
		QueueHeap<Comparendo> q = new QueueHeap<>();
		ArregloDinamico<Comparendo> ordenados = new ArregloDinamico<>(1);
		
		for (int i = 0; i < listaComparendos.darTamano(); i++) 
		{
			Comparendo c = listaComparendos.darElemento(i);
			
			q.enqueue(c);
		}
		
		while(!q.isEmpty())		
			ordenados.agregar(q.dequeueMax());
		
		
		return ordenados;
	}
}	