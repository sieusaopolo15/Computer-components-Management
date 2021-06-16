package DTO;

public class Product_Detail {
	private int maSP;
	private String chiTietSP;
	
	public Product_Detail() {
		
	}

	public Product_Detail(int maSP, String chiTietSP) {
		super();
		this.maSP = maSP;
		this.chiTietSP = chiTietSP;
	}
	//SETTER
		public void setMaSP(int maSP) {
			this.maSP = maSP;
		}
	
		public void setChiTietSP(String chiTietSP) {
			this.chiTietSP = chiTietSP;
		}
	//GETTER
	public int getMaSP() {
		return maSP;
	}

	public String getChiTietSP() {
		return chiTietSP;
	}
	
	
	
}
