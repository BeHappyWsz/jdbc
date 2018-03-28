package wsz.Jdbc_transaction.c3p0;

import java.io.Serializable;

/**
 *
 *@author  wsz
 *@createdTime 2018年3月22日
*/
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;

	private String username;
	
	private String password;
	
	public User() {};
	
	public User(int id) {
		super();
		this.id = id;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	
	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
}
