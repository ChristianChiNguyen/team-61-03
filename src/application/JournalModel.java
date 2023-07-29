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
	  * @return Arraylist with Map objects
	  */ 
	 public ArrayList<Map<String, Object>> searchJournal(String title, String context) throws SQLException {
		 
		 	// I am leaving some space for a functionality of searching by title
		 
	      	ArrayList<Map<String, Object>> journalList = new ArrayList<>();
	      	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM journal WHERE journal_context LIKE ?");
	      	stmt.setString(1, "%" + context + "%");
	      	
	        try {	
	            ResultSet rs = stmt.executeQuery();
	            ResultSetMetaData metaData = rs.getMetaData();
	            int columnCount = metaData.getColumnCount();

	            while (rs.next()) {
	                Map<String, Object> journalEntry = new HashMap<>();
	                for (int i = 1; i <= columnCount; i++) {
	                    String columnName = metaData.getColumnName(i);
	                    Object value = rs.getObject(i);
	                    journalEntry.put(columnName, value);
	                }
	                System.out.println(journalEntry.toString());
	                journalList.add(journalEntry);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return journalList;
	 }
	 
	 /** Function to update a journal entry
	  * @param title user can optionally input title
	  * @param context user must enter journal context
	  * @param _id id to search for the entry
	  * @return boolean check if the journal was updated or not.
	  */ 
	  public boolean updateJournalEntry(int id, String context, String content) throws SQLException {
           PreparedStatement stmt = conn.prepareStatement("UPDATE journal SET Context = ?, Content = ? WHERE id = ?");
	       try {
	           stmt.setString(1, context);
	           stmt.setString(2, content);
	           stmt.setInt(3, id);

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
