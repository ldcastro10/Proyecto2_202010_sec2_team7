package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.data_structures.ArregloDinamico;
import model.logic.Comparendo;
import model.logic.MVCModelo;

public class MVCView 
{
	/**
	 * Metodo constructor
	 */
	public MVCView()
	{

	} 

	public void printMenu()  
	{
		System.out.println("1. Cargar archivo");
		System.out.println("2. Mostrar los M comparendos mas graves");
		System.out.println("3. Buscar comparendos por mes y dia de la semana");
		System.out.println("4. Buscar comparendos por rango de fechas y localidad");
		System.out.println("5. Mostrar tabla ASCII");
		System.out.println("6. Requerimiento 2C");
		System.out.println("7. Requerimiento 3C");
	}


	public void printMessageCargar(MVCModelo modelo) 
	{
		System.out.println("Numero de comparendos cargados: "+ modelo.cantidadComparendos());	
		System.out.println("Comparendo con id mayor: " + modelo.darComparendoMayorID().toString());
	}

	public void printComparendosMayorGravedad(ArregloDinamico<Comparendo> arr) 
	{
		for (int i = 0; i < arr.darTamano(); i++) 
		{	
			System.out.println(arr.darElemento(i));			
		}		
	}

	public void printComparendosMesYDia(ArregloDinamico<Comparendo> arr) 
	{
		for (int i = 0; i < arr.darTamano(); i++) 
		{	
			System.out.println(arr.darElemento(i));			
		}		
	}

	public void printComparendosRangoDeFechaYLocalidad(ArregloDinamico<Comparendo> arr) 
	{
		for (int i = 0; i < arr.darTamano(); i++) 
		{	
			System.out.println(arr.darElemento(i));			
		}		
	}

	public void printTablaASCII(ArregloDinamico<Integer> arr, int dias) 
	{
		System.out.println("Rango de fechas       | Comparendos durante el año");
		System.out.println("--------------------------------------------------");

		SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd");

		try 
		{
			Date date1 = spf.parse("2018/01/01");
			
			int i = 0;

			while (date1.compareTo(spf.parse("2019/01/01")) <= 0)
			{
				Calendar c = Calendar.getInstance();
				c.setTime(date1);

				c.add(Calendar.DAY_OF_MONTH, dias);

				Date date2 = c.getTime();
				
				System.out.print(spf.format(date1) + "-" + spf.format(date2) + "|");
				
				
				for (int j = 0; j < arr.darElemento(i); j++) 				
					System.out.print("*");				

				System.out.println();
				i++;
				
				date1=date2;
			}


		} 
		catch (ParseException e) 
		{			
			e.printStackTrace();
		}
	}

	public void printReq2C(ArregloDinamico arr) 
	{
		int costoTotal = (int) arr.darElemento(0);
		int promedioEspera = (int) arr.darElemento(1);
		
		ArregloDinamico<ArregloDinamico<Integer>> datos = (ArregloDinamico<ArregloDinamico<Integer>>) arr.darElemento(2);
		ArregloDinamico<ArregloDinamico<Integer>> datos2 = (ArregloDinamico<ArregloDinamico<Integer>>) arr.darElemento(3);
		
		double limite = (double) arr.darElemento(4);
		
		System.out.println("Fecha      | Comparendos procesados            ***");
		System.out.println("           | Comparendos que estan en espera   ###");
		System.out.println("--------------------------------------------------");

		SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd");

		try 
		{
			Date date = spf.parse("2018/01/01");
			
			int i = 0;
			
			while (date.compareTo(spf.parse("2019/01/01")) < 0)
			{
				ArregloDinamico<Integer> datosPorFecha = datos.darElemento(i);
				
				if(datosPorFecha.darElemento(0) == null)
					break;
				
				System.out.print(spf.format(date) + " |");
				
				
				int cant1 = (int) ((datosPorFecha.darElemento(0) / limite) * 100);
				
				for (int j = 0; j < cant1; j++) 				
					System.out.print("*");				

				System.out.println();
				System.out.print("           |");
				
				int cant2 =  (int) ((datosPorFecha.darElemento(1) / limite) * 100);
				
				for (int j = 0; j < cant2; j++) 				
					System.out.print("#");
				
				System.out.println();
				
				Calendar c = Calendar.getInstance();
				c.setTime(date);				
				
				c.add(Calendar.DAY_OF_MONTH, 1);
				
				date = c.getTime();
				i++;
				
			}


		} 
		catch (ParseException e) 
		{			
			e.printStackTrace();
		} 
		
		System.out.println();
		
		ArregloDinamico<Integer> datos400 = datos2.darElemento(0);
		ArregloDinamico<Integer> datos40 = datos2.darElemento(1);
		ArregloDinamico<Integer> datos4 = datos2.darElemento(2);
		System.out.println("Costo diario comparendo | Tiempo minimo | Tiempo promedio | Tiempo maximo");
		System.out.println("$400                    | " + datos400.darElemento(0) + " | " + datos400.darElemento(2) + " | " + datos400.darElemento(1));
		System.out.println("$40                     | " + datos40.darElemento(0) + " | " + datos40.darElemento(2) + " | " + datos40.darElemento(1) );
		System.out.println("$4                      | " + datos4.darElemento(0) + " | " + datos4.darElemento(2) + " | " + datos4.darElemento(1) );
		
		System.out.println();
		
		System.out.println("Costo total de penalizacion que se genera en 2018: " + costoTotal);
		System.out.println("Numero promedio de dias que debe esperar un comparendo para ser procesado: " + promedioEspera);
	}
	
	

}
