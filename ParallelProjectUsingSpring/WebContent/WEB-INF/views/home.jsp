<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home-XYZ Bank</title>
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
<div align="center">

	<p><h3> Welcome ${currUser }</h3></p>
	
	<a href="checkbalance"><button class="btn">Check Balance</button></a>	
	<a href="depositmoney"><button class="btn">Deposit Money</button></a>
	<a href="fundtransfer"><button class="btn">Fund Transfer</button></a>	
	<a href="withdrawmoney"><button class="btn">Withdraw Money</button></a>
	<a href="showtransactions"><button class="btn">Show Transactions</button></a>	
	<a href="logout"><button 
							style="background-color: red; 
										 border-radius: 5px; 
										 padding: 5px; 
										 
										 color: white;
										 cursor: pointer;
										 font-weight: bold;"
	>Logout</button></a>
	<hr color="black">
</div>
</body>
</html>