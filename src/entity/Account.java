package entity;

public class Account {
	
	/*
	Username varchar(40) primary key,
	Password varchar(40) not null,
	Role varchar(40) not null
	 */
	
	private String username;
	private String password;
	private String role;
	
	public Account() {
		super();
	}

	public Account(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
