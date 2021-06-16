package DTO;

public class Warehouse {
	private int maKho;
	private String tenKho;
	
	public Warehouse() {
		
	}
	
	public Warehouse(int maKho, String tenKho) {
		this.maKho = maKho;
		this.tenKho = tenKho;
	}
	//SETTER
		public void setMaKho(int maKho) {
			this.maKho = maKho;
		}
	
		public void setTenKho(String tenKho) {
			this.tenKho = tenKho;
		}
	//GETTER
		public int getMaKho() {
			return maKho;
		}

		public String getTenKho() {
			return tenKho;
		}
	
}
