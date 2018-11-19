package com.cg.pwa.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import com.cg.pwa.bean.AccountHolder;

public class AccHolderRepository {
	
	private static EntityManagerFactory factory;
	private static EntityManager em;
	Session session;
	
	static {
		factory = Persistence.createEntityManagerFactory("JPA-PU");
	}
	
	public static EntityManager getEntityManager() {
		if(em==null || !em.isOpen()) {
			em = factory.createEntityManager();
		}
		return em;
	}
	
	private static Map<String, AccountHolder> accHolders = new HashMap<String, AccountHolder>();

	public static Map<String, AccountHolder> fetchAllAccHolders()
	{
		return accHolders;
	}
	
}
