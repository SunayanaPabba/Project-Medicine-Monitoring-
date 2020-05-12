<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Help Service</title>
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
	<a href="customerhome">Home</a>
	<a href="customerlogout"><i class="fa fa-power-off"></i> Logout</a>		
	</div>
</div>
<h1>Report Issue</h1>
<div class="formdata">
	<form:form action="reportissue" method="post" modelAttribute="help">
	<table>
	<tr>
	<td>Issue:</td>
	<td><form:input path="issue" class="formcontrol" required="true"/>
	</td></tr>
	<tr>
	<td><label for="description">Description:</label>
	<td>
	<form:textarea path="description" rows="3" cols="23"/></td>
	</tr>
	<tr>
	<td>ContactNumber:</td>
	<td><form:input path="contactNumber" name="contactNumber" class="formcontrol" required="true"/>
	</td></tr>
	</table>
	<input type="submit" value="submit" class="formsubmitbutton"/><br><br>
	</form:form>
	${message}
</div> 
</body>
</html>