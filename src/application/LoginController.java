package application;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	public LoginModel timeConsuleModel = new LoginModel();
	
	@FXML
	private Label isConnected;
	
	@FXML
	private TextField passWord;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if (timeConsuleModel.isDbConnected()) {
			isConnected.setText("TimeCapsule");
		} else {
			isConnected.setText("Not Connected");
		}
		
	}
	
	public void Login (ActionEvent event) throws IOException {
		try {
			if (timeConsuleModel.isLogin(passWord.getText())) {
				isConnected.setText("Password is correct!");
				
				//close current stage
				Node source = (Node) event.getSource();
				Stage currStage = (Stage) source.getScene().getWindow();
				currStage.close();
				
				//show new stage
				Stage primaryStage = new Stage();
				Pane root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
			} else {
				isConnected.setText("Password is not correct!");
			}
		} catch (SQLException e) {
			isConnected.setText("Password is not correct!");
			// TODO
			e.printStackTrace();
		}
	}
	
}
	
	

	
