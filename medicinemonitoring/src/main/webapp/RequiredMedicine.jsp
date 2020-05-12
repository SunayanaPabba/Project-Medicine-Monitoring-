<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Required Medicine</title>
<link rel="stylesheet" type="text/css" href="/style4.css"/>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<script type="text/javascript">
function openForm() {
	  document.getElementById("myForm").style.display = "block";
	}

	function closeForm() {
	  document.getElementById("myForm").style.display = "none";
	}
</script>
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
	<span><i class="fa fa-user-circle"></i> <%=userid%></span>
	</td>
	</tr>
	</table>
	</div>
	<div class="home">
	<a href="customerhome">Home</a>
	<a href="customerlogout"><i class="fa fa-power-off"></i> Logout</a>
	</div>		
</div>
<div class="formdata1">
<h1>Medicine Details</h1>
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
		<p>MedicineId:${medicine.medicineId}</p>
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
		<button onclick="openForm()">Order</button>
	</div>
</div>
${message}
</div>
<div class="form-popup" id="myForm">
  <form:form action="validatestock" class="form-container" modelAttribute="orderdetails" method="post">
    <h1>Enter Details</h1>
	<form:input path="medicineid" type="hidden" value="${medicine.medicineId}"/>
    <label for="Quantity"><b>Quantity</b></label>
    <form:input path="quantity" required="true"/>

    <label for="Deliveryaddress"><b>Address</b></label>
    <form:input path="address" required="true"/>
    <button type="submit" class="btn">submit</button>
    <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
  </form:form>
</div>
</body>
</body>
</html>