package DTO;

public class Category {
	private int maLoai;
	private String tenLoai;
	
	public Category() {
		
	}

	public Category(int maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}

	//SETTER
		public void setMaLoai(int maLoai) {
			this.maLoai = maLoai;
		}
	
		public void setTenLoai(String tenLoai) {
			this.tenLoai = tenLoai;
		}

	//GETTER
	public int getMaLoai() {
		return maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}
	
}
