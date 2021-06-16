package Controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import BUS.Customer_BUS;
import DTO.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class User_Detail_Controller implements Initializable {

	@FXML
	private AnchorPane anchor_Pane;
	
	@FXML
	private TableView<Customer> user_Detail_Table;
	
	@FXML
	private TableColumn<Integer, Customer> maKH;
	
	@FXML
	private TableColumn<String, Customer> hoTenKH;
	
	@FXML
	private TableColumn<String, Customer> gioiTinh;
	
	@FXML
	private TableColumn<String, Customer> ngaySinh;
	
	@FXML
	private HashMap<Integer, Customer> customers;
	
	private ObservableList<Customer> oblist = FXCollections.observableArrayList();
	
	private Customer_BUS cb = new Customer_BUS();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		maKH.setCellValueFactory(new PropertyValueFactory<Integer, Customer>("maKH"));
		hoTenKH.setCellValueFactory(new PropertyValueFactory<String, Customer>("hoTenKH"));
		gioiTinh.setCellValueFactory(new PropertyValueFactory<String, Customer>("gender"));
		ngaySinh.setCellValueFactory(new PropertyValueFactory<String, Customer>("date"));
	}
	
	public void receiver(int id) {
		customers = cb.getList();
		for(Integer i : customers.keySet()) {
			if(id == i) {
				Customer c = customers.get(i);
				oblist.add(c);
			}
		}
		
		user_Detail_Table.setItems(oblist);
	}

	@FXML
	private void Close() {
		Stage stage = (Stage) anchor_Pane.getScene().getWindow();
		stage.close();
	}
}
