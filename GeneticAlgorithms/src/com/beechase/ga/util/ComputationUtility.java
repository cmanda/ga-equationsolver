/**
 * 
 */
package com.beechase.ga.util;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.beechase.ga.GeneBean;
import com.beechase.ga.function.Function1;
import com.beechase.ga.function.Function2;
import com.beechase.ga.function.Function3;
import com.beechase.ga.function.Function4;
import com.beechase.ga.function.Function5;
import com.beechase.ga.function.Function6;


/**
 * @author balachandrasekharmanda
 * 
 *         This class is used to compute all the necessary
 *         calculations/randomizations for the Genetic Algorithm implementation
 * 
 *         This is necessarily a helper class wrapping around all the
 *         functionalities that are required for the Genetic Algoroithm
 *         implementation
 * 
 */
public class ComputationUtility
{
	static int l1 = 0;
	static int l2 = 0;
	public static double MAX_F = 0;
	public static double MIN_F = 0;
	public static double SUM_F = 0;

	public static ArrayList<Integer> CROSSOVER_COUNT 	= new ArrayList<Integer>(); 	
	public static ArrayList<Integer> MUTATION_COUNT		= new ArrayList<Integer>(); 	
	public static ArrayList<Integer> OPTIMAL_POSITION_INDICES	= new ArrayList<Integer>(); 	
	public static ArrayList<String> OPTIMAL_VALUES_Xi		= new ArrayList<String>(); 		
	public static ArrayList<Double> X1List				= new ArrayList<Double>(); 			
	public static ArrayList<Double> X2List				= new ArrayList<Double>(); 			
	public static ArrayList<Double> OFFSET_CORRECTIONS	= new ArrayList<Double>();
	public static ArrayList<Double> TOTAL_FITNESS		= new ArrayList<Double>(); 
	
	

	/**
	 * This method computes the length of the chromosome vector for the range of
	 * given function and a necessary precision value
	 * 
	 * @param selFunction		Choice of Function selected
	 * @param precisionDigits	The precision required for the optimal value
	 */
	public static long computeSizeOfChromosome(int selFunction,	int precisionDigits)
	{
		if (selFunction == 1 || selFunction == 4)
		{
			l1 = (int)Long.toBinaryString(
					(long) (Function1.rangeX * Math.pow(10, precisionDigits)))
					.length();
			l2 = 0;
		}
		else if (selFunction == 2 || selFunction == 5)
		{
			l1 = (int)Long.toBinaryString(
					(long) (Function2.rangeX1 * Math.pow(10, precisionDigits)))
					.length();
			l2 = (int)Long.toBinaryString(
					(long) (Function2.rangeX2 * Math.pow(10, precisionDigits)))
					.length();
		}
		else if (selFunction == 3 || selFunction == 6)
		{
			l1 = (int)Long.toBinaryString(
					(long) (Function3.rangeX1 * Math.pow(10, precisionDigits)))
					.length();
			l2 = (int)Long.toBinaryString(
					(long) (Function3.rangeX2 * Math.pow(10, precisionDigits)))
					.length();
		}

		//System.out.println("L1: " + l1 + " L2: " + l2);
		return (l1 + l2);
	}


	/**
	 * This method is used to generate the chromosome vectors for any given
	 * length for the whole population
	 * 
	 * @param chromosomeLength	Length of the chromosome vector
	 * @param popSize			Population size for the genetic algorithm
	 */
	public static ArrayList<String> getInitChromosomes(int chromosomeLength,
			int popSize)
	{
		ArrayList<String> initialChromosomeVectors = new ArrayList<String>();

		StringBuffer chromosome = null;
		int randomIntValue = 0;
		int bit = 0;
		for (int i = 0; i < popSize; i++)
		{
			int bitIterator = 0;
			chromosome = new StringBuffer("");
			while (bitIterator < chromosomeLength)
			{
				Random randomValue = new Random();
				randomIntValue = randomValue.nextInt(2);

				if (randomIntValue < 1)
					bit = 0;
				else
					bit = 1;

				// appending individual bits to the chromosome binary vector
				chromosome = chromosome.append(bit);
				bitIterator++;
			}
			//System.out.println("Chromosome: " + chromosome);
			initialChromosomeVectors.add(chromosome.toString());
		}
		return initialChromosomeVectors;
	}


	/**
	 * Step 3: This method is used to evaluate the fitness value 
	 * of each of the chromosome vectors
	 */
	public static ArrayList<Double> generateFitnessValues(int selFunction,
			ArrayList<String> chromosomeVectors)
	{
		ArrayList<Double> fitnessValues = null;
		fitnessValues = new ArrayList<Double>();

		double x1 = 0, x2 = 0;
		for (int i = 0; i < chromosomeVectors.size(); i++)
		{
			if(selFunction == 1 || selFunction == 4)
			{
				x1 = ComputationUtility.convertBinaryToDouble(chromosomeVectors.get(i)
						.substring(0, ComputationUtility.l1));
				
				// This is used to calculate the x1 = a + {L/(2^m-1)}{(x1)2}10
				x1 = evaluateNewX1(x1, selFunction);

				ComputationUtility.X1List.add(x1); 
				//System.out.print("\tX1: " + x1);
			}
			else
			{
				x1 = ComputationUtility.convertBinaryToDouble(chromosomeVectors.get(i)
						.substring(0, ComputationUtility.l1));
				
				// This is used to calculate the x1 = a + {L/(2^m-1)}{(x1)2}10
				x1 = evaluateNewX1(x1, selFunction);

				ComputationUtility.X1List.add(x1); 
				
				x2 = ComputationUtility.convertBinaryToDouble(chromosomeVectors.get(i)
					.substring(l1));
				x2 = evaluateNewX2(x2, selFunction);
				
				ComputationUtility.X2List.add(x2);
				//System.out.print("\tX2: " + x2);
			}


			
			// System.out.println("New values of X1, X2 are: (" + x1 + ", " + x2
			// + ")") ;

			// Computing the new value of f(x1,x2)
			if (selFunction == 1)
			{
				fitnessValues.add(i, Function1.computeFunction(x1));
			}
			else if (selFunction == 2)
			{
				fitnessValues.add(i, Function2.computeFunction(x1, x2));
			}
			else if (selFunction == 3)
			{
				fitnessValues.add(i, Function3.computeFunction(x1, x2));
			}
			else if (selFunction == 4)
			{
				fitnessValues.add(i, Function4.computeFunction(x1));
			}
			else if (selFunction == 5)
			{
				fitnessValues.add(i, Function5.computeFunction(x1, x2));
			}
			else if (selFunction == 6)
			{
				fitnessValues.add(i, Function6.computeFunction(x1, x2));
			}
			
		}
		
		if(selFunction > 3)
			fitnessValues = offsetValues(fitnessValues);
		
		// Adding the optimal position of the chromosomes to the list based on the fitness values
		int optimalPosition = fitnessValues.indexOf(ComputationUtility.maximumFitnessValue(fitnessValues));
		
		ComputationUtility.OPTIMAL_POSITION_INDICES.add(optimalPosition);
		
		if (selFunction == 1 || selFunction == 4)
		{
			ComputationUtility.OPTIMAL_VALUES_Xi.add(Double.toString(ComputationUtility.X1List.get(optimalPosition)));
		}
		else
		{
			ComputationUtility.OPTIMAL_VALUES_Xi.add(ComputationUtility.X1List.get(optimalPosition) + 
					", "+ ComputationUtility.X2List.get(optimalPosition));
		}		
		
		return fitnessValues;
	}

	public static int getIndexOfMaxValue(ArrayList<Double> fitnessValues)
	{
		int index = 0;
		
		double max = 0;
		for(int i = 0; i < fitnessValues.size(); i++)
		{
			if(fitnessValues.get(i) > max)
			{
				max = fitnessValues.get(i);
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * This method will add the offset only if it is required, 
	 * i.e. the fitness values are guaged for the negative numbers and then
	 * added with a sufficiently large number based on the distribution to make f(v) positive
	 * 
	 * @param values
	 * @return Offset Corrected values for the fitness distribution
	 */
	private static ArrayList<Double> offsetValues(ArrayList<Double> values)
	{
		double offsetVal = 0.0f;
		
		@SuppressWarnings("unused")
		double min, max;
		
		ArrayList<Double> offsetFitnessValues = new ArrayList<Double>();
		
		// Picking the min and max of the fitness values
		min = MIN_F = ComputationUtility.minimumFitnessValue(values);
		max = MAX_F = ComputationUtility.maximumFitnessValue(values);
		//System.out.println("Min: " + min + " Max: " + max);
		
		if (MIN_F < 0)
		{
			offsetVal = Math.abs(min) + 10;
			
			//System.out.println("Offset correction applied: " + offsetVal);

			for (int index = 0; index < values.size(); index++)
			{
				// Calculating new corrected fitness values
				offsetFitnessValues.add(index, values.get(index) + offsetVal);
			}	
			
		}
		else
			offsetFitnessValues = values;
		
		//Keeping track of all the offsets done in all generations
		OFFSET_CORRECTIONS.add(offsetVal);
		return offsetFitnessValues;
	}


	public static ArrayList<Double> generateProbabilityOfSelection(
			ArrayList<Double> fitnessValues)
	{
		ArrayList<Double> probabilityValues = new ArrayList<Double>();
		ComputationUtility.SUM_F = ComputationUtility.totalFitnessValue(fitnessValues);
		for (int i = 0; i < fitnessValues.size(); i++)
		{
			double val = fitnessValues.get(i) / ComputationUtility.SUM_F;
			// System.out.println("Fitness Value: " + val);
			probabilityValues.add(val);
		}
		return probabilityValues;
	}

	public static ArrayList<Double> cumulativeProbabilityValues(
			ArrayList<Double> probabilityValues)
	{
		ArrayList<Double> indexedCumulativeProbValues = new ArrayList<Double>();
		indexedCumulativeProbValues.add(0, probabilityValues.get(0));
		for (int i = 1; i < probabilityValues.size(); i++)
		{
			// This computes the cumulative probabilities
			// based upto the index of the iteration
			// such that; Q_i = Sum(P_i) for all i = 0 to pop_size
			double prevCmlVal = indexedCumulativeProbValues.get(i - 1);
			double currPrbVal = probabilityValues.get(i);
			// System.out.println("Cumulative Values:" + (prevCmlVal +
			// currPrbVal));
			indexedCumulativeProbValues.add(i, prevCmlVal + currPrbVal);
		}
		return indexedCumulativeProbValues;
	}

	/*
	 * Create a new population of the chromosomes after changing the old set of
	 * chromosomes by comparing with the roulette generated random numbers
	 */
	public static ArrayList<String> createNewPopulation(
			ArrayList<Double> cmltvPrbyVals, ArrayList<String> oldChromosomes)
	{
		ArrayList<String> newChromosomes = new ArrayList<String>();
		newChromosomes = oldChromosomes;
		// System.out.println("\nCumulative Values: " +
		// cmltvPrbyVals.toString());
		// The cmltvPrbyVals is already sorted data in the ascending order.
		ArrayList<Double> randomSequence = ComputationUtility.rotateRouletteWheel(oldChromosomes.size());

		int swapPosition = 0;
		// int swapCount = 0;
		double randomValue = 0.0;

		for (int i = 0; i < oldChromosomes.size(); i++)
		{
			swapPosition = 0;
			randomValue = randomSequence.get(i);
			// System.out.println("Random value: " + randomValue);
			while (randomValue > cmltvPrbyVals.get(swapPosition))
			{
				swapPosition++;
			}
			// System.out.println("Old V" + i + " = New V" + swapPosition);
			newChromosomes.set(i, oldChromosomes.get(swapPosition));
			// System.out.println("New Chromosome[ " + i + "]: " +
			// newChromosomes.get(i));
		}
		// System.out.println("Old Chromosomes:\t\t\t" +
		// oldChromosomes.toString());
		// System.out.println("New Chromosomes(After Conversion):\t" +
		// newChromosomes.toString());
		return newChromosomes;
	}

	/**
	 * Cross over the set of chromosomes - Recombination operation
	 */
	public static ArrayList<String> crossoverSetOfChromosomes(
			double crossOverProbRate, ArrayList<String> newChromosomes)
	{
		ArrayList<Double> anotherRandomArray = 
			ComputationUtility.rotateRouletteWheel(newChromosomes.size());
		
		ArrayList<String> offSpringChromosomes = new ArrayList<String>();
		ArrayList<String> crossedChromosomes = new ArrayList<String>();
		ArrayList<Integer> posSelChromosomes = new ArrayList<Integer>();
		crossedChromosomes = newChromosomes;

		int identifiedChromosomes = 0;
		int popSize = newChromosomes.size();
		// System.out.println("Random sequence for Cross over probability rate:\n"
		// + anotherRandomArray.toString());
		for (int i = 0; i < popSize; i++)
		{
			if (anotherRandomArray.get(i) < crossOverProbRate)
			{
				offSpringChromosomes.add(newChromosomes.get(i));
				// pushing the position of the selected chromosome into an array
				// Keep track of all the selected chromosomes for cross over
				// operations
				posSelChromosomes.add(i);
				// incrementing the selected
				identifiedChromosomes++;
			}
		}
		CROSSOVER_COUNT.add(posSelChromosomes.size());
		// If the identified chromosomes lesser than the allowed probability of
		// cross overs is odd - we need to find/delete another chromosome to
		// fill in the gap
		if (identifiedChromosomes == 0)
		{
			// Adding the 1st element of the new chromosomes set to the cross
			// over set
			offSpringChromosomes.add(0, newChromosomes.get(0));
			posSelChromosomes.add(0);
			// Adding the 2nd element of the new chromosomes set to the cross
			// over set
			offSpringChromosomes.add(1, newChromosomes.get(1));
			posSelChromosomes.add(1);
		}
		else if (identifiedChromosomes % 2 != 0)
		{
			// If odd number of chromosomes are selected,
			// We need to select another chromosome at random
			int randomNumber = new Random().nextInt(popSize);
			// The random number indexed element is selected for the cross over
			// to be applied
			offSpringChromosomes.add(identifiedChromosomes, newChromosomes
					.get(randomNumber));
			posSelChromosomes.add(randomNumber);
		}

		// System.out.println("Identified the chromosomes at these positions to be crossed-over: "
		// + posSelChromosomes.toString());
		// System.out.println("The chromosomes at these positions to be crossed-over: "
		// + offSpringChromosomes.toString());

		int randomLength = 0;
		int i = 0;
		int k = 0;
		// The incrementing is done twice to avoid the pairing of the subsequent
		// chromosomes
		for (int j = 0; j < posSelChromosomes.size(); j += 2)
		{
			String befCrossOverPosChr1, aftCrossOverPosChr1, befCrossOverPosChr2, aftCrossOverPosChr2;
			String crossOverChr1, crossOverChr2;

			// identifying a random bit position to start cross over of the
			// selected chromosomes
			randomLength = new Random().nextInt(ComputationUtility.l1 + ComputationUtility.l2);
			if (randomLength == 0)
			{
				randomLength = 1;
			}
			// System.out.println("Random Length: " + randomLength);
			String strA = offSpringChromosomes.get(i);
			String strB = offSpringChromosomes.get(i + 1);

			if (strA != null && strB != null)
			{
				befCrossOverPosChr1 = strA.substring(0, randomLength - 1);
				aftCrossOverPosChr1 = strA.substring(randomLength - 1);
				// System.out.println("Chromosome1A: " + befCrossOverPosChr1 +
				// "\tChromosome1B: " + aftCrossOverPosChr1);

				befCrossOverPosChr2 = strB.substring(0, randomLength - 1);
				aftCrossOverPosChr2 = strB.substring(randomLength - 1);
				// System.out.println("Chromosome2A: " + befCrossOverPosChr2 +
				// "\tChromosome2B: " + aftCrossOverPosChr2);

				crossOverChr1 = befCrossOverPosChr1 + aftCrossOverPosChr2;
				crossOverChr2 = befCrossOverPosChr2 + aftCrossOverPosChr1;

				// System.out.println("Chromosome1: " + crossOverChr1 +
				// "\tChromosome2: " + crossOverChr2);

				// System.out.println("Cross over Position: " +
				// posSelChromosomes.get(k));
				crossedChromosomes.set(posSelChromosomes.get(k), crossOverChr1);
				crossedChromosomes.set(posSelChromosomes.get(k + 1),
						crossOverChr2);
				k++;
			}
			i++;
		}
		// int chromosomeCount = 0;
		// for (String string : crossedChromosomes)
		// {
		// System.out.println("After Cross Over Chromosome - V" +
		// (chromosomeCount++)+ "]:" + string);
		// }
		return crossedChromosomes;
	}

	public static ArrayList<String> mutateChromosomes(double mutationProbRate,
			ArrayList<String> crossedOverChromosomes)
	{
		int popSize = crossedOverChromosomes.size();
		// Calculating the total possible length of the bits that should undergo
		// mutation
		int totalBitsAllowed = (ComputationUtility.l1 + ComputationUtility.l2) * popSize;

		ArrayList<Integer> mutatedPositions = new ArrayList<Integer>();
		ArrayList<Double> randValuesMutRateCheck = new ArrayList<Double>();
		randValuesMutRateCheck = ComputationUtility.rotateRouletteWheel(totalBitsAllowed);
		ArrayList<String> mutatedChromosomes = new ArrayList<String>();
		mutatedChromosomes = crossedOverChromosomes;

		int posMutation = 0;

		for (int i = 0; i < totalBitsAllowed; i++)
		{
			if (randValuesMutRateCheck.get(i) < mutationProbRate)
			{
				mutatedPositions.add(posMutation, i);
				posMutation++;
			}
		}
		MUTATION_COUNT.add(mutatedPositions.size());
		// System.out.println("For given Population and bit length, the total bits that are possible are: "+
		// totalBitsAllowed);
		// System.out.println("Mutations are to be done on : " +
		// mutatedPositions.size() + " bits");
		// System.out.println("They are at : " + mutatedPositions.toString());

		int chromosomeNumber = 0, bitPosition = 0;
		String mutatedChromosome = "", mutatedBeforePos = "", mutatedAfterPos = "";
		char oldBit = '0';
		char newBit = '0';

		// Iterate for all the mutated positions
		for(int j = 0; j < mutatedPositions.size(); j++)
		{
			int mutatedPosition = mutatedPositions.get(j);
			// System.out.println("Mutation Position: " + mutatedPosition);

			if (mutatedPosition == 0)
			{
			}
			else if (mutatedPosition != 0)
			{
				chromosomeNumber = mutatedPosition / (ComputationUtility.l1 + ComputationUtility.l2);
				bitPosition = mutatedPosition % (ComputationUtility.l1 + ComputationUtility.l2);

				// System.out.println("Chromosome: V[" + chromosomeNumber
				// +"], Bit Position: " + bitPosition);

				// We need to verify whether the bit is the 0th or the nth bit
				// of the chromosome.
				if (bitPosition == 0)
					bitPosition = (ComputationUtility.l1 + ComputationUtility.l2) - 1;

				mutatedChromosome = crossedOverChromosomes.get(chromosomeNumber);
				oldBit = mutatedChromosome.charAt(bitPosition);

				// Complimenting the bits for the identified bit position
				if (oldBit == '0')
				{
					newBit = '1';
				}
				else if (oldBit == '1')
				{
					newBit = '0';
				}

				// System.out.println("To be Mutated Chromosome: " +
				// mutatedChromosome);
				mutatedBeforePos = mutatedChromosome.substring(0,
						bitPosition - 1);
				mutatedAfterPos = mutatedChromosome.substring(bitPosition);
				// System.out.println("Mut_A: " + mutatedBeforePos + "\tMut_B: "
				// + mutatedAfterPos);
				mutatedChromosome = mutatedBeforePos + "" + newBit
						+ mutatedAfterPos;
				mutatedChromosomes.set(chromosomeNumber, mutatedChromosome);
			}
		}
		// for (String string : mutatedChromosomes)
		// {
		// //System.out.println("Mutated Chromosome: " + string);
		// }
		return mutatedChromosomes;
	}

	/*
	 * This method is a utility method to convert a giveny binary sequence in a
	 * string format to an integer
	 */
	private static double convertBinaryToDouble(String binarySequence)
	{
		double result = 0;
		String bit;

		for (int i = binarySequence.length(), power = 0; i > 0; i--, power++)
		{
			bit = binarySequence.substring(i - 1, i);
			result += (Integer.parseInt(bit)) * Math.pow(2, power);
		}
		// //System.out.println("Result of conversion of '" + binarySequence +
		// "':" + result);
		return result;
	}

	/*
	 * This method is used to evaluate the new value of 'x1' for the
	 * computation, the inputs are the old value of x1 and the selected function
	 */
	private static double evaluateNewX1(double oldX1, int selectedFunction)
	{
		double newX1 = 0;
		if (selectedFunction == 1)
		{
			newX1 = Function1.minX + (Function1.rangeX * oldX1)
					/ (Math.pow(2, ComputationUtility.l1) - 1);
		}
		else if (selectedFunction == 2)
		{
			newX1 = Function2.minX1 + (Function2.rangeX1 * oldX1)
					/ (Math.pow(2, ComputationUtility.l1) - 1);
		}
		else if (selectedFunction == 3)
		{
			newX1 = Function3.minX1 + (Function3.rangeX1 * oldX1)
					/ (Math.pow(2, ComputationUtility.l1) - 1);
		}
		if (selectedFunction == 4)
		{
			newX1 = Function4.minX + (Function4.rangeX * oldX1)
					/ (Math.pow(2, ComputationUtility.l1) - 1);
		}
		else if (selectedFunction == 5)
		{
			newX1 = Function5.minX1 + (Function5.rangeX1 * oldX1)
					/ (Math.pow(2, ComputationUtility.l1) - 1);
		}
		else if (selectedFunction == 6)
		{
			newX1 = Function6.minX1 + (Function6.rangeX1 * oldX1)
					/ (Math.pow(2, ComputationUtility.l1) - 1);
		}
		//System.out.println("New X1(" + x1 + "): " + newX1);
		return newX1;
	}

	/*
	 * This method is used to evaluate the new value of 'x2' for the
	 * computation, the inputs are the old value of x2 and the selected function
	 */
	private static double evaluateNewX2(double x2, int selectedFunction)
	{
		double newX2 = 0;
		if (selectedFunction == 1)
		{
			newX2 = 0;
		}
		else if (selectedFunction == 2)
		{
			newX2 = Function2.minX2 + (Function2.rangeX2 * x2)
					/ (Math.pow(2, ComputationUtility.l2) - 1);
		}
		else if (selectedFunction == 3)
		{
			newX2 = Function3.minX2 + (Function3.rangeX2 * x2)
					/ (Math.pow(2, ComputationUtility.l2) - 1);
		}
		if (selectedFunction == 4)
		{
			newX2 = 0;
		}
		else if (selectedFunction == 5)
		{
			newX2 = Function5.minX2 + (Function5.rangeX2 * x2)
					/ (Math.pow(2, ComputationUtility.l2) - 1);
		}
		else if (selectedFunction == 6)
		{
			newX2 = Function6.minX2 + (Function6.rangeX2 * x2)
					/ (Math.pow(2, ComputationUtility.l2) - 1);
		}
		//System.out.println("New X2(" + x2 + "): " + newX2);
		return newX2;
	}

	public static double maximumFitnessValue(ArrayList<Double> fitnessValues)
	{
		double max = 0;
		for (int i = 0; i < fitnessValues.size(); i++)
		{
			if (fitnessValues.get(i) > max)
			{
				max = fitnessValues.get(i);
			}
		}

		return (ComputationUtility.MAX_F = max);
	}

	public static double minimumFitnessValue(ArrayList<Double> fitnessValues)
	{
		double min = 0;
		for (int i = 0; i < fitnessValues.size(); i++)
		{
			if (fitnessValues.get(i) < min)
			{
				min = fitnessValues.get(i);
			}
		}
		return (ComputationUtility.MIN_F = min);
	}

	public static double totalFitnessValue(ArrayList<Double> fitnessValues)
	{
		double total = 0;
		for (int i = 0; i < fitnessValues.size(); i++)
		{
			total += fitnessValues.get(i);
		}
		return (ComputationUtility.SUM_F = total);
	}

	/*
	 * This method is used to rotate the roulette wheel so that the random
	 * sequence of values for the population size are obtained
	 */
	private static ArrayList<Double> rotateRouletteWheel(int populationSize)
	{
		ArrayList<Double> randomSequence = new ArrayList<Double>();
		for (int i = 0; i < populationSize; i++)
		{
			double randomValue = new Random().nextDouble();
			if (randomValue == 0.0)
			{
				randomValue = new Random().nextDouble();
			}
			// A random number in between 0 and 1 is added to the collection
			randomSequence.add(randomValue);
		}
		// System.out.println("Random Sequence: " + randomSequence.toString());
		return randomSequence;
	}

	
	public static void writeToLog(String fileName, String log) throws IOException
	{
		Date d = new Date(); 
		long ts = d.getTime();
		FileWriter fw = null;
		try
		{
			fw = new FileWriter(fileName + ts +".log");
			fw.flush();
			fw.write(log);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			fw.close();
		}
	}
	
	public static double findMaximumFunctionValue(ArrayList<Double> evalFunctionValues)
	{
		double max = 0;
		for(int i = 0; i < evalFunctionValues.size(); i++)
		{
			if(evalFunctionValues.get(i) > max)
			{
				max = evalFunctionValues.get(i);
			}
		}
		return max;
	}
	
	public static double sumOfFunctionValues(ArrayList<Double> evalFunctionValues)
	{
		double sum = 0;
		for(int i = 0; i < evalFunctionValues.size(); i++)
		{
			sum += evalFunctionValues.get(i);
		}
		return sum;
	}
	
	//This method enables all the input parameters to be read at once
	public static GeneBean readInputParameters()
	{	
		GeneBean gaBean = new GeneBean();
		
		//Reading the Function to be computed
		int locSelection = 1;
		//System.out.println("---------------------------------------------------------");		
		System.out.print("Select the function to solve:\n");
		System.out.print("1. Maximize F1\t2. Maximize F2\t3. Maximize F3\t" +
				"4. Minimize F1\t5. Minimize F2\t6. Minimize F3");
		locSelection = Integer.parseInt(readInputLines());
		
		//System.out.println("\n---------------------------------------------------------");
		//Reading the population size
		int locPopSize = 50;
		System.out.print("Input the population size (0-60):");
		locPopSize = Integer.parseInt(readInputLines());		
			
		
		//System.out.println("\n---------------------------------------------------------");
		//Reading the Precision Digits size
		int locPrecisionDigits = 8;
		System.out.print("Input the Precision Digits for the solution: ");
		locPrecisionDigits = Integer.parseInt(readInputLines());	
		
		
		//System.out.println("\n---------------------------------------------------------");
		//Reading the Probability for Cross overs 
		double locProbCrossOverRate = 0.50f;
		System.out.print("Input the Probability for Cross overs allowed in the range[0.0,1.0]: ");
		locProbCrossOverRate= Float.parseFloat(readInputLines());	
		
		//Reading the Probability for Mutations
		//System.out.println("\n---------------------------------------------------------");
		double locProbMutations = 0.01f;
		System.out.print("Input the Probability for possible Mutations allowed in the range[0.0,1.0]: ");
		locProbMutations = Float.parseFloat(readInputLines());	
		
		
		//Reading the Probability for Mutations
		//System.out.println("\n---------------------------------------------------------");
		int locGenerations = 400;
		System.out.print("Input the Generations allowed for solution: ");
		locGenerations = Integer.parseInt(readInputLines());	
			
		
		//Adding to the bean 
		gaBean.setCurrentFunction(locSelection);
		gaBean.setPopulationSize(locPopSize);
		gaBean.setPrecision(locPrecisionDigits);
		gaBean.setCrossOverProbability(locProbCrossOverRate);
		gaBean.setProbabilityMutationRate(locProbMutations);	
		gaBean.setMaxGenerations(locGenerations);
		
		//Test cases
//		gaBean.setSelectedFunction(1);
//		gaBean.setPopulationSize(20);
//		gaBean.setPrecision(6);
//		gaBean.setGenerations(100);
//		gaBean.setProbabilityCrossOver(0.25);
//		gaBean.setProbabilityMutationRate(0.01);	
		return gaBean;
	}
		
	private static String readInputLines()
	{
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));   
		String option = "";
        try
		{
            option = buffer.readLine();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	System.out.println("Execution aborted! Please provide input arguments correctly!");
        }        
        return option;
	}


	public static double findMinimumFunctionValue(
			ArrayList<Double> fitnessValues)
	{
		double min = 0;
		for(int i = 0; i < fitnessValues.size(); i++)
		{
			if(fitnessValues.get(i) < min)
			{
				min = fitnessValues.get(i);
			}
		}
		return min;
		
	}


	public static int getIndexOfMinValue(ArrayList<Double> fitnessValues)
	{
		int index = 0;
		
		double min = 0;
		for(int i = 0; i < fitnessValues.size(); i++)
		{
			if(fitnessValues.get(i) < min)
			{
				min = fitnessValues.get(i);
				index = i;
			}
		}
		return index;
	}


	public static double findMeanFunctionValue(ArrayList<Double> fitnessValues)
	{
		double sum = 0.0f;
		for(int i=0; i < fitnessValues.size(); i++)
		{
			sum += fitnessValues.get(i);
		}
		return (sum/fitnessValues.size());
	}
	
	
}
