package com.beechase.ga.util;

import java.util.ArrayList;
import java.util.Random;

import com.beechase.ga.GeneticAlgorithm;


public class Sample
{
	public static void main(String[] args)
	{
		ArrayList<Double> fitnessValues;
		
		//fitnessValues =	GeneticAlgorithm.startEvolution(gen, fn, prec, pop, pc, pm)

//		Population size test cases
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 1, 8, 10, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 1, 8, 40, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 1, 8, 100, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 1, 8, 1000, 0.50, 0.01);
		
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 4, 8, 10, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 4, 8, 40, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 4, 8, 100, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 4, 8, 1000, 0.50, 0.01);
		
//		Cross over test cases
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 2, 8, 40, 0.25, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 2, 8, 40, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 2, 8, 40, 0.75, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 2, 8, 40, 0.90, 0.01);
		
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 5, 8, 40, 0.250, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 5, 8, 40, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 5, 8, 40, 0.750, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 5, 8, 40, 0.90, 0.01);
		
//		Mutation test cases
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 3, 8, 40, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 3, 8, 40, 0.50, 0.02);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 3, 8, 40, 0.50, 0.03);
		
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 6, 8, 40, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 6, 8, 40, 0.50, 0.02);
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 6, 8, 40, 0.50, 0.03);
		
		
//		Generations test cases
//		fitnessValues =	GeneticAlgorithm.startEvolution(200, 1, 8, 40, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(500, 1, 8, 40, 0.50, 0.01);
//		fitnessValues =	GeneticAlgorithm.startEvolution(1000, 1, 8, 40, 0.50, 0.01);
		int fn = 5;
		
		ArrayList<GAResult> results = GeneticAlgorithm.geneticalAlgorithmResults(1000, fn, 8, 60, 0.65, 0.01);
		
		//ArrayList<Double> fValues = GeneticAlgorithm.res_fXi;
		//ArrayList<String> xValues = GeneticAlgorithm.res_Xi;
		System.out.println("**********************************************************");
		System.out.println("Gen.\t\t Optimal Values");
		System.out.println("**********************************************************");
//		for (int i = 0; i< fValues.size();i++)
//		{
//			
//			System.out.println((i+1) + ".\t" + fValues.get(i) + "\t" + xValues.get(i));
//		}
		for (int i = 0; i< results.size();i++)
		{			
			System.out.println((i+1) + ".\t" + results.get(i).getOptimalFitnessValue() + "\t" + results.get(i).getxValues());
		}
		for(int i = 0; i < results.size(); i++)
		{
			GeneticAlgorithm.res_fXi.add(results.get(i).getOptimalFitnessValue());
		}
		
		if(fn <= 3)
		{
			GeneticAlgorithm.optimal_Xi = GeneticAlgorithm.max_Xi = ComputationUtility.maximumFitnessValue(GeneticAlgorithm.res_fXi);
			System.out.println("Maximum Value: " + GeneticAlgorithm.optimal_Xi);
		}
		else
		{
			GeneticAlgorithm.optimal_Xi = GeneticAlgorithm.min_Xi = ComputationUtility.minimumFitnessValue(GeneticAlgorithm.res_fXi);
			System.out.println("Minimum Value: " + GeneticAlgorithm.optimal_Xi);
		}

		System.out.println("**********************************************************");
	}
	
	public static void someMethod()
	{
		int randIntValue = 0, bit = 0;
		double randDoubleValue = 0.0f;
		
        for(int i=0; i< 10; i++)
        {
        	Random rand = new Random();
            randIntValue = rand.nextInt(10);
        	if(randIntValue < 5)
        		bit = 0;
        	else
        		bit = 1;
        	System.out.print("Int bit: " + bit);
        	randDoubleValue = rand.nextDouble()*10;
        	//randDoubleValue = rand.nextGaussian()*1000;
        	//System.out.println("Random Double Value: " + randDoubleValue);
        	if(randDoubleValue < 5)
        		bit = 0;
        	else
        		bit = 1;
        	System.out.print("\tDouble bit: " + bit);
        	
        	randDoubleValue = Math.sin(randDoubleValue*100);
        	//System.out.println("Random Double Value: " + randDoubleValue);
        	if(randDoubleValue < 0)
        		bit = 0;
        	else
        		bit = 1;
        	System.out.println("\tDouble bit: " + bit);
        }   
	}
}
