package com.flipkart.service;

import java.util.List;
import java.util.stream.Collectors;


import com.flipkart.dao.AdminDao;
import com.flipkart.model.User;

//class for managing the admin operations
public class AdminOperations implements ICatalogOperations{
	public AdminDao adminDao = new AdminDao();
	//add a user to the system
	public void addUser(String username, String password, int roleId, String gender) {
		// TODO Auto-generated method stub
		adminDao.addUser(username, password, roleId, gender);
		return;
	}
	//remove a user from the system
	public void removeUser(int id) {
		// TODO Auto-generated method stub
		adminDao.removeUser(id);
	}
	//add a course into the catalog
	public void addCourse(String name, int fees) {
		// TODO Auto-generated method stub
		adminDao.addCourse(name, fees);
	}
	//remove a course from the catalog
	public void removeCourse(int id) {
		// TODO Auto-generated method stub
		adminDao.removeCourse(id);
	}
	//append Mr, Ms in front of the user
	public static User transformUser(User user) {
		if(user.getGender().equals("m")) {
			user.setUsername("Mr." + user.getUsername());
		} else {
			user.setUsername("Ms." + user.getUsername());
		}
		return user;
	}
	//view the list of users registered in the system
	public List <User> viewUsers() {
		// TODO Auto-generated method stub
		List <User> users = adminDao.viewUsers();
		//using java8 stream api
		users
			.stream()
			.map(AdminOperations::transformUser)
			.collect(Collectors.toList());
		return users;
	}
	//add a student in the students table
	public void addStudent(int userId, String name, String phone, String email, int scholar) {
		// TODO Auto-generated method stub
		adminDao.addStudent(userId, name, phone, email, scholar);
	}
	//add a professor in the professor table
	public void addProfessor(int userId, String name, String phone, String email) {
		// TODO Auto-generated method stub
		adminDao.addProfessor(userId, name, phone, email);
	}
	public void inspect(int id) {
		// TODO Auto-generated method stub
		adminDao.inspectUser(id);
	}
}
