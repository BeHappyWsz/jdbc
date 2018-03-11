package wsz.Jdbc_transaction.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import wsz.Jdbc_transaction.util.DButils;

/**
 * jdbc调用事务
 * @author wsz
 * @date 2018年3月11日
 */
public class TransactionDemo {

	private Connection conn = DButils.getConnection();;

	private PreparedStatement stmt = null;
	
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

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getStmt() {
		return stmt;
	}

	public void setStmt(PreparedStatement stmt) {
		this.stmt = stmt;
	}
	
}
