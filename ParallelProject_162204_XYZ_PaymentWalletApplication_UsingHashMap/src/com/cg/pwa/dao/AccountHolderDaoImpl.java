package com.cg.pwa.dao;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cg.pwa.bean.AccountHolder;
import com.cg.pwa.bean.TransType;
import com.cg.pwa.bean.Transaction;
import com.cg.pwa.exception.BankingException;
import com.cg.pwa.util.AccHolderRepository;

public class AccountHolderDaoImpl implements AccountHolderDao{	
	AccountHolder accHolder = null;
	Map<String, AccountHolder> accounts = null;
	@Override
	public String createAccount(AccountHolder accHolder) {
		return AccHolderRepository.createAccount(accHolder);
		
	}
	

	@Override
	public double showBalance(String userName, short pin)
			throws BankingException{
		accounts = AccHolderRepository.fetchAllAccHolders();
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
		throw new BankingException("Account Not Found!");
	}
	
	

	@Override
	public double deposit(String userName, short pin, double amount)
			throws BankingException {
		accounts = AccHolderRepository.fetchAllAccHolders();
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
		throw new BankingException("Account Not Found!");
	}

	@Override
	public double withdraw(String userName, short pin, double amount)
			throws BankingException {
		accounts = AccHolderRepository.fetchAllAccHolders();
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
		throw new BankingException("Account Not Found!");
	}

	@Override
	public boolean fundTransfer(String userName, short pin, String targetAccNo,
			double amount) throws BankingException {
		AccountHolder accHolder2 = null;
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
							
							String transId = new String(
									((Double)(Math.random() * 10000)).toString());
							
							accHolder.setTransactions
							(new Transaction
								(transId,LocalDateTime.now(),
										new BigInteger(accHolder2.getAccNo()),
										TransType.Debit, accHolder.getBalance()));
							
							accHolder2.setTransactions
							(new Transaction
								(transId,LocalDateTime.now(),
										new BigInteger(accHolder.getAccNo()),
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
		throw new BankingException("Source Account Not Found!");
	}

	@Override
	public List<Transaction> printTransactions(String userName, short pin)
			throws BankingException {
		accounts = AccHolderRepository.fetchAllAccHolders();
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
		throw new BankingException("Account Not Found!");
	}


	@Override
	public Map<String, AccountHolder> fetchAllAccounts() {
		return AccHolderRepository.fetchAllAccHolders();
	}

}
