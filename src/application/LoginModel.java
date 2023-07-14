package application;

import java.sql.*;

public class LoginModel {
	Connection conn;
	public LoginModel() {
		conn = DbConnection.Connector();
		if (conn == null) {
			System.out.println("Unsuccessful connection1");
			System.exit(1);
		}
	}
	
	public boolean isDbConnected() {
		try {
			return !conn.isClosed();
		} catch (SQLException e) {
			// TODO
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isLogin(String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
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
			//TODO
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}
}
