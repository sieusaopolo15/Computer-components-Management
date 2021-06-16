package DTO;

import org.apache.commons.codec.digest.DigestUtils;

public class Account {
	private int maTK, maKH;
	private String tenTK, mk;
	private String status;
	
	public Account() {
		
	}
	public Account(int maTK, int maKH, String tenTK, String mk, String status) {
		this.maTK = maTK;
		this.maKH = maKH;
		this.tenTK = tenTK;
		this.mk = mk;
		this.status = status;
	}
	
	//SETTER
		public void setMaTK(int maTK) {
			this.maTK = maTK;
		}
		public void setMaKH(int maKH) {
			this.maKH = maKH;
		}
		public void setTenTK(String tenTK) {
			this.tenTK = tenTK;
		}
		public void setMk(String mk) {
			this.mk = mk;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	//GETTER
		public int getMaTK() {
			return maTK;
		}
		
		public int getMaKH() {
			return maKH;
		}
		
		public String getTenTK() {
			return tenTK;
		}
		public String getMk() {
			return mk;
		}
		
		public String getStatus() {
			return status;
		}
		
}
