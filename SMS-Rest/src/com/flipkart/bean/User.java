package com.flipkart.bean;

//bean class for a user
public class User {
	//members of the class
	int id;
	String username;
	String password;
	int roleId;
	String gender;
	//2 constructors
	public User() {
		
	}
	public User(int id, String username, String password, int roleId, String gender) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.roleId = roleId;
		this.gender = gender;
	}
	//getters and setters
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
