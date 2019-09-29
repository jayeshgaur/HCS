<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Health Care System</title>
</head>
<body>
Please Login to proceed
<form action="Home" method="POST">
		<table>
			<tr>
				<td>Enter your Email-Id: </td>
				<td>
					<input type="text" name="UserEmailId" />
				</td>
			</tr>
			<tr>
				<td>Enter your Password: </td>
				<td>
					<input type="password" name="Password" />
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
	</form>form>
</body>
</html>