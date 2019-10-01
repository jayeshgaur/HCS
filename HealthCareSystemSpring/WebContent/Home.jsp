<!DOCTYPE html>
<html lang="en">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	if(null != session.getAttribute("userId")){
		response.sendRedirect("UserHome.jsp");
	}
	else if(("admin".equals(session.getAttribute("userRole")))){
		response.sendRedirect("AdminHome.jsp");
	}

%>
<head>
  <title>HealthCareSystem</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <style>
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: black;
   color: white;
}
#myCarousel
{
	height:"50%";
	width:"100%";

}
.container
{
position:relative;
text-align:center;
color:white;




}
.centered
{
position:absolute;
top:50%;
left:50%;
transform:translate(-50%,-50%);

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
      <li class="active"><a href="#">Home</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="registerPage"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="loginPage"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>
 <div class="container">
 
  <img src="<c:url value="/resources/Images/chooseTest1.jpg"/>" alt="Picture1" style="width:100%" style="height:10%"/>
  <div class="center"> <h2>Book Test on the Go with HCS</h2></div>
<p class="bg-secondary text-white"><h2>Book Test on the Go with HCS</h2></p>
</div>

<blockquote>
      When it comes to health, choose a brand India trusts.
    </blockquote>
    
<div class="footer">
<p></p>
  <p align="center">Mumbai &nbsp;&nbsp;&nbsp;&nbsp;Pune&nbsp;&nbsp;&nbsp;&nbsp;Hyderabad&nbsp;&nbsp;&nbsp;&nbsp;Delhi&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class="glyphicon glyphicon-phone"> 1800-123-4567 </span></p>
  

 
</div>
</body>
</html>





















