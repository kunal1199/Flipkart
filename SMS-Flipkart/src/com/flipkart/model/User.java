package com.flipkart.model;

//Model class for a user (can be a student, admin or professor)
public class User {
	private int id;
	private String username;
	private String password;
	private String role;
	private String gender;
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}
	
	public User() {
		
	}
	
	public User(int id, String username, String password, String role, String gender) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.gender = gender;
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
}
