<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Branch Admin</title>
<link rel="stylesheet" type="text/css" href="/style1.css"/>
<script type="text/javascript">
function validate(){
	var fn=document.getElementById("firstName").value;
	var pattern=new RegExp("^([a-zA-Z0-9]+){6,32}$");
	var phone=document.getElementById("contactNumber").value;
	var pwd=document.getElementById("password").value;
	var pattern1=new RegExp("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$");
	var dob=document.getElementById("dob").value;
	var ln=document.getElementById("lastName").value;
	var gender=document.getElementById("gender").value;
	var id=document.getElementById("adminId").value;
	var email=document.getElementById("email").value;
	var branchid=document.getElementById("branchId").value;
	var branchname=document.getElementById("branchName").value;
	var add=document.getElementById("address").value;
	var ans=document.getElementById("answer").value;
	if(fn==""||fn==null)
	{
		document.getElementById("firstName").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(ln==""||ln==null){
		document.getElementById("lastName").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(dob==""||dob==null){
		document.getElementById("dob").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(gender==""||gender==null){
		document.getElementById("lastName").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(phone==""||phone==null){
		document.getElementById("contactNumber").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(id==""||id==null){
		document.getElementById("adminId").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(pwd==""||pwd==null){
		document.getElementById("password").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(email==""||email==null){
		document.getElementById("email").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(branchid==""||branchid==null){
		document.getElementById("branchId").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(branchname==""||branchname==null){
		document.getElementById("branchName").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(add==""||add==null){
		document.getElementById("address").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(ans==""||ans==null){
		document.getElementById("answer").style.borderColor = "red";
		alert("Please update mandatory highlighted fields");
		return false;
	}else if(!pattern.test(id)){
		document.getElementById("adminId").style.borderColor = "red";
		alert("Enter userid with minimum of 6 characters with one digit");
		return false;
	}else if(!pattern1.test(pwd)){
		document.getElementById("password").style.borderColor = "red";
		alert("Enter password with minimum of 7 characters with atleast one special character and one numerical value");
		return false;
		}
	
	
}
</script>
</head>
<body>
<div class="header">
<h1>Medicine Monitoring</h1>
</div>
<h2>BranchAdmin Registration</h2>
<div class="formdata">
<form:form  onsubmit="return validate()" action="branchadminreg" modelAttribute="branchadmin" method="post">
<!-- First Name, Last Name, Age, Gender, Contact Number, BranchAdmin Id, Password -->
<table>
<tr>
<td>FirstName:</td>
<td><form:input path="firstName" id="firstName" /></td>
</tr>
<tr>
<td>LastName:</td>
<td><form:input path="lastName" id="lastName" /></td>
</tr>
<tr>
<td>Date of Birth:</td>
<td><form:input path="dob" id="dob" type="date"/></td>
</tr>
<tr>
<td>Gender:</td>
<td><form:radiobutton path="gender" value="female" id="gender" />Female
<form:radiobutton path="gender" value="male" id="gender" />Male
</td>
</tr>
<tr>
<td>Contact Number:</td>
<td><form:input path="contactNumber" id="contactNumber" />
</td>
</tr>
<tr>
<td>UserId:</td>
<td><form:input path="adminId" id="adminId" /></td>
</tr>
<tr>
<td>Password:</td>
<td><form:input path="password" id="password" type="password"/></td>
</tr>
<tr>
<td>Email:</td>
<td><form:input path="email" id="email" type="email"/></td>
</tr>
<tr>
<td>BranchId:</td>
<td><form:input path="branchId" id="branchId"/></td>
</tr>

<tr>
<td>BranchName:</td>
<td><form:input path="branchName" id="branchName"/></td>
</tr>
<tr>
<td>Address:</td>
<td><form:input path="address" id="address"/></td>
</tr>
<tr>
<td>Secret Question:</td>
<td>
<form:select path="secretQuestion" id="question" class="select-box" >
<option  selected="selected" value="What is your Birthdate?">What is your Birthdate?</option>
  <option value="In which school you have studied first?">In which school you have studied first?</option>
  <option value="What is your favourite movie?">What is your favourite movie?</option>
</form:select>
</td>
</tr>
<tr>
<td colspan="2" align="right">
<form:input path="answer" id="answer" placeholder="Answer" width="170px" />
</td>
</tr>
</table>
<input type="submit" value ="submit" class="formsubmitbutton"/>
</form:form>
Already having account?<a href="branchadminlogin">Login</a>
${message}
</div>
</body>
</html>