package BUS;

import java.util.HashMap;

import DAL.Product_Detail_DAL;
import DTO.Product_Detail;

public class Product_Detail_BUS {
	//HashMap<Integer, Product_Detail> product_details;
	Product_Detail_DAL pdd = new Product_Detail_DAL();
	
	public Product_Detail_BUS() {
		
	}
	
	public HashMap<Integer, Product_Detail> getList(){
		return pdd.getList();
	}
	
	public boolean Insert(Product_Detail pd) throws Exception{
		return pdd.Insert(pd);
	}
}
