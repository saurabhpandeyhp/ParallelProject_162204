package com.cg.pwa.bean;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private String acNo;
	private List<Transaction> transactions = new ArrayList<Transaction>();
	private AccType accType;
	private Double balance;
	private LocalDate openDate;
	
	
	public Account() {
		super();
	}
	public Account(BigInteger acNo,	AccType accType, Double balance, LocalDate openDate) {
		super();
		this.acNo = acNo.toString();
		this.accType = accType;
		this.balance = balance;
		this.openDate = openDate;
	}
	public String getAcNo() {
		return acNo.toString();
	}
	public void setAcNo(BigInteger acNo) {
		this.acNo = acNo.toString();
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Transaction transaction) {
		this.transactions.add(transaction);
	}
	public AccType getAccType() {
		return accType;
	}
	public void setAccType(AccType accType) {
		this.accType = accType;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public LocalDate getOpenDate() {
		return openDate;
	}
	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}
	@Override
	public String toString() {
		return "Account [acNo=" + acNo + ", transactions=" + transactions
				+ ", accType=" + accType + ", balance=" + balance
				+ ", openDate=" + openDate + "]";
	}
	
	
}
