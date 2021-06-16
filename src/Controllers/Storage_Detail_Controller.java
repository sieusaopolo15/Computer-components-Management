	package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import BUS.Import_BUS;
import DTO.Import;
import DTO.Import;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Storage_Detail_Controller implements Initializable {

	@FXML
	private AnchorPane anchor_Pane;
	
	//TABLE VIEW & TABLE COLUMN
		@FXML
		private TableView<Import> storage_Table;
		
		@FXML
		private TableColumn<Integer, Import> maKho;
		
		@FXML
		private TableColumn<Integer, Import> maSP;
		
		@FXML
		private TableColumn<Integer, Import> NCC;
		
		@FXML
		private TableColumn<Integer, Import> soLuong;
		
		@FXML
		private TableColumn<String, Import> ngayNhap;
		
		@FXML
		private TableColumn<String, Import> donGia;
		
		@FXML
		private TableColumn<String, Import> tongTien;
		
	//===========================================================
		
	//OTHER VARIABLES
		private HashMap<Integer, Import> imports;
		
		private ObservableList<Import> oblist = FXCollections.observableArrayList();
		
		private Import_BUS ib = new Import_BUS();
		
		private int id;
	//===========================================================
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		maKho.setCellValueFactory(new PropertyValueFactory<Integer, Import>("maKho"));
		maSP.setCellValueFactory(new PropertyValueFactory<Integer, Import>("maSP"));
		NCC.setCellValueFactory(new PropertyValueFactory<Integer, Import>("tenNCC"));
		soLuong.setCellValueFactory(new PropertyValueFactory<Integer, Import>("soLuong"));
		ngayNhap.setCellValueFactory(new PropertyValueFactory<String, Import>("ngayNhap"));
		donGia.setCellValueFactory(new PropertyValueFactory<String, Import>("donGia"));
		tongTien.setCellValueFactory(new PropertyValueFactory<String, Import>("tongTien"));
		
		storage_Table.setItems(oblist);
	}
	
	public void Receiver(int id) {
		this.id = id;
		imports = ib.getList(id);
		
		for(Integer i : imports.keySet()) {
			Import sd = imports.get(i);
			oblist.add(sd);
		}
	}
	
	@FXML
	private void Back() {
		Stage stage = (Stage) anchor_Pane.getScene().getWindow();
		stage.close();
	}
	
}
