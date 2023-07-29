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
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchJournalController implements Initializable{
	
	private JournalModel journalModel = new JournalModel();
	private ChangeStage changeStage = new ChangeStage();
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	journalModel = new JournalModel();
//        contextJournal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().));
//        contentJournal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        contextJournal.setCellValueFactory(new PropertyValueFactory<>("Context"));
        contentJournal.setCellValueFactory(new PropertyValueFactory<>("Content"));
	}
    
    @FXML
    private TableView<Map<String, Object>> journalTableView;

    @FXML
    private TableColumn<Map<String, Object>, String> contextJournal;

    @FXML
    private TableColumn<Map<String, Object>, String> contentJournal;

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
    	String viewDirectory = "/application/Main.fxml";
    	changeStage.show(viewDirectory, event);
    }
    
}
