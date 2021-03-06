<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%
	if (!("admin".equals(session.getAttribute("userRole")))) {
		response.sendRedirect("loginPage");
	} else {
%>
<head>

<meta charset="ISO-8859-1">
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

<title>Delete Center</title>
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
<a href="AdminHome.jsp"><img class="logo" src="<c:url value="/resources/Images/logo.jpg"/>" alt="Picture1"  /></a>
</div>
<div class="collapse navbar-collapse" id="micon">
<ul class="nav navbar-nav navbar-right"> 
<li><a href="Home.jsp">HOME</a></li>
<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
</ul>
</div>
</div>
</nav>
	<tag:if test="${centerList!=null }">
		<jsp:include page="ShowCenters.jsp"></jsp:include>

		<h3><br> Enter the center Id to be deleted:</h3>
	
		<form action="deleteCenterSubmit" method="POST">
			<table>
				<tr>
					<td>Center Id:<input type="text" name="centerId"></td>
					<br>
					<td><input type="submit" value="delete center"></td>
					</form>
					</tag:if>
					<tag:if test="${center != null }">
						<hr>
						<td>Are you sure you want to delete the following center?</td>
				</tr>
			</table>
			
			<form action="confirmDeleteCenter" method="POST">
				<table border="1">
					<tr>
						<td>Diagnostic Center Id</td>
						<td>Diagnostic Center Name</td>
						<td>Diagnostic Center Phone No</td>
						<td>Diagnostic Center Address</td>
					</tr>

					<tr>
						<td><input type="text" name="centerId"
							value="${center.centerId}" readonly></td>
						<td>${center.centerName}</td>
						<td>${center.centerContactNo}</td>
						<td>${center.centerAddress}</td>
					</tr>
				</table>
				<input type="submit" value="Confirm Delete">
			</form>
	</tag:if>
	<span> ${deleteMessage }</span>
	<div class="footer" style="background-color:lightblue; position=�bsolute">
 <p align="center">Mumbai &nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <span class="glyphicon glyphicon-phone"> 1800-123-4567 </span></p>
</div>
</header>
</body>
<%
	}
%>
</html>