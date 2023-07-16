package application;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.*;


public class ChangePasswordController implements Initializable {
	
	public AppModel appModel = new AppModel();
	
	@FXML
	private Label msg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (appModel.isDbConnected()) {
			msg.setText("Change password");
		} else {
			msg.setText("You are not connected");
		}
	}
	
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField securityQuestionField;
    
    public void changeHandler()
    {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String securityQuestion = securityQuestionField.getText();
        
        try { 
            if ( !appModel.isLogin(oldPassword)) {
            	msg.setText("Incorrect old password");
            }
            if( !newPasswordField.equals(confirmPassword)){
            	msg.setText("Confirm password didn't match");
            }
            
            if (appModel.updatePassword(oldPassword,confirmPassword))
            {
            	msg.setText("Password changed successfully");
            }
            
        }catch (SQLException e) {
        	System.out.println(e);
        }
        

    }
    
}
