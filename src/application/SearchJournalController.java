package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;


public class SearchJournalController implements Initializable{
	
	private JournalModel journalModel = new JournalModel();
	private ChangeStage changeStage = new ChangeStage();
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	journalModel = new JournalModel();
        contextJournal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("journal_context").toString()));
        titleJournal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("title").toString()));
	}
    
    @FXML
    private TableView<Map<String, Object>> journalTableView;

    @FXML
    private TableColumn<Map<String, Object>, String> contextJournal;

    @FXML
    private TableColumn<Map<String, Object>, String> titleJournal;

    @FXML
    private TextField searchContent;

    @FXML
    private TextField searchContext;
    
    
    public void onSearchButtonClicked(ActionEvent event) throws IOException{
        // Perform the search using the JournalModel
    	try {
            ArrayList<Map<String, Object>> searchResult = journalModel.searchJournal(searchContent.getText(),searchContext.getText());
            // Clear existing data in the TableView each time search is clicked
            journalTableView.getItems().clear();

            // Populate the TableView with the search results
            journalTableView.getItems().addAll(searchResult);
    	}catch (SQLException e) {
        	e.printStackTrace();
        }

    }
    
    //to do Edit Button Controller
    //to do Delete button controller
    
    /** Function to redirects user to Login page when clicking "Back" button */
    @FXML
    public void Back(ActionEvent event) throws Exception {
    	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to cancel?");

        // Show the confirmation page and see what user responds with 
        ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);
        
        //if user selects "ok", take the user back to main.fxml
        if (result == ButtonType.OK) {
        	String viewDirectory = "/application/Main.fxml";
        	changeStage.show(viewDirectory, event);
        }
    }
    
}
