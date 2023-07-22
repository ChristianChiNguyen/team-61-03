package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateJournalController implements Initializable {
	JournalModel journalModel = new JournalModel();
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	private Label msg;
	@FXML
    private TextField contextJournal;
    @FXML
    private TextField contentJournal;

    @FXML
    // This will create a journal, will be fired when save button is clicked
    public void createJournal() throws IOException{
        String context = contextJournal.getText();
        String content = contentJournal.getText();
        
        try {
            if (content.isEmpty()) {
                // Display an error message if any field is empty
                msg.setText("Content not available");
            }
            else {
            	if ( journalModel.addJournal(context, content)){
            		msg.setText("Journal added successfully");
            	}
            }
        }catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    @FXML
    // Send the user back to the main page
    public void Cancel(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        loginStage.setScene(scene);
        loginStage.show();
    }
}
