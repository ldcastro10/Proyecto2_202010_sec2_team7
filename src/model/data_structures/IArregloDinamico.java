package model.data_structures;

import java.util.Iterator;

public interface IArregloDinamico <T>{

	/**
	 * 
	 */
	int darTamano( );
	
	/**
	 * 
	 */
	T darElemento( int i );

	/**
	 * 
	 */
	public void agregar( T dato );
	
	/**
	 * 
	 */
	Iterator<T> iterator();

}
