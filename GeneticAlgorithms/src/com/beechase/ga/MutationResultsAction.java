package com.beechase.ga;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MutationResultsActoin
 */
public class MutationResultsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MutationResultsAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String cData = (String) session.getAttribute("cData");
		String mData = (String) session.getAttribute("mData");
		
		System.out.println("CData: " + cData + "\n" + "MData" + mData);
		
		
		out.println("<html>");
		out.println(" <head>");
	    out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
	    out.println("<title>");
	    out.println("Graphs</title>");
//	    out.println("<link href='css/view.css' rel='stylesheet' type='text/css'>");
//	    out.println("</link>");
	    out.println("<!--[if IE]>");
	    out.println("<script language='javascript' type='text/javascript' src='/GeneticAlgorithms/flot/excanvas.min.js'></script><![endif]-->");
	    out.println("<script language='javascript' type='text/javascript' src='/GeneticAlgorithms/flot/jquery.js'>");
	    out.println("</script>");
	    out.println("<script language='javascript' type='text/javascript' src='/GeneticAlgorithms/flot/jquery.flot.js'></script>");

		out.println(" </head>");
	
	
	    out.println("<body id='main_body'>");
	    //out.println("<img id='top' src='images/top.png' alt='' />");
		
	    out.println("<h1><a>Graphs</a></h1>");
	    out.println("<h2><u>Distribution of Mutation count over the generations</u></h2>");
	    out.println("Mutation rate: " + ((Double)session.getAttribute("pM")*100) + "%");
	    out.println("<div id='placeholder' style='width:800px;height:600px'></div>");				    
	    out.println("<script id='source' language='javascript' type='text/javascript'>");
		out.println("$(function () {");
		String fVData = "var d1 = ["+ cData + "];\n"
		 + "var d2 = ["+ mData + "];";
		out.println(fVData);
	    out.println(" $.plot($(\"#placeholder\"), [");
//	    out.println(" {");
//	    out.println("data: d1, " + "label: \"Cross over Count(y-axis) over Generations(x-axis),\"");
////	    out.println("lines: { show: true, steps: true, fill: true }, points: {show: true}");
//	    out.println("},");
    	out.println(" {");
	 	out.println("data: d2, " + "label: \"Mutations(y-axis) over Generations(x-axis)\" ");
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
