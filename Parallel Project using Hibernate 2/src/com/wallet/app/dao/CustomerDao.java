package com.wallet.app.dao;
/**
 * @author Akshay Kumar Modi
 */
import javax.persistence.EntityManager;

import com.wallet.app.bean.Customer;
import com.wallet.app.util.MyEntityFactory;

public class CustomerDao {
	
	public EntityManager em;
	
	public CustomerDao()
	{
		em=MyEntityFactory.getEntityManager();
	}
	
	public void startTransaction()
	{
		em.getTransaction().begin();
	}
	
	public void commitTransaction()
	{
		em.getTransaction().commit();
	}
	
	public String addCustomer(Customer c)
	{
		em.persist(c);
		return "customer added successfully";
	}
	
	public boolean validateCustomer(String username, String password)
	{
		Customer c1=em.find(Customer.class, username);
		if(c1!=null)
		{
			if(c1.getPassword().equals(password))
			{
				return true;
			}
		}
		else {
			return false;
		}
		return false;
	}

}
