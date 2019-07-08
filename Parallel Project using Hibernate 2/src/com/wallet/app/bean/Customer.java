package com.wallet.app.bean;
/**
 * @author Akshay Kumar Modi
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CustomerTable")
public class Customer {
	
	private String name;
	@Id
	private String username;
	private String password;
	private double totalamount;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String name, String username, String password, double totalamount) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.totalamount = totalamount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", username=" + username + ", password=" + password + ", totalamount="
				+ totalamount + "]";
	}

}
