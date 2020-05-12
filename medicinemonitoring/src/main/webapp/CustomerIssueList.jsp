<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Issue List</title>
<link rel="stylesheet" type="text/css" href="/style11.css"/>
</head>
<body>
<div class="formdata1">
<h2>Issue List</h2>
<table class="t2">
<tr>
<th>RequestId</th>
<th>CustomerId</th>
</tr>
<c:forEach items="${list}" var="issue">
<tr>
<td><a href="getissuedetails?token=${issue.token}">${issue.token}</a></td>
<td>${issue.customer.customerId}</td>
</tr>
</c:forEach>
</table>
</div>
<%
RequestDispatcher rd = request.getRequestDispatcher("/adminhome");
rd.include(request, response);
%>
</body>
</body>
</html>