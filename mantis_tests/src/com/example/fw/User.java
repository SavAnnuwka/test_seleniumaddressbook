package com.example.fw;

public class User {

	public String password;
	public String login;
	public String email;
	public String id;

	public User setId(String id) {
		this.id = id;
		return this;
	}

	public User setLogin(String login) {
		this.login = login;
		return this;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}


	public User setEmail(String email) {
		this.email = email;
		return this;
}

	public String getPassword() {
		return password;
	}
	
	public String getId() {
		return id;
	}
	

	public String getLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}

	


}
