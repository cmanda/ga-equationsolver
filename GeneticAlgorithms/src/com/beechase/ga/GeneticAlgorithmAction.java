package com.beechase.ga;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beechase.ga.function.Function1;
import com.beechase.ga.function.Function2;
import com.beechase.ga.function.Function3;
import com.beechase.ga.function.Function4;
import com.beechase.ga.function.Function5;
import com.beechase.ga.function.Function6;
import com.beechase.ga.util.ComputationUtility;
import com.beechase.ga.util.GAResult;


/**
 * Servlet implementation class GeneticAlgorithmAction
 */
public class GeneticAlgorithmAction extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GeneticAlgorithmAction()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String titleText = "Function: ";
		double optimal_f = 0.0f;
		String optimal_xi;
		int index = 0;
		ArrayList<GAResult> gaResults = new ArrayList<GAResult>();
		double maxFitnessValue, minFitnessValue, meanFitnessValue = 0.0f;
		String xi_max, xi_min = "";
	    
		int generations = Integer.parseInt(request.getParameter("maxGenerations").toString());
		int function = Integer.parseInt(request.getParameter("function").toString());
		int precision = Integer.parseInt(request.getParameter("precision").toString());
		int popSize = Integer.parseInt(request.getParameter("popSize").toString());
		double pC = Double.parseDouble(request.getParameter("crossOverProbability").toString());
		double pM = Double.parseDouble(request.getParameter("mutationProbability").toString());
		
		PrintWriter out = response.getWriter();
		
		gaResults = GeneticAlgorithm.geneticalAlgorithmResults(generations, function, precision, popSize, pC, pM);
		
		
		
		switch(function)
		{
			case 1: titleText += new Function1().toString();break;
			case 2: titleText += new Function2().toString();break;
			case 3: titleText += new Function3().toString();break;
			case 4: titleText += new Function4().toString();break;
			case 5: titleText += new Function5().toString();break;
			case 6: titleText += new Function6().toString();break;
		}
		HttpSession session = request.getSession();
		
		

		String l1 = "\tlabel: \"Y: " + titleText + ", X: Total Generations\", ";
		
		String l2 = "\tlabel: 'Y: Cross Over count for " + titleText +", X: Total Generations',\n";
		
		StringBuffer fitnessData = new StringBuffer();
		StringBuffer crossCountData = new StringBuffer();
		StringBuffer mutationCountData = new StringBuffer();
		StringBuffer totalFitnessData = new StringBuffer();
		StringBuffer xValues = new StringBuffer();
		ArrayList<Double> fitnessValues = new ArrayList<Double>();
		ArrayList<String> xiValues = new ArrayList<String>();
		
		for(int i = 0; i < gaResults.size(); i++)
		{
			fitnessData.append("[" + (i+1) + "," + gaResults.get(i).getOptimalFitnessValue() + "], ");
			fitnessValues.add(gaResults.get(i).getOptimalFitnessValue());
			
			xiValues.add(gaResults.get(i).getxValues());
			totalFitnessData.append("[" + (i+1) + "," + gaResults.get(i).getTotalFitnessValues() + "], ");
			crossCountData.append("[" + (i+1) + "," + gaResults.get(i).getCrossOverCounts() + "], ");
			mutationCountData.append("[" + (i+1) + "," + gaResults.get(i).getMutationCounts() + "], ");
		}
		
		maxFitnessValue = ComputationUtility.findMaximumFunctionValue(fitnessValues);
		xi_max = xiValues.get(ComputationUtility.getIndexOfMaxValue(fitnessValues));
		
		minFitnessValue = ComputationUtility.findMinimumFunctionValue(fitnessValues);
		xi_min = xiValues.get(ComputationUtility.getIndexOfMinValue(fitnessValues));
		
		meanFitnessValue = ComputationUtility.findMeanFunctionValue(fitnessValues);
		
		System.out.println("Optimal Fitness Value: " + maxFitnessValue);
		//System.out.println("X Values: (" + xi + ")");
		
		session.setAttribute("fData", fitnessData.toString());
		session.setAttribute("cData", crossCountData.toString());
		session.setAttribute("mData", mutationCountData.toString());
		session.setAttribute("maxF", maxFitnessValue);
		session.setAttribute("xi_max", xi_max);
		session.setAttribute("xi_min", xi_min);
		session.setAttribute("minF", minFitnessValue);
		
		
		
		session.setAttribute("generations", generations);
		session.setAttribute("function", function);
		session.setAttribute("precision", precision);
		session.setAttribute("popSize", popSize);
		session.setAttribute("pC", pC);
		session.setAttribute("pM", pM);
		
	
		out.println("<html>");
		out.println(" <head>");
	    out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
	    out.println("<title>");
	    out.println("Graphs</title>");
//	    out.println("<link href='css/view.css' rel='stylesheet' type='text/css'>");
	    out.println("</link>");
	    out.println("<!--[if IE]>");
	    out.println("<script language='javascript' type='text/javascript' src='/GeneticAlgorithms/flot/excanvas.min.js'></script><![endif]-->");
	    out.println("<script language='javascript' type='text/javascript' src='/GeneticAlgorithms/flot/jquery.js'>");
	    out.println("</script>");
	    out.println("<script language='javascript' type='text/javascript' src='/GeneticAlgorithms/flot/jquery.flot.js'></script>");

		out.println(" </head>");
	
	
	    out.println("<body id='main_body'>");
	    //out.println("<img id='top' src='images/top.png' alt='' />");
		
	    out.println("<h1><a>Graphs</a></h1>");
	    out.println("<h2><u>Distribution</u></h2>");
	    out.println("<b>Optimal Value: </b><u>" + GeneticAlgorithm.optimal_Xi + "</u> at<br/>");
	    out.println("<b>Xi Values: </b><u>" + xi_max + "</u><br/><br/>");
	    
//	    out.println("<b>Minimum: </b><u>" + minFitnessValue + "</u> at<br/>");
//	    out.println("<b>Xi Values: </b><u>" + xi_max + "</u><br/><br/>");
	    
	    out.println("<b>Mean of the Distribution: </b><u>" + meanFitnessValue + "</u><br/>");
	    
	    out.println("<div id='placeholder' style='width:800px;height:600px'></div>");				    
	    out.println("<script id='source' language='javascript' type='text/javascript'>");
		out.println("$(function () {");
		String fVData = "var d1 = ["+ fitnessData.toString() + "];"
		 + "var d2 = ["+ totalFitnessData.toString() + "];";
		out.println(fVData);
	    out.println(" $.plot($(\"#placeholder\"), [");
	    out.println(" {");
	    out.println("data: d1, " + l1);
	    out.println("lines: { show: true, steps: true, fill: true }, points: {show: true}");
	    out.println("},");
    	out.println(" {");
	 	out.println("data: d2, " + "label: \"Total fitness values\", yaxis:2 ");
	    //out.println("lines: { show: true }");
	    out.println("},");
//    	out.println("{");
//    	out.println("data: d2,");
// 		out.println("bars: { show: true }");
				        
	    out.println("]);");
	    out.println("});");
		out.println("</script>");
		out.println("<a href=\"showResults\">Fitness Values Graph</a><br/><br/>");
		out.println("<a href=\"crossOverResults\">Cross Over Results Graph</a><br/><br/>");
		out.println("<a href=\"mutationResults\">Mutation Results</a><br/><br/>");
		
		out.println("</h1><a href=\"index.jsp\">Home</a></body></html>");
		
		
	}
}
