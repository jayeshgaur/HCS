<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
   <link href="<c:url value="/webjars/css/footer.css" />" rel="stylesheet">
 <link href="<c:url value="/webjars/css/table.css" />" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
 
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
  </div>
</nav>
<form action="login" method="POST">
		<table align="center" >
			<tr>
				<td>Enter your Email-Id: </td>
				<td>
					<input type="text" name="email" />
				</td>
			</tr>
			<tr>
				<td>Enter your Password: </td>
				<td>
					<input type="password" name="password" />
				</td>
			</tr>
		</table>
			        <br><br>
					<center><input type="submit" value="Log In"></center>
				    <br><br>
				   <center><span>New User?
					<a href="registerPage">Registration</a></span></center> 
				
	</form>
	<span> ${errormessage} </span>
<div class="footer">
<p></p>
  <p align="center">Mumbai &nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class="glyphicon glyphicon-phone"> 1800-123-4567 </span></p>
  

 
</div>
</body>
</html>