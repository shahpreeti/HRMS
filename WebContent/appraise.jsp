<html>
<%@page import="com.pms.AppraiseBean"%>
<%@page import="com.pms.SaveAppraiseBean"%>
<style type="text/css">
  <%@include file="WEB-INF/styles/mystyle1.css" %>
  <%@include file="WEB-INF/styles/RatingStyle.css" %>
  <%@include file="WEB-INF/styles/AppraisalTabs.css" %>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript" src="scriptappraise.js"></script>
<body onload="bodyLoad()">
<div id="div2">
<p id="company"><a href="HomePageServlet" style="text-decoration: none; color:white">third(i)</a></p>
<p id="slogan">Information. Intelligence. Insight.</p>
<a href="LogoutServlet" style="float:right">Logout</a>
</div><br><br>

<p id ="pms">Performance Management System</p>
<%
out.print("Welcome to the appraisal form ");
out.print(session.getAttribute("name"));
SaveAppraiseBean sbean=(SaveAppraiseBean)request.getAttribute("sbean");
AppraiseBean abean=(AppraiseBean)request.getAttribute("abean");
int apprempid=abean.getApprempid();
out.print("\n\nDisplaying form of employee "+abean.getApprempid());
String[] secname=abean.getSections();
String[][] allforms=abean.getAllForms();
int len=secname.length;
int totalrows=allforms.length;
int totalcols=allforms[0].length;
int i=0,j=0,k=0;
String status=sbean.getAppraiseStatus();
int apprstatus=Integer.parseInt(allforms[0][8]);
Integer source=(Integer) session.getAttribute("source");
%>
<div><%=status %></div>

<div class="tab" id="tabs">
<%for(i=0;i<len;i++)
  {%>
		<button id="Section Name<%=i%>" class="tablinks" onclick="loadForm('<%=i%>')" name="Section Name<%=i%>" ><%=secname[i] %></button>
  <%}%>

</div>
<form name="form1" action="SaveAppraiseServlet?form_apprempid=<%=apprempid %>" method="post">
 <br>
<%for(j=0;j<len;j++)
{
%>
<div class="formsection" id="formsection<%=j%>" onload="editStatus(j)" >
	<%
		String[][] sectionform=abean.getForm(j);
		int slen=sectionform.length;
		if(sectionform[0][9].equals("Y"))
		{
			%><table>
			<%for(int t=0;t<slen;t++)
			{
				String self_rate,super_rate,rev_rate;
				String self_comment,super_comment,rev_comment;
				if(sectionform[t][22].equals("N"))
				{	
					  self_rate=source>=1?source>1?sectionform[0][8].equals("2")?sectionform[t][4]:"":sectionform[t][4]:"";
					  super_rate=source>=2 ?source>2?sectionform[0][20].equals("2")?sectionform[t][17]:"":sectionform[t][17]:"";
					  rev_rate=source>=3 ?source>3?sectionform[0][21].equals("2")?sectionform[t][19]:"":sectionform[t][19]:"";
					  self_comment=source>=1?source>1?sectionform[0][8].equals("2")?sectionform[t][3]:"":sectionform[t][3]:"";
					  super_comment=source>=2 ?source>2?sectionform[0][20].equals("2")?sectionform[t][16]:"":sectionform[t][16]:"";
					  rev_comment=source>=3 ?source>3?sectionform[0][21].equals("2")?sectionform[t][18]:"":sectionform[t][18]:"";					
				}
				else
				{
					  self_rate=sectionform[t][4];
					  super_rate=sectionform[t][17];
					  rev_rate=sectionform[t][19];
					  self_comment=sectionform[t][3];
					  super_comment=sectionform[t][16];
					  rev_comment=sectionform[t][18];						
				}
				String selfrate="self"+j+t;
				String superrate="super"+j+t;
				String revrate="rev"+j+t;
				String selfreview="selfrev"+j+t;
				String superreview="superrev"+j+t;
				String revreview="revrev"+j+t;
				
				
				%>
				<tr><td><label class ="category_font"><%=sectionform[t][2] %></label>
				<input name=<%=selfrate %> class="self_rating" id="<%=j %>t_self<%=t %>" maxlength="1" size="1" style="float:right" value=<%=self_rate %>>
				</td>
				<td><input name=<%=superrate %> class="super_rating" id="<%=j %>t_super<%=t %>" maxlength="1" size="1" style="float:right" value=<%=super_rate %>></td>
				<td><input name=<%=revrate %> class="rev_rating" id="<%=j %>t_rev<%=t %>" maxlength="1" size="1" style="float:right" value=<%=rev_rate %>></td>
				</tr>
				<tr>
					
						<td><label style="float:right"><font size="2">Your comments</font></label>
						<td><label style="float:right"><font size="2">Supervisor's comments</font></label></td>
						<td><label style="float:right"><font size="2">Reviewer's comments</font></label></td>
						
				</tr>
				<tr>
					<td><textarea name="<%=selfreview %>" id="<%=j %>ta_self<%=t %>" class="self_review" rows="4" cols="45" ><%=self_comment %></textarea></td>
					<td><textarea name="<%=superreview %>" id="<%=j %>ta_super<%=t %>" class="super_review" rows="4" cols="45" ><%=super_comment %></textarea></td>
					<td><textarea name="<%=revreview %>" id="<%=j %>ta_rev<%=t %>" class="rev_review" rows="4" cols="45" ><%=rev_comment %></textarea></td>
					
				<td></td>
				<td></td>
				<tr>
				<td width="300"><label><b><font size="2" color="grey">Previous Indicator</font></b></label></td>
				<td width="300"><label><b><font size="2" color="grey">Current Indicator</font></b></label></td>
				<td width="300"><label><b><font size="2" color="grey">Next Indicator</font></b></label></td>
				</tr>
				<tr id="<%=sectionform[t][15]%>" class ="criteria_field">
					<%String c1=sectionform[t][5];
					String c2=sectionform[t][6];
					String c3=sectionform[t][7];%>
					<td width="300"><label id=l1 class=pi><font size="2"><%=c1 %></font></label></td>
					<td width="300"><label id=l2 class =pi><font size="2"><%=c2 %></font></label></td>
					<td width="300"><label id=l3 class=pi><font size="2"><%=c3 %></font></label></td>
				</tr>
			
				<%}
		%></table><%	
		} 
		else{
			%><table><%
			for(int t=0;t<sectionform.length;t++)
			{
				String self_comment,super_comment,rev_comment;
				if(sectionform[t][22].equals("N"))
				{	
					  self_comment=source>=1?source>1?sectionform[0][8].equals("2")?sectionform[t][3]:"":sectionform[t][3]:"";
					  super_comment=source>=2 ?source>2?sectionform[0][20].equals("2")?sectionform[t][16]:"":sectionform[t][16]:"";
					  rev_comment=source>=3 ?source>3?sectionform[0][21].equals("2")?sectionform[t][18]:"":sectionform[t][18]:"";					
				}
				else
				{
					  self_comment=sectionform[t][3];
					  super_comment=sectionform[t][16];
					  rev_comment=sectionform[t][18];						
				}
				
				String selfreview="selfrev"+j+t;
				String superreview="superrev"+j+t;
				String revreview="revrev"+j+t;
				%>
				<tr><td style="text-align:center"><label id="<%=sectionform[t][2]%>" ><font color=#1b8791 size="5" ><b><%=sectionform[t][2] %></b></font></label></td></tr>
				<tr><td style="text-align:center"><label><font color=#E83D8D >What you had to say..?</font></label></td></tr>
		  		<tr><td style="text-align:center"><textarea id="<%=j %>ta_self<%=t %>" class="self_review" rows="5" cols="100"  name="<%=selfreview%>"  ><%=self_comment %></textarea></td></tr>
		  		<tr><td style="text-align:center"><label ><font color=#E83D8D >To be filled by your supervisor..</font></label></td></tr>
		  		<tr><td style="text-align:center"><textarea id="<%=j %>ta_super<%=t %>" class="super_review" rows="5" cols="100"  name="<%=superreview%>"  ><%=super_comment %></textarea></td></tr>
				<tr><td style="text-align:center"><label><font color=#E83D8D >To be filled by your reviewer..</font></label></td></tr>
				<tr><td style="text-align:center"><textarea id="<%=j %>ta_rev<%=t %>" class="rev_review" rows="5" cols="100"  name="<%=revreview%>"  ><%=rev_comment %></textarea></td></tr>
		  		<%
				}
			%></table><%
			}
			
		%>
	
	<input type="submit" id="savebtn" name="action" value="Save" style="float:right">
</div>
<%} %>
<div id="submission">

<input type="submit" id="subbtn" name="action" value="Submit">
</div>
</form></body>
<script type="text/javascript">
document.getElementById("Section Name0").style.backgroundColor = "#ddd";
function bodyLoad()
{
	var classes = ["self_review", "super_review", "rev_review"];
	var rateclasses=["self_rating", "super_rating", "rev_rating"];
	var phases = [<%=allforms[0][8]%>, <%=allforms[0][20]%>, <%=allforms[0][21]%>];
	var source=<%=source%>;
	for(var c=1;c<4;c++)
	{
		if(source!=c)
		{	
			var elements1 = document.getElementsByClassName(classes[c-1]);
			for(var i = 0, length = elements1.length; i < length; i++) 
			{
				document.getElementById(elements1[i].id).readOnly=true;	
				
			}

			var elements2 = document.getElementsByClassName(rateclasses[c-1]);
			for(var i = 0, length = elements2.length; i < length; i++) 
			{
				document.getElementById(elements2[i].id).readOnly=true;	
				
			}
		}
		else if(phases[c-1]!=1)
			{
				var elements1 = document.getElementsByClassName(classes[c-1]);
				for(var i = 0, length = elements1.length; i < length; i++) 
				{
					document.getElementById(elements1[i].id).readOnly=true;	
				}
				var elements2 = document.getElementsByClassName(rateclasses[c-1]);
				for(var i = 0, length = elements2.length; i < length; i++) 
				{
					document.getElementById(elements2[i].id).readOnly=true;	
				}
			}
		}
}

</script>


</html>