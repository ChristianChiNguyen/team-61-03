package application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.sql.*;


/** Represents the Controller for ChangePassword page */
public class ChangePasswordController implements Initializable {
	
	private ChangeStage changeStage = new ChangeStage();
	
	private AppModel appModel = new AppModel();
	
	@FXML
	private Label msg;
	@FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private ComboBox<String> securityQuestionField;
    @FXML
    private TextField securityAnswerField;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	    ObservableList<String> items =FXCollections.observableArrayList ("First pet's name?", "Mother's maiden name?", "City where you were born?");
	    securityQuestionField.setItems(items);
	}
	
    /** Function to update password when clicking "Change Password" 
     * @throws Exception */
    @FXML
    public void changeHandler(ActionEvent event) throws Exception
    {
        try {
        	String oldPassword = oldPasswordField.getText();
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            String securityQuestion = securityQuestionField.getValue();
            String securityAnswer = securityAnswerField.getText();
            
            if (!appModel.checkOldPassword(oldPassword)) {
            	msg.setText("Old password did not match!");
            } else if (!newPassword.equals(confirmPassword)) {
            	msg.setText("Confirming new password didn't match!");
            } else if (securityQuestion == null || securityQuestion.isEmpty()) {
                msg.setText("Please select a security question!");
            } else if (securityAnswer.isEmpty()) {
                msg.setText("Please answer the security question!");
            } else {
            	if (appModel.updatePassword(newPassword, securityQuestion, securityAnswer))
                {
                	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
                    confirmationAlert.setTitle("Confirmation");
                    confirmationAlert.setHeaderText("New password, security question and answer have been saved!");
                    confirmationAlert.setContentText("Do you want to go back to Login page?");

                    // Show the confirmation page and see what user responds with 
                    ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);
                    
                    //if user selects "ok", take the user back to Login.fxml
                    if (result == ButtonType.OK) {
                    	String viewDirectory = "/application/Login.fxml";
                    	changeStage.show(viewDirectory, event);
                    }
                }
            }
            
        }catch (SQLException e) {
        	e.printStackTrace();
        	msg.setText("Failed to reset password!");
        }
    }
    
    /** Function to redirects user to Login page when clicking "Back" button */
    @FXML
    public void Back(ActionEvent event) throws Exception {
    	String viewDirectory = "/application/Login.fxml";
    	changeStage.show(viewDirectory, event);
    }
    
}
