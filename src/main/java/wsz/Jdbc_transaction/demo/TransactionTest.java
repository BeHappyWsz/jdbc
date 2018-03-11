package wsz.Jdbc_transaction.demo;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
/**
 * 调用操作方法
 * @author wsz
 * @date 2018年3月11日
 */
public class TransactionTest {


	/**
	 * 同时更新2调数据,中间出现异常,第二个数据更新失败
	 * @throws SQLException 
	 */
	@Test
	public void update() throws SQLException {
		TransactionDemo td =new TransactionDemo();
		//取出对象中的数据库连接对象
		Connection conn = td.getConn();
		//不自动提交,默认自动提交
		conn.setAutoCommit(false);
		try {
			System.out.println("td1-1 "+td.update("td1", 1));
			System.out.println(1/0);
			System.out.println(td.update("td2", 2));
			conn.commit();//正常运行,进行提交
		} catch (Exception e) {//出现异常回滚
			conn.rollback();
			e.printStackTrace();
		}
	}
}
