package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Import;
import DTO.Supplier;

public class Import_DAL {
	
	private HashMap<Integer, Import> imports;
	private MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	
	public Import_DAL() {
		
	}
	
	public HashMap<Integer, Import> getList(){
		imports = new HashMap<>();
		try {
			ResultSet rs = mcu.Select("import");
			while(rs.next()) {
				int maPhieu = rs.getInt("maPhieu");
				int maKho = rs.getInt("maKho");
				int maSP = rs.getInt("maSP");
				int maNCC = rs.getInt("maNCC");
				int maNV = rs.getInt("maNV");
				int soLuong = rs.getInt("soLuong");
				String ngayNhap = rs.getString("ngayNhap");
				String donGia = rs.getString("donGia");
				String tongTien = rs.getString("tongTien");
				
				Import i = new Import(
						maPhieu, maKho, maSP, maNCC, maNV, 
						soLuong, ngayNhap, donGia, tongTien
				);
				
				imports.put(maPhieu, i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imports;
	}
	
	public HashMap<Integer, Import> getList(int id){
		imports = new HashMap<>();
		try {
			ResultSet rs = mcu.Select("import", "maKho='" + id + "'");
			while(rs.next()) {
				int maPhieu = rs.getInt("maPhieu");
				int maKho = rs.getInt("maKho");
				int maSP = rs.getInt("maSP");
				int maNCC = rs.getInt("maNCC");
				int maNV = rs.getInt("maNV");
				int soLuong = rs.getInt("soLuong");
				String ngayNhap = rs.getString("ngayNhap");
				String donGia = rs.getString("donGia");
				String tongTien = rs.getString("tongTien");
				
				Import i = new Import(
						maPhieu, maKho, maSP, maNCC, maNV, 
						soLuong, ngayNhap, donGia, tongTien
				);
				
				imports.put(maPhieu, i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imports;
	}
	
	public boolean Insert(Import i) throws Exception {
		String valueInsert = "" +
				"'" + i.getMaPhieu() + "', " +
				"'" + i.getMaKho() + "', " +
				"'" + i.getMaSP() + "', " +
				"'" + i.getMaNCC() + "', " +
				"'" + i.getMaNV() + "', " +
				"'" + i.getSoLuong() + "', " +
				"'" + i.getNgayNhap() + "', " +
				"'" + i.getDonGia() + "'," +
				"'" + i.getTongTien() + "'"
		;
		return mcu.Insert("import", valueInsert);
	}
	
	public boolean Update(Import i) throws Exception {
		String valueInsert = "" + 
			"soLuong='" + i.getSoLuong() + "', " +
			"tongTien='" + i.getTongTien() + "'"
		;
		
		return mcu.Update("import", valueInsert, "maPhieu='" + i.getMaPhieu() + "'");
	}
	
	public boolean Delete(int id) throws Exception{
		return mcu.Delete("import", "maPhieu='" + id + "'");
	}
	
	public boolean Delete(Supplier s) throws Exception{
		return mcu.Delete("import", "maNCC='" + s.getMaNCC() + "'");
	}
}
