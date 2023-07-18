package application;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.sql.*;


public class ChangePasswordController implements Initializable {
	
	public AppModel appModel = new AppModel();
	
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
    // Function to update password when clicking Change Password
    @FXML
    public void changeHandler() throws IOException
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
                }
            }
            
        }catch (SQLException e) {
        	e.printStackTrace();
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
