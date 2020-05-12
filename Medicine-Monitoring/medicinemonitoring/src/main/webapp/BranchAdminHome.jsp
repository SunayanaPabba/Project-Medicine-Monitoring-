<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Branch Admin Home</title>
<link rel="stylesheet" type="text/css" href="/style11.css"/>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<script type="text/javascript">
function openMenu(){
	document.getElementById("dropdown-content").style.display="block";
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
	<h1>MedicineMonitoring</h1>
	<div class="welcome">
	<table>
	<tr>
	<td>
	Welcome
	</td>
	<tr>
	<td>
	<span><i class="fa fa-user-circle"><%=userid%></i></span>
	</td>
	</tr>
	</table>
	</div>
	<div class="dropdown">
		<button onclick="openMenu()" id="dropbtn"><i class="fa fa-bars"></i></button>
		<div class="dropdown-content" id="dropdown-content">
			<a href="branchmedicineinfo?id=<%=userid%>">Medicine Information</a>
			<a href="updatebranchstocks?userid=<%=userid%>">Update Stocks</a>
			<a href="raisemedicinerequest">Raise Medicine Request</a>
			<div class="menu">
				<a href="#">Generate Report</a> 
					<div class="sub-menu">
						<a href="branchStock?userid=<%=userid%>">Stock</a>
						<a href="branchIllness?userid=<%=userid%>">Illness</a>
						<a href="branchBrand?userid=<%=userid%>">Brand</a>
						<a href="branchExpiryDate?userid=<%=userid%>">Expiry Date</a>
						<a href="branchSales?userid=<%=userid%>">Sales</a>
					</div>
			</div>
			<a href="branchadminlogout">Logout</a>			
		</div>
	</div>
</div>
</body>
</html>