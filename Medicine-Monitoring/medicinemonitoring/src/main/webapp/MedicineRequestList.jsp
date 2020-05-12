<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Medicine Request List</title>
<link rel="stylesheet" type="text/css" href="/style11.css"/>
</head>
<body>
<div class="formdata1">
<h2>List of Medicine Requests</h2>
<table class="t2">
<tr>
<th>RequestId</th>
<th>BranchId</th>
</tr>
<c:forEach items="${list}" var="req">
<tr>
<td><a href="getmedicinerequestdetails?token=${req.token}">${req.token}</a></td>
<td>${req.branchadmin.branchId}</td>
</tr>
</c:forEach>
</table>
</div>
<%
RequestDispatcher rd = request.getRequestDispatcher("/adminhome");
rd.include(request, response);
%>
</body>
</html>