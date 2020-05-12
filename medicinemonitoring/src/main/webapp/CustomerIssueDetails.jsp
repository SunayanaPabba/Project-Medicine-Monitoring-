<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Issue Details</title>
<link rel="stylesheet" type="text/css" href="/style2.css"/>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<script type="text/javascript">
function openForm(){		
  	document.getElementById("myForm").style.display = "block";
  
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}
</script>
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
	<div class="welcome">
	<table>
	<tr>
	<td>
	Welcome
	</td>
	</tr>
	<tr>
	<td>
	<span><i class="fa fa-user-circle"></i> <%=userid%></span>
	</td>
	</tr>
	</table>
	</div>
	<div class="home">
	<a href="adminhome">Home</a>
	<a href="adminlogout"><i class="fa fa-power-off"></i> Logout</a>
	</div>	
</div>
<div class="formdata2">
<h1> Customer Issue</h1>
<table>
<tr>
<th>IssueId</th>
<th>Issue</th>
<th>Description</th>
<th>ContactNumber</th>
<th>Status</th>
<th>CustomerId</th>
<th>Action</th>
</tr>
<tr>
<td>${issuedetails.token}</td>
<td>${issuedetails.issue}</td>
<td>${issuedetails.description}</td>
<td>${issuedetails.contactNumber}</td>
<td>${issuedetails.status}</td>
<td>${issuedetails.customer.customerId}</td>
<td><button onclick="openForm()">Resolution</button></td>
</tr>
</table>
<br>
${message}
</div>
<div class="form-popup"  id="myForm">
  					<form action="updateresolution" class="form-container" method="post" name="resForm">
    					<h1>Resolution</h1>
    						<input type="hidden"  value="${issuedetails.token}" name="issueid"/>
							<textarea rows="10" cols="50" placeholder="Enter resolution" name="resolution"></textarea>
    						<button type="submit" class="btn">submit</button>
    						<button type="button" class="btn cancel" onclick="closeForm()">Close</button>
  					</form>
  						
</div>
</body>
</html>