package Controllers;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import BUS.Account_BUS;
import BUS.Account_Employee_BUS;
import DTO.Account;
import DTO.Account_Employee;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class Management_Login implements Initializable {
	
	@FXML
	private Button close_Button;

	@FXML
	private Button minimize_Button;
	
	@FXML
    private JFXTextField admin_Username;

    @FXML
    private JFXPasswordField admin_Password;
    
    @FXML 	
    private JFXButton login_Button;
    
    @FXML
    private AnchorPane anchor_Pane;
    
    private Alert alert;
    private DialogPane dp = new DialogPane();
    
    private int counter;
    
    private double xOffsets, yOffsets;
    private Account_Employee_BUS aeb = new Account_Employee_BUS();
    private HashMap<Integer, Account_Employee> accounts = new HashMap<>();
    
    public void initialize(URL url, ResourceBundle rb) {
    	
    }
    
    @FXML
    private void Close(MouseEvent event) {
    	Launch.stage.close();
    }
    
    @FXML
    private void Minimize(ActionEvent event) {
    	Launch.stage.setIconified(true);
    }
    
    //SIGN OUT CHO GIAO DIỆN ADMIN (admin_Controls)
	    public void Sign_Out(Stage stage) {
	    	Launch.stage = stage;
	    	try {
	    		Parent loader = FXMLLoader.load(getClass().getResource("../GUI/Forms/Management_Login/LoginForm.fxml"));
				Scene scene = new Scene(loader);
				scene.setFill(Color.TRANSPARENT);
				Launch.stage.setScene(scene);
				//s.initStyle(StageStyle.TRANSPARENT);
				Launch.stage.show();
	    	}catch(IOException event) {
	    		Logger.getLogger(Management_Login.class.getName()).log(Level.SEVERE, null, event);
	    	}
	    }
	//==================================================
	@FXML
	private void Sign_In(ActionEvent event) {
		if(Validate_SignIn()) {
			accounts = aeb.getList();
			for(int i = 0; i < accounts.size(); i++) {
				if(admin_Username.getText().equals(accounts.get(i + 1).getTenTK())) {
					if(admin_Password.getText().equals(accounts.get(i + 1).getMk())) {
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/MainFrame.fxml"));
							Parent root = loader.load();
							Scene scene = new Scene(root);
							scene.setFill(Color.TRANSPARENT);
							Launch.stage.setScene(scene);
							Launch.stage.show();
							
							admin_Controls ac = loader.<admin_Controls>getController();
							ac.createInterface(accounts.get(i + 1));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return;
					}
					else {
						if(counter == 3) {
							Information("Mật Khẩu Nhân Viên", "Mật khẩu gợi ý: " +  accounts.get(i + 1).getMk().substring(4, 8), "");
							return;
						}
						else {
							counter++;
							Error("Lỗi Đăng Nhập", "Tài Khoản Không Tồn Tại", "");
							return;
						}
						
					}
				}
				
				else if(i + 1 == accounts.size()) {
					counter++;
					Error("Lỗi Đăng Nhập", "Tài Khoản Không Tồn Tại", "");
					return;
				}
				
			}
		}
	}
	
	private boolean Validate_SignIn() {
		if(admin_Username.getText().equals("")) {
			Error("Lỗi Tên Đăng Nhập", "Tên Đăng Nhập Không Được Để Trống", "");
			return false;
		}
		else if(admin_Password.getText().equals("")) {
			Error("Lỗi Mật Khẩu", "Mật Khẩu Không Được Để Trống", "");
			return false;
		}
		return true;
	}
    
    @FXML
    private void Drag_Stage(MouseEvent event) {
    	anchor_Pane.setOnMousePressed(e -> {
    		xOffsets =  e.getSceneX();
    		yOffsets =  e.getSceneY();
    	});
    	anchor_Pane.setOnMouseDragged(e -> {
    		Launch.stage.setX(e.getScreenX() - xOffsets);
    		Launch.stage.setY(e.getScreenY() - yOffsets);
    		Launch.stage.setOpacity(0.8f);
    	});
    	anchor_Pane.setOnDragDone(e -> {
    		Launch.stage.setOpacity(1.0f);
    	});
    	anchor_Pane.setOnMouseReleased(e -> {
    		Launch.stage.setOpacity(1.0f);
    	});
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
    
}
