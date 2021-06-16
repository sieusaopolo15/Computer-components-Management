package GUI.Frames.Function;

import java.awt.Desktop;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BUS.Product_BUS;
import Controllers.Launch;
import Controllers.Products_Management;
import DTO.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Product_Edit implements Initializable {
	
		@FXML
		private AnchorPane anchor_Pane;
		
	 	@FXML
	    private TextField maSP;

	    @FXML
	    private TextField tenSP;

	    @FXML
	    private ChoiceBox<String> loaiSP;

	    @FXML
	    private TextField thuongHieu;

	    @FXML
	    private TextField soLuong;
	    
	    //ĐƠN GIÁ
		    @FXML
		    private TextField donGiaA;
		    
		    @FXML
		    private TextField donGiaB;
		    
		    @FXML
		    private TextField donGiaC;
	    //
	    

	    @FXML
	    private ChoiceBox<String> status;
	    
	    private Alert alert;
	    private DialogPane dp;
	    
	private Stage stage;
	private String image;
	private Product_BUS pb = new Product_BUS();
	
	private String giaTien;
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	
	@FXML
	private void Save() throws Exception {
		
		if(Validate()) {
			Product p = new Product(
					Integer.parseInt(maSP.getText().toString()),
					tenSP.getText(),
					loaiSP.getSelectionModel().getSelectedItem().toString(),
					thuongHieu.getText(),
					this.image,
					Integer.parseInt(soLuong.getText().toString()),
					giaTien,
					status.getSelectionModel().getSelectedItem().toString()
			);
			pb.Update(p);
			Information("Cập Nhật", "Đã Cập Nhật Thành Công", "");
			this.stage = (Stage) anchor_Pane.getScene().getWindow();
			this.stage.close();
		}
	}
	
	@FXML
	private void Cancle(ActionEvent event) {
		stage = (Stage) anchor_Pane.getScene().getWindow();
		stage.close();
	}
	
	//RÀO LỖI NHÂP LIỆU
		private boolean Validate() {
			this.giaTien = donGiaA.getText() + "." + donGiaB.getText() + "." + donGiaC.getText();
			int count = 0;
			for(int i = 0; i < giaTien.split("\\.").length; i++) {
				if(!giaTien.split("\\.")[i].equals("")){
					count++;
				}
			}
				
			if(tenSP.getText().equals("")) {
				Error("Tên Sản Phẩm", "Tên sản phầm không được bỏ trống", "");
				tenSP.requestFocus();
				return false;
			}
			else if(thuongHieu.getText().equals("")) {
				Error("Thương Hiệu", "Thương hiệu của sản phẩm không được bỏ trống", "");
				thuongHieu.requestFocus();
				return false;
			}
			else if(soLuong.getText().equals("")) {
				Error("Số Lượng Sản Phảm", "Số lượng của sản phẩm không được bỏ trống", "");
				soLuong.requestFocus();
				return false;
			}
			else if(count < 3) {
				Error("Giá Tiền Sản Phẩm", "Giá tiền của sản phẩm không được bỏ trống", "");
				donGiaA.requestFocus();
				return false;
			}
			return true;
		}
	//=======================================================================
	
	public void createProduct(Product p) {
		this.image = p.getImage();
		
		ObservableList<String> a = FXCollections.observableArrayList(
					"CPU - Bộ Vi Xử Lý",
					"RAM",
					"Mainboard - Bo Mạch Chủ",
					"VGA - Card Màn hình",
					"Tản Nhiệt"
				);
		loaiSP.setItems(a);
		
		ObservableList<String> b = FXCollections.observableArrayList(
				"Còn Hàng",
				"Hết Hàng"
		);
		status.setItems(b);
		
		maSP.setText("00" + p.getMaSP());
		
		if(p.getMaSP() < 10) {
			maSP.setText("00" + p.getMaSP());
		}
		else {
			maSP.setText("0" + p.getMaSP());
		}
		tenSP.setText(p.getTenSP());
		loaiSP.getSelectionModel().select(p.getLoaiSP());
		thuongHieu.setText(p.getThuongHieu());
		soLuong.setText("" + p.getSoLuongConLai());
		
		//ĐƠN GIÁ
			String []s = p.getGiaTien().split("\\.");
			donGiaA.setText(s[0]);
			donGiaB.setText(s[1]);
			donGiaC.setText(s[2]);
		//
		
		status.getSelectionModel().select(p.getTrangThai());
	}
	
	private void Error(String errorName, String text, String contentText) {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(errorName);
		alert.setHeaderText(text);
		alert.setContentText(contentText);
		
		dp = alert.getDialogPane();
		dp.getStylesheets().add(getClass().getResource("../../CSS/style.css").toExternalForm());
		
		alert.showAndWait();
	}
	
	private void Information(String informationName, String text, String contentText) {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(informationName);
		alert.setHeaderText(text);
		alert.setContentText(contentText);
		
		dp = alert.getDialogPane();
		dp.getStylesheets().add(getClass().getResource("../../CSS/style.css").toExternalForm());
		
		alert.showAndWait();
	}
}
