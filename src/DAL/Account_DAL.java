package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Account;

public class Account_DAL {
	HashMap<Integer, Account> accounts;
	MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	ResultSet rs = null;
	
	private String []a = {
			"Đã Phê Duyệt",
			"Chưa Phê Duyệt",
			"Đang Kích Hoạt", 
			"Vô Hiệu Hóa",
			"Bị Hủy"
	};	
	public Account_DAL() {
	}
	
	public HashMap<Integer, Account> getList() {
		accounts = new HashMap<>();
		int i = 0;
		try {
			rs = mcu.Select("accounts");
			while(rs.next()) {
				int maTK = rs.getInt("maTK");
				int maKH = rs.getInt("maKH");
				String tenTK = rs.getString("tenTK");
				String mk = rs.getString("mk");
				int status = rs.getInt("trangThai");
				Account a = new Account(maTK, maKH, tenTK, mk, setStatus(status));
				i++;
				accounts.put(i, a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}
	
	public boolean Insert(Account a) {
		String valueInsert = 
			"'" + a.getMaTK() + "', " +
			"'" + a.getMaKH() + "', " +
			"'" + a.getTenTK() + "', " + 
			"'" + a.getMk().toString() + "', " +
			"'" + getStatus(a.getStatus()) + "'"
		;
		try {
			return mcu.Insert("accounts", valueInsert);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean Update(Account a) throws Exception {
		String valueInsert = "" +
				"mk='" + a.getMk() + "', " +
				"trangThai='" + getStatus(a.getStatus()) + "'"; 
		;
		return mcu.Update("accounts", valueInsert, "maTK='" + a.getMaTK() + "'");
	}
	
	private String setStatus(int index) {
		return a[index - 1];
	}
	
	private int getStatus(String text) {
		for(int i = 0; i < a.length; i++) {
			if(text.equals(a[i]))
				return i + 1;
		}
		return 0;
	}
}
