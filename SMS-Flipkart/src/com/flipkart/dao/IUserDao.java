package com.flipkart.dao;

//interface for the user dao
public interface IUserDao {
		//return role of the user using the username and password combination
		public String checkIdentity(String username, String password);
		//fetch userid for specific functions of the user
		public int fetchId(String username, String password);
}
