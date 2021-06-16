package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Export;

public class Export_DAL {
	
	private HashMap<Integer, Export> exports;
	private MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "" , "cnpm");
	
	public Export_DAL() {
		
	}
	
	public HashMap<Integer, Export> getList(){
		exports = new HashMap<>();
		try {
			ResultSet rs = mcu.Select("export");
			while(rs.next()) {
				int maPhieu = rs.getInt("maPhieu");
				int maSP = rs.getInt("maSP");
				int maNV = rs.getInt("maNV");
				int soLuongXuat = rs.getInt("soLuongXuat");
				String ngayXuat = rs.getString("ngayXuat");
				String lyDoXuat = rs.getString("lyDoXuat");
				
				Export e = new Export(
						maPhieu, maSP, maNV,
						soLuongXuat, ngayXuat, lyDoXuat
				);
				
				exports.put(maPhieu, e);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exports;
	}
	
	public boolean Insert(Export e) throws Exception {
		String valueInsert = "" +
				"'" + e.getMaPhieu() + "', " +
				"'" + e.getMaSP() + "', " +
				"'" + e.getMaNV() + "', " +
				"'" + e.getSoLuongXuat() + "', " +
				"'" + e.getNgayXuat() + "', " +
				"'" + e.getLydoXuat() + "'" 
		;
		return mcu.Insert("export", valueInsert);
	}
}
