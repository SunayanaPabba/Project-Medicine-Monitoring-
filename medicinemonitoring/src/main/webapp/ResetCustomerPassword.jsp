<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset Password</title>
<link rel="stylesheet" type="text/css" href="/style5.css"/>
</head>
<body>
<%
String username =(String) session.getAttribute("username");
%>
<div class="header" align="center">
<h1>Medicine Monitoring</h1>
</div>
<div class="formdata">
<h1>Reset Password</h1>
<form action="resetcpwd" method="post">
<table>
<tr>
<td>New Password:</td>
<td>
<input type="password" name="password" class="formcontrol">
</td>
</tr>
<tr>
<td>Confirmation Password:</td>
<td>
<input type="password" name="confirmationpassword" class="formcontrol">
</td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="submit" value="submit" class="formsubmitbutton">
</td>
</tr>
</table>
</form>
For Login?<a href="customerlogin">Login</a>
<br>
<span>${message}</span>
</div>
</body>
</html>
