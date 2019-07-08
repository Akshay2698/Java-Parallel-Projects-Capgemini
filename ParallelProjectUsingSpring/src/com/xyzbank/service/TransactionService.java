package com.xyzbank.service;
/**
 * @author Akshay Kumar Modi
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyzbank.bean.BankTransaction;
import com.xyzbank.dao.TransactionDaoInterface;

@Service
public class TransactionService implements TransactionServiceInterface {

	@Autowired
	TransactionDaoInterface tDao;
	
	@Override
	public double checkBalance(String username) {
		return tDao.checkBalance(username);
	}
	
	public double depositAmount(String username, double dAmount)
	{
		if(dAmount<=0)
		{
			return -1;
		} 
		else {
		return tDao.depositAmount(username, dAmount);
		}
	}
	
	public double withdrawAmount(String username, double wAmount)
	{
		return tDao.withdrawAmount(username, wAmount);
	}
	
	public String fundTransfer(String fromUser , String toUser, double transferAmount)
	{
		return tDao.fundTransfer(fromUser, toUser, transferAmount);
	}
	
	public List<BankTransaction> showTransactions(String username)
	{
		return tDao.showTransactions(username);
	}
}
