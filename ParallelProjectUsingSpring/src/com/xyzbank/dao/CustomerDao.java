package com.xyzbank.dao;
/**
 * @author Akshay Kumar Modi
 */
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xyzbank.bean.Customer;

@Repository
@Transactional
public class CustomerDao implements CustomerDaoInterface {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public String addCustomer(Customer c)
	{
		em.persist(c);
		return "customer registered successfully";
	}
	
	@Override
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
