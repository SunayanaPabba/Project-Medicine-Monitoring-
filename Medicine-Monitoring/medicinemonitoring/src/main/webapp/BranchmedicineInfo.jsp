<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Branch Medicine Details</title>
<link rel="stylesheet" type="text/css" href="/style4.css"/>
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
	<a href="branchadmin">Home</a>
	<a href="adminlogout"><i class="fa fa-power-off"></i> Logout</a>
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
		<p>mfg:${branchmedicine.manufacturer}</p>
		<span>${branchmedicine.medicineName}</span>
		</div>
		<div class="id">
		<p>MedicineId:${branchmedicine.medicineId}</p>
		</div>
		<div class="disease">
		<p>Disease:${branchmedicine.medicineFor}</p>
		</div>
		<div class="date">
		<p>MfgDate:${branchmedicine.mfgDate}</p>
		<p>ExpiryDate:${branchmedicine.expiryDate}</p>
		</div>	
	</div>
	<div class="mrp">
		<p>MRP:<span>$${branchmedicine.mrp}</span></p>
		<p>Qty:${branchmedicine.quantity}</p>
		<a href="editBranchMedicine?userid=<%=userid%>&&mid=${branchmedicine.medicineId}"><button>Edit</button></a>
	</div>
</div>
</div>
</body>
</html>