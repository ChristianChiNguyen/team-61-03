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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	public AppModel appModel = new AppModel();
	
	@FXML
	private Label isConnected;
	
	@FXML
	private PasswordField passWord;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Initialize and check if the database is connected
		if (appModel.isDbConnected()) {
			isConnected.setText("Connected");
		} else {
			isConnected.setText("Not Connected");
		}
	}
	
	public void logIn (ActionEvent event) throws IOException {
		try {
			if (appModel.isFirstLogin(passWord.getText())) {
				isConnected.setText("First time logged in!");
				
				//close current stage
				Node source = (Node) event.getSource();
				Stage currStage = (Stage) source.getScene().getWindow();
				currStage.close();
				
				//show new stage
				Stage primaryStage = new Stage();
				Pane root = FXMLLoader.load(getClass().getResource("/application/ChangePassword.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
			} else {
				if (appModel.isLogin(passWord.getText())) {
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
			}
			
		} catch (SQLException e) {
			isConnected.setText("Password is not correct!");
			e.printStackTrace();
		}
	}
	
	public void changePassword (ActionEvent event) throws IOException {
		try {
			
			//close current stage
			Node source = (Node) event.getSource();
			Stage currStage = (Stage) source.getScene().getWindow();
			currStage.close();
			
			//show new stage
			Stage primaryStage = new Stage();
			Pane root = FXMLLoader.load(getClass().getResource("/application/ChangePassword.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void forgotPassword (ActionEvent event) {
		
	}
	
}
	
	

	
