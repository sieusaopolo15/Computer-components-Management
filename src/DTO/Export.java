package DTO;

public class Export {
	private int maPhieu, maSP, maNV, soLuongXuat;
	private String HotenNV, ngayXuat, lyDoXuat;
	
	public Export() {
		
	}

	public Export(int maPhieu, int maSP, int maNV, int soLuongXuat, String ngayXuat, String lyDoXuat) {
		this.maPhieu = maPhieu;
		this.maSP = maSP;
		this.maNV = maNV;
		this.soLuongXuat = soLuongXuat;
		this.ngayXuat = ngayXuat;
		this.lyDoXuat = lyDoXuat;
	}
	//SETTER
		public void setMaPhieu(int maPhieu) {
			this.maPhieu = maPhieu;
		}
	
		public void setMaSP(int maSP) {
			this.maSP = maSP;
		}
	
		public void setMaNV(int maNV) {
			this.maNV = maNV;
		}
	
		public void setSoLuongXuat(int soLuongXuat) {
			this.soLuongXuat = soLuongXuat;
		}
	
		public void setHotenNV(String hotenNV) {
			HotenNV = hotenNV;
		}
	
		public void setNgayXuat(String ngayXuat) {
			this.ngayXuat = ngayXuat;
		}
	
		public void setLydoXuat(String lyDoXuat) {
			this.lyDoXuat = lyDoXuat;
		}
		
	//GETTER
		public int getMaPhieu() {
			return maPhieu;
		}

		public int getMaSP() {
			return maSP;
		}

		public int getMaNV() {
			return maNV;
		}

		public int getSoLuongXuat() {
			return soLuongXuat;
		}

		public String getHotenNV() {
			return HotenNV;
		}

		public String getNgayXuat() {
			return ngayXuat;
		}

		public String getLydoXuat() {
			return lyDoXuat;
		}
}
