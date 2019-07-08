package wallet.app.dao;
/**
 * @author Akshay Kumar Modi
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wallet.app.bean.BankTransaction;
import wallet.app.bean.Customer;
import wallet.app.service.BankTransactionService;

public class CustomerDao implements CustomerDaoInterface {
	public static List<Customer> customerslist=new ArrayList<Customer>();

	@Override
	public String addCustomer(Customer c)
	{
		
		String str=c.getUsername();
		for(Customer c1:customerslist)
		{
			if(c1.getUsername().equals(str))
			{
				return "already exists";	
			}
		}
		
		customerslist.add(c);
		
		return "success";	
	}
	
	@Override
	public boolean checkLoginDetails(String username, String password)
	{
		
		for(Customer c:customerslist)
		{
			if(c.getUsername().equals(username))
			{
				if(c.getPassword().equals(password))
				{
					return true;
				}
			}
		}
		return false;	
	}
	
}
