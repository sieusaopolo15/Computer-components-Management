package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import DTO.Product;

public class Product_DAL {
	MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	HashMap<Integer, Product> products;
	
	String []a = {"CPU - Bộ Vi Xử Lý"
				  , "RAM"
				  , "Mainboard - Bo Mạch Chủ"
				  , "VGA - Card Màn hình"
				  , "Tản Nhiệt" };
	
	String []b = {"Còn Hàng", "Hết Hàng"};
	
	public Product_DAL() {

	}
	
	public HashMap<Integer, Product> getList() {
		
		products = new HashMap<>();
			ResultSet rs;
			try {
				rs = mcu.select("SELECT * FROM products WHERE trangthai='6'");
				while(rs.next()) {
					int maSP = rs.getInt("maSP");
					String tenSP = rs.getString("tenSP");
					int loaiSP = rs.getInt("loaiSP");
					String thuongHieu = rs.getString("thuongHieu");
					String hinhAnh = rs.getString("hinhAnh");
					int soLuongConLai = rs.getInt("soLuongConLai");
					String giaTien = rs.getString("giaTien");
					int trangThai = rs.getInt("trangThai");
					
					Product p = new Product(maSP, tenSP, checkCategory(loaiSP), thuongHieu, hinhAnh, soLuongConLai, giaTien, checkStatus(trangThai));
					products.put(maSP, p);
				} 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return products;
	}
	
public HashMap<Integer, Product> getAll() {
		
		products = new HashMap<>();
			ResultSet rs;
			try {
				rs = mcu.select("SELECT * FROM products");
				while(rs.next()) {
					int maSP = rs.getInt("maSP");
					String tenSP = rs.getString("tenSP");
					int loaiSP = rs.getInt("loaiSP");
					String thuongHieu = rs.getString("thuongHieu");
					String hinhAnh = rs.getString("hinhAnh");
					int soLuongConLai = rs.getInt("soLuongConLai");
					String giaTien = rs.getString("giaTien");
					int trangThai = rs.getInt("trangThai");
					
					Product p = new Product(maSP, tenSP, checkCategory(loaiSP), thuongHieu, hinhAnh, soLuongConLai, giaTien, checkStatus(trangThai));
					products.put(maSP, p);
				} 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return products;
	}
	
	public boolean Insert(Product p) throws Exception{
		int category = 1;
		String text = p.getLoaiSP();
		
		if(text.equals("CPU - Bộ Vi Xử Lý")) {
			category = 1;
		}
		else if(text == "RAM") {
			category = 2;
		}
		else if(text.equals("Mainboard - Bo Mạch Chủ")) {
			category = 3;
		}
		else if(text.equals("VGA - Card Màn Hình")) {
			category = 4;
		}
		else if(text.equals("Tản Nhiệt")) {
			category = 5;
		}
		
		int status = 1;
		String text2 = p.getTrangThai();
		if(text2.equals("Còn Hàng")) {
			status = 6;
		}
		else if(text2.equals("Hết Hàng")) {
			status = 7;
		}
		
		String valueInsert = "" +
				"'" + p.getMaSP() + "', " +
				"'" + p.getTenSP() + "', " +
				"'" + category + "', " +
				"'" + p.getThuongHieu() + "', " +
				"'" + p.getImage() + "', " +
				"'" + p.getSoLuongConLai() + "', " +
				"'" + p.getGiaTien() + "', " + 
				"'" + status + "'";
		return mcu.Insert("products", valueInsert);
	}
	
	public boolean Update(Product p) throws Exception {
		int category = 1;
		String text = p.getLoaiSP();
		
		if(text.equals("CPU - Bộ Vi Xử Lý")) {
			category = 1;
		}
		else if(text == "RAM") {
			category = 2;
		}
		else if(text.equals("Mainboard - Bo Mạch Chủ")) {
			category = 3;
		}
		else if(text.equals("VGA - Card Màn Hình")) {
			category = 4;
		}
		else if(text.equals("Tản Nhiệt")) {
			category = 5;
		}
		
		int status = 1;
		String text2 = p.getTrangThai();
		if(text2.equals("Còn Hàng")) {
			status = 6;
		}
		else if(text2.equals("Hết Hàng")) {
			status = 7;
		}
		
		String valueInsert = "" +
				"tenSP='" + p.getTenSP() + "', " +
				"loaiSP='" + category + "', " +
				"thuongHieu='" + p.getThuongHieu() + "', " +
				"hinhAnh='" + p.getImage() + "', " +
				"soLuongConLai='" + p.getSoLuongConLai() + "', " +
				"giaTien='" + p.getGiaTien() + "', " + 
				"trangThai='" + status + "'";
		return mcu.Update("products", valueInsert, " maSP='" + p.getMaSP() + "'");
	}
	
	public boolean Update(int maSP, int amount, int status) throws Exception {
		String valueInsert = "" + 
				"soLuongConLai='" + amount + "', " +
				"trangThai='" + status + "'"
		;
		return mcu.Update("products", valueInsert, "maSP='" + maSP + "'");
	}
	
	public boolean Delete(int maSP) throws Exception {
		return mcu.Delete("products", " maSP='" + maSP +"'");
	}

	private String checkCategory(int index) {
		for(int i = 1; i <= a.length; i++) {
			if(index == i) {
				return a[i - 1];
			}
		}
		return null;
	}
	
	private String checkStatus(int index) {
		if(index == 6) 
			return "Còn Hàng";
		else
			return "Hết Hàng";
	}
}
