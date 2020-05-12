<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,com.medicinemonitoring.medicinemonitoring.models.*" %>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Home</title>
<link rel="stylesheet" type="text/css" href="/style6.css"/>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<script type="text/javascript">
function openMenu(){
	document.getElementById("dropdown").style.display="block";
}

window.onclick = function(event) { 
	if (!event.target.matches('.dropbtn')) { 
		document.getElementById("dropdown").style.display="none";
             
	}
}
</script>
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
	<h1>MedicineMonitoring</h1>
	<div class="search">
		<form action="searchresult" method="post">
		<select name="searchby">
			<option value="all" selected="selected">All</option>
			<option value="name">By Name</option>
			<option value="type">By Type</option>
			<option value="brand">By Brand</option>
			<option value="brand">By Illness</option>
		</select>		
		<input type="text" placeholder="Search for medicines" name="searchname"/>
		<input type="submit" value="submit"/>
	</form>
	</div>
	<div class="welcome">
	<table>
	<tr>
	<td>
	Welcome
	</td>
	<tr>
	<td>
	<span><i class="fa fa-user-circle"></i> <%=userid%></span>
	</td>
	</tr>
	</table>
	</div>
	<div class="home">
	<!--<a href="help">Help</a>-->
	<!-- <a href="customerlogout"><i class="fa fa-power-off"></i> Logout</a> -->
	</div>	
	<button onclick="openMenu()" class="dropbtn"><i class="fa fa-bars"></i></button>
</div>
	<div class="dropdown-content" id="dropdown">
	<a href="help">Help</a>
	<a href="viewresolutions">View Resolutions</a>
	<a href="vieworders">View Orders</a>
	<a href="customerlogout">Logout</a>
	</div>
<!-- View Resolutions -->
<div class="formdata1">
<h1>${resolution}</h1>
<table>
<tr>
<c:forEach items="${headerlist}" var="h">
<th>${h}</th>
</c:forEach>
</tr>
<c:forEach items="${issuelist}" var="help">
<tr>
<td>${help.issue}</td>
<td>${help.description}</td>
<td>${help.resolution}</td>
</c:forEach>
</table>
</div>
</body>
</html>