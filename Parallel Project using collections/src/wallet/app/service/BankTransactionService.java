package wallet.app.service;
/**
 * @author Akshay Kumar Modi
 */
import java.util.ConcurrentModificationException;
import java.util.List;



import wallet.app.bean.BankTransaction;
import wallet.app.dao.BankTransactionDao;

class UsernameShouldNotBeNullException extends Exception
{
	public UsernameShouldNotBeNullException(String s)
	{
		super(s);
	}
}

class InvalidAmountException extends Exception
{
	public InvalidAmountException(String s)
	{
		super(s);
	}
}


public class BankTransactionService implements BankTransactionServiceInterface {
	
	static BankTransactionDao daoTransactionObj=new BankTransactionDao();
	
	// validating add transaction
	@Override
	public String addTransaction(String username, double credit,double debit,double totalamount)
	{
		return daoTransactionObj.addTransaction(username, credit, debit, totalamount);
	}
	
	//validation method for debit amount
	@Override
	public double withdrawAmount(String username, double withdrawamount) 
	{
		try {
		if(withdrawamount==0 || withdrawamount<0 || withdrawamount==1)
		{
			throw new InvalidAmountException("ERROR: please enter amount greater than 0");
		}
		else
		{
			return daoTransactionObj.withdrawAmount(username, withdrawamount);
		}
		}
		catch (InvalidAmountException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return 1;
		}
	}
	
	//validation method for credit amount
	@Override
	public double depositAmount(String username, double depositamount) 
	{
		try {
		if(depositamount==0 || depositamount<0)
		{
			throw new InvalidAmountException("ERROR: please enter amount greater than 0");
		}
		else
		{
			return daoTransactionObj.depositAmount(username, depositamount);	
		}
		}
		catch(InvalidAmountException e)
		{
			System.out.println(e.getMessage());
			return 1;
		}
		
	}
	
	//validation for transfer amount
	@Override
	public double transferAmount(String loginusername, String recieverusername, double transferamount) 
	{
	try {	
		if(recieverusername == null)
		{
			throw new UsernameShouldNotBeNullException("ERROR: username cannot be null");
		}
		else {
			if( transferamount==0 || transferamount<0)
			{
				throw new InvalidAmountException("ERROR: transfer amount cannot be 0 or less than 0");
			}
			else {
				return daoTransactionObj.transferAmount(loginusername, recieverusername, transferamount);
			}
		}
	}///try end
	catch(UsernameShouldNotBeNullException e)
	{
		System.out.println(e.getMessage());
		return 1;
	}
	catch(InvalidAmountException e)
	{
		System.out.println(e.getMessage());
		return 2;
	}
		
	}
	
	//validation for check balance
	@Override
	public double checkBalance(String username)
	{
		try {
		if(username==null)
		{
			throw new UsernameShouldNotBeNullException("ERROR: username cannot be null");
		}
		else {
			return daoTransactionObj.checkBalance(username);
		}
		}
		catch(UsernameShouldNotBeNullException e)
		{
			System.out.println(e.getMessage());
			return 1;
		}
	}
	
	//showTransactions method for validating user name and calling dao class
	@Override
	public List<BankTransaction> showTransactions(String loginusername)
	{
		
			if(loginusername == null)
			{
				return null;
			}
			else {
				return daoTransactionObj.showTransactions(loginusername);
				}
			
	}

}
