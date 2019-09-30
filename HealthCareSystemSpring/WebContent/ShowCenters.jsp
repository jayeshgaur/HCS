<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>Diagnostic Center Id</td>
			<td>Diagnostic Center Name</td>
			<td>Diagnostic Center Phone No</td>
			<td>Diagnostic Center Address</td>
		</tr>
		<a:forEach var="center" items="${data}">
			<tr>
				<td>${center.centerId}</td>
				<td>${center.centerName}</td>
				<td>${center.centerContactNo}</td>
				<td>${center.centerAddress}</td>


			</tr>

		</a:forEach>

	</table>
	<span>Center Added Successfully!!</span>
</body>
</html>