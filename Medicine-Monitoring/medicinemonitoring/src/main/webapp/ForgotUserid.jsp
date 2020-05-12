<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" type="text/css" href="/style5.css"/>
</head>
<body>
<div class="header">
<h1>Medicine Monitoring</h1>
</div>
<div class="formdata" align="center">
<h1>Forgot UserId</h1>
<form action="/getUserId" method="post">
<table>
<tr>
<td>Contact Number:</td>
<td>
<input type="text" name="contactNumber" class="formcontrol">
</td>
</tr>
<tr>
<td>Select Question:</td>
<td>
<select name="secretQuestion" class="select-box">
<option value="What is your Birthdate?">What is your Birthdate?</option>
<option value="In which school you have studied first?">In which school you have studied first?</option>
<option value="What is your favourite movie?">What is your favourite movie?</option>
</select>

</td>
</tr>
<tr>
<td>Enter answer:</td>
<td><input type="text" name="answer" placeholder="Answer" class="formcontrol">
<br>
<span>${message}</span>
</td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="submit" value="submit" class="formsubmitbutton"> 
</td>
</tr>
</table>
</form>
For Login?<a href="adminlogin">Login</a>
</div>
</body>
</html>
