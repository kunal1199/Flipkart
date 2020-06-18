package com.flipkart.dao;

import java.util.List;

import com.flipkart.model.User;

//interface for the admin dao
public interface IAdminDao {
		//adding a user to the db
		public void addUser(String username, String password, int roleId, String gender);
		//removing a user from the db
		public void removeUser(int id);
		//add a course to the course catalog
		public void addCourse(String name, int fees);
		//remove a course from the catalog db
		public void removeCourse(int id);
		//fetch list of users from the db
		public List<User> viewUsers();
}
