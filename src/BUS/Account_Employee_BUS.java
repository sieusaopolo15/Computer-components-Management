package BUS;

import java.sql.ResultSet;
import java.util.HashMap;

import DAL.Account_Employee_DAL;
import DAL.MyConnectUnit;
import DTO.Account_Employee;

public class Account_Employee_BUS {
	private HashMap<Integer, Account_Employee> accounts;
	private MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	private Account_Employee_DAL aed = new Account_Employee_DAL();
	
	public Account_Employee_BUS() {
		
	}
	
	
	public HashMap<Integer, Account_Employee> getList(){
		accounts = new HashMap<>();
		accounts = aed.getList();
		return accounts;
	}
	
	public boolean Insert(Account_Employee ae) throws Exception {
		return aed.Insert(ae);
	}
	
}
