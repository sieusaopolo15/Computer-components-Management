package GUI.Forms.User_Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launch extends Application {
	
	public static Stage stage;
	
	public static void main(String []args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		stage.setScene(scene);
		Launch.stage = stage;
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
	}
	
	
}
