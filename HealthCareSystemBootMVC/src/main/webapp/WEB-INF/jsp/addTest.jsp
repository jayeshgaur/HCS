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
<link rel="stylesheet"  type="text/css" href="<c:url value="/webjars/css/footer.css"/>">
<link rel="stylesheet"  type="text/css" href="<c:url value="/webjars/css/header.css"/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link href="<c:url value="/webjars/css/footer.css" />" rel="stylesheet">
<link href="<c:url value="/webjars/css/table.css" />" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<title>HCS- Add Test</title>
</head>
<body>
<header>
<nav class="navbar navbar-style">
<div class="container">
<div class="navbar-header">
<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#micon">
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</button>
<a href="AdminHome.jsp"><img class="logo" src="<c:url value="/resources/Images/logo.jpg"/>" alt="Picture1"  /></a>
</div>
<div class="collapse navbar-collapse" id="micon">
<ul class="nav navbar-nav navbar-right"> 
<li><a href="Home.jsp">HOME</a></li>
<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
</ul>
</div>
</div>
</nav>

<h2>CenterList:</h2> 
<jsp:include page="ShowCenters.jsp"></jsp:include>

	<h2>Add New Test:</h2>
	<form action="/Test/Add" method="POST" id="addtestform">
		<table>
			<tr>
				<td>Center Id:</td>
				<td><input type="text" name="centerId" id="form_center_id" required=""/></td>
				<td><span class="form_error" id="centerid_error_message" style="color:red;"></span></td>
			</tr>
			<tr>
				<td>Test Name</td>
				<td><input type="text" name="testName" id="form_test_name" required=""/></td>
				<td><span class="form_error" id="testname_error_message" style="color:red;"></span></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add Test"></input></td>
			</tr>
		</table>
	</form>
		<span> ${message }</span>
		
		
	
</header>
</body>
<% } %>
<script type="text/javascript">
$(function(){
	$("#centerid_error_message").hide();
	$("#testname_error_message").hide();
	var error_centerid=false;
	var error_testname=false;
	$("#form_center_id").focusout(function(){
		check_centerid();
	});
	$("#form_test_name").focusout(function(){
		check_testname();
	});
	function check_centerid()
	{
		var pattern=/^[0-9]*$/;
		var centerid=$("#form_center_id").val();
		if(pattern.test(centerid) && centerid!=='')
			{
			$("#centerid_error_message").hide();
			$("#form_center_id").css("border-bottom","2px solid #34FA58");
			}
		else if(centerid=='')
			{
			$("#centerid_error_message").html("should not be empty");
			$("#centerid_error_message").show();
			$("#form_center_id").css("border-bottom","2px solid #F90A0A");
			error_centerid=true;
			}
		else
			{
			$("#centerid_error_message").html("should contain only numeric vaues");
			$("#centerid_error_message").show();
			$("#form_center_id").css("border-bottom","2px solid #F90A0A");
			error_centerid=true;
			
			}
	}
	function check_testname()
	{
		var pattern=/^[A-Za-z]*$/;
		var testname=$("#form_test_name").val();
		if(pattern.test(testname) && testname!=='')
			{
			$("#testname_error_message").hide();
			$("#form_test_name").css("border-bottom","2px solid #34FA58");
			}
		else if(testname=='')
			{
			$("#testname_error_message").html("should not be empty");
			$("#testname_error_message").show();
			$("#form_test_name").css("border-bottom","2px solid #F90A0A");
			error_testname=true;
			}
		else
			{
			$("#testname_error_message").html("should contain string");
			$("#testname_error_message").show();
			$("#form_test_name").css("border-bottom","2px solid #F90A0A");
			error_testname=true;
			
			}
	}
	$("#addtestform").submit(function(){
		 error_centerid=false;
		 error_testname=false;
		 check_centerid();
		 check_testname();
		 if(error_centerid===false && error_testname===false)
			 {
			 	alert("Added center successfully");
			 	return true;
			 }
		 else
			 {
			 alert("Please fill the form correctly");
			 return false;
			 }
		
	});
	
	
	
	
	
	
});

</script>
</html>