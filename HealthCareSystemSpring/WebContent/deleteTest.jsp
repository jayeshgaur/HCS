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
<title>Insert title here</title>
</head>
<body>
	<div>
		<jsp:include page="ShowCenters.jsp" />
	</div>
	<form action="removeTestSelectCenter" method="POST">
		<table>
			<tr>
				<td>Center ID</td>
				<td><input type="text" name="centerId"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Display Tests">
		</table>
	</form>
	<span>${errorMessage }</span>
	<tag:if test="${testList != null }">

		<div>Tests under the selected center:</div>

		<jsp:include page="ShowTests.jsp"></jsp:include>
		<form action="removeTestSelectTest" method="POST">
			Test ID: <input type="text" name="testId"><br> 
			<input type="submit" value="Submit Test Id to remove">
		</form>
		<span>${testErrorMessage }</span>

		<br>
		<tag:if test="${testId != null }">
		
			Confirm Delete: 
			<form action="removeTestConfirmTest" method="POST">
				CenterID: <input type="text" name="centerId" value="<%out.println(session.getAttribute("centerId"));%>" readonly>
				TestID: <input type="text" name="testId" value="<%out.println(session.getAttribute("testId"));%>" readonly>
				<input type="submit" value="Confirm Delete"> 
			</form>
		
		</tag:if>
	</tag:if>



</body>
<%
	}
%>
</html>