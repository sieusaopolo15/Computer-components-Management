package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import BUS.Warehouse_BUS;
import DTO.Warehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Storage_Management implements Initializable {
	
	private Warehouse_BUS wb = new Warehouse_BUS();
	
	//TABLE VIEW & TABLE COLUMNS
		@FXML
		private TableView<Warehouse> storage_Table;
		
		@FXML
		private TableColumn<Integer, Warehouse> maKho;
		
		@FXML
		private TableColumn<Integer, Warehouse> tenKho;
	//====================================================
	
	//OTHER VARIABLES
		private HashMap<Integer, Warehouse> warehouse;
		private ObservableList<Warehouse> oblist = FXCollections.observableArrayList();
		
		private int id;
	//====================================================
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		warehouse = wb.getList();
		for(Integer i : warehouse.keySet()) {
			Warehouse w = warehouse.get(i);
			oblist.add(w);
		}
		
		maKho.setCellValueFactory(new PropertyValueFactory<Integer, Warehouse>("maKho"));
		tenKho.setCellValueFactory(new PropertyValueFactory<Integer, Warehouse>("tenKho"));
		
		storage_Table.setItems(oblist);
		
	}
	
	@FXML
	private void Xem_ChiTiet() throws Exception {
		Warehouse w = storage_Table.getSelectionModel().getSelectedItem();
		if(w != null) {
			Stage stage = new Stage();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Storage_Detail.fxml"));
			Parent root = loader.load();
			Storage_Detail_Controller sdc = loader.<Storage_Detail_Controller>getController();
			sdc.Receiver(w.getMaKho());
			
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			stage.setScene(scene);
			
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(Launch.stage);
			
			stage.showAndWait();
		}
	}
	
	@FXML
	private void Nhap_Hang() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Import.fxml"));
		Parent root = loader.load();
		Import_Controller ic = loader.<Import_Controller>getController();
		ic.Receiver(this.id);
		
		Open_Scene(root);
	}
	
	@FXML
	private void Xuat_Hang() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Export.fxml"));
		Parent root = loader.load();
		Export_Controller ec = loader.<Export_Controller>getController();
		ec.Receiver(this.id);
		
		Open_Scene(root);
	}
	
	public void Receiver(int id) {
		this.id = id;
	}
	
	
	private void Open_Scene(Parent root) {
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		stage.setScene(scene);
			
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Launch.stage);
		stage.showAndWait();
	}
}
