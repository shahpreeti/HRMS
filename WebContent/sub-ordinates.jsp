<html>
<%@ include file="design-page.jsp" %>
<%@page import="com.pms.SubordinatesBean"%>
<%@page import="com.pms.ReviewEmpBean"%>
<%@page import="com.pms.BaseForReview"%>

<style type="text/css">
  <%@include file="WEB-INF/styles/dropdownlist.css" %>
  <%@include file="WEB-INF/styles/SubList.css" %>
</style>
<form name="form2" action="AppraiseServlet" >
<%out.print("Select any subordinate ");
out.print(session.getAttribute("name"));
Integer source=(Integer)session.getAttribute("source");
String[][] sub_list;
BaseForReview subbean;
if(source==2)
{
	subbean=(SubordinatesBean)request.getAttribute("subbean");
}
else 
{
	subbean=(ReviewEmpBean)request.getAttribute("subbean");
}
sub_list=subbean.getSubordinates_list();
request.setAttribute("subbean", subbean);

int len=sub_list.length;

%>
<div id="sub_list">
<table>
<tr>
		<th>Sno.</th>
		<th>Appr_empid</th>
		<th>Emp_id</th>
		<th>Designation</th>
		<th>Current_phase</th>
		<th>link</th>
	</tr>
<%
for(int i=0;i<len;i++)
  {
	int current_phase=Integer.parseInt(sub_list[i][2]);
	int phase=Integer.parseInt(sub_list[i][1]);
	
	String link=current_phase>=phase?"":"";
	%>
	<tr>
	<td><%=i+1 %></td>
	<td><%=sub_list[i][0]%></td>
	<td><%=sub_list[i][4]%></td>
	<td>..</td>
	<td><%=sub_list[i][2]%></td>
	<%if (current_phase>=phase) {%>
	<td><a href="AppraiseServlet?sub_apprempid=<%=sub_list[i][0]%>">Edit</a></td>
	<%} else {%>
	<td>Edit</td> <%} %>
	</tr>	
	<%
  }%>
</table>
</div>


</form>
<script>
function openForm(emp)
{
	document.getElementById("test").innerHTML =emp;
}
</script>
</html>