package com.xyzbank.service;
/**
 * @author Akshay Kumar Modi
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xyzbank.bean.Customer;
import com.xyzbank.dao.CustomerDao;
import com.xyzbank.dao.CustomerDaoInterface;
@Service
public class CustomerService implements CustomerServiceInterface {
	
	@Autowired
	CustomerDaoInterface cDao;
	
	public String addCustomer(Customer c)
	{
		
		return cDao.addCustomer(c);
	}

	public boolean validateCustomer(String username, String password)
	{
		
		boolean b=cDao.validateCustomer(username, password);
		return b;
	}
}
