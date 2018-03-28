package wsz.jdbc.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 测试DBCP连接池
 * @author wsz
 * @date 2018年3月12日
 */
public class TestDBCP {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	@Test
	public void test() {
		String sql = "select * from t_user";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Before
	public void init() {
		conn= DBCPUtils.getConn();
	}
	
	@After
	public void destory() {
		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
