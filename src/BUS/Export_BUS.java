package BUS;

import java.util.HashMap;

import DAL.Export_DAL;
import DTO.Export;

public class Export_BUS {
	private HashMap<Integer, Export> exports;
	private Export_DAL ed = new Export_DAL();
	
	public Export_BUS() {
		
	}
	
	public HashMap<Integer, Export> getList(){
		exports = ed.getList();
		return exports;
	}
	
	public boolean Insert(Export e) throws Exception {
		return ed.Insert(e);
	}
}
