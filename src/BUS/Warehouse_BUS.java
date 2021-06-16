package BUS;

import java.util.HashMap;

import DAL.Warehouse_DAL;
import DTO.Warehouse;

public class Warehouse_BUS {
	private HashMap<Integer, Warehouse> warehouse;
	private Warehouse_DAL wd = new Warehouse_DAL();
	
	public Warehouse_BUS() {
		
	}
	
	public HashMap<Integer, Warehouse> getList(){
		warehouse = wd.getList();
		return warehouse;
	}
}
