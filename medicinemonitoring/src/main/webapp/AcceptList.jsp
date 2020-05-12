<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RequestList</title>
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
	<h1>${message} List Report</h1>
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
<h2>${message} List</h2>
<h3>${ifempty}</h3>
<table class="t2">
<tr>
<th>FirstName</th>
<th>LastName</th>
<th>DateofBirth</th>
<th>Contact Number</th>
<th>Email</th>
<th>BranchId</th>
<th>BranchName</th>
<th>Address</th>
<th>Status</th>
</tr>
<c:forEach items="${list}" var="admin">
<tr>
<td>${admin.getFirstName()}</td>
<td>${admin.getLastName()}</td>
<td>${admin.getDob()}</td>
<td>${admin.getContactNumber()}</td>
<td>${admin.getEmail()}</td>
<td>${admin.getBranchId()}</td>
<td>${admin.getBranchName()}</td>
<td>${admin.getAddress()}</td>
<td>${admin.getStatus()}</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>