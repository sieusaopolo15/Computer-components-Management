package Controllers;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import java.sql.Connection;

import DAL.MyConnectUnit;
import DTO.Account_Employee;
import DTO.Customer;
import DTO.Employee;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class admin_Controls implements Initializable {
	
	Management_Login ml = new Management_Login();
	
	/*	CÁC CONTROLS */
		@FXML 
		private AnchorPane anchor_Pane;
		
		@FXML
	    private Tab system_Management;
		
		@FXML
		private StackPane stack_Pane;
		
		@FXML
		private AnchorPane anchor_Root;
	
	    @FXML
	    private JFXButton user_Management;
	
	    @FXML
	    private JFXButton employee_Management;
	    
	    @FXML
	    private JFXButton product_Management;
	    
	    @FXML
	    private JFXButton storage_Management;
	    
	    @FXML
	    private JFXButton supplier_Management;
	    
	    @FXML
	    private JFXButton bill_Management;
	    
	    @FXML
	    private JFXButton sales;
	
	    @FXML
	    private JFXButton sign_Out;
	
	    @FXML
	    private JFXButton exit;
	
	    @FXML
	    private JFXButton back_Up;
	
	    @FXML
	    private JFXButton recovery;
	
	    @FXML
	    private Pane main_pane;
    
    private Parent loader;
    
    private double x, y;
    
    public int id;
    
    private String []a = {
    	"Quản Lý",
    	"Thu Ngân", 
    	"Kiểm Kho",
    	"Nhân Viên Hỗ Trợ",
    	"Nhân Viên Giao Hàng"
    };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	ToggleDisable();
    }
    
    @FXML 
    private void QuanLy_NguoiDung() throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/User_Management.fxml"));
    	SwitchScene(loader);
    	
    	user_Management um = loader.<user_Management>getController();
    	um.Receiver(this.id);
    }
    
    @FXML
    private void QuanLy_Kho() {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Storage.fxml"));
    	SwitchScene(loader);
    	
    	Storage_Management sm = loader.<Storage_Management>getController();
    	sm.Receiver(this.id);
    }
    
    @FXML
    private void QuanLy_SanPham() throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Product.fxml"));
    	SwitchScene(loader);
    }
    
    @FXML
    private void QuanLy_NCC() throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Supplier.fxml"));
    	SwitchScene(loader);
    }
    
    
    @FXML
    private void QuanLy_NhanVien() {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Employee_Management.fxml"));
    	SwitchScene(loader);
    }
    
    @FXML
    private void Sales_Report() {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Chart_Sales.fxml"));
    	SwitchScene(loader);
    }
    
   
    
    
    @FXML
    private void QuanLy_DonHang() {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Frames/Order.fxml"));
    	SwitchScene(loader);
    }
    
    public void createInterface(Account_Employee ac) {
    	
    	this.id = ac.getMaNV();
    	if(ac.getQuyen().equals("Quản Lý")) {
    		ToggleAvailable(user_Management);
			ToggleAvailable(employee_Management);
			ToggleAvailable(storage_Management);
			ToggleAvailable(product_Management);
			ToggleAvailable(supplier_Management);
			ToggleAvailable(bill_Management);
			ToggleAvailable(sales);
    	}
    	
    	else if(ac.getQuyen().equals("Thu Ngân")) {
			ToggleAvailable(user_Management);
			ToggleAvailable(bill_Management);
		}
    	
    	else if(ac.getQuyen().equals("Kiểm Kho")) {
    		ToggleAvailable(storage_Management);
			ToggleAvailable(product_Management);
			ToggleAvailable(supplier_Management);
    	}
    	
    	else if(ac.getQuyen().equals("Nhân Viên Hỗ Trợ")) {
    		ToggleAvailable(user_Management);
    	}
    	
    	else if(ac.getQuyen().equals("Nhân Viên Giao Hàng")) {
    		ToggleAvailable(bill_Management);
    	}
    }
    
    
    
    private void SwitchScene(FXMLLoader loader) {
    	try {
    		stack_Pane.getChildren().remove(this.loader);
    		this.loader = loader.load();
    		 		
    		
			Scene scene = employee_Management.getScene();
			this.loader.translateXProperty().set(scene.getWidth());
			stack_Pane.getChildren().add(this.loader);
			
			Timeline timeline = new Timeline();
			KeyValue kv = new KeyValue(this.loader.translateXProperty(), 0, Interpolator.EASE_IN);
			KeyFrame kf = new KeyFrame(Duration.seconds(0.6), kv);
			timeline.getKeyFrames().add(kf);
			timeline.play();
		}catch(IOException event) {
			Logger.getLogger(admin_Controls.class.getName()).log(Level.SEVERE, null, event);
		}
    }
    
    private void ToggleAvailable(JFXButton button) {
    	button.setDisable(false);
    }
    
    private void ToggleDisable() {
    	user_Management.setDisable(true);
    	employee_Management.setDisable(true);
    	storage_Management.setDisable(true);
    	product_Management.setDisable(true);
    	supplier_Management.setDisable(true);
    	bill_Management.setDisable(true);
    	sales.setDisable(true);
    }
    
    @FXML 
    private void Close(ActionEvent event) {
    	Launch.stage.close();
    }
    
    @FXML 
    private void Sign_Out(ActionEvent event) {
    	ml.Sign_Out(Launch.stage);
    }
    
    @FXML
    private void Drag_Stage(MouseEvent event) {
    	anchor_Pane.setOnMousePressed(e -> {
    		x = e.getSceneX();
    		y = e.getSceneY();
    	});
    	anchor_Pane.setOnMouseDragged(e -> {
    		Launch.stage.setX(e.getScreenX() - x);
    		Launch.stage.setY(e.getScreenY() - y);
    		Launch.stage.setOpacity(0.8f);
    	});
    	anchor_Pane.setOnDragDone(e -> {
    		Launch.stage.setOpacity(1.0f);
    	});
    	anchor_Pane.setOnMouseReleased(e -> {
    		Launch.stage.setOpacity(1.0f);
    	});
    }
}
