package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;

import BUS.Account_BUS;
import BUS.Customer_BUS;
import DTO.Account;
import DTO.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Information_Controller implements Initializable {

	//INFORMATION
		@FXML
	    private TextField fullName;
	
	    @FXML
	    private JFXRadioButton male;
	
	    @FXML
	    private JFXRadioButton female;
	
	    @FXML
	    private JFXDatePicker ngaySinh;
	
	    @FXML
	    private TextField user_Name;
	
	    @FXML
	    private PasswordField old_Pass;
	
	    @FXML
	    private PasswordField new_Pass;
	    
	    @FXML
	    private PasswordField check_Pass;
	//===============================================
	
	//OTHER VARIABLES
	    private int c;
	    private String gender = "";
	    
	    private String password;
	    
	    private DialogPane dp;
	    private Alert alert;
	    
	    private Customer_BUS cb = new Customer_BUS();
	    private Account_BUS ab = new Account_BUS();
	    
	    private HashMap<Integer, Customer> customers;
	    private HashMap<Integer, Account> accounts;
	//===============================================
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void Reset() {
		fullName.setText("");
		if(male.isSelected()) {
			male.setSelected(false);
		}
		else {
			female.setSelected(false);
		}
		ngaySinh.setValue(null);
		old_Pass.setText("");
		new_Pass.setText("");
		check_Pass.setText("");
	}
	
	@FXML
	private void Save() throws Exception {
		if(Validate()) {
			Customer c = new Customer(this.c, fullName.getText(), gender, ngaySinh.getValue().toString());
			Account a = new Account(this.c, this.c, user_Name.getText(), check_Pass.getText(), "??ang K??ch Ho???t");
			cb.Update(c);
			ab.Update(a);
			
			Error("S???a Th??ng Tin Th??nh C??ng", "", "");
		}
	}
	
	//VALIDATE
		private boolean Validate() {
			
			if(fullName.getText().equals("")) {
				Error("H??? V?? T??n Kh??ch H??ng", "H??? v?? t??n kh??ch h??ng kh??ng ??u???c ????? tr???ng", "");
				fullName.requestFocus();
				return false;
			}
			else if(Check_Gender().equals("")) {
				Error("Gi???i T??nh", "Gi???i t??nh c???a kh??ch h??ng ph???i ???????c ch???n", "");
				male.requestFocus();
				return false;
			}
			else if(old_Pass.getText().equals("")) {
				Error("M???t Kh???u C??", "M???t kh???u c?? c???a kh??ch h??ng kh??ng ???????c b??? tr???ng", "");
				old_Pass.requestFocus();
				return false;
			}
			else if(!old_Pass.getText().equals(password)) {
				Error("M???t Kh???u C??", "Sai m???t kh???u c??", "");
				old_Pass.requestFocus();
				return false;
			}
			else if(new_Pass.getText().equals("")) {
				Error("M???t Kh???u M???i", "M???t kh???u m???i c???a kh??ch h??ng kh??ng ???????c b??? tr???ng", "");
				new_Pass.requestFocus();
				return false;
			}
			else if(new_Pass.getText().equals(old_Pass.getText())) {
				Error("M???t Kh???u M???i", "M???t kh???u m???i ph???i kh??c m???t kh???u c??", "");
				new_Pass.requestFocus();
				return false;
			}
			else if(!check_Pass.getText().equals(new_Pass.getText())) {
				Error("Nh???p L???i M???t Kh???u", "Ph???i nh???p ????ng v???i m???t kh???u m???i", "");
				check_Pass.requestFocus();
				return false;
			}
			return true;
		}
		
		private String Check_Gender() {
			
			if(male.isSelected()) {
				gender = "Nam";
			}
			else if(female.isSelected()) {
				gender = "N???";
			}
			return gender;
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
		
		private void Information(String infoName, String text, String contentText) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(infoName);
			alert.setHeaderText(text);
			alert.setContentText(contentText);
			
			dp = alert.getDialogPane();
			dp.getStylesheets().add(getClass().getResource("../GUI/CSS/style.css").toExternalForm());
			
			alert.showAndWait();
		}
	//===============================================================
	
		
	public void Transfer(int c) {
		customers = cb.getList();
		accounts = ab.getList();
		
		this.c = c;
		
		for(int i = 0; i < customers.size(); i++) {
			if(c == customers.get(i + 1).getMaKH() && c == accounts.get(i + 1).getMaTK()) {
				fullName.setText(customers.get(i + 1).getHoTenKH());
				if(customers.get(i + 1).getGender().equals("Nam")){
					male.setSelected(true);
				}
				else {
					female.setSelected(false);
				}
				ngaySinh.setValue(getDate(customers.get(i + 1).getDate()));
				user_Name.setText(accounts.get(i + 1).getTenTK());
				this.password = accounts.get(i + 1).getMk();
				return;
			}
		}
	}
	
	//CONVERT A STRING DATE TO DATE
		private LocalDate getDate(String dateString) {
			DateTimeFormatter date_format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(dateString, date_format);
			return localDate;
		}
	//=================================================================
}
