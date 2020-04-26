package model.logic;

import java.util.Comparator;

public class ComparadorGravedad implements Comparator<Comparendo> {

	@Override
	public int compare(Comparendo c1, Comparendo c2) 
	{
		String s1 = c1.getTipoServicio();
		String s2 = c2.getTipoServicio();	
		
		String i1 = c1.getInfraccion();
		String i2 = c2.getInfraccion();
		
		if(!s1.equals(s2))
		{
			if(s1.equals("Público"))
				return 1;
			if(s2.equals("Público"))
				return -1;
			
			if(s1.equals("Oficial"))
				return 1;
			if(s2.equals("Oficial"))
				return -1;
			
			if(s1.equals("Particular"))
				return 1;
			if(s2.equals("Particular"))
				return -1;
		}
		else
		{
			return i1.compareTo(i2);
		}
		
		return 0;
	}
}
