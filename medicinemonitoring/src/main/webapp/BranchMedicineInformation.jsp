<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Branch Medicine Information</title>
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
	<h1>Medicine Information</h1>
	</div>
	<div class="add">
	<a href="addBranchMedicine">Add</a>
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
	<a href="branchadminhome">Home</a>
	<a href="adminlogout"><i class="fa fa-power-off"></i> Logout</a>
	</div>	
</div>

<div class="formdata1">
<h1>List of Medicines</h1>
<c:forEach items="${list}" var="med">
	<div class="card">
	<div class="image">
		<img src="image.png"/>
	</div>
	<div class="info">
	<p>Imp Prescription Required</p>
	<span>${med.getMedicineName()}</span>
	<p><a href="getBranchMedicineDetails?userid=<%=userid%>&&medicineId=${med.medicineId}">MedicineId:${med.getMedicineId()}</a></p>
	</div>	
</div>
</c:forEach>
</div>
</body>
</html>