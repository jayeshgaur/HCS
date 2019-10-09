<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	if (null != session.getAttribute("userId")) {
		response.sendRedirect("UserHome");
	} else if (("admin".equals(session.getAttribute("userRole")))) {
		response.sendRedirect("AdminHome");
	}
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="css/footer.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="css/header.css"/>">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
	<header class="header">
		<nav class="navbar navbar-style">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#micon">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a href="Home"><img class="logo"
						src="<c:url value="images/logo.jpg"/>" alt="logo" /></a>
				</div>
				<div class="collapse navbar-collapse" id="micon">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="register"><span
								class="glyphicon glyphicon-user"></span> Sign Up</a></li>
						<li><a href="login"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div>
			<div class="container">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner"
						style="width: 100%; height: 500px !important;">
						<div class="item active">
							<img src="<c:url value="images/bg_1.jpg"/>"
								alt="Los Angeles" style="width: 100%;">
						</div>

						<div class="item">
							<img src="<c:url value="images/dept-1.jpg"/>"
								alt="Chicago" style="width: 100%;">
						</div>

						<div class="item">
							<img src="<c:url value="images/bg_4.jpg"/>"
								alt="New york" style="width: 100%;">
						</div>
					</div>

					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
			</div>


		</div>
		<div class="container text">
			<h3 style="color: darkblue; text-align: center;">The HealthCare
				System</h3>
			<p style="text-align: center;">
				<em>We are here for your care!</em>
			</p>
			<p>Spending hours in a queue at a diagnostic lab or a hospital is
				now a thing of the past. With HCS you can get your lab tests booking
				from the comfort of your home. Book your next lab test with HCS at
				the lowest price guaranteed!</p>
			<p>Book Diagnostic tests near you on HCS, your online lab test
				service provider for online diagnostic, medical tests and health
				checkup packages</p>
			<p>Get all the benefits of diagnostic center right from the
				comfort of your home. With a certified phlebotomy team to ensure
				diagnostic services ranging from individual tests to complete health
				checkup packages for Men, Women, Senior Citizens & Corporates. HCS
				takes care of all your diagnostic needs.</p>
			<h4 style="color: darkblue;">Wide Selection of Tests:</h4>
			<p>Labs covers a wide array of tests like blood sugar tests,
				thyroid tests, pregnancy tests, allergy tests, lipid profile, liver
				profile, platelet count, Mantoux test, VDRL test, vitamin B12
				deficiency test and more. Get details of all these tests which
				includes blood test cost, when to take the tests, why is it
				required, who should take the test and what to do before taking the
				blood tests.</p>
			Keep your health and well being in check with exclusive Health
			Checkup Packages like Men's Health Package, Women's Health Package,
			Vitamin Package, Health Package for Corporates, Diabetes Packages,
			Packages for Senior Citizens &7 more. Get full details of all tests
			available in a package when booking online pathology tests
			</h4>
			<p>Keep your health and well being in check with exclusive Health
				Checkup Packages like Men’s Health Package, Women's Health Package,
				Vitamin Package, Health Package for Corporates, Diabetes Packages,
				Packages for Senior Citizens &7 more. Get full details of all tests
				available in a package when booking online pathology tests</p>
			<p></p>
			<p></p>
		</div>
		<div class="footer" style="background-color: lightblue;">
			<p align="center">
				Mumbai
				&nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span class="glyphicon glyphicon-phone"> 1800-123-4567 </span>
			</p>
		</div>
	</header>


</body>
</html>