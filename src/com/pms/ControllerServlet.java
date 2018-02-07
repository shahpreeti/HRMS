package com.pms;
 

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControllerServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		LoginBean2 bean=new LoginBean2();
		bean.setName(name);
		bean.setPassword(password);
		
		HttpSession session=request.getSession(false);  
        if(session!=null)
        {  
        	request.setAttribute("bean",bean);
			session.setAttribute("name",name); 			
        	if(session.getAttribute("name")!=null)
        	{
			 boolean status=bean.validate();
			 bean.setMenu(name);
			 int appr_empid=bean.getAppr_empid();
			 int emp_id=bean.getEmpid();
			 if(status){
				 	session.setAttribute("appr_empid",appr_empid); 
				 	session.setAttribute("emp_id",emp_id); 
			        RequestDispatcher rd=request.getRequestDispatcher("login-success.jsp");
					rd.forward(request, response);
					
				}
				else{
					out.print("error page");
					RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
					rd.forward(request, response);
				}
        	}
        	else
        		response.sendRedirect("index.jsp");
        	
        }
		 else
		 {
			 response.sendRedirect("index.jsp");
		 }
		
		
		
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}

	
}
