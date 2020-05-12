<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MedicineRequestInfo</title>
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
	<h1>Medicine Requests</h1>
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
<h1> Medicine Request</h1>
<table>
<tr>
<th>RequestId</th>
<th>Medicine Name</th>
<th>Manufacturer</th>
<th>Disease</th>
<th>Quantity</th>
<th>Status</th>
<th>BranchId</th>
<th colspan="2" align="center">Action</th>
</tr>
<tr>
<td>${medreq.token}</td>
<td>${medreq.medicineName}</td>
<td>${medreq.manufacturer}</td>
<td>${medreq.illness}</td>
<td>${medreq.quantity}</td>
<td>${medreq.status}</td>
<td>${medreq.branchadmin.branchId}</td>
<c:if test="${medreq.status=='Denied' || medreq.status=='requested'}">
<td><a href="approvemedicinerequest?token=${medreq.token}">Approve</a></td>
</c:if>
<c:if test="${medreq.status=='Approved'}">
<td><a href="denymedicinerequest?token=${medreq.token}">Deny</a></td>
</c:if>
</tr>
</table>
<br>
${message}
</div>
</body>
</html>