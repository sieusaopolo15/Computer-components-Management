package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Supplier;

public class Supplier_DAL {
	private HashMap<Integer, Supplier> suppliers;
	private MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	
	public Supplier_DAL() {
		
	}
	
	public HashMap<Integer, Supplier> getList(){
		suppliers = new HashMap<>();
		try {
			ResultSet rs = mcu.Select("supplier");
			while(rs.next()) {
				int maNCC = rs.getInt("maNCC");
				String tenNCC = rs.getString("tenNCC");
				String SDT = rs.getString("SDT");
				String DiaChi = rs.getString("DiaChi");
				
				Supplier s = new Supplier(maNCC, tenNCC, SDT, DiaChi);
				suppliers.put(maNCC, s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return suppliers;
	}
	
	public boolean Insert(Supplier s) throws Exception {
		String valueInsert = "" +
				"'" + s.getMaNCC() + "', " +
				"'" + s.getTenNCC() + "', " +
				"'" + s.getSDT() + "', " + 
				"'" + s.getDiaChi() + "'"
		;
		return mcu.Insert("supplier", valueInsert);
	}
	
	public boolean Update(Supplier s) throws Exception {
		String valueInsert = "" +
				"tenNCC='" + s.getTenNCC() + "', " + 
				"SDT='" + s.getSDT() + "', " +
				"DiaChi='" + s.getDiaChi() + "'"
		;
		
		return mcu.Update("supplier", valueInsert, "maNCC='" + s.getMaNCC() + "'");
	}
	
	public boolean Delete(Supplier s) throws Exception{
		return mcu.Delete("supplier", "maNCC='" + s.getMaNCC() + "'");
	}
}
