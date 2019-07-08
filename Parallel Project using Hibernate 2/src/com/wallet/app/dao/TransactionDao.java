package com.wallet.app.dao;

/**
 * @author Akshay Kumar Modi
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;

import com.wallet.app.bean.BankTransaction;
import com.wallet.app.bean.Customer;
import com.wallet.app.util.MyEntityFactory;

public class TransactionDao {
	
	public EntityManager em;
	
	public TransactionDao()
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
	public String addTransaction(BankTransaction t)
	{
		em.persist(t);
		return "transaction added successfully";
		
	}
	
	public static String generateTransactionId(String username)
	{
		int randomNumber=0;
		randomNumber = (int)((Math.random()*900000)+100000);
		String cust_Id=username+randomNumber;
		return cust_Id;
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
				addTransaction(new BankTransaction(tid,username,0,wAmount,temp) );
				t.setTotalamount(temp);
				//addTransaction(new Transaction(tid,username,0,wAmount,temp));
				return temp;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
	}
	public double depositAmount(String username, double dAmount)
	{
		try {
			String tid=generateTransactionId(username);
		double total=0,temp=0;
		Customer t=em.find(Customer.class, username);
		
		total=t.getTotalamount();
		
		temp=total+dAmount;
		addTransaction(new BankTransaction(tid,username,dAmount,0,temp) );
		t.setTotalamount(temp);
		return temp;
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

		return "fund transfered";
		}
		else {
			return "transfer failed";
		}
	}
	
	public double checkBalance(String username)
	{
		try {
		Customer b1=em.find(Customer.class, username);
		double d=b1.getTotalamount();
	
		return d;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return 0;
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
