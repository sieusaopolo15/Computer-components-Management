package DTO;

public class Check_Orders {
	private int maDH, maKH, soLuong;
	private String tenKH, tenSP, ngayLap, donGia, thanhTien;
	
	public Check_Orders(int maDH, int maKH, String tenKH, String tenSP, int soLuong, String donGia,
			String thanhTien, String ngayLap) {
		this.maDH = maDH;
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.tenSP = tenSP;
		this.ngayLap = ngayLap;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
	}

	//SETTER
		public void setMaDH(int maDH) {
			this.maDH = maDH;
		}
	
		public void setMaKH(int maKH) {
			this.maKH = maKH;
		}
	
		public void setTenKH(String tenKH) {
			this.tenKH = tenKH;
		}
	
		public void setTenSP(String tenSP) {
			this.tenSP = tenSP;
		}
	
		public void setNgayLap(String ngayLap) {
			this.ngayLap = ngayLap;
		}
	
		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}
	
		public void setDonGia(String donGia) {
			this.donGia = donGia;
		}
	
		public void setThanhTien(String thanhTien) {
			this.thanhTien = thanhTien;
		}
	//GETTER
		public int getMaDH() {
			return maDH;
		}
		
		public int getMaKH() {
			return maKH;
		}

		public String getTenKH() {
			return tenKH;
		}

		public String getTenSP() {
			return tenSP;
		}

		public String getNgayLap() {
			return ngayLap;
		}

		public int getSoLuong() {
			return soLuong;
		}

		public String getDonGia() {
			return donGia;
		}

		public String getThanhTien() {
			return thanhTien;
		}
		
	
}
