<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fo" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Test</title>
</head>
<body>
	<h2>Add New Test:</h2>
	<fo:form action="chooseCenterSubmit" method="POST" modelAttribute="mycenter">
		<table>
			
			<tr>
				<td>Center Id</td>
				<td>Center Name</td>
				<td>Center Contact No</td>
				<td>Center Address</td>
			</tr>
		
			<tr>
				<td><fo:select path="centerName" items="${centerName}" /></td>
			</tr>
			<tr><td><input type="submit" value="Submit"></td></tr>
		</table>
	</fo:form>
</body>
</html>