<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Stocks</title>
<link rel="stylesheet" type="text/css" href="/style11.css"/>
</head>
<body>
<%
String userid =(String) session.getAttribute("username");
if(userid==null)
{
	response.sendRedirect("/");
}
%>
<div class="formdata1">
<h2>Branch Stock</h2>

	<c:forEach items="${list}" var="med">
		<table border="1" class="t1" id="table">
		<tr>
			<th>MedicineId</th>
			<th>MedicineName</th>
			<th>Manufacturer</th>
			<th>MedicineFor</th>
			<th>Mfg Date</th>
			<th>Expiry Date</th>
			<th>Mrp</th>
			<th>Quantity</th>
		</tr>
		<tr>
		<td>${med.medicineId}</td>
		<td>${med.medicineName}</td>
		<td>${med.manufacturer}</td>
		<td>${med.medicineFor}</td>
		<td>${med.mfgDate}</td>
		<td>${med.expiryDate}</td>
		<td>${med.mrp}</td>
		<td>${med.quantity}</td>	
		</tr>
		<tr>	
		<td colspan="8" align="center">
		<a href="editbranchstocks?userid=<%=userid%>&&mid=${med.medicineId}"><button>Edit</button></a>
		</td>
		</tr>
		</table>
	</c:forEach>
${message}
</div>
<%
RequestDispatcher rd = request.getRequestDispatcher("/branchadminhome");
rd.include(request, response);
%>
</body>
</html>