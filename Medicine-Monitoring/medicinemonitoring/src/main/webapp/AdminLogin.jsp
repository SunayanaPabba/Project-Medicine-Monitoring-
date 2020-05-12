<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" type="text/css" href="/style1.css"/>
<script type="text/javascript">
function validate(){
	var id=document.getElementById("userid").value;
	var pwd=document.getElementById("password").value;
	if(id==""){
		document.getElementById("userid").style.borderColor = "red";
		alert("please update mandatory highlighted fields");
		return false;
		}else if(pwd==""){
			document.getElementById("password").style.borderColor = "red";
			alert("please update mandatory highlighted fields");
				return false;
			}
}

</script>
</head>
<body>
<div class="header">
<h1>Medicine Monitoring</h1>
</div>
<h2>Admin Login</h2>
<div class="formdata">
<div align="center">
<form:form onsubmit="return validate()" action="loginverify" method="post" modelAttribute="adminlogin">
<table>
<tr>
<td>UserId:</td>
<td><form:input path="userId" class="formcontrol" id="userid"/></td>
</tr>
<tr>
<td>Password:</td>
<td><form:input path="password" class="formcontrol" id="password" type="password"/></td>
</tr>
</table>
<input type="submit" value="submit" class="formsubmitbutton"/>
</form:form>
<ul>
<li class="l"><a href="forgotuserId">Forgot Userid</a></li>
<li class="l"><a href="forgotpassword">Forgot Password</a></li>
</ul>
<a href="/">Home</a>
<br>
${message}
</div>
</div>
</body>
</html>