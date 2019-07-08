package com.xyzbank.bean;
/**
 * @author Akshay Kumar Modi
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="CustomerTable5")
public class Customer {
	@NotEmpty(message="name is required")
	private String name;
	@NotEmpty(message="username is required")
	@Id
	private String username;
	@NotEmpty(message="password is required")
	private String password;
	private double totalamount;
	public Customer() {
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
	

}
