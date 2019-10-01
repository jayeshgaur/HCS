<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<tr>
			<td>Test Id</td>
			<td>Test Name</td>
			<td>Test Phone No</td>
			<td>Test Address</td>
		</tr>
		<tag:forEach var="test" items="${testList}">
			<tr>
				<td>${test.centerId}</td>
				<td>${test.centerName}</td>
				<td>${test.centerContactNo}</td>
				<td>${test.centerAddress}</td>


			</tr>

		</tag:forEach>

	</table>
</body>
</html>
