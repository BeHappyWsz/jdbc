package wsz.Jdbc_transaction.c3p0;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * c3p0数据库连接池
 * @author wsz
 * @date 2018年3月12日
 * c3p0.jar+mchange-commons-java.jar
 */
public class C3P0Utils {

	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	
	/**
	 * 返回Connection以传统jdbc模式进行开发
	 * @return
	 */
	public static Connection getConn() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 返回DataSource,结合common-dbutils一起开发
	 * @return
	 */
	public static DataSource getDataSource() {
		return ds;
	}
	
	public static void main(String[] args) {
		System.out.println(C3P0Utils.getConn());
	}

}
