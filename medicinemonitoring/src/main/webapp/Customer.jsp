<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Registration</title>
<link rel="stylesheet" type="text/css" href="/style1.css"/>
<script type="text/javascript">
function validate(){
	var fn=document.getElementById("firstName").value;
	var pattern=/[A-Za-z0-9]{6,32}/;
	var phone=document.getElementById("contactNumber").value;
	var pwd=document.getElementById("password").value;
	var pattern2=/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;
	var dob=document.getElementById("dob").value;
	var ln=document.getElementById("lastName").value;
	var gender=document.getElementById("gender").value;
	var id=document.getElementById("customerId").value;
	var email=document.getElementById("email").value;
	var ans=document.getElementById("answer").value;
	 if(fn==""){
		document.getElementById("firstName").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(ln==""){
		document.getElementById("lastName").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(dob==""){
		document.getElementById("dob").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(gender==""){
		document.getElementById("gender").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(phnno==""){
		document.getElementById("contactNumber").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(id==""){
		document.getElementById("customerId").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(pwd==""){
		document.getElementById("password").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(email==""){
		document.getElementById("email").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(ans==""){
		document.getElementById("answer").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(!pattern.test(id)){
		document.getElementById("id").style.borderColor = "red";
		alert("Enter userid with minimum of 6 characters with atleast one numerical value");
		return false;
	}else if(!pattern2.test(pwd)){
		document.getElementById("password").style.borderColor = "red";
		alert("Enter password with minimum of 7 characters with one special character and numerical value");
		return false;
	}
}
</script>
</head>
<body>
<div class="header">
<h1>Medicine Monitoring</h1>
</div>
<h2>Customer Registration</h2>
<div class="formdata">
<form:form  onsubmit="return validate()" action="customerreg" modelAttribute="customer" method="post">
<!-- First Name, Last Name, Age, Gender, Contact Number, Customer Id, Password -->
<table>
<tr>
<td>FirstName:</td>
<td><form:input path="firstName" id="firstName" class="formcontrol"/></td>
</tr>
<tr>
<td>LastName:</td>
<td><form:input path="lastName" id="lastName" class="formcontrol"/></td>
</tr>
<tr>
<td>Date of Birth:</td>
<td><form:input path="dob" id="dob" type="date" class="formcontrol"/></td>
</tr>
<tr>
<td>Gender:</td>
<td><form:radiobutton path="gender" value="female" id="gender" class="formcontrol"/>Female
<form:radiobutton path="gender" value="male" id="gender" class="formcontrol"/>Male
</td>
</tr>
<tr>
<td>Contact Number:</td>
<td><form:input path="contactNumber" id="contactNumber" class="formcontrol"/>
</td>
</tr>
<tr>
<td>UserId:</td>
<td><form:input path="customerId" id="customerId" class="formcontrol"/></td>
</tr>
<tr>
<td>Password:</td>
<td><form:input path="password" id="password" type="password" class="formcontrol"/>
</tr>
<tr>
<td>Email:</td>
<td><form:input path="email" id="email" type="email" class="formcontrol"/>
</tr>
<tr>
<td>Secret Question:</td>
<td>
<form:select path="secretQuestion" id="secretQuestion" class="select-box">
<option  selected="selected" value="What is your Birthdate?">What is your Birthdate?</option>
  <option value="In which school you have studied first?">In which school you have studied first?</option>
  <option value="What is your favourite movie?">What is your favourite movie?</option>
</form:select>
</td>
</tr>
<tr>
<td colspan="2" align="right">
<form:input path="answer" id="answer" placeholder="Answer" width="170px" class="formcontrol"/>
</td>
</tr>
</table>
<input type="submit" value ="submit" class="formsubmitbutton"/>
</form:form>
Already having account?<a href="customerlogin">Login</a>
<br>
${message}
</div>
</body>
</html>