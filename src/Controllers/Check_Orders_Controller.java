package Controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;

import BUS.Check_Orders_BUS;
import BUS.Order_BUS;
import BUS.Order_Detail_BUS;
import BUS.Product_BUS;
import DTO.Check_Orders;
import DTO.Order;
import DTO.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Check_Orders_Controller implements Initializable {

	//TABLE VIEW & TABLE COLUMNS
		@FXML
		private TableView<Check_Orders> check_Orders_Table;
		
		@FXML
		private TableColumn<String, Check_Orders> nguoiNhan;
		
		@FXML
		private TableColumn<String, Check_Orders> tenSP;
		
		@FXML
		private TableColumn<Integer, Check_Orders> soLuong;
		
		@FXML
		private TableColumn<String, Check_Orders> donGia;
		
		@FXML
		private TableColumn<String, Check_Orders> thanhTien;
		
		@FXML
		private TableColumn<String, Check_Orders> ngayLap;
	//=============================================================
	//CÁC BIẾN KHÁC
		private HashMap<Integer, Check_Orders> orders;
		private HashMap<Integer, Order> list_Order;
		private HashMap<Integer, Product> products;
		
		private Check_Orders_BUS cob = new Check_Orders_BUS();
		private Order_Detail_BUS odb = new Order_Detail_BUS();
		private Product_BUS pb = new Product_BUS();
		private Order_BUS ob = new Order_BUS();
		
		private ObservableList<Check_Orders> oblist = FXCollections.observableArrayList();
		
		private DialogPane dp;
		private Alert alert;
		
		private int id;
	//=============================================================
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nguoiNhan.setCellValueFactory(new PropertyValueFactory<String, Check_Orders>("tenKH"));
		tenSP.setCellValueFactory(new PropertyValueFactory<String, Check_Orders>("tenSP"));
		soLuong.setCellValueFactory(new PropertyValueFactory<Integer, Check_Orders>("soLuong"));
		donGia.setCellValueFactory(new PropertyValueFactory<String, Check_Orders>("donGia"));
		thanhTien.setCellValueFactory(new PropertyValueFactory<String, Check_Orders>("thanhTien"));
		ngayLap.setCellValueFactory(new PropertyValueFactory<String, Check_Orders>("ngayLap"));
		
		check_Orders_Table.setItems(oblist);
	}
	
	private void Refresh() {
		ObservableList<Check_Orders> oblist = FXCollections.observableArrayList();
		orders = cob.getList(this.id);
		for(Integer i : orders.keySet()) {
			Check_Orders co = orders.get(i);
			oblist.add(co);
		}
		check_Orders_Table.setItems(oblist);
	}
	
	@FXML
	private void Cancle() throws Exception {
		Check_Orders co = check_Orders_Table.getSelectionModel().getSelectedItem();
		if(co != null) {
			list_Order = ob.getList();
			for(Integer i : list_Order.keySet()) {
				Order o = list_Order.get(i);
				if(co.getMaDH() == o.getMaDH()) {
					if(o.getStatus().equals("Đã Phê Duyệt")) {
						Error("Thông Báo Lỗi", "Đơn hàng đã phê duyệt, bạn không thể hủy được", "");
						return;
					}
					else {
						odb.Delete(o.getMaDH());
						ob.Delete(o.getMaDH());
						Information("Thông Báo", "Hủy Đơn Thành Công", "");
						Refresh();
						return;
					}
				}
			}
		}
	}
	
	private String setName(String id) {
		products = pb.getAll();
		for(Integer i : products.keySet()) {
			if(id.indexOf("" + i) != -1) {
				return products.get(i).getTenSP();
			}
		}
		return null;
	}
	
	public void Receiver(int id) {
		this.id = id;
		orders = cob.getList(id);
		
		for(Integer i : orders.keySet()) {
			Check_Orders co = orders.get(i);
			co.setTenSP(setName(co.getTenSP()));
			oblist.add(co);
		}
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
}
