<%@page import="com.medicinemonitoring.medicinemonitoring.models.Medicine"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Result</title>
<link rel="stylesheet" type="text/css" href="/style6.css"/>
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
<div class="formdata1">
<h1>Search Result for ${medicinename}</h1>
<h1>Medicine Details</h1>
<c:forEach items="${list}" var="medicine">
<div class="card">
	<div class="image">
		<img src="image.png"/>
	</div>
	<div class="info">
		<div class="mfg">
		<p>mfg:${medicine.manufacturer}</p>
		<span>${medicine.medicineName}</span>
		</div>
		<div class="id">
		<p><a href="requiredmedicine?medid=${medicine.medicineId}">MedicineId:${medicine.medicineId}</a></p>
		</div>
		<div class="disease">
		<p>Disease:${medicine.medicineFor}</p>
		</div>
		<div class="date">
		<p>MfgDate:${medicine.mfgDate}</p>
		<p>ExpiryDate:${medicine.expiryDate}</p>
		</div>	
	</div>
	<div class="mrp">
		<p>MRP:<span>$${medicine.mrp}</span></p>
	</div>
</div>
</c:forEach>
</div>
<div class="sortselect">
<form:form action="sorting" modelAttribute="medlist" method="post">
<form:hidden path="searchBy"/>
<select name="sortname">
<option selected="selected">Sort By</option>
<option value="name">Name</option>
<option value="type">Type</option>
<option value="illness">Illness</option>
<option value="brand">Brand</option>
</select>
<button type="submit">Go</button>
</form:form>
</div>
<% 
RequestDispatcher rd = request.getRequestDispatcher("CustomerHome.jsp");
rd.include(request, response);
%>
</body>
</html>