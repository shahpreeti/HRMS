<%@page import="com.pms.LoginBean2"%>
<%@page import="java.util.*"%>
<%@ include file="design-page.jsp" %>
<style type="text/css">
<%@include file="WEB-INF/styles/mystyle1.css" %>
</style>
<%
LoginBean2 bean=(LoginBean2)request.getAttribute("bean");
String name=(String) session.getAttribute("name");
int appr_empid=(Integer)session.getAttribute("appr_empid");
out.print("Welcome " +name+ " "+appr_empid);
bean.setMenu(name);
int source=1;

session.setAttribute("source",1);
%>

<br>
<br>
<ul>
       <% 
       String[][] menu1=bean.getMenu();
       for(int i=0;i<menu1.length;i++)
       {
    	   if(menu1[i][0]!=null)
    	   {
    	   %>
    		<li><a href=<%=menu1[i][1] %>><%=menu1[i][0]%></a></li>
           <%
           }
    	}%> 
           
</ul>
</body>    
                           
                
                
                
                