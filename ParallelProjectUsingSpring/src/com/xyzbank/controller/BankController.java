
package com.xyzbank.controller;
/**
 * @author Akshay Kumar Modi
 */
import java.util.List;

import javax.validation.Valid;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xyzbank.bean.BankTransaction;
import com.xyzbank.bean.Customer;
import com.xyzbank.service.CustomerServiceInterface;
import com.xyzbank.service.TransactionServiceInterface;


@Controller
public class BankController {

	@Autowired
	CustomerServiceInterface cSer;
	
	@Autowired
	TransactionServiceInterface tSer;
	
	
	@RequestMapping("/")
	public String home()
	{
		return "login";
	}
	
	@RequestMapping("/home")
	public String home2()
	{
		return "login";
	}
	
	@RequestMapping("/signup")
	public String signup(Model m)
	{
		m.addAttribute("customer", new Customer());
		return "signup";
	}
	
	@RequestMapping("/signupaction")
	public String signUpAction(@Valid Customer customer,BindingResult result,Model m)
	{
		if(result.hasErrors())
		{
			return "signup";
		}
		else {
		String ret=cSer.addCustomer(customer);
		m.addAttribute("msg", ret);
		return "success";
		}
	}
	
	@RequestMapping("/loginaction")
	public String loginAction(@RequestParam("t1")String username, @RequestParam("t2")String password,Model m, HttpSession ses)
	{
		boolean verifyUser=cSer.validateCustomer(username, password);
		if(verifyUser==true)
		{
			ses.setAttribute("currUser", username);
			m.addAttribute("name", username);
			return "home";
		}
		else {
			m.addAttribute("invalidUser", "Invalid Credentials..");
			return "login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession ses)
	{
		ses.invalidate();
		return "login";
	}
	
	@RequestMapping("/checkbalance")
	public String checkBalance(HttpSession ses,Model m)
	{
		String username=ses.getAttribute("currUser").toString();
		System.out.println(username instanceof String);
		double bal=tSer.checkBalance(username);
		if(bal==-1)
		{
			m.addAttribute("fail", "failed to found");
			return "showbalance";
		}
		else {
		m.addAttribute("balance", bal);
		return "showbalance";
		}
	}
	
	@RequestMapping("/depositmoney")
	public String deposit()
	{
		return "deposit";
	}
	
	@RequestMapping("/depositaction")
	public String depositAction(@RequestParam("t1")double dAmount,HttpSession ses,Model m)
	{
		String username=ses.getAttribute("currUser").toString();
		double retDep=tSer.depositAmount(username, dAmount);
		if(retDep<0)
		{
			m.addAttribute("msg", "Invalid amount..please enter amount greater than 0");
			return "deposit";
		}
		else {
		m.addAttribute("dep", dAmount);
		m.addAttribute("total", retDep);
		return "depositsuccess";
		}
	}
	
	@RequestMapping("/withdrawmoney")
	public String withdraw()
	{
		return "withdraw";
	}
	
	@RequestMapping("/withdrawaction")
	public String withdrawAction(@RequestParam("t1")double wAmount,HttpSession ses,Model m)
	{
		String username=ses.getAttribute("currUser").toString();
		double retWith=tSer.withdrawAmount(username, wAmount);
		if(retWith<0)
		{
			m.addAttribute("msg", "Insufficient Balance.. please check your balance and try again..");
			return "withdraw";
		}
		else {
		m.addAttribute("with", wAmount);
		m.addAttribute("total", retWith);
		return "withdrawsuccess";
		}
	}
	
	@RequestMapping("/fundtransfer")
	public String fundTransfer()
	{
		return "fundtransfer";
	}
	@RequestMapping("/transferaction")
	public String transferAction(@RequestParam("t1")String toUser,@RequestParam("t2")double transferAmount, HttpSession ses,Model m)
	{
		String username=ses.getAttribute("currUser").toString();
		String retTransfer=tSer.fundTransfer(username, toUser, transferAmount);
		if(retTransfer.equals("failed"))
		{
			m.addAttribute("msg", "Insufficeint Balance...");
			return "fundtransfer";
		}
		else
			if(retTransfer.equals("nouser"))
			{
				m.addAttribute("msg2", "Reciver Username is not found.. please check and enter again");
				return "fundtransfer";
			}
		else {
		m.addAttribute("amount", transferAmount);
		m.addAttribute("to", toUser);
		m.addAttribute("ret",retTransfer);
		return "transfersuccess";
		}
	}
	
	@RequestMapping("/showtransactions")
	public String show(Model m,HttpSession ses)
	{
		String username=ses.getAttribute("currUser").toString();
		List<BankTransaction> li=tSer.showTransactions(username);
		m.addAttribute("trs", li);
		return "transactions";
	}
}
