package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Represents the Model for Create Journal page. Clients can
 * create journal entry into database with title (optional), date,
 * time and journal context. */
public class JournalModel {
	 Connection conn;
	 
	 /** Constructor of JournalModel */
	 public JournalModel() {
	      	conn = DbConnection.Connector();
	        if (conn == null) {
	            System.out.println("Unsuccessful connection1");
	            System.exit(1);
	        }
	 }
	
	 /** Function to check if the database is connected */
	 public boolean isDbConnected() {
			try {
				return !conn.isClosed();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	 }
	 
	 /** Function to create a new journal entry
	  * @param title user can optionally input title
	  * @param context user must enter journal context
	  * @param dateTime user must enter data and time
	  * @return boolean check if journal has been added into database successfully */
	 public boolean addJournal(String title, String context, String dateTime) throws SQLException {
			PreparedStatement preparedStatement = null;
			String query = "INSERT INTO journal (title, journal_context, datetime_created) VALUES (?, ?, ?)";
		 
			try {
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1,title);
				preparedStatement.setString(2,context);
				preparedStatement.setObject(3, dateTime);

			
				int rowsInserted = preparedStatement.executeUpdate();
				if ( rowsInserted > 0 ) {
					return true;
				}
				else {
					return false;
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		 
		 return false;
	 }
	 /** Function to search a journal entry
	  * @param title user can optionally input title
	  * @param context user must enter journal context
	  * @param dateTime user must enter data and time
	  * @return ArrayList with Map objects
	  */ 
	 public ArrayList<Journal> searchJournal(String title, String context) throws SQLException {
		    PreparedStatement preparedStatement = null;
	      	ArrayList<Journal> journalList = new ArrayList<>(); 
	      	String query = "SELECT * FROM journal WHERE title LIKE ? AND journal_context LIKE ?";

	        try {
	        	preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1,"%" + title + "%");
				preparedStatement.setString(2,"%" + context + "%");
	            ResultSet rs = preparedStatement.executeQuery();
	            
	            while (rs.next()) {
	            	Integer id = rs.getInt("journal_id");
	            	String journalTitle = rs.getString("title");
	            	String journalContext = rs.getString("journal_context");
	            	System.out.println(journalContext);
	            	String created = rs.getString("datetime_created");
	            	
	                Journal journal = new Journal(id, journalTitle, journalContext, created);
	                journalList.add(journal);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return journalList;
	 }
	 
	 /** Function to update a journal entry
	  * @param context user must enter journal context
	  * @param title user can optionally input title
	  * @param dateTime user can input the new date or time
	  * @param _id id to search for the entry
	  * @return boolean check if the journal was updated or not.
	  */ 
	  public boolean updateJournalEntry(int id, String context, String title,String dateTime) throws SQLException {
           PreparedStatement stmt = conn.prepareStatement("UPDATE journal SET journal_context = ?, title= ?, datetime_created= ? WHERE id = ?");
	       try {
	           stmt.setString(1, context);
	           stmt.setString(2, title);
	           stmt.setString(3, dateTime);
	           stmt.setInt(4, id);

	           int rowsAffected = stmt.executeUpdate();

	           // Return true if at least one row was updated
	           return (rowsAffected > 0); 

	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	       // Return false if the update failed
	       return false; 
	    }
	  
	  /** Function to delete a journal entry based on the id
	   * @param _id id to search for the entry and delete it later
	   * @return boolean check if the journal was deleted or not.
	   */ 
	   public boolean deleteJournalEntry(int id) throws SQLException{
           PreparedStatement stmt = conn.prepareStatement("DELETE FROM journal WHERE id = ?");
	       try {
	           stmt.setInt(1, id);
	           int rowsAffected = stmt.executeUpdate();
	           
	           // Return true if at least one row was deleted
	           return rowsAffected > 0; 
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	       // Return false if the deletion failed
	       return false; 
	    }	 
	 
	 
}
