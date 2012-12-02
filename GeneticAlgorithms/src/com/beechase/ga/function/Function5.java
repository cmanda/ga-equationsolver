package com.beechase.ga.function;

/**
 * 
 * @author balachandrasekharmanda
 * This class is the Definition class for the Function f2 
 * f2(x1, x2)  = 418.9829*2 Ð x1 * sin(sqrt(abs(x1))) Ð x2 * sin(sqrt(abs(x2)));  
 * -500 <= x1 <= 500, -500 <= x2 <= 500
 *
 */
public class Function5
{
	public static final double minX1 = -500.00f;
	public static final double maxX1 =  500.00f;
	public static double rangeX1 = maxX1 - minX1; 
		
		
	public static final double minX2 = -500.00f;
	public static final double maxX2 =  500.00f;
	public static double rangeX2 = maxX2 - minX2;
	
	int chromosomeLenth = 0;
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "Min{f2(x1,x2)}= 418.9829*2-x1*sin(sqrt(abs(x1)))-x2*sin(sqrt(abs(x2))), x1,x2 in [-500,500]";
	}
	
	/**
	 * This method takes the value of x1, x2 and computes the value
	 * of the function for the value of x1, x2, i.e. it calculates f(x1,x2) 
	 * @param 	x1			input value for the variable x1
	 * @param 	x2			input value for the variable x2
	 * @return	f(x1,x2)	output value for the function f(x1, x2)
	 */
	public static double computeFunction(double x1, double x2)
	{
		return -837.9658 
				+ x1*(Math.sin(Math.sqrt(Math.abs(x1)))) 
				+ x2*(Math.sin(Math.sqrt(Math.abs(x2))));
	}


}
