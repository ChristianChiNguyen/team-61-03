package application;

import java.sql.*;

public class AppModel {
	Connection conn;
	
	// Constructor
	 public AppModel() {
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
	
	// Function to check if user is first time logged in
	public boolean isFirstLogin(String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		// Query to get password from database
		String query = "SELECT password from users where PASSWORD = ? and newpassword is NULL";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, password);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return true;
			} else return false;
			
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	// Function to check if user is first time logged in
	public boolean checkOldPassword(String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// Query to get password from database
		String query = "SELECT password from users where PASSWORD = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, password);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return true;
			} else return false;
			
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	// Function to check if user selects the correct security question
	public boolean checkSecurityQuestion(String security) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// Query to get security question from database
		String query = "SELECT security_question from users where SECURITY_QUESTION = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, security);
				
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return true;
			} else return false;
				
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	// Function to check if user enters the correct security answer
	public boolean checkSecurityAnswer(String securityAnswer) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// Query to get security answer from database
		String query = "SELECT security_answer from users where SECURITY_ANSWER = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, securityAnswer);
					
			resultSet = preparedStatement.executeQuery();
				
			if (resultSet.next()) {
				return true;
			} else return false;
					
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	// Function to check if user has logged in successfully
	public boolean isLogin(String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// Query to get password from database
		String query = "Select newpassword from users where newpassword = ?";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, password);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return true;
			} else return false;
			
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}

	// Function to reset the user password  
	public boolean updatePassword(String newPassword, String securityQuestion, String securityAnswer) {
	    String updateQuery = "UPDATE users SET newpassword = ?, security_question = ?, security_answer = ? WHERE id = 1";
	    PreparedStatement preparedStatement = null;
	    try { 
	        preparedStatement = conn.prepareStatement(updateQuery);
	        preparedStatement.setString(1, newPassword);
	        preparedStatement.setString(2, securityQuestion);
	        preparedStatement.setString(3, securityAnswer);
	        
	        int result = preparedStatement.executeUpdate();
	        
	        return result > 0;
	    } catch (Exception e) {
	        System.out.println(e);
	        return false;
	    }
	}


}
