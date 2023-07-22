package application;

import java.sql.*;
import java.time.LocalDateTime;

public class JournalModel {
	 Connection conn;
	 
	 public JournalModel() {
	      	conn = DbConnection.Connector();
	        if (conn == null) {
	            System.out.println("Unsuccessful connection1");
	            System.exit(1);
	        }
	 }
	
	 // Function to check if the database is connected
	 public boolean isDbConnected() {
			try {
				return !conn.isClosed();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	 }
	 
	 // Function to create new journal entries
	 public boolean addJournal(String title, String content) throws SQLException {
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			LocalDateTime currentTime = LocalDateTime.now();
			Timestamp timeStamp = Timestamp.valueOf(currentTime);
			String query = "INSERT INTO journal (Context, Content, timeStamp) VALUES (?, ?, ?)";
		 
			try {
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1,title);
				preparedStatement.setString(2,content);
				preparedStatement.setObject(3, currentTime);

			
				int rowsInserted = preparedStatement.executeUpdate();
				if ( rowsInserted > 0 ) {
					System.out.println("Successfully changed");
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
