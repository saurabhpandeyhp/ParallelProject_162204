package com.cg.pwa.bean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;

public class NumberGenerator {
	
	public static BigInteger getAccNo() {
		String acNo = null;
		BigInteger accNo = null;
		try {
			FileReader reader = new FileReader("src/NumberGenerator.properties");
			Properties prop = new Properties();
			prop.load(reader);
			acNo = prop.getProperty("nextAccount");
			accNo = new BigInteger(acNo);
			reader.close();
			FileWriter writer = new FileWriter("src/NumberGenerator.properties");
			accNo = accNo.add(new BigInteger("1"));
			prop.setProperty("nextAccount", accNo.toString());
			prop.store(writer, null);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("NumberGenerator.properties file not found inside src directory!"); 
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new BigInteger(acNo);
	}
	
	public static BigInteger getuniqueId() {
		String uId = null;
		BigInteger unId = null;
		try {
			FileReader reader = new FileReader("src/NumberGenerator.properties");
			Properties prop = new Properties();
			prop.load(reader);
			uId = prop.getProperty("nextId");
			unId = new BigInteger(uId);
			reader.close();
			FileWriter writer = new FileWriter("src/NumberGenerator.properties");
			unId = unId.add(new BigInteger("1"));
			prop.setProperty("nextId", unId.toString());
			prop.store(writer, null);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("NumberGenerator.properties file not found inside src directory!"); 
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new BigInteger(uId);
	}
	
}
