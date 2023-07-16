package application;

import java.sql.*;

public class LoginModel {
	Connection conn;
	
	//Constructor
	public LoginModel() {
		conn = DbConnection.Connector();
		if (conn == null) {
			System.out.println("Unsuccessful connection1");
			System.exit(1);
		}
	}
	
	//Function to check if the database is connected
	public boolean isDbConnected() {
		try {
			return !conn.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Function to check user is first time logged in
	public boolean isFirstLogin() throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//Query to get password from database
		String query = "Select password from users";
		try {
			preparedStatement = conn.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return false;
			} else return true;
			
		} catch (Exception e) {
			return false;
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	//Function to check if has logged in successfully
	public boolean isLogin(String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//Query to get password from database
		String query = "Select password from users where password = ?";
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
}
