package com.xyzbank.dao;
/**
 * @author Akshay Kumar Modi
 */
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xyzbank.bean.BankTransaction;
import com.xyzbank.bean.Customer;

@Repository
@Transactional
public class TransactionDao implements TransactionDaoInterface{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public double checkBalance(String username)
	{
		
		Customer b1=em.find(Customer.class, username);
		if(b1==null)
		{
			return -1;
		}
		else {
		double d=b1.getTotalamount();
	System.out.println(d);
		return d;
		}
	}
	
	public static String generateTransactionId(String username)
	{
		int randomNumber=0;
		randomNumber = (int)((Math.random()*900000)+100000);
		String cust_Id=username+randomNumber;
		return cust_Id;
	}
	
	public String addTransaction(BankTransaction t)
	{
		em.persist(t);
		return "transaction added successfully";
		
	}
	
	public double depositAmount(String username, double dAmount)
	{
		try {
			String tid=generateTransactionId(username);
		double total=0,temp=0;
		Customer t=em.find(Customer.class, username);
		
		total=t.getTotalamount();
		
		temp=total+dAmount;
		addTransaction(new BankTransaction(tid,username, dAmount, 0, temp));
		
		
		t.setTotalamount(temp);
		
		return temp;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public double withdrawAmount(String username, double wAmount)
	{
		try {
			double total=0,temp=0;
			Customer t=em.find(Customer.class, username);
			total=t.getTotalamount();
			if(wAmount>total)
			{
				return -1;
			}
			else {
				String tid=generateTransactionId(username);
				temp=total-wAmount;
				addTransaction(new BankTransaction(tid, username, 0, wAmount, temp));
				
				t.setTotalamount(temp);
				return temp;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public String fundTransfer(String fromUser , String toUser, double transferAmount)
	{
		double a=withdrawAmount(fromUser, transferAmount);
		if(a>0)
		{
			double b=depositAmount(toUser, transferAmount);
			if(b>0)
			{
				return "transfered";
			}
			else {
				return "nouser";
			}
		}
		else {
			return "failed";
		}
	}
	public List<BankTransaction> showTransactions(String username)
	{
		TypedQuery<BankTransaction> q=em.createQuery("from BankTransaction t where username=:n", BankTransaction.class);
		q.setParameter("n", username);
		List<BankTransaction> li=q.getResultList();
		return li;
	}
}
