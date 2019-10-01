<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%
	if (!("admin".equals(session.getAttribute("userRole")))) {
		response.sendRedirect("loginPage");
	} else {
		
%>
<head>
<meta charset="ISO-8859-1">
<title>Delete Center</title>
</head>
<body>
<a href="logout">Logout</a>
	<tag:if test="${centerList!=null }">
	<jsp:include page="ShowCenters.jsp"></jsp:include>
	
	<br> Enter the center Id to be deleted:
	<br>
	<form action="deleteCenterSubmit" method="POST">
		Center Id:<input type="text" name="centerId"><br> <input
			type="submit" value="delete center">
	</form>
	</tag:if>
	<tag:if test="${center != null }">
	<hr>
	Are you sure you want to delete the following center?
	<form action="confirmDeleteCenter" method="POST">
		<table border="1">
			<tr>
				<td>Diagnostic Center Id</td>
				<td>Diagnostic Center Name</td>
				<td>Diagnostic Center Phone No</td>
				<td>Diagnostic Center Address</td>
			</tr>

			<tr>
				<td><input type="text" name="centerId" value="${center.centerId}" readonly></td>
				<td>${center.centerName}</td>
				<td>${center.centerContactNo}</td>
				<td>${center.centerAddress}</td>
			</tr>
		</table>
		<input type="submit" value="Confirm Delete">
	</form>
	</tag:if>
	<span> ${deleteMessage }</span>
</body>
<% } %>
</html>