package application;
import java.sql.*;
public class DbConnection {
	public static Connection Connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:appDB.sqlite3");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
			// TODO
		}
	}
}
