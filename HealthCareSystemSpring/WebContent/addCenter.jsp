<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fo" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<%
	if (!("admin".equals(session.getAttribute("userRole")))) {
		response.sendRedirect("loginPage");
	} else {
		
%>
<head>
<meta charset="ISO-8859-1">
<title>Add Center</title>
</head>

<body>
<a href="logout">Logout</a>
	<h2>Add New Center:</h2>
	<fo:form action="addCenterSubmit" method="POST" modelAttribute="Center">
		<table>
	         <tr>
				<td>Center Name:</td>
				<td><fo:input path="centerName"/></td>
				<td><span><fo:errors path="centerName"></fo:errors></span></td>
			</tr>
			<tr>
				<td>Center Contact Number:</td>
				<td><fo:input path="centerContactNo"/></td>
				<td><span><fo:errors path="centerContactNo"></fo:errors></span></td>
			</tr>
			<tr>
			
				<td>Center Address</td>
				<td><fo:input path="centerAddress"/></td>
				<td><span><fo:errors path="centerAddress"></fo:errors></span></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Add Center"></input></td>
			</tr>
		</table>
	</fo:form>
<span> ${error }</span>
<span> ${message }</span>
</body>
<% } %>
</html>