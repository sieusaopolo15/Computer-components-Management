package DTO;

public class Chart_Sales_DTO {
	private int soLuong;
	private String loaiSP, tenSP, donGia, tongTien;

	public Chart_Sales_DTO() {
		
	}

	public Chart_Sales_DTO(int soLuong, String loaiSP, String tenSP, String donGia, String tongTien) {
		this.soLuong = soLuong;
		this.loaiSP = loaiSP;
		this.tenSP = tenSP;
		this.donGia = donGia;
		this.tongTien = tongTien;
	}
	
	//SETTER
		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}
	
		public void setLoaiSP(String loaiSP) {
			this.loaiSP = loaiSP;
		}
	
		public void setTenSP(String tenSP) {
			this.tenSP = tenSP;
		}
	
		public void setDonGia(String donGia) {
			this.donGia = donGia;
		}
		
		public void setTongTien(String tongTien) {
			this.tongTien = tongTien;
		}
		
	//GETTER

		public int getSoLuong() {
			return soLuong;
		}
	
		public String getLoaiSP() {
			return loaiSP;
		}
	
		public String getTenSP() {
			return tenSP;
		}
	
		public String getDonGia() {
			return donGia;
		}
		public String getTongTien() {
			return tongTien;
		}
	
	
}
