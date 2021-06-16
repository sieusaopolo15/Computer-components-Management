package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import DTO.Customer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class BuyingController implements Initializable {

	@FXML
	private AnchorPane anchor_Pane;
	
    @FXML
    private Label user_FullName;

    @FXML
    private JFXButton imageChooser;

    @FXML
    private JFXTextField searchBar;

    @FXML
    private JFXButton buy;

    @FXML
    private JFXButton cart;

    @FXML
    private JFXButton invoice;

    @FXML
    private JFXButton sign_Out;

    @FXML
    private JFXButton exit;

    @FXML
    private TableView<?> products;

    @FXML
    private ImageView product_Image;
    
    @FXML
    private StackPane stack_Pane;
    
    private int c;
    private Parent loader;
    private double x, y;
    
    private FXMLLoader load = new FXMLLoader(getClass().getResource("../GUI/Buying/Product.fxml"));
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	try {
    		stack_Pane.getChildren().remove(loader);
    		loader = load.load();
			Scene scene = new Scene(loader);
			loader.translateXProperty().set(scene.getWidth());
			stack_Pane.getChildren().add(loader);
			
			Timeline timeline = new Timeline();
			KeyValue kv = new KeyValue(loader.translateXProperty(), 0, Interpolator.EASE_IN);
			KeyFrame kf = new KeyFrame(Duration.seconds(1.0), kv);
			timeline.getKeyFrames().add(kf);
			timeline.play();
			
			
		}catch(IOException e) {
			Logger.getLogger(BuyingController.class.getName()).log(Level.SEVERE, null, e);
		}
    }
    
    @FXML
    private void Close_Button(ActionEvent event) {
    	Launch.stage.close();
    }
    
    //SHOPPING STAGE
	    @FXML
	    private void Buying_Scene() {
	    	FXMLLoader load = new FXMLLoader(getClass().getResource("../GUI/Buying/Product.fxml"));
	    	try {
	    		stack_Pane.getChildren().remove(loader);
	    		loader = load.load();
				Scene scene = new Scene(loader);
				loader.translateXProperty().set(scene.getWidth());
				stack_Pane.getChildren().add(loader);
				
				Timeline timeline = new Timeline();
				KeyValue kv = new KeyValue(loader.translateXProperty(), 0, Interpolator.EASE_IN);
				KeyFrame kf = new KeyFrame(Duration.seconds(1.0), kv);
				timeline.getKeyFrames().add(kf);
				timeline.play();
				
		    	Products_Controller pc = load.<Products_Controller>getController();
				pc.Data_Transfer(this.c);
				
				
			}catch(IOException e) {
				Logger.getLogger(BuyingController.class.getName()).log(Level.SEVERE, null, e);
			}
	    }
    //=======================================================================================
	   
	//INFORMATION STAGE
	    @FXML
	    private void Information() {
	    	try {
	    		FXMLLoader load = new FXMLLoader(getClass().getResource("../GUI/Buying/Information.fxml"));
	    		stack_Pane.getChildren().remove(loader);
	    		loader = load.load();
	    		
	    		Information_Controller ic = load.<Information_Controller>getController();
	    		ic.Transfer(this.c);
	    		
				Scene scene = new Scene(loader);
				loader.translateXProperty().set(scene.getWidth());
				stack_Pane.getChildren().add(loader);
				
				Timeline timeline = new Timeline();
				KeyValue kv = new KeyValue(loader.translateXProperty(), 0, Interpolator.EASE_IN);
				KeyFrame kf = new KeyFrame(Duration.seconds(1.0), kv);
				timeline.getKeyFrames().add(kf);
				timeline.play();
				
				
			}catch(IOException e) {
				Logger.getLogger(BuyingController.class.getName()).log(Level.SEVERE, null, e);
			}
	    }
	//=======================================================================================
    
	//ORDERS STAGE
	    @FXML
	    private void Check_Order() {
	    	try {
	    		FXMLLoader load = new FXMLLoader(getClass().getResource("../GUI/Buying/Bill.fxml"));
	    		stack_Pane.getChildren().remove(loader);
	    		loader = load.load();
	    		
	    		Check_Orders_Controller coc = load.<Check_Orders_Controller>getController();
	    		coc.Receiver(this.c);
	    		
				Scene scene = new Scene(loader);
				loader.translateXProperty().set(scene.getWidth());
				stack_Pane.getChildren().add(loader);
				
				Timeline timeline = new Timeline();
				KeyValue kv = new KeyValue(loader.translateXProperty(), 0, Interpolator.EASE_IN);
				KeyFrame kf = new KeyFrame(Duration.seconds(1.0), kv);
				timeline.getKeyFrames().add(kf);
				timeline.play();
				
				
			}catch(IOException e) {
				Logger.getLogger(BuyingController.class.getName()).log(Level.SEVERE, null, e);
			}
	    }
	//=======================================================================================
	    
    //TRANSFER CUSTOMER DATA TO BUYING FRAME
	    public void Login(Customer c) {
	    	
	    	this.c = c.getMaKH();
	    	
	    	user_FullName.setText("Xin Chào, " + c.getHoTenKH());
	    
	    	Products_Controller pc = load.<Products_Controller>getController();
			pc.Data_Transfer(this.c);
	    }
    //========================================================
    
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
