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
 * Servlet implementation class SaveAppraiseServlet
 */
@WebServlet("/SaveAppraiseServlet")
public class SaveAppraiseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		SaveAppraiseBean sbean=new SaveAppraiseBean();
		AppraiseBean abean=new AppraiseBean();
		abean.setQuery();
		HttpSession session=request.getSession(false); 
		int source=(int)session.getAttribute("source"); 
		String action = request.getParameter("action");
		int apprempid;
		String[][] formdata,secform;
		String[] secnames;
		if(source==1)
		{
			apprempid=(int) session.getAttribute("appr_empid");
			abean.setApprempid(apprempid);
			abean.setPhaseid(1);
			formdata=abean.getAllForms();
			secnames=abean.getSections();
			int len=secnames.length;
			int k=0;
			for(int i=0;i<len;i++)
			{
				secform=abean.getForm(i);
				int seclen=secform.length;
				for(int j=0;j<seclen;j++)
				{
					String para1="selfrev"+i+""+j;
					String para2="self"+i+""+j;
					formdata[k][3]=request.getParameter(para1);
					if(formdata[k][9].equals("Y"))
						formdata[k][4]=request.getParameter(para2);
					k++;
				}
			}
			sbean.setSelfappr(formdata);
			if ("Save".equals(action)) {
				
				sbean.setAppraiseStatus(1);
				
				sbean.saveForm_self();
				abean.setUpdatedForms(formdata);
				
				request.setAttribute("sbean",sbean);
				request.setAttribute("abean", abean);
				RequestDispatcher rd=request.getRequestDispatcher("appraise.jsp");
				rd.forward(request, response);
				
			} 
			else if ("Submit".equals(action)) {
				try {
					sbean.submitForm(1);
					}
				catch(Exception e)
				{
					System.out.println(e);
				}
			    sbean.getCount();
			    if(sbean.getCount()!=0)
			    {
			    	sbean.setAppraiseStatus(2); 
			    	abean.setUpdatedForms(formdata);
			    	request.setAttribute("abean",abean);
			    	request.setAttribute("sbean",sbean);
			    	RequestDispatcher rd=request.getRequestDispatcher("appraise.jsp");
			    	rd.forward(request, response);
			    }
			    else
			    {
			    	sbean.setAppraiseStatus(3); 
			    	abean.setUpdatedForms(formdata);
			    	request.setAttribute("abean",abean);
			    	request.setAttribute("sbean",sbean);
					RequestDispatcher rd=request.getRequestDispatcher("appraise-success.jsp");
					rd.forward(request, response);
			    }
			}		

		
		}
		if(source==2)
		{
			apprempid= Integer.parseInt(request.getParameter("form_apprempid"));
			abean.setApprempid(apprempid);
			abean.setPhaseid(2);
			formdata=abean.getAllForms();
			secnames=abean.getSections();
			int len=secnames.length;
			int k=0;
			for(int i=0;i<len;i++)
			{
				secform=abean.getForm(i);
				int seclen=secform.length;
				for(int j=0;j<seclen;j++)
				{
					String para1="superrev"+i+""+j;
					String para2="super"+i+""+j;
					formdata[k][16]=request.getParameter(para1);
					if(formdata[k][9].equals("Y"))
						formdata[k][17]=request.getParameter(para2);
					k++;
				}
			}
			sbean.setSelfappr(formdata);
			if ("Save".equals(action)) {
				for(int x=0;x<formdata.length;x++)
					{for(int y=0;y<formdata[0].length;y++)
						System.out.print(formdata[x][y]+"   ");
				System.out.println();}
				sbean.saveForm_super();
				sbean.setAppraiseStatus(1);
				abean.setUpdatedForms(formdata);
				request.setAttribute("sbean",sbean);
				request.setAttribute("abean", abean);
				RequestDispatcher rd=request.getRequestDispatcher("appraise.jsp");
				rd.forward(request, response);
				
			} 
			else if ("Submit".equals(action)) {
				try {
					sbean.submitForm(2);
					}
				catch(Exception e)
				{
					System.out.println(e);
				}
			    sbean.getCount();
			    if(sbean.getCount()!=0)
			    {
			    	sbean.setAppraiseStatus(2); 
			    	abean.setUpdatedForms(formdata);
			    	request.setAttribute("abean",abean);
			    	request.setAttribute("sbean",sbean);
			    	RequestDispatcher rd=request.getRequestDispatcher("appraise.jsp");
			    	rd.forward(request, response);
			    }
			    else
			    {
			    	sbean.setAppraiseStatus(3); 
			    	abean.setUpdatedForms(formdata);
			    	request.setAttribute("abean",abean);
			    	request.setAttribute("sbean",sbean);
					RequestDispatcher rd=request.getRequestDispatcher("appraise-success.jsp");
					rd.forward(request, response);
			    }
			}		
		}
		if(source==3) 
		{
			apprempid= Integer.parseInt(request.getParameter("form_apprempid"));
			abean.setApprempid(apprempid);
			abean.setPhaseid(3);
			formdata=abean.getAllForms();
			secnames=abean.getSections();
			int len=secnames.length;
			int k=0;
			for(int i=0;i<len;i++)
			{
				secform=abean.getForm(i);
				int seclen=secform.length;
				for(int j=0;j<seclen;j++)
				{
					String para1="revrev"+i+""+j;
					String para2="rev"+i+""+j;
					formdata[k][18]=request.getParameter(para1);
					if(formdata[k][9].equals("Y"))
						formdata[k][19]=request.getParameter(para2);
					k++;
				}
			}
			sbean.setSelfappr(formdata);
			if ("Save".equals(action)) {
				for(int x=0;x<formdata.length;x++)
					{for(int y=0;y<formdata[0].length;y++)
						System.out.print(formdata[x][y]+"   ");
				System.out.println();}
				sbean.saveForm_rev();
				sbean.setAppraiseStatus(1);
				abean.setUpdatedForms(formdata);
				request.setAttribute("sbean",sbean);
				request.setAttribute("abean", abean);
				RequestDispatcher rd=request.getRequestDispatcher("appraise.jsp");
				rd.forward(request, response);
				
			} 
			else if ("Submit".equals(action)) {
				try {
					sbean.submitForm(3);
					}
				catch(Exception e)
				{
					System.out.println(e);
				}
			    sbean.getCount();
			    if(sbean.getCount()!=0)
			    {
			    	sbean.setAppraiseStatus(2); 
			    	abean.setUpdatedForms(formdata);
			    	request.setAttribute("abean",abean);
			    	request.setAttribute("sbean",sbean);
			    	RequestDispatcher rd=request.getRequestDispatcher("appraise.jsp");
			    	rd.forward(request, response);
			    }
			    else
			    {
			    	sbean.setAppraiseStatus(3); 
			    	abean.setUpdatedForms(formdata);
			    	request.setAttribute("abean",abean);
			    	request.setAttribute("sbean",sbean);
					RequestDispatcher rd=request.getRequestDispatcher("appraise-success.jsp");
					rd.forward(request, response);
			    }
			}					
		}
	}
}
