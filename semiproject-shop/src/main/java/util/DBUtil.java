package util;

import java.sql.*;

public class DBUtil {
   public static Connection getConnection() throws Exception{ // Connection getConnection이란 메서드
      Class.forName("org.mariadb.jdbc.Driver");
      Connection conn = DriverManager.getConnection(
    		  "jdbc:mariadb://43.200.126.220/shop","root","java1234");
      return conn;
   }
}