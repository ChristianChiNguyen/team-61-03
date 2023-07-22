package application;

import java.sql.SQLException;

/** Lambda type to check if a query with a variable input returns any data*/
@FunctionalInterface
public interface IfDataExistsInterface {
	public boolean checkReturnData(String input, String query) throws SQLException;
}
