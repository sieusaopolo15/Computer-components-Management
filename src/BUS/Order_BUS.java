package BUS;

import java.util.HashMap;

import DAL.Order_DAL;
import DTO.Order;

public class Order_BUS {
	private HashMap<Integer, Order> orders;
	private Order_DAL od = new Order_DAL();
	
	public Order_BUS() {
		
	}
	
	public HashMap<Integer, Order> getList(){
		orders = new HashMap<>();
		orders = od.getList();
		return orders;
	}
	
	public boolean Insert(Order o) throws Exception {
		return od.Insert(o);
	}
	
	public boolean Delete(int maDH) throws Exception {
		return od.Delete(maDH);
	}
	
	public boolean Update(Order o) throws Exception {
		return od.Update(o);
	}
}
