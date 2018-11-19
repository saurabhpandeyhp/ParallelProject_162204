package com.cg.pwa.bean;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AccountHolder {

	@Id
	@Column(length=14)
	private String userName;
	@Column(length=14)
	private String fName, lName;
	@Column(length=14)
	private Date DOB;
	@Column(length=14)
	private short pin;
	@OneToOne(cascade=CascadeType.ALL)
	private Account account;
	
	public AccountHolder() {
		super();
	}
	public AccountHolder(String fName, String lName,
			String userName, short pin, LocalDate DOB, LocalDate openDate, Double balance,
			AccType accType) {
		super();
		this.account = new Account(NumberGenerator.getAccNo(),accType,balance,openDate);
		this.fName = fName;
		this.lName = lName;
		this.userName = userName;
		this.pin = pin;
		this.DOB = Date.valueOf(DOB);
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
		return DOB.toLocalDate();
	}
	public void setDOB(LocalDate dOB) {
		DOB = Date.valueOf(dOB);
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
		return "AccountHolder [FirstName= " + fName + "\t LastName= " + lName
				+ "\n UserName= " + userName + "\n DOB= " + DOB + "\t PIN= " + pin
				+ "\t Account= " + account + "]";
	}

	
}
