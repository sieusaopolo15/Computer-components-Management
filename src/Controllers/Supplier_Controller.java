package Controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import BUS.Import_BUS;
import BUS.Supplier_BUS;
import DTO.Import;
import DTO.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Supplier_Controller implements Initializable {
	
	//CÁC BIẾN TRONG TableView & TableColumn
		@FXML
		private TableView<Supplier> supplier_Table;
		
		@FXML
		private TableColumn<Integer, Supplier> maNCC;
		
		@FXML
		private TableColumn<String, Supplier> tenNCC;
		
		@FXML
		private TableColumn<String, Supplier> sdt;
		
		@FXML
		private TableColumn<String, Supplier> address;
	//=====================================================

	//CÁC BIẾN TRONG BIỂU MẪU
		@FXML
		private TextField maNCC_Tf;
		
		@FXML
		private TextField tenNCC_Tf;
		
		@FXML
		private TextField sdt_Tf;
		
		@FXML
		private TextField address_Tf;
	//=====================================================
	
	//CÁC BIẾN KHÁC
		private HashMap<Integer, Supplier> suppliers;
		
		private Supplier_BUS sb = new Supplier_BUS();
		private Import_BUS ib = new Import_BUS();
		
		private ObservableList<Supplier> oblist = FXCollections.observableArrayList();
		
		private Alert alert;
		private DialogPane dp;
		
		private String regex = "^[0-9]+$";
	//=====================================================
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		suppliers = sb.getList();
		for(Integer i : suppliers.keySet()) {
			Supplier s = suppliers.get(i);
			oblist.add(s);
		}
		
		getSupplierID();
		
		maNCC.setCellValueFactory(new PropertyValueFactory<Integer, Supplier>("maNCC"));
		tenNCC.setCellValueFactory(new PropertyValueFactory<String, Supplier>("tenNCC"));
		sdt.setCellValueFactory(new PropertyValueFactory<String, Supplier>("SDT"));
		address.setCellValueFactory(new PropertyValueFactory<String, Supplier>("DiaChi"));
		
		supplier_Table.setItems(oblist);
		
		supplier_Table.setOnMousePressed(e -> {
			if(e.isPrimaryButtonDown() && e.getClickCount() == 2) {
				Supplier s = supplier_Table.getSelectionModel().getSelectedItem();
				maNCC_Tf.setText("" + s.getMaNCC());
				tenNCC_Tf.setText(s.getTenNCC());
				sdt_Tf.setText(s.getSDT());
				address_Tf.setText(s.getDiaChi());	
			}
		});
	};
	
	//LẤY MÃ NHÀ CUNG CẤP MỚI NHẤT
	private void getSupplierID() {
		int a = suppliers.size();
		if(a <= 9) {
			maNCC_Tf.setText("00" + (a + 1));
		}
		else {
			maNCC_Tf.setText("0" + (a + 1));
		}
	}
	
	//TẠO MỚI LẠI TABLE
	private void Renew() {
		ObservableList<Supplier> oblist = FXCollections.observableArrayList();
		
		suppliers = sb.getList();
		for(Integer i : suppliers.keySet()) {
			Supplier s = suppliers.get(i);
			oblist.add(s);
		}
		tenNCC_Tf.setText("");
		sdt_Tf.setText("");
		address_Tf.setText("");
		getSupplierID();
		
		supplier_Table.setItems(oblist);
	}
	
	//INSERT
		@FXML
		private void Insert() throws Exception {
			if(Validate()) {
				int a = Integer.parseInt(maNCC_Tf.getText());
				Supplier s = new Supplier(
						a, tenNCC_Tf.getText(), sdt_Tf.getText(), address_Tf.getText()
				);
				sb.Insert(s);
				Information("Thêm Dữ Liệu", "Thêm thông tin nhà cung cấp thành công", "");
				Renew();
			}
		}
		
		private boolean Validate() {
			if(tenNCC_Tf.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "Tên nhà cung cấp không được bỏ trống", "");
				tenNCC_Tf.requestFocus();
				return false;
			}
			else if(sdt_Tf.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "số điện thoại không được bỏ trống", "");
				sdt_Tf.requestFocus();
				return false;
			}
			else if(!sdt_Tf.getText().matches(regex)) {
				Error("Lỗi Nhập Liệu", "số điện thoại không hợp lệ", "");
				sdt_Tf.requestFocus();
				return false;
			}
			else if(address_Tf.getText().equals("")) {
				Error("Lỗi Nhập Liệu", "Địa chỉ liên lạc không được bỏ trống", "");
				address_Tf.requestFocus();
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
		
		private void Information(String informationName, String text, String contentText) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(informationName);
			alert.setHeaderText(text);
			alert.setContentText(contentText);
			
			dp = alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("../GUI/CSS/style.css").toExternalForm());
			
			alert.showAndWait();
		}
	//============================================================================================
	
	@FXML
	private void Update() throws Exception {
		if(Validate()) {
			int a = Integer.parseInt(maNCC_Tf.getText());
			Supplier s = new Supplier(
					a, tenNCC_Tf.getText(), sdt_Tf.getText(), address_Tf.getText()
			);
			sb.Update(s);
			Information("Thêm Dữ Liệu", "Cập nhật thông tin nhà cung cấp thành công", "");
			Renew();
		}
	}
	
	@FXML
	private void Delete() throws Exception {
		Supplier s = supplier_Table.getSelectionModel().getSelectedItem();
		if(s != null) {
			ib.Delete(s);
			sb.Delete(s);
			Information("Xóa Dữ Liệu", "Xóa Dữ Liệu Nhà Cung Cấp Thành Công", "");
			Renew();
		}
	}
	
	//TẠO MỚI CÁC TRƯỜNG NHẬP LIỆU
	@FXML
	private void Refresh() {
		getSupplierID();
		tenNCC_Tf.setText("");
		sdt_Tf.setText("");
		address_Tf.setText("");
	}
}
