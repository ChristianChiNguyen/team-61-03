package application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/** Represents the Controller for Main page, including two buttons: one
 * redirects user to Create Journal Entry and one to redirects user to
 * search for old Journal Entries
 */
public class MainController implements Initializable{
	
	ChangeStage changeStage = new ChangeStage();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	/** Redirects user to Login page when clicking "Log out" button */
	@FXML
	public void Logout(ActionEvent event) throws Exception {
		String viewDirectory = "/application/Login.fxml";
		changeStage.show(viewDirectory, event);
	}
	
	/** Redirects user to Create New Journal Entry page when clicking "Create a journal entry" button */
	@FXML
    void createNewJournalEntry(ActionEvent event) throws Exception {
    	String viewDirectory = "/application/CreateJournal.fxml";
    	changeStage.show(viewDirectory, event);
    }
	/** Redirects user to Search Entry page when clicking "Search a journal entry" button */
	@FXML
    void searchJournalEntry(ActionEvent event) throws Exception {
    	String viewDirectory = "/application/SearchJournal.fxml";
    	changeStage.show(viewDirectory, event);
    }
	
}


