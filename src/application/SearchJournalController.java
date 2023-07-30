package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class SearchJournalController implements Initializable{
	
	private JournalModel journalModel = new JournalModel();
	private ChangeStage changeStage = new ChangeStage();
	
	@FXML
    private TableView<Journal> journalTableView;
	
	@FXML
    private TableColumn<Journal, Integer> id;

    @FXML
    private TableColumn<Journal, String> title;

    @FXML
    private TableColumn<Journal, String> journalContext;
    
    @FXML
    private TableColumn<Journal, String> created;

    @FXML
    private TextField searchTitle;

    @FXML
    private TextField searchContext;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	journalModel = new JournalModel();
    	journalTableView.setPlaceholder(new Label("No result found!"));
    	id.setCellValueFactory(new PropertyValueFactory<Journal, Integer>("id"));
    	title.setCellValueFactory(new PropertyValueFactory<Journal, String>("title"));
    	journalContext.setCellValueFactory(new PropertyValueFactory<Journal, String>("journalContext"));
    	created.setCellValueFactory(new PropertyValueFactory<Journal, String>("created"));
	}
    
    public void onSearchButtonClicked(ActionEvent event) throws IOException{
        // Perform the search using the JournalModel
    	try {
            ArrayList<Journal> searchResult = journalModel.searchJournal(searchTitle.getText(),searchContext.getText());
            // Clear existing data in the TableView each time search is clicked
            journalTableView.getItems().clear();
            
            // Populate the TableView with the search results
            final ObservableList<Journal> data = FXCollections.observableArrayList(searchResult);
            journalTableView.setItems(data);
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
