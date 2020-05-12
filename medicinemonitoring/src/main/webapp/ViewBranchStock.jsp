<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Branch Stock</title>
<link rel="stylesheet" type="text/css" href="/style2.css"/>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>

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
	<h1>Branch Report</h1>
	</div>	
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
<h1>Branch Report</h1>
<table>
	<tr>
	<th>
	Branch Name
	</th>
	<th>
	Branch Id
	</th>
	</tr>
	<c:forEach items="${list}" var="branch">	
	<tr>
	<!-- <td><i class="fa fa-plus-square" aria-hidden="false"></i></td> -->
	<td>${branch.branchName}</td>
	<td>
	<div class="menu">
	<a href="#" style="text-decoration:none">${branch.branchId}</a>
		<div class="submenu" >
			<c:forEach items="${medicinetypelist}" var="type">
			<a href="viewbranchstockreport?branchid=${branch.branchId}&&type=${type}">${type}</a>
			</c:forEach>
			</div>
	</div>
	</td>
	</tr>	
	</c:forEach>
	</table>	
</div>
</body>
</body>
</html>