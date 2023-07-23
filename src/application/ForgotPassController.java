package application;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/** Represents the Controller for Forgot Password or Reset Password page */
public class ForgotPassController implements Initializable {
	
	private ChangeStage changeStage = new ChangeStage();
	
    private AppModel appModel = new AppModel();
	
    @FXML
    private TextField securityAnswerField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button resetPasswordButton;
    
    @FXML
    private ComboBox<String> securityQuestionField;
    
    @FXML
    private Label msg;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	    ObservableList<String> items =FXCollections.observableArrayList ("First pet's name?", "Mother's maiden name?", "City where you were born?");
	    securityQuestionField.setItems(items);
	}    

   /** Function to reset password when clicking "Reset Password" */
    @FXML
    public void resetPassword(ActionEvent event) throws SQLException {
        // get selected question
		String securityQuestion = securityQuestionField.getValue();
		String securityAnswer = securityAnswerField.getText();
		String newPassword = newPasswordField.getText();
		String confirmPassword = confirmPasswordField.getText();

		//check if new passwords match
		if (!newPassword.equals(confirmPassword)) {
		    msg.setText("Passwords do not match");
		}else if (!appModel.checkSecurityQuestion(securityQuestion)) {
			msg.setText("Security question did not match!");
	    } else if (!appModel.checkSecurityAnswer(securityAnswer)) {
			msg.setText("Security answer did not match!");	
	    } else if (appModel.updatePassword(newPassword, securityQuestion, securityAnswer)) {
		    msg.setText("Password successfully changed");
		} else {
		    msg.setText("Failed to change password");
		    }
    }
 
    /** Function to redirects user to Login screen when clicking "Back" button */
    @FXML
    public void Back(ActionEvent event) throws Exception {
    	String viewDirectory = "/application/Login.fxml";
    	changeStage.show(viewDirectory, event);
    }
}
