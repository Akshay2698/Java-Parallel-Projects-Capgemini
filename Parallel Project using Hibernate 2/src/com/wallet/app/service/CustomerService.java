package com.wallet.app.service;
/**
 * @author Akshay Kumar Modi
 */
import com.wallet.app.bean.Customer;
import com.wallet.app.dao.CustomerDao;

public class CustomerService {
	
	CustomerDao cDao=new CustomerDao();
	
	public String addCustomer(Customer c)
	{
		cDao.startTransaction();
		cDao.addCustomer(c);
		cDao.commitTransaction();
		return "customer addedd successfully";
	}

	public boolean validateCustomer(String username, String password)
	{
		cDao.startTransaction();
		boolean b=cDao.validateCustomer(username, password);
		cDao.commitTransaction();
		return b;
	}
}
