<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Orders</title>
<link rel="stylesheet" type="text/css" href="/style2.css"/>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
<%
String userid =(String) session.getAttribute("username");
if(userid==null)
{
	response.sendRedirect("/");
}
%>
<div class="nav">
	<h1>Medicine Monitoring</h1>
	<div class="title">
	<h1>Orders</h1>
	</div>
	<div class="welcome">
	<table>
	<tr>
	<td>
	Welcome
	</td>
	<tr>
	<td>
	<span><i class="fa fa-user-circle"></i><%=userid%></span>
	</td>
	</tr>
	</table>
	</div>
	<div class="home">
	<a href="customerhome">Home</a>
	<a href="customerlogout"><i class="fa fa-power-off"></i> Logout</a>		
	</div>	
</div>
<div class="formdata2">
<h1>Orders</h1>
<table>
	<tr>
	<th>
	Medicine Id
	</th>
	<th>
	Quantity
	</th>
	<th>
	Address
	</th>
	</tr>
	<c:forEach items="${list}" var="order">	
	<tr>
	<td><a href="requiredmedicine?medid=${order.medicineid}">${order.medicineid}</a></td>
	<td>${order.quantity}</td>
	<td>${order.address}</td>
	</tr>	
	</c:forEach>
	</table>	
</div>
</body>
</html>