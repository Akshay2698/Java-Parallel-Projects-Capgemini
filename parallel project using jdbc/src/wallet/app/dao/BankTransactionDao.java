package wallet.app.dao;
/**
 * @author Akshay Kumar Modi
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import wallet.app.bean.BankTransaction;
import wallet.app.bean.Customer;
import wallet.app.util.MyConnection;


public class BankTransactionDao extends CustomerDao implements BankTransactionDaoInterface {
	//this list stores all transactions of registered users.
	static List<BankTransaction> transactionslist=new ArrayList<BankTransaction>();
	//transaction id list
	public static List<String> transactionidlist=new ArrayList<String>();
	
	Connection conn=MyConnection.getConnection();
	
	//for generating a random transaction id
	
	public static String generateTransactionId(String username)
	{
		int randomNumber=0;
		randomNumber = (int)((Math.random()*900000)+100000);
		String cust_Id=username+randomNumber;
		
		return cust_Id;
	}
	
	//for adding a transaction. it is mainly used for adding initial transaction
	@Override
	public String addTransaction(String username, double credit,double debit,double totalamount)
	{	
		String transactionid=null;
		try {
			//getting transaction ids list
			PreparedStatement ps1=conn.prepareStatement("select * from transactionsidlist");
			
			ResultSet rs1=ps1.executeQuery();
			
			while(rs1.next())
			{
				transactionidlist.add(rs1.getString(1));
			}
			//generating a transaction id for present transaction
			transactionid=generateTransactionId(username);
			
			while(true)
			{
				if(transactionidlist.contains(transactionid))
				{
					transactionid=generateTransactionId(username);	
				}
				else
				{
					break;
				}
			}//end of while true.. here we generated a unique transaction id
			
		//adding transaction into transactionslist table
		PreparedStatement ps2=conn.prepareStatement("insert into transactionlist values(?,?,?,?,?)");
		ps2.setString(1, transactionid);
		ps2.setString(2, username);
		ps2.setDouble(3, credit);
		ps2.setDouble(4, debit);
		ps2.setDouble(5, totalamount);
		int retofaddingintotable=ps2.executeUpdate();
		
		//adding transaction id into transactionsidlist table
		PreparedStatement ps3=conn.prepareStatement("insert into transactionsidlist values(?)");
		ps3.setString(1, transactionid);
		int ret2=ps3.executeUpdate();
		
		if(retofaddingintotable>0)
		{
			if(ret2>0)
			{
				return "transaction added successfully";
			}
			else {
				return "transactionid not added to table";
			}
		}
		else {
			return "transaction not added to table";
		}
		
		}//end of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return "error in table";
		}//end of catch	
		
	}
	
	//withdraw
	@Override
	public double withdrawAmount(String username, double withdrawamount)
	{
		double amountinaccount=0,temp=0;
		String transactionid=null;
		try {
			//getting total amount of an user.
			PreparedStatement ps1=conn.prepareStatement("select * from customerslist where username=?");
			ps1.setString(1, username);
			ResultSet rs1=ps1.executeQuery();
			rs1.next();
			amountinaccount=rs1.getDouble(4);
			
			if(withdrawamount>amountinaccount)
			{
				temp=1;
			}
			else {
				//debit the amount
				temp=amountinaccount-withdrawamount;
				
				PreparedStatement ps2=conn.prepareStatement("alter table customerslist set totalamount=? where username=?");
				ps2.setDouble(1, temp);
				ps2.setString(2, username);
				int retofalter=ps2.executeUpdate();
				if(retofalter>0)
				{
					//generating an unique transaction id
					
					PreparedStatement ps3=conn.prepareStatement("select * from transactionsidlist");
					
					ResultSet rs3=ps3.executeQuery();
					
					while(rs3.next())
					{
						//adding into a list  for temporary storage of transaction ids present in table.
						transactionidlist.add(rs3.getString(1));
					}
					//generating a transaction id for present transaction
					transactionid=generateTransactionId(username);
					
					while(true)
					{
						if(transactionidlist.contains(transactionid))
						{
							transactionid=generateTransactionId(username);	
						}
						else
						{
							break;
						}
					}//end of second while true.. 
					//here we generated a unique transaction id
					
					//now adding the transaction into transactions table
					
					PreparedStatement ps4=conn.prepareStatement("insert into transactionlist values(?,?,?,?,?)");
					ps4.setString(1, transactionid);
					ps4.setString(2, username);
					ps4.setDouble(3, 0);
					ps4.setDouble(4, withdrawamount);
					ps4.setDouble(5, temp);
					int retofinserttransaction=ps4.executeUpdate();
					
					if(retofinserttransaction>0)
					{
						//transaction added successfully.. now add transaction id into transaction ids list
						PreparedStatement ps5=conn.prepareStatement("insert into transactionsidlist values(?)");
						ps5.setString(1, transactionid);
						int retofinserttransactionid=ps5.executeUpdate();
						
						if(retofinserttransactionid>0)
						{
							//transaction successfully completed
							//returning the debited amount. to say that transaction has done successfully
							return temp;
						}//end of if retofinserttransactionid
						else {
							return 0;
						}
						
					}//end of if retofinserttransaction
					else {
						return 0;
					}
					
				}//end of if retofalter>0
				
				else {
					return 0;
				}
			}//end of else		
		}//end of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
		return temp;
	}//end of withdrawAmountmethod
	
	//deposit
	@Override
	public double depositAmount(String username, double depositamount) 
	{
		double temp=0;
		String transactionid=null;
		try {
			//getting total amount from customerlist table
			PreparedStatement ps1=conn.prepareStatement("select totalamount from customerslist where username=?");
			ps1.setString(1, username);
			ResultSet rs1=ps1.executeQuery();
			rs1.next();
			double amountinaccount=rs1.getDouble(1);
			
				//generating transaction id ..getting transaction id's list
				PreparedStatement ps2=conn.prepareStatement("select * from transactionsidlist");
				
				ResultSet rs2=ps2.executeQuery();
				
				while(rs2.next())
				{
					transactionidlist.add(rs2.getString(1));
				}
				//generating a transaction id for present transaction
				transactionid=generateTransactionId(username);
				
				while(true)
				{
					if(transactionidlist.contains(transactionid))
					{
						transactionid=generateTransactionId(username);	
					}
					else
					{
						break;
					}
				}//end of while true.. here we generated a unique transaction id
				
				//amount adding 
				temp=amountinaccount+depositamount;
				
				//updating amount value in table
				PreparedStatement ps3=conn.prepareStatement("alter table customerslist set totalamount=? where username=?");
				ps3.setDouble(1, temp);
				ps3.setString(2, username);
				
				int retofupdatingamount=ps3.executeUpdate();
				
				
				//adding transaction into transactionslist table
				PreparedStatement ps4=conn.prepareStatement("insert into transactionlist values(?,?,?,?,?)");
				ps4.setString(1, transactionid);
				ps4.setString(2, username);
				ps4.setDouble(3, depositamount);
				ps4.setDouble(4, 0);
				ps4.setDouble(5, temp);//temp is remaining amount after debited
				int retofaddingintotable=ps4.executeUpdate();
				
				//adding transaction id into transactionsidlist table
				PreparedStatement ps5=conn.prepareStatement("insert into transactionsidlist values(?)");
				ps5.setString(1, transactionid);
				int ret2=ps5.executeUpdate();
				
				if(retofupdatingamount>0)
				{
				if(retofaddingintotable>0)
				{
					if(ret2>0)
					{
						//successfully amount debited and returning how much amount is debited.
						return temp;
					}
					else {
						return 0;
					}
				}//end of retofaddingintotable
				else {
					return 0;
				}
				}//end of retofupdating
				else {
					return 0;
				}
		}//end of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
		
	}//end of depositAmount() method

	//transfer amount
	@Override
	public double transferAmount(String loginusername, String recieverusername, double tranferamount)

	{
		double a=0,b=0,temp1=0;
		try {
			
		
		//checking whether reciever exists
		PreparedStatement ps2=conn.prepareStatement("select username from customerslist where username=?");
		ps2.setString(1, recieverusername);
		ResultSet rs2=ps2.executeQuery();
		
			if(rs2.getString(1).equals(recieverusername))
			{
				if((a=withdrawAmount(loginusername, tranferamount))!=0)
				{
				if((b=depositAmount(recieverusername, tranferamount))!=0)
					{
					temp1=3;
					return temp1;
					}
				}
			}	
		}//end of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
		return 0;	
	}// end of transferAmount() method
	
	//check balance
	@Override
	public double checkBalance(String username)
	{
		double temp=0;
		try {
		PreparedStatement ps1=conn.prepareStatement("select totalamount from customerslist where username=?");
		ps1.setString(1, username);
		ResultSet rs1=ps1.executeQuery();
		rs1.next();
		temp=rs1.getDouble(1);
		return temp;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
		
	}
	
	//showTransactions method returns a list which contains all transactions of a particular user;
	@Override
	public List<BankTransaction> showTransactions(String loginusername)
	{
		List<BankTransaction> usertransactions=new ArrayList<BankTransaction>();
		
		try {
			PreparedStatement ps1=conn.prepareStatement("select * from transactionlist where username=?");
			ps1.setString(1, loginusername);
			ResultSet rs1=ps1.executeQuery();
			while(rs1.next())
			{
				usertransactions.add(new BankTransaction(rs1.getString(1), rs1.getString(2), rs1.getDouble(3), rs1.getDouble(4), rs1.getDouble(5)));
			}
		}//end of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		return usertransactions;	
	}//end of method

}//end of class
