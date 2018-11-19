package com.cg.pwa.bean;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Account {

	@Id
	@Column(length=14)
	private String acNo;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Transaction> transactions = new ArrayList<Transaction>();
	@Column(length=14)
	private AccType accType;
	@Column(length=14)
	private Double balance;
	@Column(length=14)
	private Date openDate;
	
	
	public Account() {
		super();
	}
	public Account(BigInteger acNo,	AccType accType, Double balance, LocalDate openDate) {
		super();
		this.acNo = acNo.toString();
		this.accType = accType;
		this.balance = balance;
		this.openDate = Date.valueOf(openDate);
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
		return openDate.toLocalDate();
	}
	public void setOpenDate(LocalDate openDate) {
		this.openDate = Date.valueOf(openDate);
	}
	@Override
	public String toString() {
		return "Account No=" + acNo +", accType=" + accType + ", balance=" + balance
				+ ", openDate=" + openDate + "]";
	}
	
	
}
