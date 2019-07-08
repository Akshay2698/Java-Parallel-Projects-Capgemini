<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign up-XYZ Bank</title>
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
	<h1 align="center">Welcome to XYZ Bank</h1>
	<f:form action="signupaction" modelAttribute="customer">
	<table align="center">
		<caption><h2>Sign up page</h2></caption>
		<tr>
		<td>Name</td>
		<td><f:input path="name"/>
			<f:errors path="name"/>
		</td>
		</tr>
		
		<tr>
		<td>Username</td>
		<td><f:input path="username"/>
			<f:errors path="username"/>
		</td>
		</tr>
		
		<tr>
		<td>Password</td>
		<td><f:input path="password"/>
			<f:errors path="password"/>
		</td>
		</tr>
		
		<tr>
		<td>Initial amount<br/> to be deposited</td>
		<td><f:input path="totalamount"/>
			<f:errors path="totalamount"/>
		</td>
		</tr>
		
		<tr>
		
		<td><button type="submit" class="btn">
										 
			SignUp
			</button>
		</td>
		</tr>
	</table>
	</f:form>
</body>
</html>