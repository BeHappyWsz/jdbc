package wsz.jdbc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * jdbc连接
 * @author wsz
 * @date 2018年3月28日
 */
public class DButils {

	private static String DRIVER_CLASS;
	private static String URL ;
	private static String USERNAME;
	private static String PASSWORD;
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("db");
		DRIVER_CLASS = bundle.getString("DRIVERCLASS");
		URL 	     = bundle.getString("URL");
		USERNAME     = bundle.getString("USERNAME");
		PASSWORD     = bundle.getString("PASSWORD");
	}
	
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
		System.out.println(getConnection());
	}
}
