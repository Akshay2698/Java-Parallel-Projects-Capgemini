package com.xyzbank.service;
/**
 * @author Akshay Kumar Modi
 */
import com.xyzbank.bean.Customer;

public interface CustomerServiceInterface {

	public String addCustomer(Customer c);
	public boolean validateCustomer(String username, String password);
}
