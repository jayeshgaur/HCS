<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Tests</title>
</head>
<body>

	<table border="1">
		<tr>
			<td><b>Test Id</b></td>
			<td><b>Test Name</b></td>
		</tr>
		<tag:forEach var="test" items="${testList}">
			<tr>
				<td>${test.testId}</td>
				<td>${test.testName}</td>
			</tr>

		</tag:forEach>

	</table>
</body>
</html>
