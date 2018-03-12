package wsz.Jdbc_transaction.util;
/**
 * c3p0数据库连接池
 * @author wsz
 * @date 2018年3月12日
 * c3p0.jar+mchange-commons-java.jar
 */

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * c3p0数据库连接池
 * @author wsz
 * @date 2018年3月12日
 */
public class C3P0Utils {

	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	
	public Connection getConn() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		C3P0Utils c3p0 = new C3P0Utils();
		System.out.println(c3p0.getConn());
	}
}
