<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<%
	if (!("admin".equals(session.getAttribute("userRole")))) {
		response.sendRedirect("loginPage");
	} else {
		
%>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="logout">Logout</a>
CenterList: 
<jsp:include page="ShowCenters.jsp"></jsp:include>

	<h2>Add New Test:</h2>
	<form action="addTestSubmit" method="POST">
		<table>
			<tr>
				<td>Center Id:</td>
				<td><input name="centerId" /></td>
			</tr>
			<tr>
				<td>Test Name</td>
				<td><input name="testName" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add Test"></input></td>
			</tr>
		</table>
	</form>
</body>
<% } %>
</html>