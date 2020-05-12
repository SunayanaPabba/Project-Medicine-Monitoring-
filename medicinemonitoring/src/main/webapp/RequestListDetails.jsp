<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Request List Details</title>
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
	<h1>Request List Report</h1>
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
<h1>Request Details</h1>
<table>
	<tr>
		<th>Name</th>
		<th>DateOfBirth</th>
		<th>Contact Number</th>
		<th>Email</th>
		<th>BranchId</th>
		<th>BranchName</th>
		<th>Address</th>
		<th>Status</th>
		<th colspan="2" align="center">Action</th>
	</tr>	
	<tr>
		<td>${branchadmin.getFirstName()}</td>
		<td>${branchadmin.getDob()}</td>
		<td>${branchadmin.getContactNumber()}</td>
		<td>${branchadmin.getEmail()}</td>
		<td>${branchadmin.getBranchId()}</td>
		<td>${branchadmin.getBranchName()}</td>
		<td>${branchadmin.getAddress()}</td>
		<td>${branchadmin.getStatus()}</td>
		<c:if test="${branchadmin.getStatus()=='No'}">
		<td><a href="accept?id=${branchadmin.getBranchId()}"><button id="accept">Accept</button></a></td>
		</c:if>
		<c:if test="${branchadmin.getStatus()=='Yes'}">
		<td><a href="reject?id=${branchadmin.getBranchId()}"><button id="reject">Reject</button></a></td>		
		</c:if>
	</tr>	
	</table>
	<br>	
	${message}
</div>
</body>
</html>