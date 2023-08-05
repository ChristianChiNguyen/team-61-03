package application;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/** Represents the Controller for Create Journal page */
public class CreateJournalController implements Initializable {    
	
	private ChangeStage changeStage = new ChangeStage();
	
	private JournalModel journalModel = new JournalModel();
    
    @FXML
    private Label msg;
	@FXML
    private TextField title;
    @FXML
    private TextArea journalContext;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField timePicker;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		datePicker.setValue(LocalDate.now());
	    timePicker.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		}

    /** Function to create new Journal Entry */
    public void createJournal(ActionEvent event) throws Exception{
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
            } else if ( journalModel.addJournal(string_title, journal_context, dateTime)){
            	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation");
                confirmationAlert.setHeaderText("New journal entry has been saved!");
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
    
    /** Function to redirects user back to Main page when clicking "Cancel" */
    @FXML
    public void Cancel(ActionEvent event) throws Exception {
    	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to cancel?");
        confirmationAlert.setContentText("Any unsaved changes will be lost.");

        // Show the confirmation page and see what user responds with 
        ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);
        
        //if user selects "ok", take the user back to main.fxml
        if (result == ButtonType.OK) {
        	String viewDirectory = "/application/Main.fxml";
        	changeStage.show(viewDirectory, event);
        }
    }
}
