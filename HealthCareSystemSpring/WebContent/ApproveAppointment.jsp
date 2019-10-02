<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>HCS-Approve</title>
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

	<div>
		<jsp:include page="ShowCenters.jsp" />
	</div>
	<form action="approveAppointmentSelectCenter" method="POST">
		<table>
			<tr>
				<td>Center ID</td>
				<td><input type="text" name="centerId"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Display Tests"></td>
		</table>
	</form>
	<span>${errorMessage }</span>
	<tag:if test="${appointmentList != null }">

		<div>Appointments under the selected center:</div>

		<jsp:include page="ShowAppointments.jsp"></jsp:include>
		<form action="approveAppointmentSelectAppointment" method="POST">
		<table><tr>
			<td>Test ID: <input type="text" name="appointmentId"><br> </td>
			<td><input type="submit" value="Submit Appointment Id to approve"></td>
			</tr></table>
		</form>
		<span>${appointmentErrorMessage }</span>

		<br>
		<tag:if test="${appointmentId != null }">
		
			Confirm Approval: 
			<form action="approveAppointmentConfirmAppointment" method="POST">
			<table><tr>
			<td>CenterID: <input type="text" name="centerId" value="<%out.println(session.getAttribute("centerId"));%>" readonly></td>
			<td>Appointment ID: <input type="text" name="appointmentId" value="<%out.println(session.getAttribute("appointmentId"));%>" readonly></td>
			<td><input type="submit" value="Confirm"> </td>
			<td><span> ${message} </span></td>
			</tr>
			</table>
			</form>
		<span> ${message } </span>
		</tag:if>
	</tag:if>
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