<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fund Transfer</title>
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
<form action="transferaction">
	<table>
		<tr>
		<td>
		Enter Reciever Username</td>
		<td>
		<input type="text" name="t1" required>
		</td>
		</tr>
		<tr>
		<td>Enter Amount to be Transfered</td>
		<td><input type="number" name="t2" required></td>
		</tr>
		<tr>
		<td><button type="submit" class="btn">Transfer Amount</button></td>
		</tr>
	</table>
	<p>${msg}</p>
	<p>${msg2}</p>
</form>
</body>
</html>