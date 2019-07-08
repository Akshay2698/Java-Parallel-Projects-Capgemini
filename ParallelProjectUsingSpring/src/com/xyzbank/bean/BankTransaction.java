package com.xyzbank.bean;
/**
 * @author Akshay Kumar Modi
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TransactionTable5")
public class BankTransaction {
	@Id 
	public String transactionid;
	public String username;
	public double credit;
	public double debit;
	public double totalamount;
	
	public BankTransaction() {
		super();
		
	}

	public BankTransaction(String transactionid, String username, double credit, double debit, double totalamount) {
		super();
		this.transactionid = transactionid;
		this.username = username;
		this.credit = credit;
		this.debit = debit;
		this.totalamount = totalamount;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	
	
}
