package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Order_Detail;

public class Order_Details_DAL {
	private HashMap<Integer, Order_Detail> details;
	private MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	
	private String []a = {
			
	};
	
	public Order_Details_DAL() {
		
	}
	
	public HashMap<Integer, Order_Detail> getList(int id){
		details = new HashMap<>();
		int i = 0;
		try {
			ResultSet rs = mcu.Select("order_details", "maDH='" + id + "'");
			while(rs.next()) {
				int maDH = rs.getInt("maDH");
				int maSP = rs.getInt("maSP");
				String nguoiNhan = rs.getString("nguoiNhan");
				String diaChiGiaoHang = rs.getString("diaChiGiaoHang");
				String sdt = rs.getString("sdt");
				int soLuong = rs.getInt("soLuong");
				String donGia = rs.getString("donGia");
				String tongTien = rs.getString("tongTien");
				
				i++;
				Order_Detail od = new Order_Detail(maDH, maSP, nguoiNhan, diaChiGiaoHang, sdt, soLuong, donGia, tongTien);
				details.put(i, od);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return details;
	}
	
	public boolean Insert(Order_Detail od) throws Exception {
		String valueInsert = "" +
				"'" + od.getMaDH() + "', " + 
				"'" + od.getMaSP() + "', " + 
				"'" + od.getNguoiNhan() + "', " + 
				"'" + od.getDiaChi() + "', " + 
				"'" + od.getSDT() + "', " + 
				"'" + od.getSoLuong() + "', " + 
				"'" + od.getDonGia() + "', " +
				"'" + od.gettongTien() + "'" 
		;		
		return mcu.Insert("order_details", valueInsert);
	}
	
	public boolean Delete(int id) throws Exception {
		return mcu.Delete("order_details", "maDH='" + id + "'");
	}
}
