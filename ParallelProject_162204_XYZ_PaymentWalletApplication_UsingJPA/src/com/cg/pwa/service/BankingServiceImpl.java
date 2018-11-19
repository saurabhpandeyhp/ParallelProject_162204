package com.cg.pwa.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.pwa.bean.AccountHolder;
import com.cg.pwa.bean.Transaction;
import com.cg.pwa.dao.AccountHolderDaoImpl;
import com.cg.pwa.exception.BankingException;

public class BankingServiceImpl implements BankingService{

	AccountHolderDaoImpl accDao = new AccountHolderDaoImpl();
	
	@Override
	public String createAccount(AccountHolder accHolder) {
		accDao.beginTransaction();
		String acNo = accDao.createAccount(accHolder);
		accDao.commitTransaction();
		return acNo;
	}

	@Override
	public double showBalance(String userName, short pin) throws BankingException {
		accDao.beginTransaction();
		double bal = accDao.showBalance(userName, pin);
		accDao.commitTransaction();
		return bal;
	}

	@Override
	public double deposit(String userName, short pin, double amount) throws BankingException {
		accDao.beginTransaction();
		double bal = accDao.deposit(userName, pin, amount);
		accDao.commitTransaction();
		return bal;
	}

	@Override
	public double withdraw(String userName, short pin, double amount) throws BankingException {
		accDao.beginTransaction();
		double bal = accDao.withdraw(userName, pin, amount);
		accDao.commitTransaction();
		return bal;
	}

	@Override
	public double fundTransfer(String userName, short pin, String targetAccNo,
			double amount) throws BankingException {
		accDao.beginTransaction();
		double bal = accDao.fundTransfer(userName, pin, targetAccNo, amount);
		accDao.commitTransaction();
		return bal;
	}

	@Override
	public List<Transaction> printTransactions(String userName, short pin) throws BankingException {
		accDao.beginTransaction();
		List<Transaction> transactions = accDao.printTransactions(userName, pin);
		accDao.commitTransaction();
		return transactions;
	}

	@Override
	public List<AccountHolder> fetchAllAccounts() {
		accDao.beginTransaction();
		List<AccountHolder> accounts = accDao.fetchAllAccounts();
		accDao.commitTransaction();
		return accounts;
	}
	
	@Override
	public boolean validateFName(String fName) throws BankingException {
		Pattern pattern =Pattern.compile("[A-Z][a-z]+");
		Matcher matcher = pattern.matcher(fName);
		if(matcher.matches())
		{
			return true;
		}
		else
		{
			throw new BankingException("The First Name should start with "
					+ "Capital Letter followed by small letters");
		}
	}

	@Override
	public boolean validateLName(String lName) throws BankingException {
		Pattern pattern =Pattern.compile("[A-Z][a-z]+");
		Matcher matcher = pattern.matcher(lName);
		if(matcher.matches())
		{
			return true;
		}
		else
		{
			throw new BankingException("The Last Name should start with "
					+ "Capital Letter followed by small letters");
		}
	}

	@Override
	public boolean validateUserName(String userName) throws BankingException {
		Pattern pattern =Pattern.compile("[a-z]{7}[a-z]+");
		Matcher matcher = pattern.matcher(userName);
		if(matcher.matches() && userName.length()>=8)
		{
			return true;
		}
		else
		{
			throw new BankingException("The UserName should be atleast 8 charchters long "
					+ "with all small letters only");
		}
	}

	@Override
	public boolean validateDOB(String DOB) throws BankingException {
		Pattern pattern =Pattern.compile("[0-3][0-9]"+"-"+"[0-9][0-9]"+"-"+"[1-1][0-9][0-9][0-9]");
		Matcher matcher = pattern.matcher(DOB);
		if(matcher.matches())
		{
			return true;
		}
		else
		{
			throw new BankingException("Invalid date ! "
					+ "Should be of pattren [dd-MM-yyyy]");
		}
	}

	@Override
	public boolean validateOpenBalAmount(String openBalAmount) throws BankingException{
		Double amt = Double.parseDouble(openBalAmount);
		if(amt >= 1000.0)
		{
			return true;
		}
		else
		{
			throw new BankingException("The opening account must be atleast 1000");
		}
	}

	@Override
	public boolean validateAccType(String accType) throws BankingException {
		if(accType.equals("Saving") || accType.equals("Current"))
		{
			return true;
		}
		else
		{
			throw new BankingException("The account type can be either be"
					+ " [Saving] or [Current] only");
		}
	}

	@Override
	public boolean validatePin(String pin) throws BankingException {
		Pattern pattern =Pattern.compile("[0-9]{4}");
		Matcher matcher = pattern.matcher(pin);
		if(matcher.matches())
		{
			return true;
		}
		else
		{
			throw new BankingException("Invalid PIN ! "
					+ "Should be only numbers having 4 digits");
		}
	}

	@Override
	public boolean validateAmount(String amount) throws BankingException{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean validateAccNo(String acNo) throws BankingException{
		// TODO Auto-generated method stub
		return true;
	}

	

}
