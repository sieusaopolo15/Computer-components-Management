package BUS;

import java.util.HashMap;

import DAL.Customer_DAL;
import DAL.MyConnectUnit;
import DTO.Customer;

public class Customer_BUS {
	MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	HashMap<Integer, Customer> customer_list;
	Customer_DAL cd = new Customer_DAL();
	
	
	public Customer_BUS() {
	
	}
	
	public HashMap<Integer, Customer> getList() {
		customer_list = new HashMap<>();
		customer_list = cd.getList();
		return customer_list;
	}
	public boolean Insert(Customer c) {
		return cd.Insert(c);
	}
	public boolean Update(Customer c) throws Exception {
		return cd.Update(c);
	}
}
