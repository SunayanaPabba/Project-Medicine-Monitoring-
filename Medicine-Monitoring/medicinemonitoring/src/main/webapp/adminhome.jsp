<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" type="text/css" href="/style11.css">
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<script type="text/javascript">
function openForm(field){
	
		var y=document.getElementById("mybtn").value;		
	  	document.getElementById("myForm").style.display = "block";
	  	document.forms["repoForm"]["bid"].value=field.value;
	}

	function closeForm() {
	  document.getElementById("myForm").style.display = "none";
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
		<button><i class="fa fa-bars"></i></button>
		<div class="dropdown-content">
		<a href="medicineinfo">MedicineInformation</a>
		<a href="updatestocks">UpdateStocks</a>
		<a href="viewbranchstock">ViewBranchStock</a>
		<a href="viewmedicinerequest">ViewMedicineRequest</a>
		<div class="menu">
		<a href="#">Registration Requests</a>
			<div class="sub-menu">
				<a href="req">Branch Admin Requests</a>
				<a href="AcceptedList">Accepted Requests</a>
				<a href="RejectedList">Rejected Requests</a>
			</div>
		</div>
		<div class="menu">
		<a href="#">GenerateReport</a>
			<div class="sub-menu">
			<c:forEach items="${list}" var="branch">
				<button  value="${branch.branchId}" onclick="openForm(this)" id="mybtn">${branch.branchId}</button>
			</c:forEach>
			</div>
		</div>
		<a href="viewissues">View Issues</a>
		<a href="adminlogout">Logout</a>
		</div>		
	</div>
</div>

<div class="form-popup"  id="myForm">
  					<form action="generatereport" class="form-container" method="post" name="repoForm">
    					<h1>Select Report</h1>
    						<input type="hidden"  id="bid" name="bid"/>
							<select name="report">
									<option value="stock">Stock</option>
									<option value="brand">Brand</option>
									<option value="illness">Illness</option>
									<option value="expirydate">Expiry Date</option>
									<option value="sales">Sales</option>
							</select>
    						<button type="submit" class="btn">submit</button>
    						<button type="button" class="btn cancel" onclick="closeForm()">Close</button>
  					</form>
  						
</div>				
</body>
</html>