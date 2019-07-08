package com.wallet.app.service;

/**
 * @author Akshay Kumar Modi
 */

import java.util.List;

import com.wallet.app.bean.BankTransaction;
import com.wallet.app.dao.TransactionDao;

public class TransactionService {
	
	TransactionDao tDao=new TransactionDao();
	
	public static String generateTransactionId(String username)
	{
		int randomNumber=0;
		randomNumber = (int)((Math.random()*900000)+100000);
		String cust_Id=username+randomNumber;
		return cust_Id;
	}
	
	public String addTransaction(String username, double credit, double debit, double total)
	{
		String tid=generateTransactionId(username);
		tDao.startTransaction();
		String str=tDao.addTransaction(new BankTransaction(tid, username, credit, debit, total));
		tDao.commitTransaction();
		return str;
	}
	
	public double withdrawAmount(String username, double wAmount)
	{
		if(wAmount==0 || wAmount<0)
		{
			return -2;
		}
		else {
		tDao.startTransaction();
		double d=0;
		d=tDao.withdrawAmount(username, wAmount);
		
		tDao.commitTransaction();
		return d;
		}
	}
	
	public double depositAmount(String username, double dAmount)
	{
		if(dAmount==0 || dAmount<0)
		{
			return -2;
		}
		else {
		tDao.startTransaction();
		double d=0;
		d=tDao.depositAmount(username, dAmount);
		
		tDao.commitTransaction();
		return d;
		}
	}
	
	public String fundTransfer(String fromUser, String toUser, double amount)
	{
		if(amount==0 || amount<0)
		{
			return null;
		}
		else {
		tDao.startTransaction();
		String str=tDao.fundTransfer(fromUser, toUser, amount);
		tDao.commitTransaction();
		return str;
		}
	}
	
	public List<BankTransaction> showTransactions(String username)
	{
		tDao.startTransaction();
		List<BankTransaction> li=tDao.showTransactions(username);
		tDao.commitTransaction();
		return li;
	}
	
	public double checkBalnace(String username)
	{
		tDao.startTransaction();
		double d1=tDao.checkBalance(username);
		tDao.commitTransaction();
		return d1;
	}

}
