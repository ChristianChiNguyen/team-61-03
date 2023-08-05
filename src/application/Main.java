/** Introduction: the main goal of the application is to store journal entries.
 * The application applies MVC Model (Model-View-Control)
 * Version 1. features:
 * . Log in first time
 * . Logout
 * . Change password
 * . Reset password (forgot password) based on security question and answer
 * . Create new journal entry
 * */

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/** Represents the Main class of the application */
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Load the login page
			Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
