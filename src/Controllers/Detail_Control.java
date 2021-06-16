package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import BUS.Order_BUS;
import BUS.Order_Detail_BUS;
import BUS.Product_BUS;
import DTO.Order_Detail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Detail_Control implements Initializable {

	@FXML
	private AnchorPane anchor_Pane;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField address;
	
	@FXML
	private TextField tel;
	
	private DialogPane dp;
	private Alert alert;
	private String regex = "^[^0-9]*$";
	private String tel_Regex = "^[0-9]*$";
	
	private int maDH, maSP, soLuong, amount;
	private String donGia, tongTien;
	
	private Order_Detail_BUS odb = new Order_Detail_BUS();
	private Order_BUS ob = new Order_BUS();
	private Product_BUS pb = new Product_BUS();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		Launch.stage.focusedProperty();
		if(!anchor_Pane.isFocused()){
			anchor_Pane.requestFocus();
		}
	}
	
	@FXML
	private void Cancle(ActionEvent event) throws Exception {
		Stage stage = (Stage) anchor_Pane.getScene().getWindow();
		ob.Delete(this.maDH);
		pb.Update(this.maSP, this.amount, 6);
		stage.close();
	}
	
	@FXML
	private void Save(ActionEvent event) throws Exception {
		if(Validate()) {
			String name = this.name.getText();
			String address = this.address.getText();
			String tel = this.tel.getText();
			int a = this.amount - this.soLuong;
			
			Order_Detail od = new Order_Detail(this.maDH, this.maSP, name, address, tel, soLuong, this.donGia, this.tongTien);
			
			if(a == 0) {
				pb.Update(this.maSP, a, 7);
			}
			else if(a != 0) {
				pb.Update(this.maSP, a, 6);
			}
				
			
			odb.Insert(od);
			
			Stage stage = (Stage) anchor_Pane.getScene().getWindow();
			stage.close();
		}
	}
	
	public void TransferData(int maDH, int maSP, int soLuong, String donGia, String tongTien, int amount) {
		this.maDH = maDH;
		this.maSP = maSP;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.tongTien = tongTien;
		this.amount = amount;
	}
	
	//VALIDATE
		private boolean Validate() {
			if(name.getText().equals("")) {
				Error("Tên Người Nhận", "Họ và tên người nhận không được bỏ trống", "");
				name.requestFocus();
				return false;
			}
			else if(!name.getText().matches(regex)) {
				Error("Tên Người Nhận", "Họ và tên người nhận không hợp lệ", "");
				name.requestFocus();
				return false;
			}
			else if(address.getText().equals("")) {
				Error("Địa Chỉ Giao", "Địa chỉ giao hàng không được để trống", "");
				address.requestFocus();
				return false;
			}
			else if(tel.getText().equals("")) {
				Error("Số Điện Thoại", "Số Điện Thoại không được để trống", "");
				tel.requestFocus();
				return false;
			}
			else if(!tel.getText().matches(tel_Regex)) {
				Error("Số Điện Thoại", "Số Điện Thoại không hợp lệ", "");
				tel.requestFocus();
				return false;
			}
			return true;
		}
		
		private void Error(String errorName, String text, String contentText) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle(errorName);
			alert.setHeaderText(text);
			alert.setContentText(contentText);
			
			dp = alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("../GUI/CSS/style.css").toExternalForm());
			
			alert.showAndWait();
		}
	//==================================================================
}
