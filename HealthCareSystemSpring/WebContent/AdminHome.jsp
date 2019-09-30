<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
	if (!("admin".equals(session.getAttribute("userRole")))) {
		response.sendRedirect("loginPage");
	} else {
		
%>

<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
</head>
<body>
<h2> Welcome Admin</h2>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">HealthCareSystem</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="Home.jsp">Home</a></li>
    </ul>
  </div>
</nav>
<a href=""></a>
<table>
	<tr>
		<td>Pick your Operation.</td>
	</tr>
	<tr>
		<td><a href="addCenterPage">Add Center</a></td>
	</tr>
	<tr>
		<td><a href="deleteCenterPage">Remove Center</a></td>
	</tr>
	<tr>
		<td><a href="addTestPage">Add Test</a></td>
	</tr>
	<tr>
		<td><a href="removeTestPage">Remove Test</a></td>
	</tr>
	<tr>
		<td><a href="approveAppointmentPage">Approve Appointment</a></td>
	</tr>
	
</table>
<a href="logout">Logout</a>
</body>

<% } %>
</html>