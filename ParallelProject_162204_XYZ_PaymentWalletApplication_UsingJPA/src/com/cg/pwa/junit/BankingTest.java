package com.cg.pwa.junit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.Test;
import com.cg.pwa.bean.AccType;
import com.cg.pwa.bean.AccountHolder;
import com.cg.pwa.dao.AccountHolderDao;
import com.cg.pwa.dao.AccountHolderDaoImpl;
import com.cg.pwa.exception.BankingException;

public class BankingTest {

/*	@Test
	public void testAccountHolderDaoImpl() {
		Assert.assertEquals(expected, actual);
	}*/
	String accNo = null;
	EntityManagerFactory fac = Persistence.createEntityManagerFactory("JPA-PU");
	EntityManager em = fac.createEntityManager();
	AccountHolderDao bankDao = new AccountHolderDaoImpl();
	@Test
	public void testCreateAccount() {
		em.getTransaction().begin();
		AccountHolder accHolder = new AccountHolder
			("Shiv", "Pala", "shivpala", (short)1111, 
				LocalDate.parse("10-10-1999", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
				LocalDate.now(), 1000.0, AccType.Saving);
		Assert.assertNotNull(accNo =  bankDao.createAccount(accHolder));	
		em.getTransaction().commit();
	}

	@Test
	public void testShowBalance() {
		em.getTransaction().begin();
		try {
			Assert.assertNotNull( bankDao.showBalance("shivpala", (short)1111));
			em.getTransaction().commit();
		} catch (BankingException e) {
			em.getTransaction().commit();
			e.printStackTrace();
		}
	}
	

	@Test
	public void testDeposit() {
		em.getTransaction().begin();
		try {
			Assert.assertNotNull( bankDao.deposit("shivpala", (short)1111, 2000));
			em.getTransaction().commit();
		} catch (BankingException e) {
			em.getTransaction().commit();
			e.printStackTrace();
		}
	}

	@Test
	public void testWithdraw() {
		em.getTransaction().begin();
		try {
			Assert.assertNotNull( bankDao.withdraw("shivpala", (short)1111, 1000));
			em.getTransaction().commit();
		} catch (BankingException e) {
			em.getTransaction().commit();
			e.printStackTrace();
		}
	}


	@Test
	public void testFetchAllAccounts() {
		em.getTransaction().begin();
		Assert.assertNotNull( bankDao.fetchAllAccounts());
		em.getTransaction().commit();
	}

}
