package com.cg.pwa.bean;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class AccountHolder {

	private static BigInteger accNo = new BigInteger("29400100016946");
	private String fName, lName, userName;
	private LocalDate DOB;
	private short pin;
	private Account account;
	
	public AccountHolder() {
		super();
	}
	
	public AccountHolder(String fName, String lName,
			String userName, short pin, LocalDate DOB, LocalDate openDate, Double balance,
			AccType accType) {
		super();
		this.account = new Account(accNo,accType,balance,openDate);
		accNo = accNo.add(new BigInteger("1"));
		this.fName = fName;
		this.lName = lName;
		this.userName = userName;
		this.pin = pin;
		this.DOB = DOB;
	}
	public String getAccNo() {
		return account.getAcNo();
	}
	public void setAccNo(BigInteger acN) {
		account.setAcNo(acN);
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDate getDOB() {
		return DOB;
	}
	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}
	public LocalDate getOpenDate() {
		return account.getOpenDate();
	}
	public void setOpenDate(LocalDate openDate) {
		account.setOpenDate(openDate);
	}
	public double getBalance() {
		return account.getBalance();
	}
	public void setBalance(double balance) {
		account.setBalance(balance);
	}
	public AccType getAccType() {
		return account.getAccType();
	}
	public void setAccType(AccType accType) {
		account.setAccType(accType);
	}
	public short getPin() {
		return pin;
	}
	public void setPin(short pin) {
		this.pin = pin;
	}
	public List<Transaction> getTransactions() {
		return account.getTransactions();
	}
	public void setTransactions(Transaction transaction) {
		account.setTransactions(transaction);
	}

	@Override
	public String toString() {
		return "AccountHolder [fName=" + fName + ", lName=" + lName
				+ ", userName=" + userName + ", DOB=" + DOB + ", pin=" + pin
				+ ", account=" + account + "]";
	}

	
}
