package wsz.Jdbc_transaction.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButils {

	private static String DRIVER_CLASS="com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/jdbc";
	private static String USERNAME= "wsz";
	private static String PASSWORD= "wsz";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void classAll(Connection conn,PreparedStatement stmt,ResultSet rs) {
		try {
			if(conn != null)
				conn.close();
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection conn = getConnection();
		System.out.println(conn);
	}
}
