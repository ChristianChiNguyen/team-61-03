package application;

import java.sql.*;

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
	 
}
