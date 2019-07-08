package wallet.app.dao;
/**
 * @author Akshay Kumar Modi
 */
import wallet.app.bean.Customer;

public interface CustomerDaoInterface {
	
	public String addCustomer(Customer c);
	public boolean checkLoginDetails(String username, String password);
	
	

}
