<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<link rel="stylesheet" type="text/css" href="/style5.css"/>
</head>
<body>
<div class="header" align="center">
<h1>Medicine Monitoring</h1>
</div>
<div class="formdata">
<h1>Forgot Password</h1>
<form action="getcpwd" method="post">
<table>
<tr>
<td>Enter Userid:</td>
<td>
<input type="text" name="userId" class="formcontrol">
</td>
</tr>
<tr>
<td>Select question:</td>
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
<td><input type="text" name="answer" class="formcontrol">
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
</div>
<br>
<span>${message}</span>
</div>
</body>
</html>
