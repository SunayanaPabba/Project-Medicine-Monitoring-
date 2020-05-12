<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EditMedicineInfo</title>
<link rel="stylesheet" type="text/css" href="/style2.css"/>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<script type="text/javascript">
function validate(){
	var quantity=document.getElementById("quantity").value;
	if(quantity==0||quantity==null||quantity==""){
		document.getElementById("quantity").style.borderColor="red";
		alert("Please update mandatory highlighted fields");
		return false;
		}
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
<h1>Edit Stock</h1>
<div class="formdata">
<form:form onsubmit="return validate()" action="updateBranchMedicineStock?medid=${editBranchMedicine.medicineId}" modelAttribute="editBranchMedicine" method="post">
<table>
<tr>
<td>Medicine Name:</td>
<td><form:input path="medicineName" type="text" id="medicineName"  readonly="true"/></td>
</tr>
<tr>
<td>Treatment:</td>
<td><form:input path="medicineFor" type="text" id="medicineFor" readonly="true" /></td>
</tr>
<tr>
<td>Manufacturer:</td>
<td><form:input path="manufacturer" type="text" id="manufacturer" readonly="true"/></td>
</tr>
<tr>
<td>Manufacturing Date:</td>
<td><form:input path="mfgDate" type="text" id="mfgDate" readonly="true"/></td>
</tr>
<tr>
<td>Expiry Date:</td>
<td><form:input path="expiryDate" type="text" id="expiryDate" readonly="true"/>
</td>
</tr>
<tr>
<td>Maximum Retail Price(MRP):</td>
<td><form:input path="mrp" type="text" id="mrp" readonly="true"/></td>
</tr>
<tr>
<td>Quantity</td>
<td><form:input path="quantity" id="quantity"/>
</tr>
</table>
<input type="submit" value ="submit" class="formsubmitbutton"/>
</form:form>
<br>
${message}
</div>
</body>
</html>