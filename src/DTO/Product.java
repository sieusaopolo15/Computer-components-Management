package DTO;


import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Product {
	
	private int maSP, soLuongConLai;
	private ImageView hinhAnh;
	private String tenSP, loaiSP, thuongHieu, giaTien, trangThai;
	private String image;
	
	public Product() {
		
	}

	public Product(int maSP,
				   String tenSP, 
				   String loaiSP, 
				   String thuongHieu, 
				   String hinhAnh, 
				   int soLuongConLai,
				   String giaTien,
				   String trangThai
				  ) {
		this.maSP = maSP;
		this.trangThai = trangThai;
		this.tenSP = tenSP;
		this.loaiSP = loaiSP;
		this.thuongHieu = thuongHieu;
		Image image = new Image(getClass().getResourceAsStream(hinhAnh));
		
		this.hinhAnh = new ImageView(image);
		this.image = hinhAnh;
		
		this.soLuongConLai = soLuongConLai;
		this.giaTien = giaTien;
	}
	//SETTER
		public void setMaSP(int maSP) {
			this.maSP = maSP;
		}
		
		public void setTrangThai(String trangThai) {
			this.trangThai = trangThai;
		}
	
		public void setTenSP(String tenSP) {
			this.tenSP = tenSP;
		}
	
		public void setLoaiSP(String loaiSP) {
			this.loaiSP = loaiSP;
		}
	
		public void setThuongHieu(String thuongHieu) {
			this.thuongHieu = thuongHieu;
		}
	
		public void setHinhAnh(String hinhAnh) {
			this.hinhAnh = new ImageView(new Image(getClass().getResourceAsStream(hinhAnh)));
		}
	
		public void setSoLuongConLai(int soLuongConLai) {
			this.soLuongConLai = soLuongConLai;
		}
	
		public void setGiaTien(String giaTien) {
			this.giaTien = giaTien;
		}
	//GETTER
		public int getMaSP() {
			return maSP;
		}
		
		public String getID() {
			return "" + maSP;
		}
		
		public String getTrangThai() {
			return trangThai;
		}

		public String getTenSP() {
			return tenSP;
		}

		public String getLoaiSP() {
			return loaiSP;
		}

		public String getThuongHieu() {
			return thuongHieu;
		}

		public ImageView getHinhAnh() {
			return hinhAnh;
		}
		
		public String getImage() {
			return this.image;
		}

		public int getSoLuongConLai() {
			return soLuongConLai;
		}

		public String getGiaTien() {
			return giaTien;
		}
		
}
