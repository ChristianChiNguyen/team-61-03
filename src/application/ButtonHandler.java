package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/** Class to handle the button "Select" for each jounrnal entry after search 
 * and redirects user to Edit Journal page.
 */
public class ButtonHandler implements EventHandler<ActionEvent> {
	private EditJournalController editJournalController;

    /** Constructor to create a ButtonHandler with input Journal
     * @param journal each buttonHandler is assigned to a journal entry
     */
    public ButtonHandler(Journal journal) {
        this.editJournalController = new EditJournalController(journal);
    }

    @Override
    public void handle(ActionEvent event) {
    	// Handler event to change stage to EditJournal
    	String viewDirectory = "/application/EditJournal.fxml";
        ChangeStage changeStage = new ChangeStage();
        try {
			changeStage.show(viewDirectory, event, editJournalController);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
