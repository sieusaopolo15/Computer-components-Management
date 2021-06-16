package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import BUS.Account_BUS;
import BUS.Customer_BUS;
import BUS.Employee_BUS;
import DTO.Account;
import DTO.Customer;
import DTO.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class user_Management implements Initializable {

	    @FXML
	    private TableView<Account> user_Table;

	    @FXML
	    private TableColumn<Account, Integer> maKH;
	    
	    @FXML
	    private TableColumn<Account, Integer> maTK;

	    @FXML
	    private TableColumn<Account, String> tenTK;

	    @FXML
	    private TableColumn<Account, String> trangThai;
	    
	    @FXML
	    private JFXButton btn_setStatus;
	    
	    @FXML
	    private JFXTextField search_Box;
	    
	    //CÁC BIẾN DATA
		    private ObservableList<Account> oblist = FXCollections.observableArrayList();
		    
		    private Account_BUS ab = new Account_BUS();
		    private Employee_BUS eb = new Employee_BUS();
		    
		    private HashMap<Integer, Account> accounts = new HashMap<>();
		    private HashMap<Integer, Employee> employees = new HashMap<>();
	    //=====================================================================
		
		//CÁC BIẾN KHÁC
		    private Alert alert;
		    private DialogPane dp;
	    //=====================================================================
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			accounts = ab.getList();
			
			for(Integer i : accounts.keySet()) {
				Account c = accounts.get(i);
				oblist.add(c);
			}
			
			maTK.setCellValueFactory(new PropertyValueFactory<Account, Integer>("maTK"));
			maKH.setCellValueFactory(new PropertyValueFactory<Account, Integer>("maKH"));
	    	tenTK.setCellValueFactory(new PropertyValueFactory<Account, String>("tenTK"));
	    	trangThai.setCellValueFactory(new PropertyValueFactory<Account, String>("status"));
	    
	    	user_Table.setItems(oblist);
		}
		
		//SEARCH
		@FXML 
		private void Search(KeyEvent event) {
			
			user_Table.getItems().stream().filter(item -> ("" + item.getMaKH()).equals(search_Box.getText())).findAny().ifPresent(item -> {
				user_Table.getSelectionModel().select(item);
				user_Table.scrollTo(item);
			});
			
			user_Table.getItems().stream().filter(item -> item.getTenTK().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				user_Table.getSelectionModel().select(item);
				user_Table.scrollTo(item);
			});
			
			user_Table.getItems().stream().filter(item -> ("" + item.getMaTK()).indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				user_Table.getSelectionModel().select(item);
				user_Table.scrollTo(item);
			});
			
			user_Table.getItems().stream().filter(item -> item.getStatus().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				user_Table.getSelectionModel().select(item);
				user_Table.scrollTo(item);
			});
		}
		//==================================================================
		
		@FXML
		private void setStatus() throws Exception {
			Account c = user_Table.getSelectionModel().getSelectedItem();
			if(c != null) {
				if(c.getStatus().equals("Đang Kích Hoạt")) {
					c.setStatus("Vô Hiệu Hóa");
					ab.Update(c);
				}
				else if(c.getStatus().equals("Vô Hiệu Hóa")) {
					c.setStatus("Đang Kích Hoạt");
					ab.Update(c);
				}
			}
			else {
				Error("Lỗi Dữ Liệu", "Bạn chưa chọn tài khoản để kích hoạt/vô hiệu hóa", "");
				return;
			}
			refresh();
		}
		
		@FXML
		private void checkInfo() throws IOException {
			Account c = user_Table.getSelectionModel().getSelectedItem();
			if(c != null) {
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/User_Detail.fxml"));
				Parent root = loader.load();
				
				User_Detail_Controller udc = loader.<User_Detail_Controller>getController();
				udc.receiver(c.getMaKH());
				
				Scene scene = new Scene(root);
				scene.setFill(Color.TRANSPARENT);
				stage.setScene(scene);
			
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(Launch.stage);
				
				stage.showAndWait();
			}
		}
		
		//REFRESH
			private void refresh() {
				accounts = ab.getList();
				
				ObservableList<Account> oblist = FXCollections.observableArrayList();
				for(Integer i : accounts.keySet()) {
					Account a = accounts.get(i);
					oblist.add(a);
				}
				user_Table.setItems(oblist);
			}
		//=====================================================================
		
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
		
		public void Receiver(int id) {
			employees = eb.getList();
			for(Integer i : employees.keySet()) {
				if(id == employees.get(i).getMaNV()) {
					if(employees.get(i).getChucVu().equals("Nhân Viên Hỗ Trợ"))
						btn_setStatus.setDisable(true);
				}
			}
		}
}
