package com.beechase.ga.function;

/**
 * 
 * @author balachandrasekharmanda
 * 
 * This class is the Definition class for the Function f1
 * f1(x) = x * sin(10¹ * x) + 1.0; -1.00<= x <= 2.00 
 *
 */
public class Function1
{
	public static final double minX = -1.0f;
	public static final double maxX =  2.0f;
	public static double rangeX = maxX - minX; 
		
	int chromosomeLenth = 0;
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "Max{f1(x)}: x*sin(10*PI*x)+1.0, x in [-1,2]";
	}
	

	/**
	 * This method takes the value of x and computes the value
	 * of the function for the value of x, i.e. it calculates f(x)
	 * 
	 * @param 	x		input value to the function
	 * @return	f(x)	output value of the function
	 */
	public static double computeFunction(double x)
	{
		return (x*(Math.sin(10*Math.PI*x)) + 1.0);
	}


}
