package application;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/** Represents the Controller for Edit Journal page */
public class EditJournalController implements Initializable {    
	
	private ChangeStage changeStage = new ChangeStage();
	
	private JournalModel journalModel = new JournalModel();
	
	private Journal journal = new Journal();
	
	public EditJournalController(Journal journal) {
        this.journal = journal;
    }

	@FXML
    private Label msg;
	@FXML
    private TextField title;
    @FXML
    private TextField journalContext;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField timePicker;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	title.setText(journal.getTitle());
    	journalContext.setText(journal.getJournalContext());
    	String dateTimeString = journal.getCreated();
    	LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    	LocalDate localDate = dateTime.toLocalDate();
    	String timeString = DateTimeFormatter.ofPattern("HH:mm:ss").format(dateTime);
    	
		datePicker.setValue(localDate);
	    timePicker.setText(timeString);
		}

    /** Function to edit Journal Entry */
    public void editJournal(ActionEvent event) throws Exception{
        String string_title = title.getText();
        String journal_context = journalContext.getText();
        String date = "";
        if (datePicker.getValue() != null) {
        	date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        String time = timePicker.getText();
        String dateTime = date + " " + time;
        
        try {
            if (journal_context.isEmpty()) {
                // Display an error message if context field is empty
                msg.setText("Please enter journal's context!");
            } else if (date.isEmpty()) {
            	// Display an error message if date field is empty
                msg.setText("Please enter date!");
            } else if (time.isEmpty()) {
            	// Display an error message if time field is empty
                msg.setText("Please enter time!");
            } else if ( journalModel.updateJournalEntry(journal.getId(), journal_context,string_title, dateTime)){
            	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation");
                confirmationAlert.setHeaderText("Journal entry has been updated!");
                confirmationAlert.setContentText("Do you want to go back to Main page?");

                // Show the confirmation page and see what user responds with 
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);
                
                //if user selects "ok", take the user back to main.fxml
                if (result == ButtonType.OK) {
                	String viewDirectory = "/application/Main.fxml";
                	changeStage.show(viewDirectory, event);
                }
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    /** Function to delete Journal Entry */
    @FXML
    public void deleteJournal(ActionEvent event) throws Exception{
    	try {
            if (journalModel.deleteJournalEntry(journal.getId())) {
            	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation");
                confirmationAlert.setHeaderText("Journal entry has been deleted!");
                confirmationAlert.setContentText("Back to Main page?");

                // Show the confirmation page and see what user responds with 
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);
                
                //if user selects "ok", take the user back to main.fxml
                if (result == ButtonType.OK) {
                	String viewDirectory = "/application/Main.fxml";
                	changeStage.show(viewDirectory, event);
                } else if (result == ButtonType.CANCEL) {
                	String viewDirectory = "/application/SearchJournal.fxml";
                	changeStage.show(viewDirectory, event);
                }
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    /** Function to redirects user back to Main page when clicking "Cancel" */
    @FXML
    public void Cancel(ActionEvent event) throws Exception {
    	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to cancel?");
        confirmationAlert.setContentText("Any unsaved changes will be lost.");

        // Show the confirmation page and see what user responds with 
        ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);
        
        //if user selects "ok", take the user back to SearchJournal.fxml
        if (result == ButtonType.OK) {
        	String viewDirectory = "/application/SearchJournal.fxml";
        	changeStage.show(viewDirectory, event);
        }
    }
}
