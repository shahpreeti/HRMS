<%@ include file="design-page.jsp" %>
<%@page import="com.pms.SaveAppraiseBean"%>
<body>
<% 
SaveAppraiseBean sbean=(SaveAppraiseBean)request.getAttribute("sbean");
out.print(sbean.getAppraiseStatus());
%><br><%

%>
</body>
