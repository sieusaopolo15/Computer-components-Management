package BUS;

import java.util.HashMap;

import DAL.Employee_DAL;
import DTO.Employee;

public class Employee_BUS {
	HashMap<Integer, Employee> employee_list;
	Employee_DAL ed = new Employee_DAL();
	
	public Employee_BUS() {
		
	}
	
	public HashMap<Integer, Employee> getList(){
		employee_list = new HashMap<>();
		employee_list = ed.getList();
		return employee_list;
	}
	
	public boolean Insert(Employee e) throws Exception {
		return ed.Insert(e);
	}
	
	public boolean Update(Employee e) throws Exception {
		return ed.Update(e);
	}
}
