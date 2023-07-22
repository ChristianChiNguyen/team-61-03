package application;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotPassController {
	
    public AppModel appModel = new AppModel();
	
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
