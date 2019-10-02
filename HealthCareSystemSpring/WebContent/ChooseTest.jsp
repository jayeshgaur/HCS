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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link href="<c:url value="/webjars/css/footer.css" />" rel="stylesheet">
<link href="<c:url value="/webjars/css/table.css" />" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<title>Insert title here</title>
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