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

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet("/HomePageServlet")
public class HomePageServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);  
        if(session!=null)
        {  
        	if(session.getAttribute("name")!=null)
        	{
	        	String name=(String)session.getAttribute("name");  
				LoginBean2 bean=new LoginBean2();
				bean.setName(name);
				request.setAttribute("bean",bean);
				bean.setMenu(name);
				RequestDispatcher rd=request.getRequestDispatcher("login-success.jsp");
				rd.forward(request, response);
        	}
        	 else
             {
             	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
     			rd.forward(request, response);
             }
        }
        else
        {
        	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
        }
	}

}
