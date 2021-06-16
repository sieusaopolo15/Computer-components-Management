package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Check_Orders;

public class Check_Orders_DAL {
	private HashMap<Integer, Check_Orders> orders;
	private MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");

	public Check_Orders_DAL() {
		
	}
	
	public HashMap<Integer, Check_Orders> getList(int id){
		orders = new HashMap<>();
		try {
			ResultSet rs = mcu.select("SELECT od.maDH, o.maKH, od.nguoiNhan, od.maSP, od.soLuong, od.donGia, od.tongTien, o.NgayLap\r\n" + 
					"FROM orders AS o, order_details as od\r\n" + 
					"WHERE o.maDH = od.maDH AND o.maKH='" + id + "'");
			int i = 0;
			while(rs.next()) {
				int maDH = rs.getInt("maDH");
				int maKH = rs.getInt("maKH");
				String nguoiNhan = rs.getString("nguoiNhan");
				int maSP = rs.getInt("maSP");
				int soLuong = rs.getInt("soLuong");
				String donGia = rs.getString("donGia");
				String tongTien = rs.getString("tongTien");
				String ngayLap = rs.getString("ngayLap");
				
				Check_Orders co = new Check_Orders(
						maDH, maKH, nguoiNhan, "" + maSP, soLuong,
						donGia, tongTien, ngayLap
				);
				i++;
				orders.put(i, co);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
}
