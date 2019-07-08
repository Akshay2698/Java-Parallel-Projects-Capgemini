package wallet.app.main;
/**
 * @author Akshay Kumar Modi
 */
import java.util.List;
import java.util.Scanner;

import wallet.app.bean.BankTransaction;
import wallet.app.bean.Customer;
import wallet.app.service.BankTransactionService;
import wallet.app.service.CustomerService;

public class CustomerMain {
	
	static BankTransactionService serviceTransactionObj=new BankTransactionService();
	static CustomerService serviceCustomerObj = new CustomerService();

	public static void main(String[] args) {
		
		Scanner input=new Scanner(System.in);
		
		//boolean flag1=true;;
		
		while(true)
		{
			int choiceformainmenu=0;
			System.out.println("enter 1 for register");
			System.out.println("enter 2 for Log in to your wallet");
			System.out.println("enter 3 for Exit from Application");
			
			choiceformainmenu = input.nextInt();
		
			switch(choiceformainmenu)
			{
			case 1: //switch case for registering new customer
				System.out.println("enter name ");
				String name=input.next();
				System.out.println("\nenter Username (Username should be greater than 4 characters)");
				String username=input.next();
				System.out.println("\nenter Password (Password should be greater than 4 characters)");
				String password=input.next();
				System.out.println("\nenter initial deposit:");
				double amount=input.nextDouble();
			
				
				String retofaddcustomer=serviceCustomerObj.addCustomer(new Customer(name, username, password, amount));
				if(retofaddcustomer.equals("username<5"))
				{
					System.out.println("Registration failed..\nusername should be greater than 4 characters\nPlease register again");
				}
				else
					if(retofaddcustomer.equals("password<5"))
					{
						System.out.println("Registration failed...\npassword should be greater than 4 characters\nplease register again");
					}
					else
						if(retofaddcustomer.equals("already exists"))
						{
							System.out.println("Registration failed..\nUsername already exists.. please register with different username");
						}
						else
							if(retofaddcustomer.equals("failed.."))
							{
								System.out.println();
							}
							else
								if(retofaddcustomer.equals("success"))
								{
									serviceTransactionObj.addTransaction(username, amount, 0, amount);
						
									System.out.println(username+" successfully registered");
								}
					
				break;
				
			case 2://switch case for login
				System.out.println("enter username");
				String loginusername=input.next();
				System.out.println("enter password");
				String loginpassword=input.next();
				boolean checkuser=serviceCustomerObj.checkLoginDetails(loginusername, loginpassword);
				if(checkuser)
				{
					System.out.println("\nwelcome "+loginusername);
					boolean flag=true;
					//operations after log in 
					while(flag)
					{
						System.out.println("enter 1 for withdraw money (debit)");
						System.out.println("enter 2 for deposit money (credit)");
						System.out.println("enter 3 for fund transfer to another account");
						System.out.println("enter 4 for check balance");
						System.out.println("enter 5 for transaction history");
						System.out.println("enter 6 for log out");
						
						@SuppressWarnings("resource")
						Scanner input2=new Scanner(System.in);
						int choiceforusermenu=input2.nextInt();
						
						switch(choiceforusermenu)
						{
						case 1://option for withdraw amount
							System.out.println("enter amount to be with drawn");
							double withdrawamount=input2.nextDouble();
							//generates a unique transaction id for withdraw
							
							double retofwithdraw=serviceTransactionObj.withdrawAmount(loginusername, withdrawamount) ;
							
							if(retofwithdraw==0)
							{
								System.out.println("you have insufficient balance");
							}
							else
								if(retofwithdraw==1)
								{
									System.out.println();
								}
								else
								{
									System.out.println("amount Rs."+withdrawamount+"  is withdrawn");
								}	
						break;
						
						case 2://for depositing money
							System.out.println("enter amount to be deposited");
							double depositamount=input2.nextDouble();
							
							double retofdeposit=serviceTransactionObj.depositAmount(loginusername, depositamount);
							
							if(retofdeposit==0)
							{
								System.out.println("please enter amount greater than 0");
							}
							else
								if(retofdeposit==1)
								{
									System.out.println();
								}
								else
								{
									System.out.println("amount Rs."+depositamount+" is credited");
									
								}
							
							break;
						
						case 3://fund transfer
							System.out.println("enter username of reciever accoount");
							String recieverusername=input.next();
							System.out.println("enter amount to be transfered");
							double transferamount=input.nextDouble();
							
							
							double retoftransferamount=serviceTransactionObj.transferAmount(loginusername,recieverusername, transferamount	);
							if(retoftransferamount==0)
							{
								System.out.println("Fund Transfer failed. please try again with correct credentials.");
							}
							else
								if(retoftransferamount==1)
								{
									System.out.println();
								}
							else
							if(retoftransferamount==3){
								System.out.println(transferamount+" amount tranfered to "+recieverusername);
							}
							
							break;
						
						case 4://check balance
							double retofbalance=serviceTransactionObj.checkBalance(loginusername);
							
							if(retofbalance==0)
							{
								System.out.println("please enter username");
							}
							else
								System.out.println("your current balance is: "+retofbalance);
							break;
							
						case 5://transaction history
							List<BankTransaction> retofshowtransactions=serviceTransactionObj.showTransactions(loginusername);
							
							if(retofshowtransactions == null)
							{
								System.out.println("username cannot be null");
							}
							else
							{
								int serialno=1;
								System.out.println("SerialNo TransactionId\tUsername\tCredit\tDebit\tTotal Balance ");
								for(BankTransaction b:retofshowtransactions)
								{
									
									System.out.println(serialno+"\t "+b.getTransactionid()+"\t"+b.getUsername()+"\t\t"+b.getCredit()+"\t"+b.getDebit()+"\t"+b.getTotalamount());
									serialno++;
								}
							}
							
							break;
							
						case 6://logout
							//input2.close();
							System.out.println("Thanks for using our Bank");
							flag=false;
							break;
						
							default :
								System.out.println("wrong choice");
						}//end of second switch after user logged in
					
					}//end of while. in case 2 where user is logged in.
		
				}//end of if , where user is validated for authentication.
				//else for user login credentials, if user is not valid user.
				else {
					System.out.println("please enter valid details");					
				}
				
				break;
				
			case 3:
				input.close();
				System.out.println("Exited from Application \n Thank You for using our Bank.");
				System.exit(0);
				break;
				
			default :
				System.out.println("wrong choice");
				
				}//end of switch for main menu
			
			
			
		}//end of while
		
	}//end of main

}//end of class
