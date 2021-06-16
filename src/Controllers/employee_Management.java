package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import BUS.Account_Employee_BUS;
import BUS.Employee_BUS;
import DTO.Account_Employee;
import DTO.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class employee_Management implements Initializable {

	@FXML
	private JFXTextField search_Box;
	/* TABLE & DATA*/
		@FXML
		private TableView<Employee> employee_Table;
		
		@FXML 
		private TableColumn<Employee, Integer> maNV;
		
		@FXML 
		private TableColumn<Employee, String> ChucVu;
		
		@FXML 
		private TableColumn<Employee, String> hoNV;

		@FXML 
		private TableColumn<Employee, String> tenNV;
		
		@FXML 
		private TableColumn<Employee, String> ngaySinh;
		
		@FXML 
		private TableColumn<Employee, String> ngayVaoLam;
		
		private ObservableList<Employee> oblist = FXCollections.observableArrayList();
		private HashMap<Integer, Employee> employee_list = new HashMap<>();
		private HashMap<Integer, Account_Employee> accounts = new HashMap<>();
	/*===============================================================*/
	
	Employee_BUS eb = new Employee_BUS();
	Account_Employee_BUS aeb = new Account_Employee_BUS();
			
	// BIỂU MẪU
		@FXML
		private TextField tf_MaNV;
	
		@FXML
		private TextField tf_HoNV;
		
		@FXML
	    private TextField tf_TenNV;
		
		@FXML
		private ChoiceBox<String> cb_Quyen;
	
	    @FXML
	    private JFXDatePicker dp_NgaySinh;
	
	    @FXML
	    private JFXDatePicker dp_NgayVaoLam;
    //===================================
	    
	private Alert alert;
	private DialogPane dp;
	private String regex_number = "^[^0-9]+$";
    
	private ObservableList<String> choicebox = FXCollections.observableArrayList(
			"Chức Vụ",
			"Quản Lý", 
			"Thu Ngân",
			"Kiểm Kho",
			"Nhân Viên Hỗ Trợ", 
			"Nhân Viên Giao Hàng"
	);
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		employee_list = eb.getList();
		accounts = aeb.getList();
		
		for(Integer i : employee_list.keySet()) {
			Employee e = employee_list.get(i);
			oblist.add(e);
		}
		
		maNV.setCellValueFactory(new PropertyValueFactory<>("maNV"));
		ChucVu.setCellValueFactory(new PropertyValueFactory<>("ChucVu"));
		hoNV.setCellValueFactory(new PropertyValueFactory<>("hoNV"));
		tenNV.setCellValueFactory(new PropertyValueFactory<>("tenNV"));
		ngaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		ngayVaoLam.setCellValueFactory(new PropertyValueFactory<>("ngayVaoLam"));
		
		getMaNV();
		getQuyen(cb_Quyen, choicebox);
		employee_Table.setItems(oblist);
		
		employee_Table.setOnMousePressed(e -> {
			if(e.isPrimaryButtonDown() && e.getClickCount() == 2) {
				Employee E = employee_Table.getSelectionModel().getSelectedItem();
				if(E.getMaNV() < 10) {
					tf_MaNV.setText("00" + E.getMaNV());
				}
				else if(E.getMaNV() >= 10) {
					tf_MaNV.setText("0" + E.getMaNV());
				}
				
				tf_HoNV.setText(E.getHoNV());
				tf_TenNV.setText(E.getTenNV());
				cb_Quyen.getSelectionModel().select(find_Role(E.getMaNV()));
				dp_NgaySinh.setValue(getDate(E.getNgaySinh()));
				dp_NgayVaoLam.setValue(getDate(E.getNgayVaoLam()));
			}
		});
	}
	
	//TÌM KIẾM
		@FXML 
		private void Search(KeyEvent event) {
			
			employee_Table.getItems().stream().filter(item -> item.getNVId().equals(search_Box.getText())).findAny().ifPresent(item -> {
				employee_Table.getSelectionModel().select(item);
				employee_Table.scrollTo(item);
			});
			
			employee_Table.getItems().stream().filter(item -> item.getChucVu().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				employee_Table.getSelectionModel().select(item);
				employee_Table.scrollTo(item);
			});
			
			employee_Table.getItems().stream().filter(item -> item.getHoNV().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				employee_Table.getSelectionModel().select(item);
				employee_Table.scrollTo(item);
			});
			
			employee_Table.getItems().stream().filter(item -> item.getTenNV().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				employee_Table.getSelectionModel().select(item);
				employee_Table.scrollTo(item);
			});
	
			employee_Table.getItems().stream().filter(item -> item.getNgaySinh().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				employee_Table.getSelectionModel().select(item);
				employee_Table.scrollTo(item);
			});
			
			employee_Table.getItems().stream().filter(item -> item.getNgayVaoLam().indexOf(search_Box.getText()) != -1).findAny().ifPresent(item -> {
				employee_Table.getSelectionModel().select(item);
				employee_Table.scrollTo(item);
			});
		}
	//==========================================================================
	
	//INSERT
		@FXML
		private void Insert() throws Exception {
			if(Validate_Insert()) {
				//TẠO THÔNG TIN CÁ NHÂN CỦA NHÂN VIÊN
					Employee e = new Employee(
						Integer.parseInt(tf_MaNV.getText()),
						cb_Quyen.getSelectionModel().getSelectedItem().toString(),
						tf_HoNV.getText(),
						tf_TenNV.getText(),
						dp_NgaySinh.getValue().toString(),
						dp_NgayVaoLam.getValue().toString()
					);
					eb.Insert(e);
				//======================================================
					
				// TẠO TÀI KHOẢN NHÂN VIÊN
					Account_Employee ae = new Account_Employee(
						Integer.parseInt(tf_MaNV.getText()),
						Integer.parseInt(tf_MaNV.getText()),
						cb_Quyen.getSelectionModel().getSelectedItem().toString(),
						"NV_" + tf_MaNV.getText().toString(),
						dp_NgaySinh.getValue().toString().replaceAll("-", "")
					);
					aeb.Insert(ae);
				//======================================================
					
				Information("Cập Nhật Nhân Viên", "Đã thêm thành công 1 nhân viên", "");
				
				employee_list = eb.getList();
				refresh();
			}
		}
		
		private void refresh() {
			getMaNV();
			tf_HoNV.setText("");
			tf_TenNV.setText("");
			cb_Quyen.getSelectionModel().select("Chức Vụ");
			dp_NgaySinh.setValue(null);
			dp_NgayVaoLam.setValue(null);
			
			ObservableList<Employee> oblist = FXCollections.observableArrayList();
			for(Integer i : employee_list.keySet()) {
				Employee e = employee_list.get(i);
				oblist.add(e);
			}
			employee_Table.setItems(oblist);
		}
	//===================================================================
	
	//EDIT
		@FXML
		private void Edit() throws Exception {
			if(Validate_Update()) {
				Employee e = new Employee(
						Integer.parseInt(tf_MaNV.getText()),
						cb_Quyen.getSelectionModel().getSelectedItem().toString(),
						tf_HoNV.getText(),
						tf_TenNV.getText(),
						dp_NgaySinh.getValue().toString(),
						dp_NgayVaoLam.getValue().toString()
					);
				eb.Update(e);
				
				Information("Cập Nhật Dữ Liệu", "Cập nhật thông tin nhân viên thành công", "");
				employee_list = eb.getList();
				refresh();
			}
		}
		
	//===================================================================
		
	//REFRESH
		@FXML
		private void Refresh() {
			refresh();
		}
	//===================================================================
		
	//VALIDDATE
		private boolean Validate_Insert() {
			if(tf_HoNV.getText().equals("")) {
				Error("Họ Nhân Viên", "Họ của nhân viên bị trống", "");
				tf_HoNV.requestFocus();
				return false;
			}
			else if(!tf_HoNV.getText().matches(regex_number)) {
				Error("Họ Nhân Viên", "Họ của nhân viên không hợp lệ", "");
				tf_HoNV.requestFocus();
				return false;
			}
			else if(tf_TenNV.getText().equals("")) {
				Error("Tên Nhân Viên", "Tên của nhân viên bị trống", "");
				tf_TenNV.requestFocus();
				return false;
			}
			else if(!tf_TenNV.getText().matches(regex_number)) {
				Error("Tên Nhân Viên", "Tên của nhân viên không hợp lệ", "");
				tf_TenNV.requestFocus();
				return false;
			}
			else if(cb_Quyen.getSelectionModel().getSelectedItem().equals("")) {
				Error("Chức Vụ Của Nhân Viên", "Chức vụ của nhân viên bị trống", "");
				cb_Quyen.requestFocus();
				return false;
			}
			else if(cb_Quyen.getSelectionModel().getSelectedItem().equals("Chức Vụ")) {
				Error("Chức Vụ Của Nhân Viên", "Chức vụ của nhân viên không hợp lệ", "");
				cb_Quyen.requestFocus();
				return false;
			}
			else if(dp_NgaySinh.getValue() == null) {
				Error("Ngày Sinh Của Nhân Viên", "Ngày sinh của nhân viên bị trống", "");
				cb_Quyen.requestFocus();
				return false;
			}
			else if(Calendar.getInstance().get(Calendar.YEAR) - dp_NgaySinh.getValue().getYear() < 18) {
				Error("Ngày Sinh Của Nhân Viên", "Nhân viên chưa đủ 18 tuổi", "");
				dp_NgaySinh.requestFocus();
				return false;	
			}
			else if(dp_NgayVaoLam.getValue() == null) {
				Error("Ngày Vào Làm Của Nhân Viên", "Ngày vào làm của nhân viên bị trống", "");
				dp_NgayVaoLam.requestFocus();
				return false;
			}
			
			return true;
		}
		
		private boolean Validate_Update() {
			if(tf_HoNV.getText().equals("")) {
				Error("Họ Nhân Viên", "Họ của nhân viên bị trống", "");
				tf_HoNV.requestFocus();
				return false;
			}
			else if(tf_TenNV.getText().equals("")) {
				Error("Tên Nhân Viên", "Tên của nhân viên bị trống", "");
				tf_TenNV.requestFocus();
				return false;
			}
			else if(cb_Quyen.getSelectionModel().getSelectedItem().equals("")) {
				Error("Chức Vụ Của Nhân Viên", "Chức vụ của nhân viên bị trống", "");
				cb_Quyen.requestFocus();
				return false;
			}
			else if(cb_Quyen.getSelectionModel().getSelectedItem().equals("Chức Vụ")) {
				Error("Chức Vụ Của Nhân Viên", "Chức vụ của nhân viên không hợp lệ", "");
				cb_Quyen.requestFocus();
				return false;
			}
			else if(dp_NgaySinh.getValue() == null) {
				Error("Ngày Sinh Của Nhân Viên", "Ngày sinh của nhân viên bị trống", "");
				cb_Quyen.requestFocus();
				return false;
			}
			else if(Calendar.getInstance().get(Calendar.YEAR) - dp_NgaySinh.getValue().getYear() < 18) {
				Error("Ngày Sinh Của Nhân Viên", "Nhân viên chưa đủ 18 tuổi", "");
				dp_NgaySinh.requestFocus();
				return false;	
			}
			else if(dp_NgayVaoLam.getValue() == null) {
				Error("Ngày Vào Làm Của Nhân Viên", "Ngày vào làm của nhân viên bị trống", "");
				dp_NgayVaoLam.requestFocus();
				return false;
			}
			
			return true;
		}
		
		private void getMaNV() {
			for(int i = 1; i <= employee_list.size(); i++) {
				if(i + 1 >= employee_list.size()) {
					if(i + 1 < 10) {
						tf_MaNV.setText("00" + (i + 1));
					}
					else if(i + 1 >= 10){
						tf_MaNV.setText("0" + (i + 1));
					}
				}
			}
		}
		
		private void getQuyen(ChoiceBox<String> cb, ObservableList<String> list) {
			cb.setItems(choicebox);
			cb.getSelectionModel().select("Chức Vụ");
		}
	//====================================================================
	
		
	//TÌM CHỨC VỤ CỦA NHÂN VIÊN THÔNG QUA TÀI KHOẢN CỦA NHÂN VIÊN
		private String find_Role(int maNV) {
			accounts = aeb.getList();
			for(int i = 1; i < accounts.size(); i++) {
				if(maNV == accounts.get(i).getMaNV()) {
					return accounts.get(i).getQuyen();
				}
			}
			return null;
		}
	//=============================================================
	
	//BIẾN ĐỔI String -> LocalDate
		private LocalDate getDate(String dateString) {
			DateTimeFormatter date_format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(dateString, date_format);
			return localDate;
		}
	//==============================================================================
		
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
