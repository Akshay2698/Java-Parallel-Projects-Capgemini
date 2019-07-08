
package com.wallet.app.util;
/**
 * @author Akshay Kumar Modi
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyEntityFactory {
	
	private static EntityManagerFactory emf;
	
	static {
		emf=Persistence.createEntityManagerFactory("dbconn");
	}
	public static EntityManager getEntityManager()
	{
		return emf.createEntityManager();
	}
}
