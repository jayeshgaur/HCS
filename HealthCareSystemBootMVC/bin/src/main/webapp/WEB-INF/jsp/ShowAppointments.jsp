<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<tr>
			<td>Appointment Id</td>
			<td>Diagnostic Center Name</td>
			<td>Test Name</td>
			<td>Date and Time</td>
			<td>Status</td>
		</tr>
		<a:forEach var="appointment" items="${appointmentList}">
			<tr>
				<td>${appointment.appointmentId}</td>
				<td>${appointment.center.getCenterName()}</td>
				<td>${appointment.test.getTestName()}</td>
				<td>${appointment.dateTime}</td>
			<a:if test="${appointment.getAppointmentStatus() == 0 }">	
				<td>Pending</td>
			</a:if>
			<a:if test="${appointment.getAppointmentStatus() == 1 }">	
				<td>Approved</td>
			</a:if>
			
			</tr>

		</a:forEach>

	</table>
</body>
</html>