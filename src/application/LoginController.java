package application;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class LoginController implements Initializable {
	
	private ChangeStage changeStage = new ChangeStage();
	
	private AppModel appModel = new AppModel();
	
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
	
	// handles the event where the user needs to login
	public void logIn (ActionEvent event) throws Exception {
		try {
			if (appModel.isFirstLogin(passWord.getText())) {
				isConnected.setText("First time logged in!");
				
				// if first time logged in, redirects user to change password
		    	String viewDirectory = "/application/ChangePassword.fxml";
		    	changeStage.show(viewDirectory, event);
				
			} else {
				if (appModel.isLogin(passWord.getText())) {
					isConnected.setText("Password is correct!");
					
					// if successfully logged in, redirects user to Main page
			    	String viewDirectory = "/application/Main.fxml";
			    	changeStage.show(viewDirectory, event);
					
				} else {
					isConnected.setText("Password is not correct!");
				}
			}
			
		} catch (SQLException e) {
			isConnected.setText("Password is not correct!");
			e.printStackTrace();
		}
	}
	
	// handles the event where the user click "ChangePassword" button
	public void changePassword (ActionEvent event) throws Exception {
		try {
			
			String viewDirectory = "/application/ChangePassword.fxml";
	    	changeStage.show(viewDirectory, event);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	// handles the event where the user clicks "ForgotPassword" button
	public void forgotPassword (ActionEvent event) throws Exception {
		 try {
			 	String viewDirectory = "/application/ForgotPassword.fxml";
		    	changeStage.show(viewDirectory, event);
		    	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
}
