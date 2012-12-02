/**
 * 
 */
package com.beechase.ga;

import java.util.ArrayList;

/**
 * @author balachandrasekharmanda
 * This class is used to set and retrieve the bean parameters for the
 * implementation of Genetic Algorithm.
 * 
 * Properties such as Population Size, Cross-over probability, Mutation rate
 * Size of Generations decide the characteristics of the solution given by a 
 * Genetic algorithm approach of identifying the optimal values for a function
 * 
 */
public class GeneBean
{
	int selectedFunction = 1;
	int populationSize = 0;
	int precision = 0;
	double crossOverProbability;// = 0.25;
	double mutationRateProbability;// = 0.01;
	int maxGenerations;// = 1000;
	double optimalValue;
	ArrayList<Double> variableValues;
	ArrayList<Chromosome> chromosomes;
	
	
	public int getCurrentFunction()
	{
		return this.selectedFunction;
	}
	public void setCurrentFunction(int val)
	{
		this.selectedFunction = val;
	}	
	
	public int getPopulationSize()
	{
		return this.populationSize;
	}
	public void setPopulationSize(int val)
	{
		this.populationSize = val;
	}
	
	public int getPrecision()
	{
		return this.precision;
	}
	public void setPrecision(int val)
	{
		this.precision = val;
	}
	
	public double getCrossOverProbability()
	{
		return this.crossOverProbability;
	}
	public void setCrossOverProbability(double val)
	{
		this.crossOverProbability = val;
	}
	
	public double getProbabilityMutationRate()
	{
		return this.mutationRateProbability;
	}
	public void setProbabilityMutationRate(double val)
	{
		this.mutationRateProbability = val;
	}
	
	public int getMaxGenerations()
	{
		return this.maxGenerations;
	}
	public void setMaxGenerations(int val)
	{
		this.maxGenerations = val;
	}
	
	
	public void printBeanDetails()
	{
//		String s = "";
//		s = "********************************************************************\n"; 
//		if(getSelectedFunction()== 1)
//			s += "\nSelected function is: " + new Function1().toString(); 
//		else if(getSelectedFunction()== 2)
//			s += "\nSelected function is: " + new Function2().toString();
//		s += "\nSelected Population Size: " + getPopulationSize() + 
//			 "\nSelected Precision Digits: " + getPrecision() + 
//			 "\nSelected Probability for Cross overs: " + getProbabilityCrossOver() + 
//			 "\nSelected Probability for Mutations allowed: " + getProbabilityMutationRate() + 
//			 "\nSelected Required Generations for the solution: " + getGenerations() + 		
//			 "\n********************************************************************";
//		System.out.println(s);
//		return s;
	}
	/**
	 * @return the selectedFunction
	 */
	public int getSelectedFunction()
	{
		return selectedFunction;
	}
	/**
	 * @param selectedFunction the selectedFunction to set
	 */
	public void setSelectedFunction(int selectedFunction)
	{
		this.selectedFunction = selectedFunction;
	}
	/**
	 * @return the mutationRateProbability
	 */
	public double getMutationRateProbability()
	{
		return mutationRateProbability;
	}
	/**
	 * @param mutationRateProbability the mutationRateProbability to set
	 */
	public void setMutationRateProbability(double mutationRateProbability)
	{
		this.mutationRateProbability = mutationRateProbability;
	}
	/**
	 * @return the optimalValue
	 */
	public double getOptimalValue()
	{
		return optimalValue;
	}
	/**
	 * @param optimalValue the optimalValue to set
	 */
	public void setOptimalValue(double optimalValue)
	{
		this.optimalValue = optimalValue;
	}
	/**
	 * @return the variableValues
	 */
	public ArrayList<Double> getVariableValues()
	{
		return variableValues;
	}
	/**
	 * @param variableValues the variableValues to set
	 */
	public void setVariableValues(ArrayList<Double> variableValues)
	{
		this.variableValues = variableValues;
	}
	/**
	 * @return the chromosomes
	 */
	public ArrayList<Chromosome> getChromosomes()
	{
		return chromosomes;
	}
	/**
	 * @param chromosomes the chromosomes to set
	 */
	public void setChromosomes(ArrayList<Chromosome> chromosomes)
	{
		this.chromosomes = chromosomes;
	}
}
