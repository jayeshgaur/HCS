<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
.card-img-top {
	width: 280px;
	height: 180px;
}
</style>
<title>HCS-Admin Home</title>
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
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</nav>

	<h2>Welcome Admin</h2>
	<h3>Pick Your Operation</h3>

	<table>
		<tr>
			<td>Pick your Operation.</td>
		</tr>
		<tr>
			<td><a href="addCenterPage">Add Center</a></td>
		</tr>
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-4">
					<div class="card-deck">
						<div class="card">
							<img class="card-img-top"
								src="<c:url value="/resources/Images/DC.jpg"/>"
								alt="Card image cap">
							<div class="card-body">
								<h4 class="card-title">Diagnostic Center</h4>
								<p class="card-text">If you want to add or remove Diagnostic
									Center,Click on the particular link given below</p>
								<a href="addCenterPage" class="btn btn-primary">Add Center</a> <a
									href="deleteCenterPage" class="btn btn-primary">Remove
									Center</a>
								<div class="card-footer">
									<small class="text-muted">Information should be
										circulated to all Departments</small>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="card">
								<img class="card-img-top"
									src="<c:url value="/resources/Images/chooseTest.jpg"/>"
									alt="Card image cap">
								<div class="card-body">
									<h4 class="card-title">Test</h4>
									<p class="card-text">Choose to Add or Remove test in a
										particular Diagnostic Center</p>
								</div>
								<a href="addTestPage" class="btn btn-primary">Add Test</a> <a
									href="removeTestPage" class="btn btn-primary">Remove Test</a>

								<div class="card-footer">
									<small class="text-muted">These changes will update all
										the data. Confirm before continuing</small>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="card">
								<img class="card-img-top"
									src="<c:url value="/resources/Images/ApproveAppointment.jpg"/>"
									alt="Card image cap">
								<div class="card-body">
									<h4 class="card-title">Appointment</h4>
									<p class="card-text">Approve Customer's Appointment</p>
								</div>
								<a href="approveAppointmentPage" class="btn btn-primary">Approve
									Appointment</a>
								<div class="card-footer">
									<small class="text-muted">Check availabilty before
										Approving</small>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
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
</body>

<div class="footer">
	<p></p>
	<p align="center">
		Mumbai
		&nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="glyphicon glyphicon-phone"> 1800-123-4567 </span>
	</p>



</div>

<%
	}
%>
</html>