<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fo" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%
	if (!("admin".equals(session.getAttribute("userRole")))) {
		response.sendRedirect("login");
	} else {
		
%>
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
<title>Add Center</title>
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
<a href="AdminHome"><img class="logo" src="<c:url value="images/logo.jpg"/>" alt="Picture1"  /></a>
</div>
<div class="collapse navbar-collapse" id="micon">
<ul class="nav navbar-nav navbar-right"> 
<li><a href="AdminHome">HOME</a></li>
<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
</ul>
</div>
</div>
</nav>



	<h3>Add New Center:</h3>
	<fo:form action="/AddCenter" method="POST" modelAttribute="Center">
		<table>
	         <tr>
				<td>Center Name:</td>
				<td><fo:input path="centerName" id="form_center_name"/></td>
				<%-- <td><span><fo:errors path="centerName"></fo:errors></span></td>
				 --%><td><span class="form_error" id="centername_error_message" style="color:red;"><fo:errors path="centerName"></fo:errors></span></td>
			</tr>
			<tr>
				<td>Center Contact Number:</td>
				<td><fo:input path="centerContactNo" id="form_center_contact_no"/></td>
				<%-- <td><span></span></td>
			 --%><td><span class="form_error" id="centercontactno_error_message" style="color:red;"><fo:errors path="centerContactNo"></fo:errors></span></td>
			</tr>
			<tr>
			
				<td>Center Address</td>
				<td><fo:input path="centerAddress" id="form_center_address"/></td>
				<%-- <td><span></span></td> --%>
			<td><span class="form_error" id="centeraddress_error_message" style="color:red;"><fo:errors path="centerAddress"></fo:errors></span></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Add Center"></input></td>
			</tr>
		</table>
	</fo:form>
<span style="color:red;"> ${error }</span>
<span > ${message }</span>
<%-- <div style="text-align: center;" class="alert alert-danger"> ${message} </div> --%>

<div class="footer" style="background-color:lightblue; position=äbsolute">
 <p align="center">Mumbai &nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <span class="glyphicon glyphicon-phone"> 1800-123-4567 </span></p>
</div>
</header>
</body>
<% } %>

<script type="text/javascript">
$(function(){
	$("#centername_error_message").hide();
	$("#centercontactno_error_message").hide();
	$("#centeraddress_error_message").hide();
	var error_centername=false;
	var error_centercontactno=false;
	var error_centeradress=false;
	$("#form_center_name").focusout(function(){
		check_centername();
	});
	$("#form_center_contact_no").focusout(function(){
		check_centercontactno();
	});
	$("#form_center_address").focusout(function(){
			check_centeraddress();
	});
	function check_centername()
	{
		var pattern=/^[A-Za-z]*$/;
		var centername=$("#form_center_name").val();
		if(pattern.test(centername) && centername!=='')
			{
			$("#centername_error_message").hide();
			$("#form_center_name").css("border-bottom","2px solid #34FA58");
			}
		else if(centername=='')
			{
			$("#centername_error_message").html("Field should not be empty");
			$("#centername_error_message").show();
			$("#form_center_name").css("border-bottom","2px solid #F90A0A");
			error_centername=true;
			}
		else
		{
		$("#centername_error_message").html("Field should contain string");
		$("#centername_error_message").show();
		$("#form_center_name").css("border-bottom","2px solid #F90A0A");
		error_centername=true;
		
		}
		
	}
	
	function check_centeraddress()
	{
		var pattern=/^[A-Za-z0-9]*$/;
		var centeraddress=$("#form_center_address").val();
		if(pattern.test(centeraddress) && centeraddress!=='')
			{
			$("#centeraddress_error_message").hide();
			$("#form_center_address").css("border-bottom","2px solid #34FA58");
			}
		else if(centeraddress=='')
			{
			$("#centeraddress_error_message").html("Field should not be empty");
			$("#centeraddress_error_message").show();
			$("#form_center_address").css("border-bottom","2px solid #F90A0A");
			error_centeraddress=true;
			}
	}
	function check_centercontactno()
	{
		var pattern=/^[0-9]*$/;
		var centercontactno=$("#form_center_contact_no").val();
		var length=$("#form_center_contact_no").val().length;
		if(pattern.test(centercontactno) && centercontactno!=='' && length==10 )
			{
			$("#centercontactno_error_message").hide();
			$("#form_center_contact_no").css("border-bottom","2px solid #34FA58");
			}
		else if(centercontactno=='')
			{
			$("#centercontactno_error_message").html("should not be empty");
			$("#centercontactno_error_message").show();
			$("#form_center_contact_no").css("border-bottom","2px solid #F90A0A");
			error_centercontactno=true;
			}
		else if(length!=10)
			{
			$("#centercontactno_error_message").html("should contain 10 digit");
			$("#centercontactno_error_message").show();
			$("#form_center_contact_no").css("border-bottom","2px solid #F90A0A");
			error_centercontactno=true;
			
			}
		else
			{
			$("#centercontactno_error_message").html("should contain only numeric vaues");
			$("#centercontactno_error_message").show();
			$("#form_center_contact_no").css("border-bottom","2px solid #F90A0A");
			error_centercontactno=true;
		
			}
	}
	$("#addcenterform").submit(function(){
		 error_centername=false;
		 error_centercontactno=false;
		 error_centeraddress=false;
		 check_centername();
		 check_centercontactno();
		 check_centeraddress();
		 if(error_centername===false && error_centercontactno===false && error_centeraddress===false)
			 {
			 	//alert("Added center successfully");
			 	return true;
			 }
		 else
			 {
			// alert("Please fill the form correctly");
			 return false;
			 }
		
	});
	
	
	
	
	
	
});
</script>
</html>