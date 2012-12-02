package com.beechase.ga;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.beechase.ga.util.ComputationUtility;
import com.beechase.ga.util.GAResult;
import com.beechase.ga.util.LogFileWriter;


public class GeneticAlgorithm
{
	static GeneBean gaBean;
	long chromosomeLength = 0;
	ArrayList<String> initChromosomeSet, updatedChromosomeSet, crossedOverChromosomeSet, mutatedChromosomeSet;
	ArrayList<Double> evalFunctionValues, probabilitySelectionValues, cumulativeProbabilityValues;
	static boolean logEnabled = false;
	
	public static ArrayList<Element> chromosomesXmlElements = new ArrayList<Element>();
	public static ArrayList<Element> resultXmlElements = new ArrayList<Element>();
	public static ArrayList<Element> generationXmlElements = new ArrayList<Element>();
	
	public static ArrayList<Double> res_fXi= new ArrayList<Double>();
	public static ArrayList<String> res_Xi = new ArrayList<String>();
	public static double max_Xi = 0.0f;
	public static double min_Xi = 0.0f;
	public static double optimal_Xi = 0.0f;
	
	Document root = new Document();
	
	/**
	 * This method is used to run the program from the command line
	 * @param args
	 * @throws IOException 
	 */

	public static void main(String[] args) throws IOException
	{	
		
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(); 
	
		gaBean = ComputationUtility.readInputParameters();	
		
		int generations = gaBean.getMaxGenerations();
		
		System.out.println("*******************************************************************************************");		
		System.out.println("Gen.\t\t\tOPTIMUM{Eval(Xi)}\t\t\tXi");
		System.out.println("*******************************************************************************************");
		geneticAlgorithm.chromosomeLength = ComputationUtility.computeSizeOfChromosome(gaBean.getCurrentFunction(), gaBean.getPrecision());
		System.out.println("Computing size of the chromosomes: " + geneticAlgorithm.chromosomeLength);
		
		geneticAlgorithm.initChromosomeSet = ComputationUtility.getInitChromosomes((int)geneticAlgorithm.chromosomeLength, gaBean.getPopulationSize());
		//System.out.println("Initializing the chromosome set: " + gac.initChromosomeSet.toString());
		//updateContent(chromosomesContent,gac.initChromosomeSet);
		
		//Element head = LogFileWriter.createHeadElement(gaBean);

		for(int i = 0; i < generations; i++)		
		{
			//Element chromo = LogFileWriter.createChromosomesElement(geneticAlgorithm.initChromosomeSet, "initial set");
			
			//chromosomesXmlElements.add(chromo);
			
			System.out.print((i+1) + "\t");
			
			geneticAlgorithm.evalFunctionValues = ComputationUtility.generateFitnessValues(gaBean.getCurrentFunction(), geneticAlgorithm.initChromosomeSet);
			
			//Element result = LogFileWriter.createResultsElement(geneticAlgorithm.evalFunctionValues);
			//resultXmlElements.add(result);
			
			//Element generation = LogFileWriter.generationResultsElement(i+1, result, chromo);
			//generationXmlElements.add(generation);
			
			geneticAlgorithm.probabilitySelectionValues = ComputationUtility.generateProbabilityOfSelection(geneticAlgorithm.evalFunctionValues);
			
			geneticAlgorithm.cumulativeProbabilityValues = ComputationUtility.cumulativeProbabilityValues(geneticAlgorithm.probabilitySelectionValues);
			
			geneticAlgorithm.updatedChromosomeSet = ComputationUtility.createNewPopulation(geneticAlgorithm.cumulativeProbabilityValues, geneticAlgorithm.initChromosomeSet);
			
			geneticAlgorithm.crossedOverChromosomeSet = ComputationUtility.crossoverSetOfChromosomes(gaBean.getCrossOverProbability(), geneticAlgorithm.updatedChromosomeSet);
			
			geneticAlgorithm.mutatedChromosomeSet = ComputationUtility.mutateChromosomes(gaBean.getProbabilityMutationRate(), geneticAlgorithm.crossedOverChromosomeSet);
			
			geneticAlgorithm.initChromosomeSet = geneticAlgorithm.mutatedChromosomeSet;
			
			double fXi = 0.0f;
			String xi = "";
			if(gaBean.selectedFunction <= 3)
			{
				fXi = ComputationUtility.findMaximumFunctionValue(geneticAlgorithm.evalFunctionValues);
				xi 	= ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				if(gaBean.selectedFunction > 1)
				{
					xi = ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				}
			}
			else
			{
				//System.out.print("OffsetValues: " + ComputationUtility.OFFSET_CORRECTIONS.get(i));
				fXi = -(ComputationUtility.findMaximumFunctionValue(geneticAlgorithm.evalFunctionValues) - ComputationUtility.OFFSET_CORRECTIONS.get(i));
				xi 	= ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				if(gaBean.selectedFunction > 4)
				{
					xi = ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				}
			}
			System.out.println("\t" + fXi + "\t\t" +xi);	
			res_fXi.add(fXi);
			res_Xi.add(xi);
		}
		
//		System.out.println("*******************************************************************************************");
//		System.out.println("\tGENERATION\t\tCROSSOVER COUNT\tMUTATION COUNT");
//		System.out.println("*******************************************************************************************");
//		
//		for (int i = 0; i < generations;i++)
//		{
//			System.out.println("\t\t"+(i+1)+"\t\t" + ComputationUtility.CROSSOVER_COUNT.get(i) + "\t\t" 
//					+ ComputationUtility.MUTATION_COUNT.get(i) );//+ "\t" + ComputationUtility.X1_X2_OPTIMAL.get(i));
//		}
//		System.out.println("*******************************************************************************************");
		
		if(logEnabled)
		{
			System.out.println("Log files enabled....writing the content");
			//writeLogFile(head, generationXmlElements);
		}
		else
			System.out.println("File Logging disabled");
		
		if(gaBean.selectedFunction <= 3)
			System.out.println("Maximum Value: " + ComputationUtility.maximumFitnessValue(res_fXi));
		else
			System.out.println("Minimum Value: " + ComputationUtility.minimumFitnessValue(res_fXi));
		
		System.out.println("Execution ended");		
	}

	/**
	 * 
	 * @param ga
	 * @param head
	 * @param chromosomes
	 * @param result
	 */
	@SuppressWarnings("unused")
	private static void writeLogFile(Element head, ArrayList<Element> generations)
	{
		Document doc = new Document();
		Element ga = new Element("GeneticAlgorithm");
		doc.setRootElement(ga);
		
		for(int gen = 0; gen < generations.size(); gen++)
		{
			ga.addContent(generations.get(gen));
		}	
		
	    try {
	      XMLOutputter serializer = new XMLOutputter();
	      serializer.setFormat(Format.getPrettyFormat());
	      serializer.output(doc, System.out);
	    }
	    catch (IOException e) {
	      System.err.println(e);
	    }
	}
	
	public String getDataForGraph(int generations, ArrayList<Double> f, ArrayList<Double> F, ArrayList<Double> Xi)
	{
		StringBuffer data = new StringBuffer();
		
		
		
		return data.toString();
		
	}

	public static ArrayList<Double> startEvolution(int gen, int fn, int precision, int popSize, double pC, double pM)
	{
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(); 
		GeneBean gaBean = new GeneBean();
		ArrayList<GAResult> results = new ArrayList<GAResult>();
		
		
		gaBean.setCurrentFunction(fn);
		gaBean.setPopulationSize(popSize);
		gaBean.setPrecision(precision);
		gaBean.setCrossOverProbability(pC);
		gaBean.setProbabilityMutationRate(pM);	
		gaBean.setMaxGenerations(gen);	
		
		int generations = gaBean.getMaxGenerations();
		
		geneticAlgorithm.chromosomeLength = ComputationUtility.computeSizeOfChromosome(gaBean.getCurrentFunction(), gaBean.getPrecision());
		
		geneticAlgorithm.initChromosomeSet = ComputationUtility.getInitChromosomes((int)geneticAlgorithm.chromosomeLength, gaBean.getPopulationSize());
		
		//Element head = LogFileWriter.createHeadElement(gaBean);

		for(int i = 0; i < generations; i++)		
		{
			GAResult gaResult = new GAResult();
			
			Element chromo = LogFileWriter.createChromosomesElement(geneticAlgorithm.initChromosomeSet, "initial set");
			
			chromosomesXmlElements.add(chromo);
			
			System.out.print((i+1) + "\t");
			
			geneticAlgorithm.evalFunctionValues = ComputationUtility.generateFitnessValues(gaBean.getCurrentFunction(), geneticAlgorithm.initChromosomeSet);
			
			Element result = LogFileWriter.createResultsElement(geneticAlgorithm.evalFunctionValues);
			resultXmlElements.add(result);
			
			Element generation = LogFileWriter.generationResultsElement(i+1, result, chromo);
			generationXmlElements.add(generation);
			
			geneticAlgorithm.probabilitySelectionValues = ComputationUtility.generateProbabilityOfSelection(geneticAlgorithm.evalFunctionValues);
			
			geneticAlgorithm.cumulativeProbabilityValues = ComputationUtility.cumulativeProbabilityValues(geneticAlgorithm.probabilitySelectionValues);
			
			geneticAlgorithm.updatedChromosomeSet = ComputationUtility.createNewPopulation(geneticAlgorithm.cumulativeProbabilityValues, geneticAlgorithm.initChromosomeSet);
			
			geneticAlgorithm.crossedOverChromosomeSet = ComputationUtility.crossoverSetOfChromosomes(gaBean.getCrossOverProbability(), geneticAlgorithm.updatedChromosomeSet);
			
			geneticAlgorithm.mutatedChromosomeSet = ComputationUtility.mutateChromosomes(gaBean.getProbabilityMutationRate(), geneticAlgorithm.crossedOverChromosomeSet);
			
			geneticAlgorithm.initChromosomeSet = geneticAlgorithm.mutatedChromosomeSet;
			
			double fXi = 0.0f;
			String xi = "";
			if(gaBean.selectedFunction <= 3)
			{
				fXi = ComputationUtility.findMaximumFunctionValue(geneticAlgorithm.evalFunctionValues);
				xi 	= ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				if(gaBean.selectedFunction > 1)
				{
					xi = ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				}
			}
			else
			{
				//System.out.print("OffsetValues: " + ComputationUtility.OFFSET_CORRECTIONS.get(i));
				fXi = -(ComputationUtility.findMaximumFunctionValue(geneticAlgorithm.evalFunctionValues) - ComputationUtility.OFFSET_CORRECTIONS.get(i));
				xi 	= ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				if(gaBean.selectedFunction > 4)
				{
					xi = ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				}
			}
			if (fn > 3)
			{
				fXi = -fXi;
			}
			gaResult.setOptimalFitnessValue(fXi);
			gaResult.setxValues(xi);
			gaResult.setCrossOverCounts(ComputationUtility.CROSSOVER_COUNT.get(i));
			gaResult.setMutationCounts(ComputationUtility.MUTATION_COUNT.get(i));
			gaResult.setTotalFitnessValues(ComputationUtility.totalFitnessValue(geneticAlgorithm.evalFunctionValues));
			results.add(gaResult);
			System.out.println("\t" + fXi + "\t\t" +xi);	
			res_fXi.add(fXi);
			res_Xi.add(xi);
		}
		
		if(logEnabled)
		{
			System.out.println("Log files enabled....writing the content");
			//writeLogFile(head, generationXmlElements);
		}
		else
			System.out.println("File Logging disabled");
		
		if(gaBean.selectedFunction <= 3)
		{
			optimal_Xi = max_Xi = ComputationUtility.maximumFitnessValue(res_fXi);
			System.out.println("Maximum Value: " + optimal_Xi);
		}
		else
		{
			optimal_Xi = min_Xi = ComputationUtility.minimumFitnessValue(res_fXi);
			System.out.println("Minimum Value: " + optimal_Xi);
		}
		System.out.println("Execution ended");
		
		return res_fXi;	
	}
	
	/**
	 * This method is used to run the genetic algorithm and capture the results in a collection
	 * to be utilized by the JSON objects for displaying it int he graph
	 * 
	 * @param gen
	 * @param fn
	 * @param precision
	 * @param popSize
	 * @param pC
	 * @param pM
	 * @return Collection of GAResult comprising of all the information related to the result.
	 */
	public static ArrayList<GAResult> geneticalAlgorithmResults(int gen, int fn, int precision, int popSize, double pC, double pM)
	{
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(); 
		GeneBean gaBean = new GeneBean();
		ArrayList<GAResult> results = new ArrayList<GAResult>();
		
		
		gaBean.setCurrentFunction(fn);
		gaBean.setPopulationSize(popSize);
		gaBean.setPrecision(precision);
		gaBean.setCrossOverProbability(pC);
		gaBean.setProbabilityMutationRate(pM);	
		gaBean.setMaxGenerations(gen);	
		
		int generations = gaBean.getMaxGenerations();
		
		//Calculating the chromosome length required
		geneticAlgorithm.chromosomeLength = ComputationUtility.computeSizeOfChromosome(gaBean.getCurrentFunction(), gaBean.getPrecision());
		
		//Initializing the chromosomes, randomly for the first rush
		geneticAlgorithm.initChromosomeSet = ComputationUtility.getInitChromosomes((int)geneticAlgorithm.chromosomeLength, gaBean.getPopulationSize());
		
		//Element head = LogFileWriter.createHeadElement(gaBean);

		for(int i = 0; i < generations; i++)		
		{
			GAResult result = new GAResult();
			
			//Calculating the evaluation values for the first time, for each selected function
			geneticAlgorithm.evalFunctionValues = ComputationUtility.generateFitnessValues(gaBean.getCurrentFunction(), geneticAlgorithm.initChromosomeSet);
			
			//Calculating selection probability values by rotating the roulette wheel
			geneticAlgorithm.probabilitySelectionValues = ComputationUtility.generateProbabilityOfSelection(geneticAlgorithm.evalFunctionValues);
			
			//Calculating cumulative probability values 
			geneticAlgorithm.cumulativeProbabilityValues = ComputationUtility.cumulativeProbabilityValues(geneticAlgorithm.probabilitySelectionValues);
			
			//Updating the old population to the  new population based on the selected chromosomes after swapping
			geneticAlgorithm.updatedChromosomeSet = ComputationUtility.createNewPopulation(geneticAlgorithm.cumulativeProbabilityValues, geneticAlgorithm.initChromosomeSet);
			
			//Setting up the platform ready and crossing over the chromosomes - recombination
			geneticAlgorithm.crossedOverChromosomeSet = ComputationUtility.crossoverSetOfChromosomes(gaBean.getCrossOverProbability(), geneticAlgorithm.updatedChromosomeSet);
			
			//Mutated chromosome set 
			geneticAlgorithm.mutatedChromosomeSet = ComputationUtility.mutateChromosomes(gaBean.getProbabilityMutationRate(), geneticAlgorithm.crossedOverChromosomeSet);
			
			//Replacing the older generation after the whole process - for the next generation
			geneticAlgorithm.initChromosomeSet = geneticAlgorithm.mutatedChromosomeSet;
			
			double fXi = 0.0f;
			String xi = "";
			if(gaBean.selectedFunction <= 3)
			{
				fXi = ComputationUtility.findMaximumFunctionValue(geneticAlgorithm.evalFunctionValues);
				xi 	= ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				if(gaBean.selectedFunction > 1)
				{
					xi = ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				}
			}
			else
			{
				//System.out.print("OffsetValues: " + ComputationUtility.OFFSET_CORRECTIONS.get(i));
				fXi = -(ComputationUtility.findMaximumFunctionValue(geneticAlgorithm.evalFunctionValues) - ComputationUtility.OFFSET_CORRECTIONS.get(i));
				xi 	= ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				if(gaBean.selectedFunction > 4)
				{
					xi = ComputationUtility.OPTIMAL_VALUES_Xi.get(i);
				}
			}
			
//			if(gaBean.selectedFunction <= 3)
//				System.out.println("Maximum Value: " + ComputationUtility.maximumFitnessValue(res_fXi));
//			else
//				System.out.println("Minimum Value: " + ComputationUtility.minimumFitnessValue(res_fXi));
			
			
			//Capturing results relevant for analysis
			result.setOptimalFitnessValue(fXi);
			result.setxValues(xi);
			result.setCrossOverCounts(ComputationUtility.CROSSOVER_COUNT.get(i));
			result.setMutationCounts(ComputationUtility.MUTATION_COUNT.get(i));
			result.setTotalFitnessValues(ComputationUtility.totalFitnessValue(geneticAlgorithm.evalFunctionValues));
			results.add(result);
		}
		
		for(int i = 0; i < results.size(); i++)
		{
			res_fXi.add(results.get(i).getOptimalFitnessValue());
		}
		
		if(fn <= 3)
		{
			optimal_Xi = max_Xi = ComputationUtility.maximumFitnessValue(res_fXi);
			System.out.println("Maximum Value: " + optimal_Xi);
		}
		else
		{
			optimal_Xi = min_Xi = ComputationUtility.minimumFitnessValue(res_fXi);
			System.out.println("Minimum Value: " + optimal_Xi);
		}
		System.out.println("Execution ended");
		
		return results;	
	}


}
