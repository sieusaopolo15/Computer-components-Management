package DTO;

public class Account_Employee {
	private int maTK, maNV;
	private String Quyen, tenTK, mk;
	
	public Account_Employee() {
		
	}
	
	public Account_Employee(int maTK,
							int maNV,
							String Quyen,
							String tenTK,
							String mk) {
		this.maTK = maTK;
		this.maNV = maNV;
		this.Quyen = Quyen;
		this.tenTK = tenTK;
		this.mk = mk;
	}
	//SETTER
		public void setMaTK(int maTK) {
			this.maTK = maTK;
		}
		
		public void setMaNV(int maNV) {
			this.maNV = maNV;
		}
	
		public void setQuyen(String quyen) {
			Quyen = quyen;
		}
	
		public void setTenTK(String tenTK) {
			this.tenTK = tenTK;
		}
	
		public void setMk(String mk) {
			this.mk = mk;
		}
	//GETTER
		public int getMaTK() {
			return maTK;
		}
		
		public int getMaNV() {
			return maNV;
		}

		public String getQuyen() {
			return Quyen;
		}

		public String getTenTK() {
			return tenTK;
		}

		public String getMk() {
			return mk;
		}
		
}
