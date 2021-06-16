package BUS;

import java.util.HashMap;

import DAL.Import_DAL;
import DTO.Import;
import DTO.Supplier;

public class Import_BUS {
	private HashMap<Integer, Import> imports;
	private Import_DAL id = new Import_DAL();
	
	public Import_BUS() {
		
	}
	
	public HashMap<Integer, Import> getList(){
		imports = id.getList();
		return imports;
	}
	
	public HashMap<Integer, Import> getList(int id){
		imports = this.id.getList(id);
		return imports;
	}
	
	public boolean Insert(Import i) throws Exception {
		return id.Insert(i);
	}
	
	public boolean Update(Import i) throws Exception {
		return id.Update(i);
	}
	
	public boolean Delete(int id) throws Exception {
		return this.id.Delete(id);
	}
	
	public boolean Delete(Supplier s) throws Exception{
		return id.Delete(s);
	}
}
