package wsz.jdbc.dbcp;

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
	
	/**
	 * 加载配置文件属性
	 */
	static{
		Properties pops = new Properties();
		try {
			pops.load(Object.class.getResourceAsStream(config));
			DS = BasicDataSourceFactory.createDataSource(pops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回Connection以传统jdbc方式进行操作
	 * @return
	 */
	public static Connection getConn() {
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
	
	/**
	 * 返回DataSource,操作方式与c3p0类似
	 * @return
	 */
	public static DataSource getDataSource() {
		return DS;
	}
	
	public static void main(String[] args) {
		System.out.println(DBCPUtils.getConn());
		System.out.println(DBCPUtils.getDataSource());
	}
}
