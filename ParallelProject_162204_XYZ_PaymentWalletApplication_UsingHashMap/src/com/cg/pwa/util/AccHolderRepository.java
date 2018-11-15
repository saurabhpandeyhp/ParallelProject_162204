package com.cg.pwa.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import com.cg.pwa.bean.AccountHolder;
import com.cg.pwa.bean.AccType;

public class AccHolderRepository {

	private static Map<String, AccountHolder> accHolders = new HashMap<String, AccountHolder>();
	
	static
	{
		
		AccountHolder ac1 = new AccountHolder
				("Saurabh", "Pandey", "saurabhp",
				(short)9999, LocalDate.of(1996, Month.JULY, 10),
				LocalDate.of(2018, Month.SEPTEMBER, 26), 75676.78,
				AccType.Saving);
		
		AccountHolder ac2 = new AccountHolder
				("Ritambhara", "Srivastava", "tarasriv",
				(short)8888, LocalDate.of(1997, Month.APRIL, 16),
				LocalDate.of(2017, Month.SEPTEMBER, 23), 67348.83,
				AccType.Current);
		
		accHolders.put(ac1.getAccNo(), ac1);
		accHolders.put(ac2.getAccNo(), ac2);
	}
	
	public static Map<String, AccountHolder> fetchAllAccHolders()
	{
		return accHolders;
	}
	
	public static String createAccount(AccountHolder accHolder)
	{
		accHolders.put(accHolder.getAccNo(), accHolder);
		return accHolder.getAccNo();
	}
}
