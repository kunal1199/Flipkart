package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.User;
import com.flipkart.dao.UserDao;

//user operation class to manage user operations
public class UserOperations {
	//initializing userDao to access the database
	UserDao userDao = new UserDao();
	
	//fetch a list of all the users
	public List<User> fetchUsers() {
		return userDao.fetchUsers();
	}
	
	//fetch details of a specific user
	public User fetchById(int id) {
		// TODO Auto-generated method stub
		return userDao.fetchById(id);
	}

	//add a user
	public int addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
		return user.getId();
	}

	//drop a user
	public void dropUser(int id) {
		// TODO Auto-generated method stub
		userDao.dropUser(id);
	}

	//update details of a user
	public void updateUser(int id, User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(id, user);
	}

}
