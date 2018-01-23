<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div>${message}</div>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>fromAccount</th>
				<th>toAccount</th>
				<th>date</th>
				<th>type</th>
				<th>amount</th>
				<th>status</th>
				<th>systemIp eew</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="b" items="${transactions}">
				<tr>
					<td>${b.id}</td>
					<td>${b.fromAccount.customer.name}</td>
					<td>${b.toAccount.customer.name}</td>
					<td>${b.date}</td>
					<td>${b.type}</td>
					<td>${b.amount}</td>
					<td>${b.status}</td>
					<td>${b.systemIp}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<a href="moneyTransfer">Transfer Money</a>

</body>
</html>