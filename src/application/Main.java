/** Introduction: the main goal of the application is to store journal entries.
 * The application applies MVC Model (Model-View-Control)
 * Version 0.2 features:
 * . Check if the user is first time logged in
 * . Ask the user to set the new password and security question and answer
 * . After setting the new password, user can log into main page using the new password
 * . After logging into the main page, user can log out and the application returns to log in page
 * */

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

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
