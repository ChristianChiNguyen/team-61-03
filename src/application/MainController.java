package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainController implements Initializable{
	
	ChangeStage changeStage = new ChangeStage();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	@FXML
	public void Logout(ActionEvent event) throws Exception {
		String viewDirectory = "/application/Login.fxml";
		changeStage.show(viewDirectory, event);
	}
	@FXML
    void createNewJournalEntry(ActionEvent event) throws IOException {
        Parent createJournalParent = FXMLLoader.load(getClass().getResource("/application/CreateJournal.fxml"));
        Scene createJournalScene = new Scene(createJournalParent);

        // This line gets the stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        window.setScene(createJournalScene);
        window.show();
    }
	
	}


