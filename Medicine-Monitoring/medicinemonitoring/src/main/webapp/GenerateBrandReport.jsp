<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Report</title>
<link rel="stylesheet" type="text/css" href="/style2.css"/>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
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
<div class="formdata2">
<h1>${branchid} Report</h1>
<table>
	<tr>
	<th>
	Brands
	</th>
	</tr>
	<c:forEach items="${list}" var="manufacturer">	
	<tr>
	<!-- <td><i class="fa fa-plus-square" aria-hidden="false"></i></td> -->
	<td>${manufacturer}</td>
	</tr>	
	</c:forEach>
	</table>	
</div>
</body>
</html>