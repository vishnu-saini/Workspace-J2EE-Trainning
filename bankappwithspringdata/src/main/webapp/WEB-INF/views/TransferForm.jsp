<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form:form action="moneyTransfer" method="post" commandName="moneyTransferForm">
	From Account Number:<form:input path="fromAccount" />
		<form:errors path="fromAccount" class="error" />
		<br />
	To Account Number:<form:input path="toAccount" />
		<form:errors path="toAccount" class="error" />
		<br />
	Amount:<form:input path="amount" />
		<form:errors path="amount" class="error" />
		<br />
		<input type="submit" />
	</form:form>

</body>
</html>