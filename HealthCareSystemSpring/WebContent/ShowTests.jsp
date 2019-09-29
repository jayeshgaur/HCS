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
			<th>Test Id</th>
			<th>Test Name</th>

		</tr>
		<tag:forEach var="dcTest" items="${data}">
			<form:form>
				<tr>
					<td><form:input type="text" value="${dcTest.testId}"
							path="testId" /></td>
					<td><form:input type="text" value="${dcTest.testName}"
							path="testName" /></td>
				</tr>
				<tr>
					<td><input type="checkbox" value="Add Test"></input></td>
				</tr>
			</form:form>
		</tag:forEach>
	</table>
</body>
</html>
<%--  or we can do this check box system after putting 
this line in loop as we have to select multiple test as well in one go!
				<td>
					<form:select path="Test">
						<form:option value=value="${dcTest.testName}">Test Name</form:option>
						
					</form:select> 
				</td>  --%>
