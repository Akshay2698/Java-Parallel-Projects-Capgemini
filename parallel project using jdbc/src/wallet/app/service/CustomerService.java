package wallet.app.service;
/**
 * @author Akshay Kumar Modi
 */
import wallet.app.bean.Customer;
import wallet.app.dao.CustomerDao;

public class CustomerService implements CustomerServiceInterface {
	
	static CustomerDao daoCustomerObj=new CustomerDao();
	
	@Override
	public String addCustomer(Customer c)
	{
		if(c.getUsername().length()<5)
			{
				return "username<5";
			}
			else
				if(c.getPassword().length()<5)
				{
					return "password<5";
				}
				else
				{
					return daoCustomerObj.addCustomer(c);
				}
	}
	
	@Override
	public boolean checkLoginDetails(String username, String password)
	{
		
			return daoCustomerObj.checkLoginDetails(username, password);
	}
}
