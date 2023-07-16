package application;

import java.sql.*;

public class AppModel {
	Connection conn;
	
	//Constructor
	public AppModel() {
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
	
	//Function to check if user is first time logged in
	public boolean isFirstLogin(String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//Query to get password from database
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
	
	//Function to check if has logged in successfully
	public boolean isLogin(String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//Query to get password from database
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
	
	//Function to change password from Change Password page
	public boolean updatePassword(String oldPassword, String newPassword) throws SQLException{
		String updateQuery = "Update users set password = ?, newpassword = ? where id = 1";
		PreparedStatement preparedStatement = null;
		try { 
			preparedStatement = conn.prepareStatement(updateQuery);
			preparedStatement.setString(1, oldPassword);
			preparedStatement.setString(2, newPassword);
			
			int result = preparedStatement.executeUpdate();
			
			if ( result > 0 )
			{
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
