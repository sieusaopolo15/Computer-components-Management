package Controllers;

import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import BUS.Chart_Sales_BUS;
import DTO.Chart_Sales_DTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class Chart_Sales_Controller implements Initializable {

	@FXML
	private HBox hbox;
	
	@FXML
	private JFXComboBox<String> choices;
	
	//TABLE VIEW & DATA
		@FXML
		private TableView<Chart_Sales_DTO> sales_Table;
		
		@FXML
		private TableColumn<String, Chart_Sales_DTO> loaiSP;
		
		@FXML
		private TableColumn<String, Chart_Sales_DTO> tenSP;
		
		@FXML
		private TableColumn<Integer, Chart_Sales_DTO> soLuongThuc;
		
		@FXML
		private TableColumn<String, Chart_Sales_DTO> donGia;
		
		@FXML
		private TableColumn<String, Chart_Sales_DTO> doanhThu;
	//===================================================================
	
	//CÁC BIẾN KHÁC
		private HashMap<Integer, Chart_Sales_DTO> sales;
		
		private Chart_Sales_BUS csb = new Chart_Sales_BUS();
		
		private ObservableList<Chart_Sales_DTO> oblist = FXCollections.observableArrayList();
		private ObservableList<String> choice = FXCollections.observableArrayList(
				"Thống Kê Sản Phẩm",
				"Theo Tháng",
				"Theo Năm",
				"Theo Hàng Bán Chạy"
		);
	//===================================================================
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		loaiSP.setCellValueFactory(new PropertyValueFactory<String, Chart_Sales_DTO>("loaiSP"));
		tenSP.setCellValueFactory(new PropertyValueFactory<String, Chart_Sales_DTO>("tenSP"));
		soLuongThuc.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
		donGia.setCellValueFactory(new PropertyValueFactory<String, Chart_Sales_DTO>("donGia"));
		doanhThu.setCellValueFactory(new PropertyValueFactory<String, Chart_Sales_DTO>("tongTien"));
		
		createHashMap("");
		createChoices();
		
		
		
		
		choices.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number num1, Number num2) {
				String str = choices.getItems().get((Integer) num2);
				createComboBox(str);
			}
		});
	}
	
	private void createComboBox(String text) {
		
		JFXComboBox<String> box = new JFXComboBox<>();
		int j = 1;
		
		ObservableList<String> list = FXCollections.observableArrayList();
		if(text.equals("Theo Tháng")) {
			for(Node child : hbox.getChildren()) {
				j++;
			}
			if(j > 2) {
				hbox.getChildren().remove(1);
				j = 1;
			}
			list.add("Thống Kê Doanh Thu Theo Tháng");
			for(int i = 1; i <= 12; i++) {
				list.add("" + i);
			}
			box.setItems(list);
			box.getSelectionModel().selectFirst();
			hbox.getChildren().add(box);
			box.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observableValue, Number num1, Number num2) {
					String str = box.getItems().get((Integer) num2);
					createHashMap(" AND ngayLap LIKE '%-" + str + "-%'");
				}
			});
		}
		else if(text.equals("Theo Năm")) {
			for(Node child : hbox.getChildren()) {
				j++;
			}
			if(j > 2) {
				hbox.getChildren().remove(1);
				j = 1;
			}
			list.add("Thống Kê Doanh Thu Theo Năm");
			for(int i = Calendar.getInstance().get(Calendar.YEAR) - 4; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
				list.add("" + i);
			}
			box.setItems(list);
			box.getSelectionModel().selectFirst();
			hbox.getChildren().add(box);
			
			box.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observableValue, Number num1, Number num2) {
					String str = box.getItems().get((Integer) num2);
					
					createHashMap(" AND ngayLap LIKE '%" + str + "-%'");
				}
			});
		}
		else if(text.equals("Theo Hàng Bán Chạy")) {
			for(Node child : hbox.getChildren()) {
				j++;
			}
			if(j > 2) {
				hbox.getChildren().remove(1);
				j = 1;
			}
			createHashMap(" ORDER BY soLuong DESC");
		}
		else {
			for(Node child : hbox.getChildren()) {
				j++;
			}
			if(j > 2) {
				hbox.getChildren().remove(1);
				j = 1;
			}
			createHashMap("");
		}
	}
	
	private void createHashMap(String text) {
		ObservableList<Chart_Sales_DTO> oblist = FXCollections.observableArrayList();	
		sales = csb.getList(text);
		for(Integer i : sales.keySet()) {
			Chart_Sales_DTO csd = sales.get(i);	
			oblist.add(csd);
		}
		sales_Table.setItems(oblist);
	}
	
	private void createChoices() {
		choices.setItems(choice);
		choices.getSelectionModel().selectFirst();
	}

}
