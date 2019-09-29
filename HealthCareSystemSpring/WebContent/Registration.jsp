<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="tag" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<table border="1">
		<tag:form action="registration" method="POST"
			modelAttribute="customer">
			<tr>
				<td>Password</td>
				<td><tag:input path="userPassword" /></td>
				<td><span><tag:errors path="userPassword">
						</tag:errors></span></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><tag:input path="userName" /></td>
				<td><span><tag:errors path="userName">
						</tag:errors></span></td>
			</tr>
			<tr>
				<td>Email-Id</td>
				<td><tag:input path="userEmail" /></td>
				<td><span><tag:errors path="userEmail">
						</tag:errors></span></td>
			</tr>
			<tr>
				<td>Contact Number</td>
				<td><tag:input path="contactNo" /></td>
				<td><span><tag:errors path="contactNo">
						</tag:errors></span></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><tag:input path="age" /></td>
				<td><span><tag:errors path="age">
						</tag:errors></span></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><tag:radiobutton path="gender" value="Male" /></td>
				<td><tag:radiobutton path="gender" value="Female" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="Register" /></td>
			</tr>
		</tag:form>
	</table>
</body>
</html>