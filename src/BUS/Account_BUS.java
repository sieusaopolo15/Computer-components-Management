package BUS;

import java.util.HashMap;

import DAL.Account_DAL;
import DTO.Account;

public class Account_BUS {
	Account_DAL ad = new Account_DAL();
	HashMap<Integer, Account> accounts;
	
	public Account_BUS() {

	}
	
	public HashMap<Integer, Account> getList(){
		accounts = ad.getList();
		return accounts;
	}
	
	public boolean Insert(Account a) {
		return ad.Insert(a);
	}
	
	public boolean Update(Account a) throws Exception {
		return ad.Update(a);
	}
}
