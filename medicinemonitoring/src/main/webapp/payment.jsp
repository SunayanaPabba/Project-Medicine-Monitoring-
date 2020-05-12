<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
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
<h1>Payment Details</h1>
<div class="formdata">
<form:form onsubmit="return validate()" action="payment" modelAttribute="order" method="post">
<form:input path="medicineid" type="hidden"/>
<form:input path="quantity" type="hidden"/>
<form:input path="address" type="hidden"/>
<table>
<tr>
	<td>PaymentMode:</td>
	<td>
	<form:select path="paymentmode" id="mode">
		<option value="Creditcard" selected="selected">CreditCard</option>
  		<option value="Debitcard">DebitCard</option>
  	</form:select>
	</td>
	</tr>
<tr>
<td>Card Number:</td>
<td><input type="number" name="CardNumber" id="CardNumber" placeholder="CardNumber" required="required"/></td>
</tr>
<tr>
<td>CVV:</td>
<td><input type="number" name="cvv" id="cvv" placeholder="CVV" required="required"/></td>
</tr>
<tr>
<td>Expiry Date:</td>
<td><input type="date" name="expirydate" id="expirydate" required="required"/></td>
</tr>
<tr>
<td>Name on Card:</td>
<td><input type="text" name="name" id="name"  placeholder="Name" required="required"/></td>
</tr>
</table>
<input type="submit" value ="submit" class="formsubmitbutton"/>
</form:form>
<br>
${message}
</div>
</body>
</html>