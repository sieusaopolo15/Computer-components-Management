package GUI.Forms.User_Login;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.digest.DigestUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;

import BUS.Account_BUS;
import BUS.Customer_BUS;
import Controllers.BuyingController;
import Controllers.Launch;
import DAL.MyConnectUnit;
import DTO.Account;
import DTO.Customer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainController implements Initializable {
	@FXML
	private VBox vbox;
	
	@FXML
	private VBox login;
	
	@FXML
	private ImageView close_btn;
	
	@FXML
	private AnchorPane ap;
	
	@FXML
	private Pane pane;
	
	/* FORM ĐĂNG NHẬP */
	 @FXML
	    private Label signIn_Label;

	    @FXML
	    private TextField email;

	    @FXML
	    private FontAwesomeIconView email_Icon;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private FontAwesomeIconView password_Icon;

	    @FXML
	    private JFXButton forgot_Password;

	    @FXML
	    private JFXButton sign_In;
	    
	    //SIGN UP
	    
	    	@FXML
	    	private VBox vbox_SignUp;
	    	
			@FXML
	    	private TextField fullName;
    
	    	@FXML
	    	private TextField email_SignUp;
	    	
	    	@FXML
	    	private TextField password_SignUp;
	    
	    	@FXML
	    	private TextField repass_SignUp;
	    	
	    	@FXML
	    	private ToggleGroup gender;
	    	
	    	@FXML
	    	private JFXRadioButton male;
	    	
	    	@FXML
	    	private JFXRadioButton female;
	    	
	    	@FXML
	    	private JFXDatePicker birthDate;
	    
	    	@FXML
	    	private JFXButton signUp;
	    //===============================================================

	    private Alert alert;
	    
	    private DialogPane dp; 
	    
	    private double x, y;
	
	
	private Parent fxml;
	
	Account_BUS ab = new Account_BUS();
	Customer_BUS cb = new Customer_BUS();
	HashMap<Integer, Account> accounts = new HashMap<>();
	HashMap<Integer, Customer> customers = new HashMap<>();
	
	//	REGEXES
		String regex_Email = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
	//========================================================================
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
		t.setToX(600);
		t.play();
		t.setOnFinished((e) -> {
			try {
				fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
				vbox.setMaxHeight(400);
				vbox.setLayoutY(-33);
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
				Launch.stage = (Stage) ap.getScene().getWindow();
				
			}catch(IOException ex) {
				Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
	}
	
	@FXML
	private void open_SignUp(ActionEvent event) {
		vbox.setMaxHeight(600);
		vbox.setLayoutY(-87);
		TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
		t.setToX(20);
		t.play();
		t.setOnFinished(e -> {
			try {
				fxml = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
			}catch(IOException ex) {
				Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
	}
	
	@FXML
	private void open_SignIn(ActionEvent event) {
		vbox.setMaxHeight(400);
		vbox.setLayoutY(-33);
		TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
		t.setToX(600);
		t.play();
		t.setOnFinished(e -> {
			try {
				fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
				vbox.getChildren().removeAll();
				vbox.getChildren().setAll(fxml);
				
			}catch(IOException ex) {
				Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
	}
	
	@FXML
	void Close(MouseEvent event) {
		Launch.stage.close();
	}
	
	/* SỰ KIỆN CHO FORM ĐĂNG NHẬP */
		
		//SIGN IN
			@FXML
			private void Sign_In(ActionEvent event) throws IOException {
				Log_In();
			}
			
			@FXML
			private void onEnter(KeyEvent e) throws IOException {
				if(e.getCode() == KeyCode.ENTER) {
					Log_In();
				}
			}
			
			private void Log_In() throws IOException {
				if(Validate_Login()) {
					customers = cb.getList();
					for(int i = 0; i <	accounts.size(); i++) {
						if(accounts.get(i + 1).getTenTK().equals(email.getText())) {
							if(password.getText().equals(accounts.get(i + 1).getMk())) {
								if(!accounts.get(i + 1).getStatus().equals("Vô Hiệu Hóa")) {
									FXMLLoader load = new FXMLLoader(getClass().getResource("../../Buying/MainFrame.fxml"));
									Parent root = load.load();
									Scene scene = new Scene(root);
									scene.setFill(Color.TRANSPARENT);
									Launch.stage.setScene(scene);
									Launch.stage.show();
									
									BuyingController bc = load.<BuyingController>getController();
									bc.Login(customers.get(i + 1));
									break;
								}
								else {
									Error("Lỗi Đăng Nhập", "Tài khoản của bạn bị vô hiệu hóa", "");
									email.requestFocus();
									break;
								}
							}
						}
					}
				}
			}
			//Kiểm tra email với password lúc đăng nhập
			private boolean Validate_Login() {
				accounts = ab.getList();
				if(email.getText().equals("")) {
					Error("Lỗi Email", "Email không được để trống", "");
					email.requestFocus();
					return false;
					
				}
				else if(!email.getText().matches(regex_Email)) {
					Error("Lỗi Email", "Email không hợp lệ", "");
					email.requestFocus();
					return false;
				}
				else if(password.getText().equals("")) {
					Error("Lỗi Mật Khẩu", "Mật Khẩu không được để trống", "");
					password.requestFocus();
					return false;
				}
				else {
					for(int i = 0; i < accounts.size(); i++) {
						if(email.getText().equals(accounts.get(i + 1).getTenTK())) {
							if(password.getText().equals(accounts.get(i + 1).getMk())) {
								return true;
							}
							else {
								Error("Lỗi Mật Khẩu", "Sai Mật Khẩu", "");
								password.requestFocus();
								return false;
							}
						}
					}
				}
				return false;
			}
			
			
		//============================================================
			
		//SIGN UP
			@FXML
			private void Sign_Up(ActionEvent event) {
				Form_SignUp();
			}
			
			@FXML
			private void onEnter_SignUp(KeyEvent e) {
				if(e.getCode() == KeyCode.ENTER) {
					Form_SignUp();
				}
			}
			
			private void Form_SignUp() {
				customers = cb.getList();
				if(Validate_Register()) {
					//System.out.println("Tạo tài khoản thành công");
					String name = fullName.getText();
					String email = email_SignUp.getText();
					String password = password_SignUp.getText();
					String gender = "";
					if(male.isSelected()) {
						gender = "Nam";
					}
					else {
						gender = "Nữ";
					}
					String date = birthDate.getValue().toString();
					
					if(accounts.size() == 0 ) {
						Customer c = new Customer(1, name, gender, date);
						Account a = new Account (1, 1, email, password, "Đang Kích Hoạt");
						
						cb.Insert(c);
						ab.Insert(a);
					}
					else {
						for(int i = 1; i <= accounts.size(); i++) {
							if(i != accounts.get(i).getMaTK()) {
								Customer c = new Customer(i, name, gender, date);
								Account a = new Account (i, i, email, password, "Đang Kích Hoạt");
								
								if(cb.Insert(c))
									ab.Insert(a);
							}
							
							if(i  >= accounts.size()) {
								Customer c = new Customer(i + 1, name, gender, date);
								Account a = new Account (i + 1, i + 1, email, password, "Đang Kích Hoạt");
								
								if(cb.Insert(c))
									ab.Insert(a);
							}
						}
					}
					Information("Đăng Kí", "Đăng Kí Thành Công", "");
				}
			}
			
			private boolean Validate_Register() {
				accounts = ab.getList();
				if(fullName.getText().equals("")) {
					Error("Lỗi Họ và Tên", "Họ và Tên không được để trống", "");
					fullName.requestFocus();
					return false;
				}
				else if(email_SignUp.getText().equals("")) {
					Error("Lỗi Email", "Email không được để trống", "");
					email_SignUp.requestFocus();
					return false;
				}
				else if(!email_SignUp.getText().matches(regex_Email)) {
					Error("Lỗi Email", "Email không hợp lệ", "");
					email_SignUp.requestFocus();
					return false;
				}
				else if(password_SignUp.getText().equals("")) {
					Error("Lỗi Mật Khẩu", "Mật Khẩu không được để trống", "");
					password_SignUp.requestFocus();
					return false;
				}
				else if(repass_SignUp.getText().equals("")) {
					Error("Lỗi Nhập Lại Mật Khẩu", "Nhập Lại Mật Khẩu không được để trống", "");
					repass_SignUp.requestFocus();
					return false;
				}
				else if(!repass_SignUp.getText().equals(password_SignUp.getText())) {
					Error("Sai Dữ Liệu Mật Khâu", "Hai Mật Khẩu phải giống nhau", "");
					repass_SignUp.setText("");
					repass_SignUp.requestFocus();
					return false;
				}
				else if(birthDate.getValue() == null) {
					Error("Ngày Sinh bị lỗi", "Ngày Sinh không hợp lệ", "");
					birthDate.requestFocus();
					return false;
				}
				else if(Calendar.getInstance().get(Calendar.YEAR) - birthDate.getValue().getYear() < 13) {
					Error("Năm Sinh Bị Lỗi", "Năm Sinh Xàm Xí, Sửa Lại Đê", "");
					birthDate.requestFocus();
					return false;
				}
				else 
					if(!male.isSelected() && !female.isSelected()) {
					Error("Giới tính không hợp lệ", "Dữ liệu giới tính chưa được chọn", "");
					return false;
				}
				
				else {
					for(int i = 0; i < accounts.size(); i++) {
						if(email_SignUp.getText().equals(accounts.get(i + 1).getTenTK())) {
							Error("Lỗi Email", "Email đã có người đăng kí", "");
							email_SignUp.requestFocus();
							return false;
						}
						
					}
				}
				return true;
			}
			
			private void Error(String errorName, String text, String contentText) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle(errorName);
				alert.setHeaderText(text);
				alert.setContentText(contentText);
				
				dp = alert.getDialogPane();
				dp.getStylesheets().add(getClass().getResource("../../CSS/style.css").toExternalForm());
				
				alert.showAndWait();
			}
			
			private void Information(String informationName, String text, String contentText) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle(informationName);
				alert.setHeaderText(text);
				alert.setContentText(contentText);
				
				dp = alert.getDialogPane();
				dp.getStylesheets().add(getClass().getResource("../../CSS/style.css").toExternalForm());
				
				alert.showAndWait();
			}
		//========================================================================
	@FXML
	private void Drag_Stage() {
		
		pane.setOnMousePressed(e -> {
    		x = e.getSceneX();
    		y = e.getSceneY();
    	});
    	pane.setOnMouseDragged(e -> {
    		Launch.stage.setX(e.getScreenX() - x);
    		Launch.stage.setY(e.getScreenY() - y);
    		Launch.stage.setOpacity(0.8f);
    	});
    	pane.setOnDragDone(e -> {
    		Launch.stage.setOpacity(1.0f);
    	});
    	pane.setOnMouseReleased(e -> {
    		Launch.stage.setOpacity(1.0f);
    	});
	}
}
