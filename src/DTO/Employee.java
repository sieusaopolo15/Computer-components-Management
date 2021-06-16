package DTO;

public class Employee {
	private int maNV;
	private String ChucVu, hoNV, tenNV, ngaySinh, ngayVaoLam;
	
	private String NVId;
	
	public Employee() {
		
	}
	
	public Employee(int maNV,
					String ChucVu,
					String hoNV, 
					String tenNV, 
					String ngaySinh, 
					String ngayVaoLam) {
		this.maNV = maNV;
		this.ChucVu = ChucVu;
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.ngayVaoLam = ngayVaoLam;
	}
	//SETTER
		public void setMaNV(int maNV) {
			this.maNV = maNV;
		}

		public void setChucVu(String ChucVu) {
			this.ChucVu = ChucVu;
		}
		
		public void setHoNV(String hoNV) {
			this.hoNV = hoNV;
		}

		public void setTenNV(String tenNV) {
			this.tenNV = tenNV;
		}

		public void setNgaySinh(String ngaySinh) {
			this.ngaySinh = ngaySinh;
		}
		public void setNgayVaoLam(String ngayVaoLam) {
			this.ngayVaoLam = ngayVaoLam;
		}
			
	//GETTER
		public int getMaNV() {
			return maNV;
		}
		
		public String getNVId() {
			return "" + maNV;
		}
		
		public String getChucVu() {
			return ChucVu;
		}
	
		public String getHoNV() {
			return hoNV;
		}
	
		public String getTenNV() {
			return tenNV;
		}
	
		public String getNgaySinh() {
			return ngaySinh;
		}
	
		public String getNgayVaoLam() {
			return ngayVaoLam;
		}
	
}
