package com.cg.pwa.dao;

import java.util.List;
import com.cg.pwa.bean.AccountHolder;
import com.cg.pwa.bean.Transaction;
import com.cg.pwa.exception.BankingException;

public interface AccountHolderDao {

	public String createAccount(AccountHolder accHolder);
	public double showBalance(String userName, short pin) throws BankingException;
	public double deposit(String userName, short pin, double amount) throws BankingException;
	public double withdraw(String userName, short pin, double amount) throws BankingException;
	public double fundTransfer(String userName, short pin,String targetAccNo, double amount) throws BankingException;
	public List<Transaction> printTransactions(String userName, short pin) throws BankingException;
	public List<AccountHolder> fetchAllAccounts();
	void beginTransaction();
	void commitTransaction();
}
