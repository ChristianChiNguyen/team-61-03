package application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    
    //Function to update password when clicking Change Password
    @FXML
    public void changeHandler() throws IOException
    {
        try {
        	String oldPassword = oldPasswordField.getText();
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            
            if( !newPassword.equals(confirmPassword)){
            	msg.setText("Confirming new password didn't match!");
            } else {
            	if (appModel.updatePassword(oldPassword,confirmPassword))
                {
                	msg.setText("Password changed successfully!");
                }
            }
            
        }catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    //Log out function when clicking the Log Out button
    @FXML
    public void Logout(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        loginStage.setScene(scene);
        loginStage.show();
    }
    
}
