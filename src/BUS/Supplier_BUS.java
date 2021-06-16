package BUS;

import java.util.HashMap;

import DAL.Supplier_DAL;
import DTO.Supplier;

public class Supplier_BUS {
	private HashMap<Integer, Supplier> suppliers;
	private Supplier_DAL sd = new Supplier_DAL();
	
	public Supplier_BUS() {
		
	}
	
	public HashMap<Integer, Supplier> getList(){
		suppliers = sd.getList();
		return suppliers;
	}
	
	public boolean Insert(Supplier s) throws Exception {
		return sd.Insert(s);
	}
	
	public boolean Update(Supplier s) throws Exception {
		return sd.Update(s);
	}
	
	public boolean Delete(Supplier s) throws Exception {
		return sd.Delete(s);
	}
}
