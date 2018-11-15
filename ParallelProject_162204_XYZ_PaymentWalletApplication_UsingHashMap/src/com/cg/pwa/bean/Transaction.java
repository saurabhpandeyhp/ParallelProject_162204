package com.cg.pwa.bean;


import java.math.BigInteger;
import java.time.LocalDateTime;

import com.cg.pwa.bean.TransType;

public class Transaction
{
	private String transId;
	private LocalDateTime transDateTime;
	private BigInteger transWith;
	private TransType transType;
	private double balance;
	
	public Transaction() {
		super();
	}

	public Transaction(String transId, LocalDateTime transDateTime,
			BigInteger transWith, TransType transType, double balance) {
		super();
		this.transId = transId;
		this.transDateTime = transDateTime;
		this.transWith = transWith;
		this.transType = transType;
		this.balance = balance;
	}
	
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public LocalDateTime getTransDateTime() {
		return transDateTime;
	}
	public void setTransDateTime(LocalDateTime transDateTime) {
		this.transDateTime = transDateTime;
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
		return "Transaction [transId=" + transId + ", transDateTime="
				+ transDateTime + ", transWith=" + transWith + ", transType="
				+ transType + ", balance=" + balance + "]";
	}	
}
