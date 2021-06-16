package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Category;
import DTO.Chart_Sales_DTO;

public class Chart_Sales_DAL {
	private MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	private Category_DAL cd = new Category_DAL();
	private HashMap<Integer, Chart_Sales_DTO> reports;
	private HashMap<Integer, Category> categories;
	
	public Chart_Sales_DAL() {
		
	}
	
	public HashMap<Integer, Chart_Sales_DTO> getList(String text){
		reports = new HashMap<>();
		try {
			ResultSet rs = mcu.select("SELECT p.loaiSP, p.tenSP, od.soLuong, p.giaTien, od.tongTien, o.ngayLap\r\n" + 
					"FROM products as p, order_details AS od, orders AS o\r\n" + 
					"WHERE (o.maDH = od.maDH AND od.maSP = p.maSP)" + text); 	
			int i = 1;
			while(rs.next()) {
				int maLoai = rs.getInt("loaiSP");
				String tenSP = rs.getString("tenSP");
				int soLuong = rs.getInt("soLuong");
				String donGia = rs.getString("giaTien");
				String tongTien = rs.getString("tongTien");
				
				Chart_Sales_DTO csd = new Chart_Sales_DTO(soLuong, getCategoryName(maLoai), tenSP, donGia, tongTien);
				reports.put(i, csd);
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reports;
	}
	
	private String getCategoryName(int index) {
		categories = cd.getList();
		return categories.get(index).getTenLoai();
	}
}
