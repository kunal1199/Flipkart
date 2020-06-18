package com.flipkart.client;


import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.model.Course;
import com.flipkart.model.User;
import com.flipkart.service.AdminOperations;
import com.flipkart.service.UserOperations;
import com.flipkart.utils.DateTimeUtil;

//admin client class used for managing student functionalities
public class AdminClient {
	//logger object initialized for this class
	private static Logger logger = Logger.getLogger(UserClient.class);
	static Scanner sc = new Scanner(System.in);
	//adminoperation object created for admin functionalities
	static AdminOperations adminOperations = new AdminOperations();
	static int adminId;
	public AdminClient(int id) {
		// TODO Auto-generated constructor stub
		adminId = id;
	}
	//displays the required menu
	public void showMenu() {
		int choice = 0;
		do {
			System.out.println();
			logger.info("1: View Catalog");
			logger.info("2: Add a user");
			logger.info("3: Remove a user");
			logger.info("4: Add a course in the catalog.");
			logger.info("5: Remove a course from the catalog.");
			logger.info("6: View users");
			logger.info("7: View Details of a specific user");
			logger.info("8: Logout");
			logger.info("9: Exit");
			choice=sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				viewCatalog();
				break;
			case 2:
				addUser();
				break;
			case 3:
				removeUser();
				break;
			case 4:
				addCourse();
				break;
			case 5:
				removeCourse();
				break;
			case 6:
				viewUsers();
				break;
			case 7:
				viewDetails();
				break;
			case 8:
				logger.info("Logged out at " +DateTimeUtil.getDateTime());
				UserClient.login();
				break;
			}
		}while(choice!=9);	
	}
	
	//view the list of users registered with us
	private void viewUsers() {
		// TODO Auto-generated method stub
		logger.info("Here is the list of users registered with us");
		List <User> users = adminOperations.viewUsers();
		//using java8 for each
		users
		.forEach(user -> logger.info(String.format("Id: %2d, Name: %14s, Role: %8s", user.getId(), user.getUsername(), user.getRole())));
	}
	
	//remove courses from the catalog
	private void removeCourse() {
		// TODO Auto-generated method stub
		logger.info("Enter the id of the course you want to delete");
		int id = sc.nextInt();
		adminOperations.removeCourse(id);
	}
	
	//add a course to the catalog
	private void addCourse() {
		// TODO Auto-generated method stub
		logger.info("Enter name of the course you want to add.");
		String name = sc.nextLine();
		logger.info("Enter fees for the course in thousands.");
		int fees = sc.nextInt();
		adminOperations.addCourse(name, fees);
	}
	
	//view details of a specific user
	private void viewDetails() {
		logger.info("Enter id of the user you want to inspect");
		int id = sc.nextInt();
		adminOperations.inspect(id);
	}
	
	//remove a user from the users table
	private void removeUser() {
		// TODO Auto-generated method stub
		logger.info("Enter id of the user you want to delete");
		int id = sc.nextInt();
		adminOperations.removeUser(id);
	}
	
	//add a user to our sms
	private void addUser() {
		// TODO Auto-generated method stub
		logger.info("Enter name of the user");
		String username = sc.nextLine();
		logger.info("Set up a password for the user");
		String password = sc.nextLine();
		logger.info("Enter the role of the user");
		String role = sc.nextLine();
		logger.info("Enter the gender of the user(m for male, f for female)");
		String gender = sc.nextLine();
		if(role.equals("student")) {
			adminOperations.addUser(username, password, 1, gender);
			int userId=new UserOperations().fetchId(username, password);
			logger.info("Enter the name of the student");
			String name = sc.nextLine();
			logger.info("Enter the phone number of the student");
			String phone = sc.nextLine();
			logger.info("Enter the email of the student");
			String email = sc.nextLine();
			logger.info("Is any scholarship applicable?(0 for no, 1 for yes)");
			int scholar = sc.nextInt();
			adminOperations.addStudent(userId, name, phone, email, scholar);
		} else if(role.equals("professor")) {
			adminOperations.addUser(username, password, 2, gender);
			int userId=new UserOperations().fetchId(username, password);
			logger.info("Enter the name of the professor");
			String name = sc.nextLine();
			logger.info("Enter the phone number of the professor");
			String phone = sc.nextLine();
			logger.info("Enter the email of the professor");
			String email = sc.nextLine();
			adminOperations.addProfessor(userId, name, phone, email);
		} else {
			logger.error("Invalid role, please try again");
			addUser();
		}
	}
	
	//view the catalog for the semester
	private void viewCatalog() {
		List <Course> courses = adminOperations.viewCatalog();
		//using java8 forEach
		courses
		.forEach(course -> logger.info(String.format("Id:%2d; Course:%-8s",course.getId(), course.getName())));
	}
}
