<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<%
	if (null == session.getAttribute("userId")) {
		response.sendRedirect("login");
	} else {
		
%>
<head>

<meta charset="ISO-8859-1">
<link rel="stylesheet"  type="text/css" href="<c:url value="css/footer.css"/>">
<link rel="stylesheet"  type="text/css" href="<c:url value="css/header.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link href="<c:url value="css/footer.css" />" rel="stylesheet">
<link href="<c:url value="css/table.css" />" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<title>HCS-ChooseCenter</title>
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
<a href="UserHome"><img class="logo" src="<c:url value="images/logo.jpg"/>" alt="Picture1"  /></a>
</div>
<div class="collapse navbar-collapse" id="micon">
<ul class="nav navbar-nav navbar-right"> 
<li><a href="UserHome">HOME</a></li>
<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
</ul>
</div>
</div>
</nav>
<h2>CenterList:</h2> 
<jsp:include page="ShowCenters.jsp"></jsp:include>

	<h2>Choose Center:</h2>
	<form action="SelectTests" method="POST">
		<table>
			<tr>
				<td>Center Id:</td>
				<td><input name="centerId" /></td>
			</tr>
				<tr>
				<td><input type="submit" value="Submit Center"></input></td>
			</tr>
		</table>
	</form>
	<tag:if test="${message != null }">
	<div style="text-align: center;" class="alert alert-danger">${message }</div>
	</tag:if>
<tag:if test="${testList != null }">

			<table border=1>

				<tr>
					<td>Test Id</td>
					<td>Test Name</td>
				</tr>

				<c:forEach var="test" items="${testList}">
					<tr>

						<td>${test.testId}</td>
						<td>${test.testName}</td>


					</tr>

				</c:forEach>
			</table>
			<form action="confirmAppointment" method="POST">
				<table>
					<tr>
						<td>Select Test Id:<input type="text" name="testId"><br>
						<td>Select Date and Time:<input type="datetime-local"
							name="dateAndTime" /><br></td>
						<td>User Id:<input type="text"
							value="<%out.print(session.getAttribute("userId"));%>"
							name="userId" readonly /><br></td>
						<td>Center Id:<input type="text"
							value="<%out.print(session.getAttribute("centerId"));%>"
							name="centerId" readonly><br></td>
						<td><input type="submit" value="Confirm Appointment" /></td>
					</tr>
				</table>
			</form>
			<tag:if test="${testmessage != null }">
			<div style="text-align: center;" class="alert alert-danger"> ${testmessage } </div>
			</tag:if>
		</tag:if>
	


</header>
</body>

<% } %>
</html>