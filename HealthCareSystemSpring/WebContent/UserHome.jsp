<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%
	if (null == session.getAttribute("userId")) {
		response.sendRedirect("loginPage");
	} else {
		
%>




<head>
<meta charset="ISO-8859-1">
<title>Health Care System</title>
</head>
<body>
	<h2>
		Welcome, user Id:
		<%
		out.print(session.getAttribute("userId"));
	%>
	</h2>
	<table>
		<tr>
			<td>Pick your Operation.</td>
		</tr>
		<tr>
			<td><a href="addAppointmrntPage">Add Appointment</a></td>
		</tr>
		<tr>
			<td><a href="viewAppointmentPage">View Appointment</a></td>
		</tr>
		<tr>
			<td><a href="viewProfilePage">View Profile</a></td>
		</tr>

	</table>
</body>
<a href="logout">Logout</a>
<% } %>
</html>
