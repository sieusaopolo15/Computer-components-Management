package DTO;

public class Order_Detail {
	private int maDH, maSP, soLuong;
	private String nguoiNhan, diaChi, donGia, tongTien, sdt;
	
	public Order_Detail() {
		
	}
	
	public Order_Detail(int maDH, int maSP, String nguoiNhan, String diaChi, String sdt, int soLuong, String donGia, String tongTien) {
		this.maDH = maDH;
		this.maSP = maSP;
		this.nguoiNhan = nguoiNhan;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.tongTien = tongTien;
	}
	//SETTER
		public void setMaDH(int maDH) {
			this.maDH = maDH;
		}
	
		public void setMaSP(int maSP) {
			this.maSP = maSP;
		}
	
		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}
	
		public void setnguoiNhan(String nguoiNhan) {
			this.nguoiNhan = nguoiNhan;
		}
	
		public void setDiaChi(String diaChi) {
			this.diaChi = diaChi;
		}
		
		public void setDonGia(String donGia) {
			this.donGia = donGia;
		}
	
		public void settongTien(String tongTien) {
			this.tongTien = tongTien;
		}
	//GETTER
		public int getMaDH() {
			return maDH;
		}

		public int getMaSP() {
			return maSP;
		}

		public int getSoLuong() {
			return soLuong;
		}

		public String getNguoiNhan() {
			return nguoiNhan;
		}
		
		public String getSDT() {
			return sdt;
		}

		public String getDiaChi() {
			return diaChi;
		}
		
		public String getDonGia() {
			return donGia;
		}

		public String gettongTien() {
			return tongTien;
		}
}
