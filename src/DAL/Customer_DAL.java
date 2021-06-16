package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Customer;

public class Customer_DAL {
	MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	
	
	public Customer_DAL() {
		
	}
	
	public HashMap<Integer, Customer> getList() {
		HashMap<Integer, Customer> customer_list = new HashMap<>();
		
		int i = 0;
		try {
			ResultSet rs = mcu.Select("customer");
			while(rs.next()) {
				int maKH = rs.getInt("maKH");
				String hoTenKH = rs.getString("hoKH");
				String gioiTinh = rs.getString("gioiTinh");
				String ngaySinh = rs.getString("ngaySinh");
				
				i++;
				Customer c = new Customer(maKH, hoTenKH, gioiTinh, ngaySinh);
				customer_list.put(i, c);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer_list;
	}
	
	public boolean Insert(Customer c) {
		String valueInsert = "" + 
				"'" + c.getMaKH() + "', " +
				"'" + c.getHoTenKH() + "', " + 
				"'" + c.getGender() + "', " +
				"'" + c.getDate() + "'";
		try {
			return mcu.Insert("customer", valueInsert);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean Update(Customer c) throws Exception {
		String valueInsert = "" +  
			"hoKH='" + c.getHoTenKH() + "', " +
			"gioiTinh='" + c.getGender() + "', " +
			"ngaySinh='" + c.getDate() + "'"
		;
		return mcu.Update("customer", valueInsert, "maKH='" + c.getMaKH() + "'");
	}
	
	
}
