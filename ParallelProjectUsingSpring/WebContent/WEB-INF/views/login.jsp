<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login- XYZ Bank</title>
</head>
<body>
	<h1 align="center">Welcome to XYZ Bank</h1>
	<form action="loginaction">
	<table align="center">
		<caption><h2>Login to XYZ Bank</h2></caption>
		<tr><td>Username</td>
		<td><input type="text" name="t1" required></td>
		</tr>
		<tr>
		<td>Password</td>
		<td><input type="password" name="t2" required></td>
		</tr>
		<tr>
		<td><button type="submit" style="background-color: green; 
										 border-radius: 5px; 
										 padding: 5px;
										 padding-right:18px; 
										 border-color: white;
										 cursor:pointer;
										 color:white;
										 font-weight: bold;
		">Login</button>
		</td>
		</tr>
	</table>
	</form>
	<p align="center" style="color: red">${invalidUser }</p>
	<p align="center"><a href="signup">Create new Account? click here...</a></p>
</body>
</html>