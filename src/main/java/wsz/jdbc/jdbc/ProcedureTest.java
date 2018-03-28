package wsz.jdbc.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 存储过程调用
 * @author wsz
 * @date 2018年3月28日
 */
public class ProcedureTest {

	private Connection conn = null;
	
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	create procedure selectAll()
	BEGIN
		select * from t_user;
	end; 
	 */
	@Test
	public void selectAll() {
		String sql = "call selectAll()";
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
	
	/**
	create procedure selectWithParam(in username VARCHAR(20))
	begin 
		select *
		from t_user t
		where t.username like CONCAT('%',username,'%');
	end; 
	 * 
	 */
	@Test
	public void selectWithParam() {
		String param ="1";
		String sql = "call selectWithParam(?);";
		try {
			CallableStatement cstmt = conn.prepareCall(sql);
//			cstmt.setString(1, param); //查询不出数据
			cstmt.setNString(1, param);//如果查询条件为中文,需要使用NString否则无法查询出数据?
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
	
	/**
	create procedure paramAndOut(in username varchar(255),out count int(11))
	BEGIN
		select count(*) into count
		from t_user t
		where t.username like CONCAT('%',username,'%');
	end;
	 */
	@Test
	public void paramAndOut() {
		String param ="1";
		String sql = "call paramAndOut(?,?);";
		try {
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1, param);
			cstmt.registerOutParameter(2, Types.INTEGER);//返回的参数
			cstmt.execute();
			int count = cstmt.getInt(2);//获取返回的参数
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
