package wsz.Jdbc_transaction.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wsz.Jdbc_transaction.util.DButils;

public class ProcedureTest {

	private Connection conn = null;

	private PreparedStatement stmt = null;
	
	private ResultSet rs = null;
	
	@Before
	public void init() {
		conn = DButils.getConnection();
	}

	@After
	public void destroy() {
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

	@Test
	public void selectAll() {
		String sql = "call selectAll";
		try {
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.execute();
			rs = cstmt.getResultSet();
			while(rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				System.out.println("id:"+id+" username:"+username+" password:"+password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectWithParam() {
		String param ="王";
		String sql = "call selectWithParam(?);";
		try {
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1, param);
			cstmt.execute();
			rs = cstmt.getResultSet();
			while(rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				System.out.println("id:"+id+" username:"+username+" password:"+password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	@Test
	public void paramAndOut() {
		String param ="王";
		String sql = "call paramAndOut(?);";
		try {
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1, param);
			cstmt.execute();
			rs = cstmt.getResultSet();
			while(rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				System.out.println("id:"+id+" username:"+username+" password:"+password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
