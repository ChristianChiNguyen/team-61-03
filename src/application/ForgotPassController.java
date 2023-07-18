package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

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


    public void resetPassword(ActionEvent event) {
        // get selected question
		String securityQuestion = securityQuestionField.getValue();
		String securityAnswer = securityAnswerField.getText();
		String newPassword = newPasswordField.getText();
		String confirmPassword = confirmPasswordField.getText();

		//check if new passwords match
		if (newPassword.equals(confirmPassword)) {
		    if (appModel.updatePassword(newPassword, securityQuestion, securityAnswer)) {
		        msg.setText("Password successfully changed");
		    } else {
		        msg.setText("Failed to change password");
		    }
		} else {
		    msg.setText("Passwords do not match");
		}
    }


    private boolean securityAnswerIsValid(String securityAnswer) {
        // Implement the logic to validate the security answer
        // Return true if the answer is valid, false otherwise
        return true;
    }

    private boolean newPasswordsMatch(String newPassword, String confirmPassword) {
        // Implement the logic to check if the new passwords match
        // Return true if they match, false otherwise
        return newPassword.equals(confirmPassword);
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
