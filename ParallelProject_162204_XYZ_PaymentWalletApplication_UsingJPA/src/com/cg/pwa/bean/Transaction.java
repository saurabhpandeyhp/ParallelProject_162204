package com.cg.pwa.bean;


import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction
{
	@Id
	@Column(length=18)
	private String uniqueID;
	@Column(length=14)
	private String transId;
	@Column(length=25)
	private String transDateTime;
	@Column(length=14)
	private BigInteger transWith;
	@Column(length=8)
	private TransType transType;
	@Column(length=14)
	private double balance;
	@Column(length=14)
	private double amount;
	
	public Transaction() {
		super();
	}

	public Transaction(String transId, LocalDateTime transDateTime,
			BigInteger transWith, TransType transType,double amount, double balance) {
		super();
		this.uniqueID = NumberGenerator.getuniqueId().toString();
		this.transId = transId;
		this.transDateTime = transDateTime.toString();
		this.transWith = transWith;
		this.transType = transType;
		this.amount = amount;
		this.balance = balance;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public LocalDateTime getTransDateTime() {
		return LocalDateTime.parse(this.transDateTime);
	}
	public void setTransDateTime(LocalDateTime transDateTime) {
		this.transDateTime = transDateTime.toString();
	}
	public BigInteger getTransWith() {
		return transWith;
	}
	public void setTransWith(BigInteger transWith) {
		this.transWith = transWith;
	}
	public TransType getTransType() {
		return transType;
	}
	public void setTransType(TransType transType) {
		this.transType = transType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Transaction ID= " + transId + "\t DateTime= "
				+ transDateTime + " With= " + transWith + " Operation= "
				+ transType + " Amount= " + amount +" Balance= " + balance;
	}	
}
