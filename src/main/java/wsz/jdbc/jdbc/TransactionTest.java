package wsz.jdbc.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 调用操作方法
 * @author wsz
 * @date 2018年3月11日
 */
public class TransactionTest {


	private Connection conn;
	private PreparedStatement stmt = null;
	
	@Before
	public void InitialContext() {
		conn = DButils.getConnection();
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
	
	/**
	 * 同时更新2调数据,中间出现异常,第二个数据更新失败
	 * @throws SQLException 
	 */
	@Test
	public void update() throws SQLException {
		//不自动提交,默认自动提交
		conn.setAutoCommit(false);
		try {
			System.out.println("td1-1 "+this.update("td1", 1));
			System.out.println(1/0);
			System.out.println(this.update("td2", 2));
			conn.commit();//正常运行,进行提交
		} catch (Exception e) {//出现异常回滚
			conn.rollback();
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新操作
	 * @param username
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean update(String username,int id) throws SQLException {
		String sql="update t_user set username=? where id=?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setInt(2, id);
		return !stmt.execute();//结果为ResultSet则为true,取反
	}

}
