package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	 @FXML
	    public void Logout(ActionEvent event) throws Exception {
	        // get the current stage
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

	        // close the current (Main) window
	        stage.close();

	        // open the Login window
	        Stage loginStage = new Stage();
	        Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
	        Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        loginStage.setScene(scene);
	        loginStage.show();
	    }
	

	}


