package application;
import javafx.beans.property.*;
import javafx.scene.control.Button;

public class Journal {
	private SimpleIntegerProperty id;
	private SimpleStringProperty title;
	private SimpleStringProperty journalContext;
	private SimpleStringProperty created;
	private Button button;
	
	public Journal() {
		
	}
	
	public Journal(Integer id, String title, String journalContext, String created) {
		this.id = new SimpleIntegerProperty(id);
		this.title = new SimpleStringProperty(title);;
		this.journalContext = new SimpleStringProperty(journalContext);;
		this.created = new SimpleStringProperty(created);
		this.button = new Button("Select");
	}
	
	/** 
	 * @return the journal_id
	 * */
	public Integer getId() {
		return this.id.get();
	}
	
	/** 
	 * @param id the journal_id to set
	 * */
	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
	
	
	/** 
	 * @return the title
	 * */
	public String getTitle() {
		return this.title.get();
	}
	
	/** 
	 * @param title the title to set
	 * */
	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	}
	
	/** 
	 * @return the journal_context
	 * */
	public String getJournalContext() {
		return this.journalContext.get();
	}
	
	/** 
	 * @param journalContext the journal_context to set
	 * */
	public void setJournalContext(String journalContext) {
		this.journalContext = new SimpleStringProperty(journalContext);
	}
	
	/** 
	 * @return the date/time created
	 * */
	public String getCreated() {
		return this.created.get();
	}
	
	/** 
	 * @param created the created (date/time) to set
	 * */
	public void setCreated(String created) {
		this.created = new SimpleStringProperty(created);
	}
	
	/** 
	 * @return the select button to Edit/Delete journal entry
	 * */
	public Button getButton() {
		return this.button;
	}
	
	/** 
	 * @param button the select button to set
	 * */
	public void setButton(Button button) {
		this.button = button;
	}
	
	

}
