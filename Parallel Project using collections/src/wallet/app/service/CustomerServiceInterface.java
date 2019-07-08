package wallet.app.service;
/**
 * @author Akshay Kumar Modi
 */
import wallet.app.bean.Customer;

public interface CustomerServiceInterface {
	public String addCustomer(Customer c);
	public boolean checkLoginDetails(String username, String password);
	

}
