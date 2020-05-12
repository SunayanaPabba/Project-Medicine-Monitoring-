<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RequestList</title>
<link rel="stylesheet" type="text/css" href="/style11.css"/>
</head>
<body>
<div class="formdata1">
<h2>List of Requests</h2>
<table  class="t2">
<tr>
<th>BranchId</th>
<th>BranchName</th>
</tr>
<c:forEach items="${list}" var="admin">
<tr>
<td><a href="getRequestListDetails?id=${admin.getBranchId()}">${admin.getBranchId()}</a></td>
<td>${admin.getBranchName()}</td>
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