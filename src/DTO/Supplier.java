package DTO;

public class Supplier {
	private int maNCC;
	private String tenNCC, SDT, DiaChi;
	
	public Supplier() {
		
	}

	public Supplier(int maNCC, String tenNCC, String SDT, String DiaChi) {
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.SDT = SDT;
		this.DiaChi = DiaChi;
	}
	//SETTER
		public void setMaNCC(int maNCC) {
			this.maNCC = maNCC;
		}
	
		public void setTenNCC(String tenNCC) {
			this.tenNCC = tenNCC;
		}
		
		public void setSDT(String SDT) {
			this.SDT = SDT;
		}
		
		public void setDiaChi(String DiaChi) {
			this.DiaChi = DiaChi;
		}
	//GETTER
		public int getMaNCC() {
			return maNCC;
		}

		public String getTenNCC() {
			return tenNCC;
		}
		
		public String getSDT() {
			return SDT;
		}
		
		public String getDiaChi() {
			return DiaChi;
		}
}
