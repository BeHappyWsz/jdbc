package wsz.Jdbc_transaction.c3p0;
/**
 *c3p0+common-dbutils进行数据库操作
 *@author  wsz
 *@createdTime 2018年3月22日
*/

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Before;
import org.junit.Test;

public class C3p0Demo {

	private QueryRunner qr;
	
	@Before
	public void init() {
		qr = new QueryRunner(C3P0Utils.getDataSource());
	}
	
	@Test
	public void add() {
		String sql = "insert into t_user(username,password) values(?,?)";
		try {
			int update = qr.update(sql,"cp","cp");
			System.out.println(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void batchAdd() {
		String sql = "insert into t_user(username,password) values(?,?)";
		Object[][] params = {{"a","a"},{"b","b"}};
		try {
			List<Integer> flag = qr.insertBatch(sql, new ColumnListHandler<Integer>(), params);
			System.out.println(flag);//返回主键
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		String sql = "delete from t_user where username =?";
		try {
			int update = qr.update(sql,"cp");
			System.out.println(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量删除
	 */
	@Test
	public void batchDelete() {
		Object[][] params = {{"qwerty"},{"asddfg"},{"aa"}};
		String sql = "delete from t_user where username =?";
		try {
			int[] batch = qr.batch(sql, params);
			for(int a : batch) {
				System.out.print(a+"  ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		String sql = "update t_user set password=? where id=?";
		try {
			int update = qr.update(sql,"cp",27);
			System.out.println(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAll() {
		String sql = "select id,username,password from t_user";
		try {
			List<User> query = qr.query(sql, new BeanListHandler<User>(User.class));
			System.out.println(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getById() {
		String sql = "select id,username,password from t_user where id=?";
		try {
			User query = qr.query(sql, new BeanHandler<User>(User.class),1);
			System.out.println(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void countAll() {
		String sql = "select count(*) from t_user ";
		try {
			long count = (Long)qr.query(sql, new ScalarHandler<Object>(1));
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void page() {
		String sql = "select id,username,password from t_user limit ?,?";
		try {
			List<User> query = qr.query(sql, new BeanListHandler<User>(User.class),0,10);
			System.out.println(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
