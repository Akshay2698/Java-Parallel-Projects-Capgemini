<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>show Transactions</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/home.jsp"/>
<table border="1">
	<tr>
	<th>Transaction ID</th>
	<th>Credit</th>
	<th>Debit</th>
	<th>Total Amount</th>
	</tr>
	<c:forEach var="b" items="${trs}">
		<tr>
		<td>${b.transactionid}</td>
		<td style="color: green">+ ${b.credit}</td>
		<td style="color: red">- ${b.debit}</td>
		<td>${b.totalamount}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>