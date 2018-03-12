package wsz.Jdbc_transaction.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * 使用dbcp数据库连接池
 * @author wsz
 * @date 2018年3月12日
 */
public class DBCPUtils {

	private static DataSource DS;
	
	private static final String config ="/dbcp.properties";
	
	public DBCPUtils() {
		init();
	}
	
	/**
	 * 加载配置文件属性
	 */
	public void init() {
		Properties pops = new Properties();
		try {
			pops.load(Object.class.getResourceAsStream(config));
			DS = BasicDataSourceFactory.createDataSource(pops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		Connection conn = null;
		if(DS != null) {
			try {
				conn = DS.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static void main(String[] args) {
		DBCPUtils dbcpUtils = new DBCPUtils();
		System.out.println(dbcpUtils.getConn());
	}
}
