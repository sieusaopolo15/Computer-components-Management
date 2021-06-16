package BUS;

import java.util.HashMap;

import DAL.Product_DAL;
import DTO.Product;

public class Product_BUS {
	HashMap<Integer, Product> products;
	Product_DAL pd = new Product_DAL();
	
	public Product_BUS() {
		
	}
	
	public HashMap<Integer, Product> getList(){
		return pd.getList();
	}
	
	public HashMap<Integer, Product> getAll(){
		return pd.getAll();
	}
	
	public boolean Insert(Product p) throws Exception {
		return pd.Insert(p);
	}
	
	public boolean Update(Product p) throws Exception {
		return pd.Update(p);
	}
	
	public boolean Update(int maSP, int amount, int status) throws Exception{
		return pd.Update(maSP, amount, status);
	}
	
	public boolean Delete(int maSP) throws Exception {
		return pd.Delete(maSP);
	}
}
