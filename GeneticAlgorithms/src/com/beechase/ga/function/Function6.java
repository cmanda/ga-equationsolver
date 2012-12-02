package com.beechase.ga.function;

/**
 * 
 * @author balachandrasekharmanda
 * This class is the Definition class for the Function f3 
 * f3(x1, x2)  = 1/4000 * (x1^2+x2^2) - cos(x1) * cos(x2 / sqrt(2)) + 1;
 * -600 <= x1 <= 600 -600 <= x2 <= 600
 */
public class Function6
{
	public static final double minX1 = -600.0f;
	public static final double maxX1 =  600.0f;
	public static double rangeX1 = maxX1 - minX1; 
		
		
	public static final double minX2 = -600.0f;
	public static final double maxX2 =  600.f;
	public static double rangeX2 = maxX2 - minX2;
	
	int chromosomeLenth = 0;
	
	public String toString()
	{
		return "Max{f2(x1,x2)=1/4000*(x1^2+x2^2)-cos(x1)*cos(x2/sqrt(2)), x1,x2 in [-600,600]";
	}
	
	public static double computeFunction(double x1, double x2)
	{
		return -(0.00025*(Math.pow(x1,2) + Math.pow(x2,2)) - (Math.cos(x1))*(Math.cos(x2*Math.sqrt(0.5)))) ;
	}

}
