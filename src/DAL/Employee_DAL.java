package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Account_Employee;
import DTO.Employee;

public class Employee_DAL {
	MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	HashMap<Integer, Employee> employee_list;
	ResultSet rs = null;
	String []a = {
		"Quản Lý",
		"Thu Ngân",
		"Kiểm Kho",
		"Nhân Viên Hỗ Trợ",
		"Nhân Viên Giao Hàng"
	};
	
	public Employee_DAL() {
		
	}
	
	public HashMap<Integer, Employee> getList() {
		employee_list = new HashMap<>();
		try {
			int i = 0;
			rs = mcu.Select("employee");
			while(rs.next()) {
				Employee e = new Employee();
				int maNV = rs.getInt("maNV");
				int ChucVu = rs.getInt("ChucVu");
				String hoNV = rs.getString("hoNV");
				String tenNV = rs.getString("tenNV");
				String ngaySinh = rs.getString("ngaySinh");
				String ngayVaoLam = rs.getString("ngayVaoLam");
				
				i++;
				e = new Employee(maNV, a[ChucVu - 1], hoNV, tenNV, ngaySinh, ngayVaoLam);
				employee_list.put(i, e);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee_list;
	}
	
	public boolean Insert(Employee e) throws Exception {
		String valueInsert = "" + 
			"'" + e.getMaNV() + "', " +
			"'" + getRole(e.getChucVu()) + "', " +
			"'" + e.getHoNV() + "', " +
			"'" + e.getTenNV() + "', " +
			"'" + e.getNgaySinh() + "', " +
			"'" + e.getNgayVaoLam() + "'";
		
		return mcu.Insert("employee", valueInsert);
	}
	
	public boolean Update(Employee e) throws Exception {
		String valueInsert = "" +
				"ChucVu=" + getRole(e.getChucVu()) + ", " +
				"hoNV='" + e.getHoNV() + "', " +
				"tenNV='" + e.getTenNV() + "', " +
				"ngaySinh='" + e.getNgaySinh() + "', " +
				"ngayVaoLam='" + e.getNgayVaoLam() + "'" 
		;
		return mcu.Update("employee", valueInsert, "maNV='" + e.getMaNV() + "'");
	}
	
	private int getRole(String text) {
		for(int i = 1; i <= a.length; i++) {
			if(text.equals(a[i - 1]))
				return i;
		}
		return 0;
	}
}
