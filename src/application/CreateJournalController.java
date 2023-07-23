package application;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    private TextField journalContext;
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
    public void createJournal(ActionEvent event) throws IOException{
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
            		msg.setText("Journal entry has been saved successfully!");
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    /** Function to redirects user back to Main page when clicking "Cancel" */
    @FXML
    public void Cancel(ActionEvent event) throws Exception {
    	String viewDirectory = "/application/Main.fxml";
    	changeStage.show(viewDirectory, event);
    }
}
