package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getConnection() throws Exception {
		Class.forName("org.mariadb.jdbc.Drivers");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shop","gdj58","java1234");
		conn.setAutoCommit(false);
		return conn;
	}
}