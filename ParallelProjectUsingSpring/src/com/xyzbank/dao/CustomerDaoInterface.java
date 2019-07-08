package com.xyzbank.dao;
/**
 * @author Akshay Kumar Modi
 */
import com.xyzbank.bean.Customer;

public interface CustomerDaoInterface {
	public String addCustomer(Customer c);
	public boolean validateCustomer(String username, String password);

}
