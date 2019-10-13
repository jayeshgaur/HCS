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
			<td><b>Diagnostic Center Id</b></td>
			<td><b>Diagnostic Center Name</b></td>
			<td><b>Diagnostic Center Phone No</b></td>
			<td><b>Diagnostic Center Address</b></td>
		</tr>
		<a:forEach var="center" items="${centerList}">
			<tr>
				<td>${center.centerId}</td>
				<td>${center.centerName}</td>
				<td>${center.centerContactNo}</td>
				<td>${center.centerAddress}</td>


			</tr>

		</a:forEach>

	</table>

</body>
</html>