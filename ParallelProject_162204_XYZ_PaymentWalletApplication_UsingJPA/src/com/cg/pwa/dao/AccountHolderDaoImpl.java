package com.cg.pwa.dao;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cg.pwa.bean.AccountHolder;
import com.cg.pwa.bean.TransType;
import com.cg.pwa.bean.Transaction;
import com.cg.pwa.exception.BankingException;
import com.cg.pwa.util.AccHolderRepository;

public class AccountHolderDaoImpl implements AccountHolderDao{	
	AccountHolder accHolder = null;
	Map<String, AccountHolder> accounts = null;
			
	EntityManager em;
	
	public AccountHolderDaoImpl() {
		super();
		em = AccHolderRepository.getEntityManager();
	}

	@Override
	public void beginTransaction() {
		em.getTransaction().begin();
	}

	@Override
	public void commitTransaction() {
		em.getTransaction().commit();
	}
	
	
	@Override
	public String createAccount(AccountHolder accHolder) {
		
		Transaction transaction = new Transaction
				(String.valueOf((int)(Math.random()*2147483647)),
					LocalDateTime.now(), new BigInteger(accHolder.getAccNo()),
					TransType.Credit, accHolder.getBalance(), accHolder.getBalance());
		accHolder.setTransactions(transaction);
		em.persist(accHolder);
		return accHolder.getAccNo();
		
	}
	

	@Override
	public double showBalance(String userName, short pin) throws BankingException{
		
		try{
		AccountHolder account = em.find(AccountHolder.class, userName);
		if(account.getPin() == pin)
		{
			return account.getBalance();
		}
		else
		{
			commitTransaction();
			throw new BankingException("Incorrect PIN!");
		}
		}catch(NullPointerException e) {
			commitTransaction();
			throw new BankingException("Account not found!");
		}
		
		
		/*accounts = AccHolderRepository.fetchAllAccHolders();
		Iterator<AccountHolder> it = accounts.values().iterator();
		while(it.hasNext())
		{
			accHolder = it.next();
			if(accHolder.getUserName().equals(userName)
					&& accHolder.getPin() == pin)
			{
				return accHolder.getBalance();
			}
		}
		throw new BankingException("Account Not Found!");*/
	}
	
	

	@Override
	public double deposit(String userName, short pin, double amount) throws BankingException {
		
		try{
			AccountHolder account = em.find(AccountHolder.class, userName);
			if(account.getPin() == pin)
			{
				account.setBalance(account.getBalance() + amount);
				Transaction transaction = new Transaction
					(String.valueOf((int)(Math.random()*2147483647)),
						LocalDateTime.now(), new BigInteger(account.getAccNo()),
						TransType.Credit, amount, account.getBalance());
				account.setTransactions(transaction);
				return account.getBalance();
			}
			else
			{
				commitTransaction();
				throw new BankingException("Incorrect PIN!");
			}
		}catch(NullPointerException e) {
			commitTransaction();
			throw new BankingException("Account not found!");
		}
		
		
		/*accounts = AccHolderRepository.fetchAllAccHolders();
		Iterator<AccountHolder> it = accounts.values().iterator();
		while(it.hasNext())
		{
			accHolder = it.next();
			if(accHolder.getUserName().equals(userName)
					&& accHolder.getPin() == pin)
			{
				accHolder.setBalance(accHolder.getBalance()+amount);
				accHolder.setTransactions
					(new Transaction(String.valueOf((((int)Math.random() * 1000000000))),
								LocalDateTime.now(), new BigInteger(accHolder.getAccNo()),
								TransType.Credit, accHolder.getBalance()));
				return accHolder.getBalance(); 
			}
		}
		throw new BankingException("Account Not Found!");*/
	}

	@Override
	public double withdraw(String userName, short pin, double amount) throws BankingException {
		
		try{
			AccountHolder account = em.find(AccountHolder.class, userName);
			if(account.getPin() == pin)
			{
				account.setBalance(account.getBalance() - amount);
				Transaction transaction = new Transaction
				(String.valueOf((int)(Math.random()*2147483647)),
					LocalDateTime.now(), new BigInteger(account.getAccNo()),
					TransType.Debit, amount, account.getBalance());
				account.setTransactions(transaction);
				return account.getBalance();
			}
			else
			{
				commitTransaction();
				throw new BankingException("Incorrect PIN!");
			}
		}catch(NullPointerException e) {
			commitTransaction();
			throw new BankingException("Account not found!");
		}
		

		
		
		/*accounts = AccHolderRepository.fetchAllAccHolders();
		Iterator<AccountHolder> it = accounts.values().iterator();
		while(it.hasNext())
		{
			accHolder = it.next();
			if(accHolder.getUserName().equals(userName)
					&& accHolder.getPin() == pin)
			{
				double balance = accHolder.getBalance();
				if(balance >= amount){
					accHolder.setBalance(balance-amount);
					
					accHolder.setTransactions
					(new Transaction
						(new String(String.valueOf(((int)(Math.random() * 1000000000)))),
								LocalDateTime.now(), new BigInteger(accHolder.getAccNo()),
								TransType.Debit, accHolder.getBalance()));
					
					return accHolder.getBalance();
				}
				else
				{
					throw new BankingException("Insufficient Balance!");
				}
			}
		}
		throw new BankingException("Account Not Found!");*/
	}

	@Override
	public double fundTransfer(String userName, short pin, String targetAccNo,
			double amount) throws BankingException {
		
		AccountHolder source = null,
					target = null;
		
			source = em.find(AccountHolder.class, userName);
			if(source != null)
			{
				if(source.getPin() == pin)
				{
					double balance = source.getBalance();
					if(balance >= amount)
					{
						TypedQuery<AccountHolder> ac = em.createQuery
							("from AccountHolder where ACCOUNT_ACNO= :acn",
									AccountHolder.class);
						ac.setParameter("acn", targetAccNo);
						target = ac.getSingleResult();
						if(target != null)
						{
							source.setBalance(source.getBalance() - amount);
							target.setBalance(target.getBalance() + amount);
							String transId =String.valueOf((int)(Math.random()*2147483647)); 
							Transaction transaction1 = new Transaction
							(transId, LocalDateTime.now(), new BigInteger(target.getAccNo()),
								TransType.Debit, amount, source.getBalance());
								Transaction transaction2 = new Transaction
							(transId, LocalDateTime.now(), new BigInteger(source.getAccNo()),
								TransType.Credit, amount, target.getBalance());
							source.setTransactions(transaction1);
							target.setTransactions(transaction2);
							em.persist(transaction1);
							em.persist(transaction2);
							
							return source.getBalance();
						}
						else
						{
							commitTransaction();
							throw new BankingException("Target Account not found!");
						}
					}
					else
					{
						commitTransaction();
						throw new BankingException("Insufficient Balance!");
					}
				}
				else
				{
					commitTransaction();
					throw new BankingException("Incorrect PIN!");
				}
			}
			else
			{
				commitTransaction();
				throw new BankingException("Source Account not found!");
			}
		
		
		
	
		
		
		
		
		
		
		/*AccountHolder accHolder2 = null;
		accounts = AccHolderRepository.fetchAllAccHolders();
		Iterator<AccountHolder> it = accounts.values().iterator();
		while(it.hasNext())
		{
			accHolder = it.next();
			if(accHolder.getUserName().equals(userName)
					&& accHolder.getPin() == pin)
			{
				double balance = accHolder.getBalance();
				if(balance >= amount)
				{
					Iterator<AccountHolder> it2 = accounts.values().iterator();
					while(it2.hasNext())
					{
						accHolder2 = it2.next();
						if(accHolder2.getAccNo().equals(targetAccNo))
						{
							accHolder.setBalance(balance-amount);
							accHolder2.setBalance(accHolder2.getBalance()+amount);
							
							String transId = new String(((Double)(Math.random() * 10000)).toString());
							
							accHolder.setTransactions
							(new Transaction
								(transId,LocalDateTime.now(), new BigInteger(accHolder2.getAccNo()),
										TransType.Debit, accHolder.getBalance()));
							
							accHolder2.setTransactions
							(new Transaction
								(transId,LocalDateTime.now(), new BigInteger(accHolder.getAccNo()),
										TransType.Credit, accHolder2.getBalance()));
							
							return true;
						}
					}
					throw new BankingException("Target Account Not Found!");
				}
				else
				{
					throw new BankingException("Insufficient Balance!");
				}
			}
		}
		throw new BankingException("Source Account Not Found!");*/
	}

	@Override
	public List<Transaction> printTransactions(String userName, short pin) throws BankingException {
	
		AccountHolder account = em.find(AccountHolder.class, userName);	
		if(account != null)	
		{
			if(account.getPin() == pin)
			{
				return account.getTransactions();
			}
			else
			{
				commitTransaction();
				throw new BankingException("Incorrect PIN!");
			}
		}
		else
		{
			commitTransaction();
			throw new BankingException("Account not found!");
		}
		
		
		
		
		/*accounts = AccHolderRepository.fetchAllAccHolders();
		Iterator<AccountHolder> it = accounts.values().iterator();
		while(it.hasNext())
		{
			accHolder = it.next();
			if(accHolder.getUserName().equals(userName)
					&& accHolder.getPin() == pin)
			{
				return accHolder.getTransactions();
			}
		}
		throw new BankingException("Account Not Found!");*/
	}


	@Override
	public List<AccountHolder> fetchAllAccounts() {
		
		TypedQuery<AccountHolder> query = em.createQuery("from AccountHolder",AccountHolder.class);
		List<AccountHolder> accounts = query.getResultList();
		return accounts;
	
		/*return AccHolderRepository.fetchAllAccHolders();*/
	}

}
