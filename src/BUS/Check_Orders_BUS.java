package BUS;

import java.util.HashMap;

import DAL.Check_Orders_DAL;
import DTO.Check_Orders;

public class Check_Orders_BUS {
	private HashMap<Integer, Check_Orders> orders;
	private Check_Orders_DAL cod = new Check_Orders_DAL();
	
	public Check_Orders_BUS() {
		
	}
	
	public HashMap<Integer, Check_Orders> getList(int id){
		return cod.getList(id);
	}
}
