package DAL;

import java.sql.ResultSet;
import java.util.HashMap;

import DTO.Product_Detail;

public class Product_Detail_DAL {
	ResultSet rs;
	HashMap<Integer, Product_Detail> product_details;
	MyConnectUnit mcu = new MyConnectUnit("localhost", "root", "", "cnpm");
	
	public Product_Detail_DAL() {
		createList();
	}
	
	private void createList() {
		product_details = new HashMap<>();
		try {
			rs = mcu.Select("products_detail");
			while(rs.next()) {
				int maSP = rs.getInt("maSP");
				String chiTietSP = rs.getString("chiTietSP");
				Product_Detail pd = new Product_Detail(maSP, chiTietSP);
				product_details.put(maSP, pd);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<Integer, Product_Detail> getList(){
		return product_details;
	}
	
	public boolean Insert(Product_Detail pd) throws Exception{
		String valueInsert = "" +
				"'" + pd.getMaSP() + "', " +
				"'" + pd.getChiTietSP() + "'"
		;
		return mcu.Insert("products_detail", valueInsert);
	}
}
