package DTO;

public class Order {
	private int maDH, maKH, maNV;
	private String hoTenNV, ngayLap, tongTien, status;
	
	public Order() {
		
	}
	
	public Order(int maDH, int maKH, String hoTenNV, String ngayLap, String tongTien, String status) {
		this.maDH = maDH;
		this.maKH = maKH;
		this.hoTenNV = hoTenNV;
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
		this.status = status;
	}
	//SETTER
		public void setMaDH(int maDH) {
			this.maDH = maDH;
		}
	
		public void setMaKH(String ngayLap) {
			this.ngayLap = ngayLap;
		}
		
		public void setMaNV(int maNV) {
			this.maNV = maNV;
		}
		
		public void setHoTenNV(String hoTenNV) {
			this.hoTenNV = hoTenNV;
		}
	
		public void getTongTien(String tongTien) {
			this.tongTien = tongTien;
		}
	
		public void setStatus(String status) {
			this.status = status;
		}
	//GETTER
		public int getMaDH() {
			return maDH;
		}
		
		public int getMaKH() {
			return maKH;
		}
		
		public int getMaNV() {
			return maNV;
		}
		
		public String getHoTenNV() {
			return hoTenNV;
		}

		public String getNgayLap() {
			return ngayLap;
		}

		public String getTongTien() {
			return tongTien;
		}

		public String getStatus() {
			return status;
		}
		
}
