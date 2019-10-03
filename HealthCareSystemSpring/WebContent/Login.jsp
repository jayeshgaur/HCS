<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	if(null != session.getAttribute("userId")){
		response.sendRedirect("UserHome.jsp");
	}
	else if(("admin".equals(session.getAttribute("userRole")))){
		response.sendRedirect("AdminHome.jsp");
	}

%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"  type="text/css" href="<c:url value="/webjars/css/footer.css"/>">
<link rel="stylesheet"  type="text/css" href="<c:url value="/webjars/css/header.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link href="<c:url value="/webjars/css/footer.css" />" rel="stylesheet">
<link href="<c:url value="/webjars/css/table.css" />" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</head>
<header>
<nav class="navbar navbar-style">
<div class="container">
<div class="navbar-header">
<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#micon">
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</button>
<a href="Home.jsp"><img class="logo" src="<c:url value="/resources/Images/logo.jpg"/>" alt="Picture1"  /></a>
</div>
<div class="collapse navbar-collapse" id="micon">
<ul class="nav navbar-nav navbar-right"> 
<li><a href="Home.jsp">HOME</a></li>
</ul>
</div>
</div>
</nav>
	<form action="login" method="POST">
		<table>
			<tr>
				<td>Enter your Email-Id:</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Enter your Password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr><td><input type="submit" value="Log In"></td></tr>
			<tr><td><span>New User? <a href="registerPage">Registration</a></span>
		</td></tr>
		</table>
		<br>
		<br>
		
		
		<br>
		<br>
		
			

	</form>
	<span> ${errormessage} </span>
	<div class="footer" style="background-color:lightblue; position=äbsolute">
 <p align="center">Mumbai &nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <span class="glyphicon glyphicon-phone"> 1800-123-4567 </span></p>
</div>
</header>
</body>
</html>