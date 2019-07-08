package wallet.app.util;
/**
 * @author Akshay Kumar Modi
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	
	private static Connection conn;
	
	static {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","akshay","1234");
			
			System.out.println("Connected..");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		return conn;
	}
	

}
