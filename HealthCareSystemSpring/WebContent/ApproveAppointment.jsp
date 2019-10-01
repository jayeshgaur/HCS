<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%
	if (!("admin".equals(session.getAttribute("userRole")))) {
		response.sendRedirect("loginPage");
	} else {
%>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
				<td><input type="submit" value="Display Tests">
		</table>
	</form>
	<span>${errorMessage }</span>
	<tag:if test="${appointmentList != null }">

		<div>Appointments under the selected center:</div>

		<jsp:include page="ShowAppointments.jsp"></jsp:include>
		<form action="approveAppointmentSelectAppointment" method="POST">
			Test ID: <input type="text" name="appointmentId"><br> 
			<input type="submit" value="Submit Appointment Id to approve">
		</form>
		<span>${appointmentErrorMessage }</span>

		<br>
		<tag:if test="${appointmentId != null }">
		
			Confirm Approval: 
			<form action="approveAppointmentConfirmAppointment" method="POST">
				CenterID: <input type="text" name="centerId" value="<%out.println(session.getAttribute("centerId"));%>" readonly>
				Appointment ID: <input type="text" name="appointmentId" value="<%out.println(session.getAttribute("appointmentId"));%>" readonly>
				<input type="submit" value="Confirm"> 
			</form>
		<span> ${message } </span>
		</tag:if>
	</tag:if>



</body>
<% } %>
</html>