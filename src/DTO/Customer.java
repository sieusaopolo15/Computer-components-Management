package DTO;

public class Customer {
	private int maKH;
	private String hoTenKH, gender, date;
	
	public Customer() {
		
	}
	
	public Customer(int maKH,
					String hoTenKH, 
					String gender, 
					String date) {
		
		this.maKH = maKH;
		this.hoTenKH = hoTenKH;
		this.gender = gender;
		this.date = date;
	}
	
	//SETTER
		public void setMaKH(int maKH) {
			this.maKH = maKH;
		}
		
	
		public void setHoTenKH(String hoTenKH) {
			this.hoTenKH = hoTenKH;
		}
	
	
		public void setgioiTinh(String gender) {
			this.gender = gender;
		}
	
		public void setngaySinh(String date) {
			this.date = date;
		}
		
	//GETTER
		public int getMaKH() {
			return maKH;
		}

		public String getHoTenKH() {
			return hoTenKH;
		}

		public String getGender() {
			return gender;
		}

		public String getDate() {
			return date;
		}
}
