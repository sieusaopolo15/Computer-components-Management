package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Account_Employee;

public class Account_Employee_DAL {
	private HashMap<Integer, Account_Employee> accounts;
	private MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	
	private String []a = {
			"Quản Lý", "Thu Ngân", "Kiểm Kho", "Nhân Viên Hỗ Trợ", "Nhân Viên Giao Hàng"
	};
		
	
	public Account_Employee_DAL() {
		createList();
	}
	
	private void createList() {
		accounts = new HashMap<>();
		try {
			ResultSet rs = mcu.Select("accounts_employee");
			while(rs.next()) {
				int maTK = rs.getInt("maTK");
				int maNV = rs.getInt("maNV");
				String tenTK = rs.getString("tenTK");
				String mk = rs.getString("mk");
				int Quyen = rs.getInt("Quyen");
				
				Account_Employee ae = new Account_Employee(
						maTK,
						maNV,
						a[Quyen - 1],
						tenTK, 
						mk
				);
				accounts.put(maTK, ae);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<Integer, Account_Employee> getList(){
		return accounts;
	}
	
	
	public boolean Insert(Account_Employee ae) throws Exception {
		
		String valueInsert = "" +
			"'" + ae.getMaTK() + "', " +
			"'" + ae.getMaNV() + "', " +
			"'" + ae.getTenTK() + "', " +
			"'" + ae.getMk() + "', " +
			"'" + getRole(ae.getQuyen()) + "'"
		;
		return mcu.Insert("accounts_employee", valueInsert);
	}
	
	private int getRole(String text) {
		for(int i = 1; i <= a.length; i++) {
			if(text.equals(a[i - 1])) {
				return i;
			}
		}
		return 5;
	}
}
