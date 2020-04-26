package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}
		
	public void run()     
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin )
		{
			view.printMenu();

			int option = lector.nextInt();
			switch(option)
			{
				case 1:
					try 
				    {
						System.out.println("Ingresar el archivo a cargar: ");
						int archivo = lector.nextInt();
						
						switch (archivo) 
						{	
							case 1:
								modelo.cargarArchivoJSON("./data/Comparendos_DEI_2018_Bogotá_D.C_small.geojson");	
								break;
							case 2:
								modelo.cargarArchivoJSON("./data/Comparendos_DEI_2018_Bogotá_D.C_small_50000_sorted.geojson");	
								break;
							case 3:
								modelo.cargarArchivoJSON("./data/Comparendos_DEI_2018_Bogotá_D.C_50000_.geojson");
								break;
							case 4:
								modelo.cargarArchivoJSON("./data/Comparendos_DEI_2018_Bogotá_D.C.geojson");
								break;

							default:
								System.out.println("Archivo erroneo");
								break;
						}
											
						
						System.out.println("Informacion Cargada");							
						view.printMessageCargar(modelo);
				    }
				    catch (IOException e) 
				    { 
				    	System.out.println("Se genero un error: " + e.getMessage() );						
				    }				    
					break;
					
				case 2:
					
					System.out.println("Cantidad de datos a mostrar: ");
					int cant = lector.nextInt();
					
					view.printComparendosMayorGravedad(modelo.obtnerLosMComparendosConMayorGravedad(cant));
					
					break;
					
				case 3:
					
					System.out.println("Mes del año: ");
					int mes = lector.nextInt();
					
					System.out.println("Dia semana: ");
					String dia = lector.next();
					
					view.printComparendosMesYDia(modelo.buscarComparendosPorMesYDiaSemana(mes, dia));
					
					break;
					
				case 4:
					System.out.println("Ingresar rango fecha: ");
					
					String fecha1 = lector.next();
					String fecha2 = lector.next();
					
					System.out.println("Ingresar localidad: ");
					
					String localidad = lector.next();
					
					try 
					{
						view.printComparendosRangoDeFechaYLocalidad(modelo.buscarComparendosPorRangoDeFechaYLocalidad(fecha1, fecha2, localidad));
					}
					catch (ParseException e) 
					{
						System.out.println("Se presento un error de formato");
					}
					break;
					
				case 5: 
					System.out.println("Ingrese el intervalo de dias:");
					int dias = lector.nextInt();
					
					view.printTablaASCII(modelo.mostrartablaASCII(dias), dias);					
					break;
					
				case 6:
					view.printReq2C(modelo.Parte2CY3C(false));
					break;
				case 7:
					view.printReq2C(modelo.Parte2CY3C(true));
					break;
					
				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
