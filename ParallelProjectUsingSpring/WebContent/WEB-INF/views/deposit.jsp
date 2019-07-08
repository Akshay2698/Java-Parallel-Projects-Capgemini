<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deposit</title>
<style>
	.btn {
  background-color: DodgerBlue;
  border: none;
  color: white;
  border-radius: 5px; 
  padding: 5px; 
  font-size: 16px;
  cursor: pointer;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/home.jsp"/>
<form action="depositaction">
<table>
	<tr>
	<td>Enter Amount to be deposited</td>
	<td><input type="number" name="t1" required></td>
	</tr>
	<tr colspan="2">
	<td>
	<button type="submit" class="btn">Deposit</button>
	</td>
	</tr>	
</table>
<p>${msg}</p>
</form>
</body>
</html>