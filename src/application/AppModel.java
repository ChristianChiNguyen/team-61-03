package application;

import java.sql.*;

public class AppModel {
	Connection conn;
	
	/** Constructor for AppModel that connects to database */
	 public AppModel() {
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
	
	/** Lambda expression to check if a query with a variable input returns any data*/
	IfDataExistsInterface ifDataExists = (String input, String query) -> {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, input);
			
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
	};
	
	/** Function to check if user is first time logged in */
	public boolean isFirstLogin(String password) throws SQLException {
		// Query to get password from database
		String query = "SELECT password from users where PASSWORD = ? and newpassword is NULL";
		return ifDataExists.checkReturnData(password, query);
	}
	
	/** Function to check if the old password matches database */
	public boolean checkOldPassword(String password) throws SQLException {
		// Query to get password from database
		String query = "SELECT password from users where PASSWORD = ?";
		return ifDataExists.checkReturnData(password, query);
	}
	
	/** Function to check if user selects the correct security question */
	public boolean checkSecurityQuestion(String security) throws SQLException {
		// Query to get security question from database
		String query = "SELECT security_question from users where SECURITY_QUESTION = ?";
		return ifDataExists.checkReturnData(security, query);
	}
	
	/** Function to check if user enters the correct security answer */
	public boolean checkSecurityAnswer(String securityAnswer) throws SQLException {
		// Query to get security answer from database
		String query = "SELECT security_answer from users where SECURITY_ANSWER = ?";
		return ifDataExists.checkReturnData(securityAnswer, query);
	}
	
	/** Function to check if user has logged in successfully */
	public boolean isLogin(String password) throws SQLException {
		// Query to get password from database
		String query = "Select newpassword from users where newpassword = ?";
		return ifDataExists.checkReturnData(password, query);
	}

	/** Function to reset the user password */
	public boolean updatePassword(String newPassword, String securityQuestion, String securityAnswer) {
	    String updateQuery = "UPDATE users SET password = ?, newpassword = ?, security_question = ?, security_answer = ? WHERE id = 1";
	    PreparedStatement preparedStatement = null;
	    try { 
	        preparedStatement = conn.prepareStatement(updateQuery);
	        preparedStatement.setString(1, newPassword);
	        preparedStatement.setString(2, newPassword);
	        preparedStatement.setString(3, securityQuestion);
	        preparedStatement.setString(4, securityAnswer);
	        
	        int result = preparedStatement.executeUpdate();
	        
	        return result > 0;
	    } catch (Exception e) {
	        System.out.println(e);
	        return false;
	    }
	}
}
