package Controllers;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import BUS.Order_Detail_BUS;
import DTO.Order_Detail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Order_Detail_Controller implements Initializable {

	@FXML
	private AnchorPane anchor_Pane;
	
	//TABLE VIEW & TABLE COLUMNS
		@FXML
		private TableView<Order_Detail> order_Detail_Table;
		
		@FXML
		private TableColumn<Integer, Order_Detail> maDH;
		
		@FXML
		private TableColumn<Integer, Order_Detail> maSP;
		
		@FXML
		private TableColumn<String, Order_Detail> receiver;
		
		@FXML
		private TableColumn<String, Order_Detail> address;
		
		@FXML
		private TableColumn<String, Order_Detail> telephone;
		
		@FXML
		private TableColumn<Integer, Order_Detail> amount;
		
		@FXML
		private TableColumn<String, Order_Detail> price;
		
		@FXML
		private TableColumn<String, Order_Detail> money;
	//==================================================
		
	//OTHER VARIABLES
		private HashMap<Integer, Order_Detail> details;
		
		private Order_Detail_BUS odb = new Order_Detail_BUS();
		
		private ObservableList<Order_Detail> oblist = FXCollections.observableArrayList();
		
		private int id;
	//==================================================
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		maDH.setCellValueFactory(new PropertyValueFactory<Integer, Order_Detail>("maDH"));
		maSP.setCellValueFactory(new PropertyValueFactory<Integer, Order_Detail>("maSP"));
		receiver.setCellValueFactory(new PropertyValueFactory<String, Order_Detail>("nguoiNhan"));
		address.setCellValueFactory(new PropertyValueFactory<String, Order_Detail>("diaChi"));
		telephone.setCellValueFactory(new PropertyValueFactory<String, Order_Detail>("sdt"));
		amount.setCellValueFactory(new PropertyValueFactory<Integer, Order_Detail>("soLuong"));
		price.setCellValueFactory(new PropertyValueFactory<String, Order_Detail>("donGia"));
		money.setCellValueFactory(new PropertyValueFactory<String, Order_Detail>("tongTien"));
		
		
	}
	
	
	@FXML
	private void goBack() {
		Stage stage = (Stage) anchor_Pane.getScene().getWindow();
		stage.close();
	}
	
	public void getID(int id) {
		details = odb.getList(id);
		for(Integer i : details.keySet()) {
			Order_Detail od = details.get(i);
			oblist.add(od);
		}
		order_Detail_Table.setItems(oblist);
	}
}
