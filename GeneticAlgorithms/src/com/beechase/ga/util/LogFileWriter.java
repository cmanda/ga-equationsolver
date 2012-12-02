package com.beechase.ga.util;

import java.util.ArrayList;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import com.beechase.ga.GeneBean;
import com.beechase.ga.function.Function1;
import com.beechase.ga.function.Function2;
import com.beechase.ga.function.Function3;
import com.beechase.ga.function.Function4;
import com.beechase.ga.function.Function5;
import com.beechase.ga.function.Function6;


public class LogFileWriter
{
	public Document document;
	
	/**
	 * 
	 * @param gene
	 * @return
	 */
	public static Element createHeadElement(GeneBean gene)
	{	
		Element headElement = new Element("genetic-algorithm-results");
		
		Attribute attribute = new Attribute("runtime", new java.util.Date().toString());
		headElement.setAttribute(attribute);
		
		
		Element function = new Element("function");
		switch(gene.getCurrentFunction())
		{
			case 1: function.setAttribute("id", "1");
					function.setText(new Function1().toString());
					break;
			case 2: function.setAttribute("id","2");
					function.setText(new Function2().toString());
					break;
			case 3: function.setAttribute("id", "3");
					function.setText(new Function3().toString());
					break;
			case 4: function.setAttribute("id", "1");
					function.setText(new Function4().toString());
					break;
			case 5: function.setAttribute("id","2");
					function.setText(new Function5().toString());
					break;
			case 6: function.setAttribute("id", "3");
					function.setText(new Function6().toString());
					break;
			default:function.setText("unknown");
					break;
		}
		headElement.addContent(function);
		
		
		//Creating all the nodes that are given by the user
		Element functionValues = new Element("function-params");
		
		Element precisionDigits = new Element("precision");
		precisionDigits.setText(Integer.toString(gene.getPrecision()));
		
		Element popSize = new Element("pop-size");
		popSize.setText(Integer.toString(gene.getPopulationSize()));
		
		Element totalGenerations = new Element("total-generations");
		totalGenerations.setText(Integer.toString(gene.getMaxGenerations()));		
		
		Element crossOverProbability = new Element("cross-over-probability");
		crossOverProbability.setText(Double.toString(gene.getCrossOverProbability()));
		
		Element mutationProbability  = new Element("mutation-probability");
		mutationProbability.setText(Double.toString(gene.getProbabilityMutationRate()));
		
		functionValues.addContent(precisionDigits);
		functionValues.addContent(popSize);
		functionValues.addContent(totalGenerations);
		functionValues.addContent(crossOverProbability);
		functionValues.addContent(mutationProbability);
		
		headElement.addContent(functionValues);
		
		return headElement;		
	}
	
	
	public static Element createChromosomesElement(ArrayList<String> initChromosomesList, String type)
	{
		Element chromosomesElement = new Element("chromosomes");
		chromosomesElement.setAttribute("type",type);
		
		for(int i = 0; i < initChromosomesList.size(); i++)
		{
			Element child = new Element("chromosome");
			child.setAttribute("id", Integer.toString(i+1));
			child.setText(initChromosomesList.get(i));
			chromosomesElement.addContent(child);
		}
		return chromosomesElement;
	}
	
	public static Element createResultsElement(ArrayList<Double> evaluationValues)
	{
		Element resultSet = new Element("evaluation-function-values");
		resultSet.setAttribute("sum",Double.toString(ComputationUtility.sumOfFunctionValues(evaluationValues)));
		resultSet.setAttribute("max", Double.toString(ComputationUtility.findMaximumFunctionValue(evaluationValues)));
		
		
		for(int i = 0; i < evaluationValues.size(); i++)
		{
			Element child = new Element("evaluation-value");
			child.setAttribute("id", Integer.toString(i+1));
			child.setText(Double.toString(evaluationValues.get(i)));
			resultSet.addContent(child);
		}
		
		return resultSet;
	}
	
	public static Element generationResultsElement(int genId, Element result, Element chromosomes)
	{
		Element generationResult = new Element("generation");
		generationResult.setAttribute("id", Integer.toString(genId));
		generationResult.addContent(result);
		generationResult.addContent(chromosomes);
		return generationResult;
		
	}


	
}
