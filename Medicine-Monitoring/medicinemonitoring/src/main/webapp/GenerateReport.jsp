<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Generate Report</title>
</head>
<body>
<%
String userid =(String) session.getAttribute("name");
if(userid==null)
{
	response.sendRedirect("/");
}
%>
<div class="nav">
	<h1>Medicine Monitoring</h1>
	<div class="title">
	<h1> Brand Report</h1>
	</div>	
	<div class="welcome">
	<table>
	<tr>
	<td>
	Welcome
	</td>
	</tr>
	<tr>
	<td>
	<span><i class="fa fa-user-circle"></i> <%=userid%></span>
	</td>
	</tr>
	</table>
	</div>
	<div class="home">
	<a href="adminhome">Home</a>
	<a href="adminlogout"><i class="fa fa-power-off"></i> Logout</a>
	</div>	
</div>
</body>
</html>