<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Medicine</title>
<link rel="stylesheet" type="text/css" href="/style2.css"/>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<script type="text/javascript">
function validate(){
	var name=document.getElementById("medicineName").value;
	var disease=document.getElementById("medicineFor").value;
	var manufacturer=document.getElementById("manufacturer").value;
	var mfgdate=document.getElementById("mfgDate").value;
	var expirydate=document.getElementById("expiryDate").value;
	var price=document.getElementById("mrp").value;
	var quantity=document.getElementById("quantity").value;
	if(name==null||name==""){
		document.getElementById("medicineName").style.borderColor="red";
		alert("Please update highlighted mandatory fields");
		return false;
	}else if(disease==null||disease==""){
		document.getElementById("medicineFor").style.borderColor="red";
		alert("Please update highlighted mandatory fields");
		return false;		
	}else if(manufacturer==null||manufacturer==""){
		document.getElementById("manufacturer").style.borderColor="red";
		alert("Please update highlighted mandatory fields");
		return false;		
	}else if(mfgdate==null||mfgdate==""){
		document.getElementById("mfgDate").style.borderColor="red";
		alert("Please update highlighted mandatory fields");
		return false;				
	}else if(expirydate==null||expirydate==""){
		document.getElementById("expiryDate").style.borderColor="red";
		alert("Please update highlighted mandatory fields");
		return false;				
	}else if(price==null||price==""){
		document.getElementById("mrp").style.borderColor="red";
		alert("Please update highlighted mandatory fields");
		return false;				
	}else if(quantity==null||quantity==""){
		document.getElementById("quantity").style.borderColor="red";
		alert("Please update highlighted mandatory fields");
		return false;				
	}
}
</script>
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
	<h1>Medicine Information</h1>
	</div>
	<div class="add">
	<a href="add">Add</a>
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
	<a href="adminhome">Home</a>
	<a href="adminlogout"><i class="fa fa-power-off"></i> Logout</a>		
	</div>
</div>
<h1>Add Medicine</h1>
<div class="formdata">
<form:form onsubmit="return validate()" action="addMedicine" modelAttribute="addMedicine" method="post">
<table>
<tr>
<td>Medicine Name:</td>
<td><form:input path="medicineName" id="medicineName" /></td>
</tr>
<tr>
<td>Treatment:</td>
<td><form:input path="medicineFor" id="medicineFor" /></td>
</tr>
<tr>
<td>Manufacturer:</td>
<td><form:input path="manufacturer" id="manufacturer"/></td>
</tr>
<tr>
<td>Manufacturing Date:</td>
<td><form:input path="mfgDate" id="mfgDate"  type="date"/></td>
</tr>
<tr>
<td>Expiry Date:</td>
<td><form:input path="expiryDate" id="expiryDate"  type="date"/>
</td>
</tr>
<tr>
<td>Maximum Retail Price(MRP):</td>
<td><form:input path="mrp" id="mrp" /></td>
</tr>
<tr>
<td>Quantity</td>
<td><form:input path="quantity" id="quantity"/>
</tr>
<tr>
	<td>Type:</td>
	<td>
	<form:select path="type" id="type" class="select-box" >
		<option  selected="selected" value="Tablets">Tablets</option>
  		<option value="Cream">Cream</option>
  		<option value="Drops">Drops</option>
  		<option value="Syrups">Syrups</option>
  		<option value="Injections">Injections</option>
  		<option value="Salines">Salines</option>
	</form:select>
	</td>
	</tr>
</table>
<input type="submit" value ="submit" class="formsubmitbutton"/>
</form:form>
<br>
${message}
</div>
</body>
</html>