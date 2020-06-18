package com.flipkart.service;

import com.flipkart.dao.UserDao;

//class for managing the user operations
public class UserOperations {
	//user dao object created to access the users table
	static UserDao userDao = new UserDao();
	//checks role of the user if it exits in the db
	public String checkIdentity(String username, String password) {
		return userDao.checkIdentity(username, password);
	}
	//fetched id of a registered user
	public int fetchId(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.fetchId(username, password);
	}
}
