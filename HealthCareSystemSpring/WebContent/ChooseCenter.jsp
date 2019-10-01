<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
<a href="logout">Logout</a>
CenterList: 
<jsp:include page="ShowCenters.jsp"></jsp:include>

	<h2>Choose Center:</h2>
	<form action="ChooseCenterSubmit" method="POST">
		<table>
			<tr>
				<td>Center Id:</td>
				<td><input name="centerId" /></td>
			</tr>
				<tr>
				<td><input type="submit" value="Submit Center"></input></td>
			</tr>
		</table>
	</form>
	<span>${message }</span>
</body>
</body>
</html>