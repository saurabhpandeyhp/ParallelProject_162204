package com.cg.pwa.pl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.pwa.bean.AccountHolder;
import com.cg.pwa.bean.AccType;
import com.cg.pwa.bean.Transaction;
import com.cg.pwa.exception.BankingException;
import com.cg.pwa.service.BankingService;
import com.cg.pwa.service.BankingServiceImpl;

public class Banking {

	static BankingService bank = new BankingServiceImpl();
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
	
		int option = 0;

		while(true)
		{
			System.out.println("\n\n**********XYY PAYMENT WALLET APPLICATION**********");
			System.out.println("--------------------------------------------------");
			System.out.println();
			System.out.println("1. CREATE NEW ACCOUNT\t2. SHOW BALANCE");
			System.out.println("3. DEPOSIT\t\t4. WITHDRAW");
			System.out.println("5. FUND TRANSFER\t6. PRINT TRANSACTIONS");
			System.out.println("7. SHOW ALL ACCOUNTS\t0. EXIT");
			option = sc.nextInt();
			
			switch(option)
			{
				case 1 :
					Banking.createAccount(); break;
				case 2 :
					Banking.showBalance(); break;
				case 3 :
					Banking.deposit(); break;
				case 4:
					Banking.withdraw(); break;
				case 5:
					Banking.fundTransfer(); break;
				case 6 :
					Banking.printTransaction(); break;
				case 7 :
					Banking.showAllAccounts(); break;
				default :
					System.out.println("Invalid choice!");
			}
		}
	}
	
	private static void showAllAccounts() {
		List<AccountHolder> accounts = bank.fetchAllAccounts();
		AccountHolder account = null; 
		Iterator<AccountHolder> iterator =
				accounts.iterator();
		while(iterator.hasNext())
		{
			account = iterator.next();
			System.out.print("\n"+account);
		}
	}

	private static void printTransaction() {
		boolean flag1 = true,
				flag2 = true;

		String 	userName = null,
				pin = null;
		
		while(flag1)
		{
			System.out.println("Enter UserName : ");
			userName = sc.next();
			try {
				if(bank.validateUserName(userName))
				{
					flag1 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag2)
		{
			System.out.println("Enter PIN : ");
			pin = sc.next();
			try {
				if(bank.validatePin(pin))
				{
					flag2 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			transactions = bank.printTransactions(userName, Short.parseShort(pin));
		} catch (NumberFormatException | BankingException e) {
			System.out.println(e.getMessage());
		}
		Iterator<Transaction> it = transactions.iterator();
		if(!it.hasNext())
		{
			System.out.println("No transactions made yet !");
		}
		else
		{
			while(it.hasNext())
			{
				System.out.println(" "+it.next());
			}
		}
		
	}

	public static void createAccount()
	{
		boolean flag1 = true,
				flag2 = true,
				flag3 = true,
				flag4 = true,
				flag5 = true,
				flag6 = true,
				flag7 = true;
		
		String 	fName = null,
				lName = null,
			   	userName = null,
				pin = null;
		String DOB = null;
		LocalDate openDate = LocalDate.now();
		String openingBalAmount = "0";
		String accType = null;
		
		while(flag1)
		{
			System.out.println("Enter First Name : ");
			fName = sc.next();
			try {
				if(bank.validateFName(fName))
				{
					flag1 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag2)
		{
			System.out.println("Enter Last Name : ");
			lName = sc.next();
			try {
				if(bank.validateLName(lName))
				{
					flag2 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag3)
		{
			System.out.println("Enter Dte of Birth [dd-MM-yyyy]: ");
			DOB = sc.next();
			try {
				if(bank.validateDOB(DOB))
				{
					flag3 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag4)
		{
			System.out.println("Enter  Account Type [Saving/Current] : ");
			accType = sc.next();
			try {
				if(bank.validateAccType(accType))
				{
					flag4 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag5)
		{
			System.out.println("Enter UserName : ");
			userName = sc.next();
			try {
				if(bank.validateUserName(userName))
				{
					flag5 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag6)
		{
			System.out.println("Enter PIN : ");
			pin = sc.next();
			try {
				if(bank.validatePin(pin))
				{
					flag6 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag7)
		{
			System.out.println("Enter Opening Account Balance : ");
			openingBalAmount = sc.next();
			try {
				if(bank.validateOpenBalAmount(openingBalAmount))
				{
					flag7 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		
		AccountHolder accHolder = new AccountHolder
		(fName, lName, userName, Short.parseShort(pin),
		LocalDate.parse(DOB,DateTimeFormatter.ofPattern("dd-MM-yyyy")),
		openDate, Double.parseDouble(openingBalAmount), AccType.valueOf(accType));
		String acNo = bank.createAccount(accHolder);
		System.out.println("Your Account Number is : "+acNo);
	}
	
	
	public static void showBalance()
	{
		boolean flag1 = true,
				flag2 = true;

		String 	userName = null,
				pin = null;
		
		while(flag1)
		{
			System.out.println("Enter UserName : ");
			userName = sc.next();
			try {
				if(bank.validateUserName(userName))
				{
					flag1 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag2)
		{
			System.out.println("Enter PIN : ");
			pin = sc.next();
			try {
				if(bank.validatePin(pin))
				{
					flag2 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		
		double balance = 0;
		try {
			balance = bank.showBalance(userName, Short.parseShort(pin));
			System.out.println("Your Balance is : "+balance);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		} catch (BankingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void deposit()
	{
		boolean flag1 = true,
				flag2 = true,
				flag3 = true;
		String 	userName = null,
				pin = null;
		String amount = "0";
		
		while(flag1)
		{
			System.out.println("Enter UserName : ");
			userName = sc.next();
			try {
				if(bank.validateUserName(userName))
				{
					flag1 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag2)
		{
			System.out.println("Enter PIN : ");
			pin = sc.next();
			try {
				if(bank.validatePin(pin))
				{
					flag2 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag3)
		{
			System.out.println("Enter Amount : ");
			amount = sc.next();
			try {
				if(bank.validateAmount(amount))
				{
					flag3 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		
		double balance;
		try {
			balance = bank.deposit(userName, Short.parseShort(pin), Double.parseDouble(amount));
			System.out.println(amount+" DEPOSITED!");
			System.out.println("Your Balance is : "+balance);
		} catch (NumberFormatException | BankingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void withdraw()
	{
		boolean flag1 = true,
				flag2 = true,
				flag3 = true;
		String 	userName = null,
				pin = null;
		String amount = "0";
		
		while(flag1)
		{
			System.out.println("Enter UserName : ");
			userName = sc.next();
			try {
				if(bank.validateUserName(userName))
				{
					flag1 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag2)
		{
			System.out.println("Enter PIN : ");
			pin = sc.next();
			try {
				if(bank.validatePin(pin))
				{
					flag2 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag3)
		{
			System.out.println("Enter Amount : ");
			amount = sc.next();
			try {
				if(bank.validateAmount(amount))
				{
					flag3 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		
		double balance;
		try {
			balance = bank.withdraw(userName, Short.parseShort(pin), Double.parseDouble(amount));
			System.out.println(amount+" WITHDRAWN !");
			System.out.println("Your Balance is : "+balance);
		} catch (NumberFormatException | BankingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void fundTransfer()
	{
		boolean flag1 = true,
				flag2 = true,
				flag3 = true,
				flag4 = true;
		String 	userName = null,
				pin = null,
				acNo = null;
		String amount = "0";
		
		while(flag1)
		{
			System.out.println("Enter UserName : ");
			userName = sc.next();
			try {
				if(bank.validateUserName(userName))
				{
					flag1 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag2)
		{
			System.out.println("Enter PIN : ");
			pin = sc.next();
			try {
				if(bank.validatePin(pin))
				{
					flag2 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag3)
		{
			System.out.println("Enter Amount : ");
			amount = sc.next();
			try {
				if(bank.validateAmount(amount))
				{
					flag3 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		while(flag4)
		{
			System.out.println("Enter Target Account No.: ");
			acNo = sc.next();
			try {
				if(bank.validateAccNo(acNo))
				{
					flag4 = false;
				}
			} catch (BankingException e) {
				System.out.println(e.getMessage());
			}
		}
		
		try {
			double bal = bank.fundTransfer(userName, Short.parseShort(pin), acNo,Double.parseDouble(amount));
				System.out.println(amount+" TRANSFERRED to !"+acNo);
				System.out.println("Your Balance is : "+bal);
		} catch (NumberFormatException | BankingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	

}
