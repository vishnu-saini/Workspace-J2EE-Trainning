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
<div style="color:red">${errorMessage}</div>
<div style="color:green">${successMessage}</div>

	<table border=1>
		<thead>
			<tr>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Price</th>
				<th>Category</th>
				<th colspan=2>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${products}" var="product">
				<tr>
					<td><c:out value="${product.id}" /></td>
					<td><c:out value="${product.name}" /></td>
					<td><c:out value="${product.price}" /></td>
					<td><c:out value="${product.category}" /></td>
					<td><a href="product.do?action=edit&id=${product.id}">Update</a></td>
					<td><a href="product.do?action=delete&id=${product.id}">Delete</a></td>
					<td><a href="order.do?id=${product.id}">Order</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="product.do?action=insert">Add User</a>
	</p>

</body>
</html>