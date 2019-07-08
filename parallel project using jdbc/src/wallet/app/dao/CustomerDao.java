package wallet.app.dao;
/**
 * @author Akshay Kumar Modi
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wallet.app.bean.Customer;
import wallet.app.util.MyConnection;

public class CustomerDao implements CustomerDaoInterface {
	
	Connection conn=MyConnection.getConnection();
	
	@Override
	public String addCustomer(Customer c)
	{
		try {
			//checking whether a username already exists or not?
			PreparedStatement ps1=conn.prepareStatement("select username from customerslist where username=?");
			ps1.setString(1, c.getUsername());
			ResultSet rs1=ps1.executeQuery();
			
			if(rs1.next())
			{
				return "already exists";
			}
			
			else {
				//inserting into table
				PreparedStatement ps2=conn.prepareStatement("insert into customerslist values(?,?,?,?)");
				ps2.setString(1, c.getName());
				ps2.setString(2, c.getUsername());
				ps2.setString(3, c.getPassword());
				ps2.setDouble(4, c.getTotalamount());
				int retofinsert=ps2.executeUpdate();
				if(retofinsert>0)
				{
					return "success";
				}
				else {
					return "failed";
				}
				}//end of else
			
		}//end of try
		
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			
			return "failed..";
		}//end of catch
	}//end of addCustomer()
	
	@Override
	public boolean checkLoginDetails(String username, String password)
	{
		
		try {
			//checking whether a user exists or not?
			PreparedStatement ps3=conn.prepareStatement("select username,password from customerslist where username=?");
			ps3.setString(1,username);
			ResultSet rs1=ps3.executeQuery();
			rs1.next();
			String usernamefromtable=rs1.getString(1);
			String passwordfromtable=rs1.getString(2);
			if(usernamefromtable.equals(username))
			{
				if(passwordfromtable.equals(password))
				{
				return true;
				}
			}
		}//end of try
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}//end of catch
		return false;	
	}// end of checklogindetails() method
	
}//end of addCustomer class
