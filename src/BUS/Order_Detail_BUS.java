package BUS;

import java.util.HashMap;

import DAL.Order_Details_DAL;
import DTO.Order_Detail;

public class Order_Detail_BUS {
	private HashMap<Integer, Order_Detail> details;
	private Order_Details_DAL odd = new Order_Details_DAL();
	
	public Order_Detail_BUS() {
		
	}
	
	public HashMap<Integer, Order_Detail> getList(int id){
		details = new HashMap<>();
		details = odd.getList(id);
		return details;
	}
	
	public boolean Insert(Order_Detail od) throws Exception {
		return odd.Insert(od);
	}
	
	public boolean Delete(int id) throws Exception {
		return odd.Delete(id);
	}
}
