package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launch extends Application {
	
	public static Stage stage = null;
	public static String text = null;
	
	public static void main(String []args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//Parent root = FXMLLoader.load(getClass().getResource("../GUI/Frames/MainFrame.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("../GUI/Forms/Management_Login/LoginForm.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("../GUI/Buying/MainFrame.fxml"));
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		Launch.stage = stage;
		stage.show();
	}
	
	
}
