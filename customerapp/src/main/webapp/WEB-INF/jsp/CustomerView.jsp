<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Logged in user is ${username}
	<a href="logout.do">Click here to logout</a>
<div style="color:red">${errorMessage}</div>
<div style="color:green">${successMessage}</div>

	<table border=1>
		<thead>
			<tr>
				<th>Customer Id</th>
				<th>Customer Name</th>
				<th>Address</th>
				<th>Phone</th>
				<th colspan=2>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${customers}" var="customer">
				<tr>
					<td><c:out value="${customer.id}" /></td>
					<td><c:out value="${customer.name}" /></td>
					<td><c:out value="${customer.address}" /></td>
					<td><c:out value="${customer.phone}" /></td>
					<td><a href="customer.do?action=edit&id=${customer.id}">Update</a></td>
					<td><a href="customer.do?action=delete&id=${customer.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="customer.do?action=insert">Add User</a>
	</p>

</body>
</html>