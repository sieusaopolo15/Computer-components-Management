package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Category;

public class Category_DAL {
	private HashMap<Integer, Category> categories;
	private MyConnectUnit mcu = new MyConnectUnit("localhost" ,"root", "", "cnpm");
	
	public Category_DAL() {
		
	}
	
	public HashMap<Integer, Category> getList(){
		categories = new HashMap<>();
		try {
			ResultSet rs = mcu.Select("category");
			while(rs.next()) {
				int maLoai = rs.getInt("maLoai");
				String tenLoai = rs.getString("tenLoai");
				
				Category c = new Category(maLoai, tenLoai);
				categories.put(maLoai, c);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categories;
	}
}
