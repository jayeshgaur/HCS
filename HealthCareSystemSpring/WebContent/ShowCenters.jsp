<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Center Id</th>
			<th>Center Name</th>
			<th>Center Contact Number </th>
			<th>Center Address</th>
		</tr>
		<tag:forEach var="dc" items="${data}">
		<form:form action="centerShow" method="POST" modelAttribute="Center">
			<tr>
				<td><form:input type="text" value="${dc.centerId}" path="centerId"/></td>
				<td><form:input type="text" value="${dc.centerName}" path="centerName"/></td>
				<td><form:input type="text" value="${dc.centerContactNumber}" path="centerContactNumber"/></td>
				<td><form:input type="text" value="${dc.centerAddress}" path="centerAddress"/></td>
			</tr>
			<td><input type="submit" value="Submit" /></td>
			</form:form>		
		</tag:forEach>
	</table>
</body>
</html>