package wallet.app.service;
/**
 * @author Akshay Kumar Modi
 */
import java.util.List;

import wallet.app.bean.BankTransaction;

public interface BankTransactionServiceInterface {
	public String addTransaction(String username, double credit,double debit,double totalamount);
	public double withdrawAmount(String username, double withdrawamount);
	public double depositAmount(String username, double depositamount);
	public double transferAmount(String loginusername, String recieverusername, double transferamount);
	public double checkBalance(String username);
	public List<BankTransaction> showTransactions(String loginusername);
	
}
