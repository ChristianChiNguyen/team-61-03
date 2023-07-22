package application;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ForgotPassController {
	
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
    
    public void initialize() {
	    ObservableList<String> items =FXCollections.observableArrayList ("First pet's name?", "Mother's maiden name?", "City where you were born?");
	    securityQuestionField.setItems(items);
	}    

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
 
    // Back button that takes the user from change password screen to main login screen
    @FXML
    public void Back(ActionEvent event) throws Exception {
    	String viewDirectory = "/application/Login.fxml";
    	changeStage.show(viewDirectory, event);
    }
}
