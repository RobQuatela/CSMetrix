<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quality types</title>
</head>
<body>
	<h1>Quality types</h1>
	<a href="Recordings">Recordings</a>
	<br />
	<form name="frmAddQuality" method="POST" action="QualityTypes">
		<Strong>Name: </Strong><input type="text" name="txtName"><br />
		<Strong>Description: </Strong><br />
		<input type="text" name="txtDescription"><br />
		<button type="submit" name="btnSubmit">Submit</button>
	</form>
	<table>
		<tr>
			<th>name</th>
			<th>description</th>
		</tr>
		<c:forEach var="qt" items="${qts }">
			<tr>
				<td><c:out value="${qt.name }" /></td>
				<td><c:out value="${qt.description }" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>