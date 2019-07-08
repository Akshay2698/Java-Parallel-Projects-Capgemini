<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deposit Success</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/home.jsp"/>
<p>Amount ${dep} deposited to your account successfully...</p>
<br>
<p>Your current account balance is: ${total}</p>
</body>
</html>