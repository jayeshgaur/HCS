<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>HCS-RemoveTest</title>
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

	<div>
		<jsp:include page="ShowCenters.jsp" />
	</div>
	<form action="SelectCenter" method="POST" id="centerform">
		<table>
			<tr>
				<td>Center ID</td>
				<td><input type="text" name="centerId" id="form_center_id" ></td>
				<td><span class="form_error" id="centerid_error_message" style="color:red;"></span></td>
			</tr>
			<tr>
				<td><input type="submit" value="Display Tests">
		</table>
	</form>
	<span>${errorMessage }</span>
	<tag:if test="${testList != null }">

		<div>Tests under the selected center:</div>

		<jsp:include page="ShowTests.jsp"></jsp:include>
		<form action="/SelectTest" method="POST" id="testform">
			<table>
				<tr>
					<td>Test ID: <input type="text" name="testId" id="form_test_id"><br>
					</td>
					<td><span class="form_error" id="testid_error_message" style="color:red;"></span></td>
					<td><input type="submit" value="Submit Test Id to remove"></td>

					<td><span>${testErrorMessage }</span></td>
				</tr>
			</table>
		</form>
		<td><span>${testErrorMessage }</span></td>

		<br>
		<tag:if test="${testId != null }">

			<form action="/TestConfirm" method="POST">
				<table>
					<tr>
						<td>Confirm Delete:</td>
						<td>CenterID: <input type="text" name="centerId"
							value="<%out.println(session.getAttribute("centerId"));%>"
							readonly></td>
				<td>TestID: <input type="text" name="testId"
							value="<%out.println(session.getAttribute("testId"));%>" readonly></td>
				<td><input type="submit" value="Confirm Delete"> </td>
				</tr></table>
			
</form>

						</tag:if>
	</tag:if>
	<!-- <div class="footer" style="background-color:lightblue; position=äbsolute">
 <p align="center">Mumbai &nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <span class="glyphicon glyphicon-phone"> 1800-123-4567 </span></p>
</div> -->
</header>


</body>
<script type="text/javascript">
$(function(){
	$("#centerid_error_message").hide();
	var error_centerid=false;
	$("#form_center_id").keyup(function(){
		check_centerid();
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
	
	
	
	$("#centerform").submit(function(){
		 error_centerid=false;
		 check_centerid();
		 if(error_centerid===false)
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
<script type="text/javascript">
$(function(){
	$("#testid_error_message").hide();
	var error_testid=false;
	$("#form_test_id").keyup(function(){
		check_testid();});
	
		function check_testid()
		{
			var pattern=new RegExp(/^[0-9]*$/i);
			
			 var testid=$("#form_test_id").val();
			//var testid = document.getElementById("form_test_id");
			//("Inside checkTestId"+testid);
			if(pattern.test(testid) && testid!=='')
				{
				
				$("#testid_error_message").hide();
				$("#form_test_id").css("border-bottom","2px solid #34FA58");
				//alert("error in testid");
				}
			else if(testid=='')
				{
				$("#testid_error_message").html("should not be empty");
				$("#testid_error_message").show();
				$("#form_test_id").css("border-bottom","2px solid #F90A0A");
				error_testid=true;
				//alert("error in testid1");
				}
			else
				{
				$("#testid_error_message").html("should contain only numeric vaues");
				$("#testid_error_message").show();
				$("#form_test_id").css("border-bottom","2px solid #F90A0A");
				error_testid=true;
				//alert("error in testid2");
				
				}
		}	
		
	$("#testform").submit(function(){
		 error_testid=false;
		// console.log("inside submit fx");
		 check_testid();
		 if(error_testid===false)
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