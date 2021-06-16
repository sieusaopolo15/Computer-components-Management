package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import BUS.Employee_BUS;
import DAL.MyConnectUnit;
import DTO.Employee;
import DTO.Order;

public class Order_DAL {
	
	private HashMap<Integer, Order> orders;
	private HashMap<Integer, Employee> employees;
	
	private MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	private Employee_BUS eb = new Employee_BUS();
	
	private String[] a = {
		"Đã Phê Duyệt",
		"Chưa Phê Duyệt",
		"Hợp Lệ",
		"Không Hợp Lệ"
	};
	
	public Order_DAL() {
		
	}
	
	public HashMap<Integer, Order> getList() {
		orders = new HashMap<>();
		int i = 0;
		try {
			ResultSet rs = mcu.Select("orders");
			while(rs.next()) {
				
				int maDH = rs.getInt("maDH");
				int maKH = rs.getInt("maKH");
				int maNV = rs.getInt("maNVGiaoHang");
				String ngayLap = rs.getString("NgayLap");
				String tongTien = rs.getString("tongTien");
				int Status = rs.getInt("trangThai");
				Order order = new Order(maDH, maKH, setName(maNV), ngayLap, tongTien, setStatus(Status));
				i++;
				orders.put(i, order);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	private String setStatus(int index) {
		for(int i = 0; i < a.length; i++) {
			if(index - 1 == i) {
				return a[i];
			}
		}
		return null;
	}
	
	private int getStatus(String str) {
		int index = 0;
		for(int i = 0; i < a.length; i++) {
			if(str.equals(a[i])) {
				index = i + 1;
				break;
			}
		}
		return index;
	}
	
	public boolean Insert(Order o) throws Exception {
		String valueInsert = "" + 
				"'" + o.getMaDH() + "', " +
				"'" + o.getMaKH() + "', " +	
				"'" + o.getNgayLap() + "', " +
				"'" + o.getTongTien() + "', " +
				"'" + 1 + "', " +
				"'" + getStatus(o.getStatus()) + "'";
		;
		return mcu.Insert("orders", valueInsert);
	}
	
	public boolean Update(Order o) throws Exception {
		String valueInsert = "" +
				"maNVGiaoHang='" + o.getMaNV() + "', " +
				"trangThai='" + getStatus(o.getStatus()) + "'"
		;
		return mcu.Update("orders", valueInsert, "maDH='" + o.getMaDH() + "'");
	}
	
	public boolean Delete(int maDH) throws Exception {
		return mcu.Delete("orders", "maDH='" + maDH + "'");
	}
	
	private String setName(int index) {
		employees = eb.getList();
		for(int i = 1; i <= employees.size(); i++) {
			if(index == employees.get(i).getMaNV()){
				String str = employees.get(index).getHoNV() + " " + employees.get(index).getTenNV();
				return str;
			}
				
		}
		return null;
	}
}
