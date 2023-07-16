package application;
import javafx.scene.control.*;

import java.io.IOException;
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

	}
	
	@FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField securityQuestionField;
    @FXML
    private TextField securityAnswerField;
    
    public void changeHandler() throws IOException
    {
        try {
        	String oldPassword = oldPasswordField.getText();
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            
            if ( !appModel.isFirstLogin(oldPassword)) {
            	msg.setText("Incorrect old password");
            }
            if( !newPassword.equals(confirmPassword)){
            	msg.setText("Confirm password didn't match");
            }
            
            if (appModel.updatePassword(oldPassword,confirmPassword))
            {
            	msg.setText("Password changed successfully");
            }
            
        }catch (SQLException e) {
        	e.printStackTrace();
        }
        

    }
    
}
