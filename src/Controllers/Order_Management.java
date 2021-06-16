package Controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import BUS.Employee_BUS;
import BUS.Order_BUS;
import BUS.Order_Detail_BUS;
import DTO.Employee;
import DTO.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Order_Management implements Initializable {
	
	
	private Order_BUS ob = new Order_BUS();
	
	//TABLE VIEW & TABLE COLUMN
		@FXML
		private TableView<Order> order_Table;
		
		@FXML
		private TableColumn<Order, Integer> maDH;
		
		@FXML
		private TableColumn<Order, Integer> maKH;
		
		@FXML
		private TableColumn<Order, String> hoTenNV;
		
		@FXML
		private TableColumn<Order, String> ngayLap;
		
		@FXML 
		private TableColumn<Order, String> tongTien;
		
		@FXML
		private TableColumn<Order, String> trangThai;
	//===================================================
		
	@FXML
	private JFXTextField search_Box;
		
	//CHOICE BOX
		@FXML
		private ChoiceBox<String> NV;
		
		@FXML
		private ChoiceBox<String> Status;
	//====================================================
	
	//CÁC BIẾN KHÁC
		private HashMap<Integer, Order> orders;
		private HashMap<Integer, Employee> employees;
		private HashMap<Integer, String> em = new HashMap<>();;
		
		private ObservableList<Order> oblist = FXCollections.observableArrayList();
		private ObservableList<String> role = FXCollections.observableArrayList();
		private ObservableList<String> status_Name = FXCollections.observableArrayList(
				"Đã Phê Duyệt",
				"Chưa Phê Duyệt",
				"Đang Kích Hoạt",
				"Vô Hiệu Hóa"
		);
		
		private Employee_BUS eb = new Employee_BUS();
		private Order_Detail_BUS odb = new Order_Detail_BUS();
		
		
		
		private ArrayList<Integer> IDs;
	//===================================================
	
	@Override 
	public void initialize(URL url, ResourceBundle rb) {
		orders = ob.getList();
		Tao_HoTenNV();
		getItems(NV, role);
		Tao_TrangThai();
		
		for(Integer i : orders.keySet()) {
			Order o = orders.get(i);
			oblist.add(o);
		}
		
		maDH.setCellValueFactory(new PropertyValueFactory<Order, Integer>("maDH"));
		maKH.setCellValueFactory(new PropertyValueFactory<Order, Integer>("maKH"));
		hoTenNV.setCellValueFactory(new PropertyValueFactory<Order, String>("hoTenNV"));
		ngayLap.setCellValueFactory(new PropertyValueFactory<Order, String>("ngayLap"));
		tongTien.setCellValueFactory(new PropertyValueFactory<Order, String>("tongTien"));
		trangThai.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
		
		order_Table.setItems(oblist);
	}
	
	@FXML 
	private void Search(KeyEvent event) {
		
		order_Table.getItems().stream().filter(item -> item.getHoTenNV().equals(search_Box.getText())).findAny().ifPresent(item -> {
			order_Table.getSelectionModel().select(item);
			order_Table.scrollTo(item);
		});
		
		order_Table.getItems().stream().filter(item -> item.getNgayLap().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
			order_Table.getSelectionModel().select(item);
			order_Table.scrollTo(item);
		});
		
		order_Table.getItems().stream().filter(item -> item.getStatus().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
			order_Table.getSelectionModel().select(item);
			order_Table.scrollTo(item);
		});
		
		order_Table.getItems().stream().filter(item -> ("" + item.getMaDH()).indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
			order_Table.getSelectionModel().select(item);
			order_Table.scrollTo(item);
		});
	}
	
	//DELETE
		@FXML
		private void Delete() throws Exception {
			Order o = order_Table.getSelectionModel().getSelectedItem();
			if(o != null) {
				odb.Delete(o.getMaDH());
				ob.Delete(o.getMaDH());
				Refresh();
			}
		}
	//================================================================
		
	//EDIT
		@FXML
		private void Edit() throws Exception {
			Order o = order_Table.getSelectionModel().getSelectedItem();
			if(o != null) {
				for(Integer i : em.keySet()) {
					if(NV.getSelectionModel().getSelectedItem().indexOf("" + i) != -1) {
						o.setMaNV(i);
					}
				}
				o.setStatus(Status.getSelectionModel().getSelectedItem().toString());
				ob.Update(o);
				Refresh();
			}
		}
	//=================================================================
	
	//INFORMATION
		@FXML
		private void Open_Detail() throws IOException{
			Order o = order_Table.getSelectionModel().getSelectedItem();
			if(o != null) {
				Stage stage = new Stage();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Order_Detail.fxml"));
				Parent root = loader.load();
				
				Order_Detail_Controller odc = loader.<Order_Detail_Controller>getController();
				odc.getID(o.getMaDH());
				
				Scene scene = new Scene(root);
				scene.setFill(Color.TRANSPARENT);
				stage.setScene(scene);
				
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(Launch.stage);
				stage.showAndWait();
			}
		}
		
	//=================================================================
	
	private void Refresh() {
		ObservableList<Order> oblist = FXCollections.observableArrayList();
		orders = ob.getList();
		for(Integer i : orders.keySet()) {
			Order o = orders.get(i);
			oblist.add(o);
		}
		order_Table.setItems(oblist);
	}
	
	//CREATE ELEMENTS IN CHECKBOX
		private void Tao_TrangThai() {
			Status.setItems(status_Name);
			Status.getSelectionModel().select("Đã Phê Duyệt");
		}
	
		private void Tao_HoTenNV() {
			employees = eb.getList();
			IDs = new ArrayList<>();
			if(employees.size() != 0) {
				for(int i = 1; i <= employees.size(); i++) {
					if(employees.get(i).getChucVu().equals("Nhân Viên Giao Hàng")) {
						IDs.add(employees.get(i).getMaNV());
						em.put(i, i + " - " +employees.get(i).getHoNV() + " " + employees.get(i).getTenNV());
						role.add(em.get(i));
					}
				}
			}
		}
		
		private void getItems(ChoiceBox<String> cb, ObservableList<String> a) {
			cb.setItems(a);
			cb.getSelectionModel().selectFirst();
		}
	//========================================================================
}
