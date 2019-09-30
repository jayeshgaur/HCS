<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title>Login Page</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style>
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: black;
   color: white;
}
th, td {
  padding: 15px;
  font-size: 16px;
}


</style>
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
				   <center><span >New User?
					<a href="registerPage">Registration</a></span></center> 
				
	</form>
	<span> ${errormessage} </span>
<div class="footer">
<p></p>
  <p align="center">Mumbai &nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class="glyphicon glyphicon-phone"> 1800-123-4567 </p>
  

 
</div>
</body>
</html>