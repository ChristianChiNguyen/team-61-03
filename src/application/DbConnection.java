package application;
import java.sql.*;


/** Class to check database connection using JDBC to connect to sqlite file */
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
