package DTO;

public class Import {
	private int maPhieu, maKho, maSP, maNCC, maNV, soLuong;
	private String HoTenNV, tenNCC, ngayNhap, donGia, tongTien;
	
	public Import() {
		
	}

	public Import(int maPhieu, int maKho, int maSP, int maNCC, int maNV, int soLuong, String ngayNhap, String donGia, String tongTien) {
		this.maPhieu = maPhieu;
		this.maKho = maKho;
		this.maSP = maSP;
		this.maNCC = maNCC;
		this.maNV = maNV;
		this.soLuong = soLuong;
		this.ngayNhap = ngayNhap;
		this.donGia = donGia;
		this.tongTien = tongTien;
	}
	
	//SETTER
		public void setMaPhieu(int maPhieu) {
			this.maPhieu = maPhieu;
		}
	
		public void setMaKho(int maKho) {
			this.maKho = maKho;
		}
	
		public void setMaSP(int maSP) {
			this.maSP = maSP;
		}
	
		public void setMaNCC(int maNCC) {
			this.maNCC = maNCC;
		}
		
		public void setTenNCC(String name) {
			this.tenNCC = name;
		}
	
		public void setMaNV(int maNV) {
			this.maNV = maNV;
		}
	
		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}
		
		public void setHoTenNV(String HoTenNV) {
			this.HoTenNV = HoTenNV;
		}
	
		public void setNgayNhap(String ngayNhap) {
			this.ngayNhap = ngayNhap;
		}
		
		public void setDonGia(String donGia) {
			this.donGia = donGia;
		}
		
		public void setTongTien(String tongTien) {
			this.tongTien = tongTien;
		}
		
	//GETTER
		public int getMaPhieu() {
			return maPhieu;
		}

		public int getMaKho() {
			return maKho;
		}

		public int getMaSP() {
			return maSP;
		}

		public int getMaNCC() {
			return maNCC;
		}
		
		public String getTenNCC() {
			return tenNCC;
		}

		public int getMaNV() {
			return maNV;
		}

		public int getSoLuong() {
			return soLuong;
		}

		public String getNgayNhap() {
			return ngayNhap;
		}
		
		public String getDonGia() {
			return donGia;
		}
		
		public String getTongTien() {
			return tongTien;
		}
	
	
}
