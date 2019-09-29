<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Health Care System</title>
</head>
<body>
Please Login to proceed
<form action="login" method="POST">
		<table>
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
			<tr>
				<td>
					<input type="submit" value="Log In">
				</td>
			</tr>
			<tr>
				<td>
					<a href="registrationPage">Registration</a>
				</td>
			</tr>
		</table>
	</form>
	<span> ${errormessage} </span>
</body>
</html>