package wallet.app.dao;
/**
 * @author Akshay Kumar Modi
 */
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import wallet.app.bean.BankTransaction;
import wallet.app.bean.Customer;


public class BankTransactionDao extends CustomerDao implements BankTransactionDaoInterface {
	//this list stores all transactions of registered users.
	static List<BankTransaction> transactionslist=new ArrayList<BankTransaction>();
	//transaction id list
	public static List<String> transactionidlist=new ArrayList<String>();

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
		String transactionid=generateTransactionId(username);
		while(true)
		{
			if(transactionidlist.contains(transactionid))
			{
				transactionid=generateTransactionId(username);
			}
			else
				break;
		}
		
		transactionslist.add(new BankTransaction(transactionid, username, credit, debit, totalamount));
		transactionidlist.add(transactionid);
		return "first transaction added successfully";
	}
	
	//withdraw
	@Override
	public double withdrawAmount(String username, double withdrawamount) 
	{
		
		double amountinaccount=0,temp=0;
		for(Customer c:customerslist)
		{
			if(c.getUsername().equals(username))
			{
				amountinaccount=c.getTotalamount();
				if(withdrawamount>amountinaccount)
				{
					temp=1;
				}
				else
				{
					//generating transaction id
					String transactionid=generateTransactionId(username);
					while(true)
					{
						if(transactionidlist.contains(transactionid))
						{
							transactionid=generateTransactionId(username);
						}
						else
							break;
					}
					
				temp=amountinaccount-withdrawamount;
				c.setTotalamount(temp);
				//add withdraw transaction into list
				transactionslist.add(new BankTransaction(transactionid, username, 0,withdrawamount, temp));
				transactionidlist.add(transactionid);
		
				return temp;
				}
			}
		}//end of for
		return 0;
		
	}
	
	//deposit
	@Override
	public double depositAmount(String username, double depositamount) 
	{
		
		double amountinaccount=0,temp=0;
		for(Customer c:customerslist)
		{
			if(c.getUsername().equals(username))
			{
				//generating transaction id
				String transactionid=generateTransactionId(username);
				while(true)
				{
					if(transactionidlist.contains(transactionid))
					{
						transactionid=generateTransactionId(username);
					}
					else
						break;
				}
				
				amountinaccount=c.getTotalamount();
				temp=amountinaccount+depositamount;
				c.setTotalamount(temp);	
				
				//add deposit transaction into list
				transactionslist.add(new BankTransaction(transactionid, username, depositamount, 0, temp));
				transactionidlist.add(transactionid);
		
				return temp;
			}
		}
		
		return 0;
	}
	
	//transfer amount
	@Override
	public double transferAmount(String loginusername, String recieverusername, double tranferamount)

	{
	double a=0,temp1=0,b=0;
		for(Customer c:customerslist)
		{
			if(c.getUsername().equals(recieverusername))
			{
				if((a=withdrawAmount(loginusername, tranferamount))!=0)
				{
				if((b=depositAmount(recieverusername, tranferamount))!=0)
					{
					temp1=3;
					return temp1;
					};
				}
			}
		}
		return 0;	
	}
	
	//check balance
	@Override
	public double checkBalance(String username)
	{
		double temp=0;
		for(Customer c:customerslist)
		{
			if(c.getUsername().equals(username))
			{
				temp=c.getTotalamount();
				return temp;
			}
		}
		return 0;
	}
	
	//showTransactions method returns a list which contains all transactions of a particular user;
	@Override
	public List<BankTransaction> showTransactions(String loginusername)
	{
		List<BankTransaction> usertransactions=new ArrayList<BankTransaction>();
		for(BankTransaction b4:transactionslist)
		{
			if(b4.getUsername().equals(loginusername))
			{
				usertransactions.add(b4);	
			}
		}
		return usertransactions;	
	}

}
