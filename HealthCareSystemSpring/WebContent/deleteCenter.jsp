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

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">HealthCareSystem</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="Home.jsp">Home</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>

	<tag:if test="${centerList!=null }">
	<jsp:include page="ShowCenters.jsp"></jsp:include>
	
	<br> Enter the center Id to be deleted:
	<br>
	<form action="deleteCenterSubmit" method="POST">
		Center Id:<input type="text" name="centerId"><br> <input
			type="submit" value="delete center">
	</form>
	</tag:if>
	<tag:if test="${center != null }">
	<hr>
	Are you sure you want to delete the following center?
	<form action="confirmDeleteCenter" method="POST">
		<table border="1">
			<tr>
				<td>Diagnostic Center Id</td>
				<td>Diagnostic Center Name</td>
				<td>Diagnostic Center Phone No</td>
				<td>Diagnostic Center Address</td>
			</tr>

			<tr>
				<td><input type="text" name="centerId" value="${center.centerId}" readonly></td>
				<td>${center.centerName}</td>
				<td>${center.centerContactNo}</td>
				<td>${center.centerAddress}</td>
			</tr>
		</table>
		<input type="submit" value="Confirm Delete">
	</form>
	</tag:if>
	<span> ${deleteMessage }</span>
	<div class="footer">
		<p></p>
		<p align="center">
			Mumbai
			&nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="glyphicon glyphicon-phone"> 1800-123-4567 </span>
		</p>

	</div>
</body>
<% } %>
</html>