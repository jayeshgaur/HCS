<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fo" uri="http://www.springframework.org/tags/form" %>

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
<style>
</style>
<title>Add Center</title>
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



	<h2>Add New Center:</h2>
	<fo:form action="addCenterSubmit" method="POST" modelAttribute="Center">
		<table>
	         <tr>
				<td>Center Name:</td>
				<td><fo:input path="centerName"/></td>
				<td><span><fo:errors path="centerName"></fo:errors></span></td>
			</tr>
			<tr>
				<td>Center Contact Number:</td>
				<td><fo:input path="centerContactNo"/></td>
				<td><span><fo:errors path="centerContactNo"></fo:errors></span></td>
			</tr>
			<tr>
			
				<td>Center Address</td>
				<td><fo:input path="centerAddress"/></td>
				<td><span><fo:errors path="centerAddress"></fo:errors></span></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Add Center"></input></td>
			</tr>
		</table>
	</fo:form>
<span> ${error }</span>
<span> ${message }</span>

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