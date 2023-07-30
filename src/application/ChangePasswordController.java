package application;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public void changeHandler() throws Exception
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
                	msg.setText("Password changed successfully!");
                	Stage currentStage = (Stage) oldPasswordField.getScene().getWindow();
                    // Load the login fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Login.fxml"));
                    Parent root = loader.load();
                    // Set the login fxml on the current stage
                    currentStage.setScene(new Scene(root));
                }
            }
            
        }catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    /** Function to redirects user to Login page when clicking "Back" button */
    @FXML
    public void Back(ActionEvent event) throws Exception {
    	String viewDirectory = "/application/Login.fxml";
    	changeStage.show(viewDirectory, event);
    }
    
}
