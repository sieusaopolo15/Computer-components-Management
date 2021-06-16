package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Warehouse;

public class Warehouse_DAL {
	private HashMap<Integer, Warehouse> warehouse;
	private MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	
	public Warehouse_DAL() {
		
	}
	public HashMap<Integer, Warehouse> getList(){
		warehouse = new HashMap<>();
		try {
			ResultSet rs = mcu.Select("storage"); 
			while(rs.next()) {
				int maKho = rs.getInt("maKho");
				String tenKho = rs.getString("tenKho");
				Warehouse wh = new Warehouse(maKho, tenKho);
				warehouse.put(maKho, wh);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return warehouse;
	}
	
}
