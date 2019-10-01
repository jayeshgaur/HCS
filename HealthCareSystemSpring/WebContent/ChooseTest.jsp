<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="tag" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<%
	if (null == session.getAttribute("userId")) {
		response.sendRedirect("loginPage");
	} else {
		
%>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="addAppointmentPage">Choose Other Center</a>
<table border=1>

<tr><td>Test Id</td>
<td>Test Name</td>
</tr>

<a:forEach var="test" items="${testList}">
			<tr>
				
				<td>${test.testId}</td>
				<td>${test.testName}</td>


			</tr>

		</a:forEach>
</table>
<form action="confirmAppointment" method="POST">
Select Test Id:<input type="text" name="testId"><br>
Select Date and Time:<input type="datetime-local" name="dateAndTime" /><br>
User Id:<input type="text" value="<% out.print(session.getAttribute("userId")); %>" name="userId" readonly/><br>
Center Id:<input type="text" value="<% out.print(session.getAttribute("centerId")); %>" name="centerId" readonly><br>
<input type="submit" value="Confirm Appointment" />
</form>
<span> ${message } </span>
</body>

<% } %>
</html>