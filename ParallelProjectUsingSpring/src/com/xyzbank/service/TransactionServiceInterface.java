package com.xyzbank.service;
/**
 * @author Akshay Kumar Modi
 */
import java.util.List;

import com.xyzbank.bean.BankTransaction;

public interface TransactionServiceInterface {
	
	public double checkBalance(String username);
	public double depositAmount(String username, double dAmount);
	public double withdrawAmount(String username, double wAmount);
	public String fundTransfer(String fromUser , String toUser, double transferAmount);
	public List<BankTransaction> showTransactions(String username);
}
