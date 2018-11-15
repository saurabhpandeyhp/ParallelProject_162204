package com.cg.pwa.service;

import java.util.List;
import java.util.Map;

import com.cg.pwa.bean.AccountHolder;
import com.cg.pwa.bean.Transaction;
import com.cg.pwa.exception.BankingException;

public interface BankingService {


	public String createAccount(AccountHolder accHolder);
	public double showBalance(String userName, short pin) throws BankingException;
	public double deposit(String userName, short pin, double amount) throws BankingException;
	public double withdraw(String userName, short pin, double amount) throws BankingException;
	public boolean fundTransfer(String userName, short pin,String targetAccNo, double amount)
			throws BankingException;
	public List<Transaction> printTransactions(String userName, short pin)
			throws BankingException;
	public Map<String, AccountHolder> fetchAllAccounts();
	public boolean validateFName(String fName) throws BankingException;
	public boolean validateLName(String lName) throws BankingException;
	public boolean validateUserName(String userName) throws BankingException;
	public boolean validateDOB(String DOB) throws BankingException;
	public boolean validateOpenBalAmount(String openBALAmount) throws BankingException;
	public boolean validateAccType(String accType) throws BankingException;
	public boolean validatePin(String pin) throws BankingException;
	public boolean validateAmount(String amount) throws BankingException;
	public boolean validateAccNo(String acNo) throws BankingException;
}
