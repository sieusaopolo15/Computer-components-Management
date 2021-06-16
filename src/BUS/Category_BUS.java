package BUS;

import java.util.HashMap;

import DAL.Category_DAL;
import DTO.Category;

public class Category_BUS {
	private HashMap<Integer, Category> categories;
	private Category_DAL cd = new Category_DAL();

	public Category_BUS() {
		
	}
	
	public HashMap<Integer, Category> getList(){
		categories = cd.getList();
		return categories;
	}
}
