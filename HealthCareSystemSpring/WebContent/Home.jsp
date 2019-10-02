<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	if(null != session.getAttribute("userId")){
		response.sendRedirect("UserHome.jsp");
	}
	else if(("admin".equals(session.getAttribute("userRole")))){
		response.sendRedirect("AdminHome.jsp");
	}

%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
<link rel="stylesheet"  type="text/css" href="<c:url value="/webjars/css/footer.css"/>">
<link rel="stylesheet"  type="text/css" href="<c:url value="/webjars/css/header.css"/>">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<header class="header">
<nav class="navbar navbar-style">
<div class="container">
<div class="navbar-header">
<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#micon">
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</button>
<a href=""><img class="logo" src="<c:url value="/resources/Images/logo.jpg"/>" alt="Picture1"  /></a>
</div>
<div class="collapse navbar-collapse" id="micon">
<ul class="nav navbar-nav navbar-right">
<li><a href="registerPage"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> <li><a href="loginPage"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
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
    <div class="carousel-inner" style=" width:100%; height: 500px !important;">
      <div class="item active">
        <img  src="<c:url value="/resources/Images/bg_1.jpg"/>" alt="Los Angeles" style="width:100%;">
      </div>

      <div class="item">
        <img src="<c:url value="/resources/Images/dept-1.jpg"/>" alt="Chicago" style="width:100%;">
      </div>
    
      <div class="item">
        <img src="<c:url value="/resources/Images/bg_4.jpg"/>" alt="New york" style="width:100%;">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>


</div>
<div class="container text-center">
  <h3>The HealthCare System</h3>
  <p><em>We are here for your care!</em></p>
  <p>HealthCareSystem </p>
  
   </div>
   <div class="footer" style="background-color:lightblue; position=äbsolute">
 <p align="center">Mumbai &nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <span class="glyphicon glyphicon-phone"> 1800-123-4567 </span></p>
</div>
</header>


</body>
</html>