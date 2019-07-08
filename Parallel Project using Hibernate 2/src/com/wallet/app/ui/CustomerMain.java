package com.wallet.app.ui;

/**
 * @author Akshay Kumar Modi
 */

import java.util.List;
import java.util.Scanner;

import com.wallet.app.bean.BankTransaction;
import com.wallet.app.bean.Customer;
import com.wallet.app.service.CustomerService;
import com.wallet.app.service.TransactionService;

public class CustomerMain {
	
	static CustomerService cService=new CustomerService();
	
	static TransactionService cService2=new TransactionService();
	public static void main(String args[])
	{
		Scanner input=new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Register");
			System.out.println("2.Login");
			int choice=0;
			choice=input.nextInt();
			
			switch(choice)
			{
			case 1:
				System.out.println("enter customer name");
				String name=input.next();
				System.out.println("enter username");
				String username=input.next();
				System.out.println("enter password");
				String password=input.next();
				System.out.println("enter intial deposit");
				double amount=input.nextDouble();
				
				System.out.println(cService.addCustomer(new Customer(name,username,password,amount)));
				System.out.println(cService2.addTransaction(username,amount,0,amount));
				break;
			
			case 2:
				System.out.println("enter username");
				String lUsername=input.next();
				System.out.println("enter password");
				String lPassword=input.next();
				
				boolean b=cService.validateCustomer(lUsername, lPassword);
				if(b==true)
				{
					System.out.println("welcome "+lUsername);
					boolean flag=true;
					while(flag)
					{
					System.out.println("\n 1. Withdraw Money");
					System.out.println("2. Deposit Money");
					System.out.println("3. Fund Transfer");
					System.out.println("4. Check Balance");
					System.out.println("5. Transaction History");
					System.out.println("6. Logout");
					
					Scanner input2=new Scanner(System.in);
					int choice2=input2.nextInt();
					
					switch(choice2)
					{
					case 1://withdraw
						System.out.println("enter amount to be with drawn");
						double wAmount=0;
						wAmount=input2.nextDouble();
						double ret=0;
						ret=cService2.withdrawAmount(lUsername, wAmount);
						if(ret>0)
						{
						System.out.println("amount Rs."+wAmount+" debited successfully \n balance amount is: "+ret);
						}
						else 
						if(ret==-1){
							System.out.println("ERROR: insufficent balance in your account.");
						}
						else if(ret==-2)
						{
							System.out.println("ERROR: withdraw amount should begreater than 0");
						}
						break;
						
					case 2:
						System.out.println("enter amount to deposit");
						double dAmount=0;
						dAmount=input2.nextDouble();
						double ret2=0;
						ret2=cService2.depositAmount(lUsername, dAmount);
						
						if(ret2>0)
						{
						System.out.println("amount Rs."+dAmount+" credited successfully \n balance amount is: "+ret2);
						}
						else
							if(ret2==-2)
							{
								System.out.println("ERROR: deposit amount should be greater than 0");
							}
						break;
						
					case 3:
						System.out.println("enter reciever username");
						String toUser=input2.next();
						System.out.println("enter amount to be transfered");
						double transferAmount=input2.nextDouble();
						String str1=cService2.fundTransfer(lUsername, toUser, transferAmount);
						if(str1==null)
						{
							System.out.println("transfer amount should be greater than 0");
						}
						else {

							System.out.println(str1);
						}
						break;
						
					case 4:
						double bal=0;
						bal=cService2.checkBalnace(lUsername);
						System.out.println("your balance is: "+bal);
						break;
					case 5:
						int serialno=1;
						List<BankTransaction> li=cService2.showTransactions(lUsername);
						System.out.println("serialNo TransactionId\tCredit\tDebit\tTotalAmount");
						for(BankTransaction b2:li)
						{
							System.out.println(serialno+"\t"+b2.getTransactionid()+"\t"+b2.getCredit()+"\t"+b2.getDebit()+"\t"+b2.getTotalamount());
							serialno++;
						}
						break;
					case 6:
						System.out.println("Thanks for visiting");
						flag=false;
						break;
						
					default:
						System.out.println("wrong choice");
					}//end of switch(choice2) i.e. switch after login
					}//end of second while
				}//if upto after login
				else {
				System.out.println("invalid credentials");
				}
				break;
			default:
				System.out.println("wrong choice");
			
			}//end of switch(choice)
		}//end of first while
	}

}//end of customer main
