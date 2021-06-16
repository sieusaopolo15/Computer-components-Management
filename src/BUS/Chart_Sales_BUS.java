package BUS;

import java.util.HashMap;

import DAL.Chart_Sales_DAL;
import DTO.Chart_Sales_DTO;

public class Chart_Sales_BUS {
	private Chart_Sales_DAL csd = new Chart_Sales_DAL();
	//private HashMap<Integer, Chart_Sales_DTO> reports;
	
	public Chart_Sales_BUS() {
		
	}
	
	public HashMap<Integer, Chart_Sales_DTO> getList(String text){
		return csd.getList(text);
	}
}
