<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Request Medicine</title>
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
	<div class="welcome">
	<table>
	<tr>
	<td>
	Welcome
	</td>
	<tr>
	<td>
	<span><i class="fa fa-user-circle"></i><%=userid%></span>
	</td>
	</tr>
	</table>
	</div>
	<div class="home">
	<a href="branchadminhome">Home</a>
	<a href="branchadminlogout"><i class="fa fa-power-off"></i> Logout</a>		
	</div>
</div>
<h1>Request Medicine</h1>
<div class="formdata">
<form:form onsubmit="return validate()" action="addMedicineRequest" modelAttribute="addMedicineRequest" method="post">
<table>
<tr>
<td>Medicine Name:</td>
<td><form:input path="medicineName" id="medicineName" required="required"/></td>
</tr>
<tr>
<td>Treatment:</td>
<td><form:input path="illness" id="illness" required="required"/></td>
</tr>
<tr>
<td>Manufacturer:</td>
<td><form:input path="manufacturer" id="manufacturer" required="required"/></td>
</tr>
<tr>
<td>Quantity</td>
<td><form:input path="quantity" id="quantity" required="required"/></td>
</tr>
</table>
<input type="submit" value ="submit" class="formsubmitbutton"/>
</form:form>
<br>
${message}
</div>
</body>
</html>