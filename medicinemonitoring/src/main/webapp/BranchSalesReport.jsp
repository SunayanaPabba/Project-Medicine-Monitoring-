<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

function getMonths()
{
	document.getElementById("year1").style.display="none";
	document.getElementById("divisions").style.display="block";
	document.getElementById("year").innerHTML="<select name='year'><option>2019</option><option>2020</option></select>";
	document.getElementById("month").innerHTML="<select name='month'><option value='1'>January</option><option value='2'>February</option><option value='3'>March</option><option value='4'>April</option><option value='5'>May</option><option value='6'>June</option><option value='7'>July</option><option value='8'>August</option><option value='9'>September</option><option value='10'>October</option><option value='11'>November</option><option value='12'>December</option></select>";
}
function getYears(){
	document.getElementById("divisions").style.display="none";
	document.getElementById("year1").style.display="block";
	document.getElementById("year1").innerHTML="<select name='year'><option>2019</option><option>2020</option></select>";
}
function getSixmonths(){
	document.getElementById("divisions").style.display="none";
	document.getElementById("year1").style.display="none";
}
</script>
<meta charset="ISO-8859-1">
<title>Sales Report</title>
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
	<h1>Sales Report</h1>
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
	<a href="branchadminhome">Home</a>
	<a href="branchadminlogout"><i class="fa fa-power-off"></i> Logout</a>
	</div>	
</div>
<div class="formdata2">
<h1>Report</h1>
<form action="getsalesreportdetails" method="post">
<input type="radio" name="sales" onclick="getMonths()" value="monthly"/>Monthly
<input type="radio" name="sales" onclick="getSixmonths()" value="sixmonths"/>Six Months
<input type="radio" name="sales" value="yearly" onclick="getYears()"/>Yearly
<input type="submit" value="Generate">
<div class="divisions" id="divisions">
<div id="year">
</div>
<div id="month">
</div>
</div>
<div id="year1" class="year">
</div>
</form>
<div class="tabledata">
<table>
<tr>
<c:forEach items="${headerlist}" var="head">
<th>${head}</th>
</c:forEach>
</tr>
<c:forEach items="${orderlist}" var="order">
<tr>
<td>${order.medicineid}</td>
<td>${order.quantity}</td>
<td>${order.orderDate}</td>
<td>${order.customer.firstName}</td>
</tr>
</c:forEach>
</table>
</div>
</div>
</body>
</html>