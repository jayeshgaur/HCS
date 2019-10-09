<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="tag" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"  type="text/css" href="<c:url value="/webjars/css/footer.css"/>">
<link rel="stylesheet"  type="text/css" href="<c:url value="/webjars/css/header.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link href="<c:url value="css/footer.css" />" rel="stylesheet">
<link href="<c:url value="css/table.css" />" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style>
</style>
<title>Registration</title>
</head>
<body>
<header>
<nav class="navbar navbar-style">
<div class="container">
<div class="navbar-header">
<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#micon">
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</button>
<a href="Home"><img class="logo" src="<c:url value="images/logo.jpg"/>" alt="Picture1"  /></a>
</div>
<div class="collapse navbar-collapse" id="micon">
<ul class="nav navbar-nav navbar-right"> 
<li><a href="Home">HOME</a></li>
</ul>
</div>
</div>
</nav>
	<table>
		<tag:form action="register" method="POST" modelAttribute="customer">
	

			<tr>
				<td>Name</td>
				<td><tag:input path="userName" /></td>
				<td><span><tag:errors path="userName">
						</tag:errors></span></td>
			</tr>
			<tr>
				<td>Email-Id</td>
				<td><tag:input path="userEmail" /></td>
				<td><span><tag:errors path="userEmail">
						</tag:errors></span></td>

			</tr>
			<tr>
				<td>Set Password</td>
				<td><tag:input path="userPassword" /></td>
				<td><tag:errors path="userPassword">
					</tag:errors></td>
			</tr>
			<tr>
				<td>Contact Number</td>
				<td><tag:input path="contactNo" /></td>
				<td><span><tag:errors path="contactNo">
						</tag:errors></span></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><tag:input path="age" /></td>
				<td><span><tag:errors path="age">
						</tag:errors></span></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><tag:radiobutton path="gender" value="Male" />Male <tag:radiobutton
						path="gender" value="Female" />Female <tag:radiobutton
						path="gender" value="Other" />Other</td>
				<td><span><tag:errors path="gender">
						</tag:errors></span></td>
			</tr>
			<tr><td><input type="submit" value="Register" /></td></tr>
			
		</tag:form>
	</table>

	<div class="footer" style="background-color:lightblue; position=äbsolute">
 <p align="center">Mumbai &nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <span class="glyphicon glyphicon-phone"> 1800-123-4567 </span></p>
</div>
</header>
</body>
</html>