package com.pms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ReviewEmpServlet")
public class ReviewEmpServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//String name=request.getParameter("name");
		HttpSession session=request.getSession(false);  
        if(session!=null)
        {  
        	if(session.getAttribute("name")!=null)
        	{
        	String name=(String)session.getAttribute("name");  
	        int empid=(int) session.getAttribute("emp_id");
	        ReviewEmpBean subbean=new ReviewEmpBean();
			subbean.setParam(empid);
			subbean.setSubordinates_list();
			request.setAttribute("subbean",subbean);
			session.setAttribute("source",3);
			RequestDispatcher rd=request.getRequestDispatcher("sub-ordinates.jsp");
			rd.forward(request, response);
        	}
        	else request.getRequestDispatcher("index.jsp").forward(request, response);  
		}
        else
        {
        	out.print("Please login first");  
            request.getRequestDispatcher("index.jsp").forward(request, response);  
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
