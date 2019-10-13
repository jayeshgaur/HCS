<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"  type="text/css" href="<c:url value="css/footer.css"/>">
<link rel="stylesheet"  type="text/css" href="<c:url value="css/header.css"/>"> 
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link href="<c:url value="css/footer.css" />" rel="stylesheet">
<link href="<c:url value="css/table.css" />" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>Default Error Page</title>
</head>
<body>
<nav class="navbar navbar-style">
<div class="container">
<div class="navbar-header">
<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#micon">
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</button>
<a href="Home"><img class="logo" src="<c:url value="images/logo.jpg"/>" alt="Picture1"  /></a>
</div>
<div class="collapse navbar-collapse" id="micon">
<ul class="nav navbar-nav navbar-right"> 
<li><a href="Home">HOME</a></li>
<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
</ul>
</div>
</div>
</nav>
</body>
</html>