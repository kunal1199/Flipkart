package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.exception.UserException;
import com.flipkart.service.UserOperations;
import com.flipkart.utils.DateTimeUtil;

//user client class for identification and required menu display
public class UserClient {
	private static Logger logger = Logger.getLogger(UserClient.class);
	// main method prompts for login of the user
	public static void main(String[] args) {
		login();
	}
	static void login() {
		// TODO Auto-generated method stub
		//make a dao
			UserOperations userOperations = new UserOperations();
			Scanner sc = new Scanner(System.in);
			//taking user details for identification purposes
			try {
				logger.info("Welcome to SMS(Student Management System)");
				logger.info("Please enter your authentication details:-");
				logger.info("Username: ");
				String username = sc.nextLine();
				logger.info("Password: ");
				String password = sc.nextLine();
				//usertype stores the type of the person that is willing to login
				String userType = userOperations.checkIdentity(username, password);
				int id = userOperations.fetchId(username, password);
				//based on the role the respective client menu is displayed
				if(userType.equals("")) {
					throw new UserException();
				} else {
					if(userType.equals("admin")) {
						logger.info("Logged in as admin at " + DateTimeUtil.getDateTime());
						new AdminClient(id).showMenu();
					} else if(userType.equals("professor")) {
						logger.info("Logged in as professor at " + DateTimeUtil.getDateTime());
						new ProfessorClient(id).showMenu();
					} else if(userType.equals("student")) {
						logger.info("Logged in as student at " + DateTimeUtil.getDateTime());
						new StudentClient(id).showMenu();
					}
				}
			} catch(UserException e) {
				e.viewLog();
				login();
			} finally {
				sc.close();
			}
	}
}
