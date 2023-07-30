package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/** Class to handle the button "Select" for each jounrnal entry after search 
 * and redirects user to Edit Journal page.
 */
public class ButtonHandler implements EventHandler<ActionEvent> {
	private Journal journal;

    public ButtonHandler(Journal journal) {
        this.journal = journal;
    }

    @Override
    public void handle(ActionEvent event) {
        ChangeStage changeStage = new ChangeStage();      
        try {
			changeStage.show(event, journal);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
