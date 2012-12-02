package com.beechase.ga.util;


public class GAResult
{
	public double optimalFitnessValue;
	public double totalFitnessValues;
	public String xValues;
	public int crossOverCounts;
	public int mutationCounts;
	
	
	
	public double getOptimalFitnessValue()
	{
		return optimalFitnessValue;
	}
	public void setOptimalFitnessValue(double optimalFitnessValue)
	{
		this.optimalFitnessValue = optimalFitnessValue;
	}
	public double getTotalFitnessValues()
	{
		return totalFitnessValues;
	}
	public void setTotalFitnessValues(double totalFitnessValues)
	{
		this.totalFitnessValues = totalFitnessValues;
	}
	public String getxValues()
	{
		return xValues;
	}
	public void setxValues(String xValues)
	{
		this.xValues = xValues;
	}
	public int getCrossOverCounts()
	{
		return crossOverCounts;
	}
	public void setCrossOverCounts(int crossOverCounts)
	{
		this.crossOverCounts = crossOverCounts;
	}
	public int getMutationCounts()
	{
		return mutationCounts;
	}
	public void setMutationCounts(int mutationCounts)
	{
		this.mutationCounts = mutationCounts;
	}
	
}
