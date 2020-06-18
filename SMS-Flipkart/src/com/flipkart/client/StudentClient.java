package com.flipkart.client;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.dao.CatalogDao;
import com.flipkart.exception.CourseException;
import com.flipkart.exception.DropRegisteredException;
import com.flipkart.exception.PaymentException;
import com.flipkart.model.Course;
import com.flipkart.service.RegistrationOperations;
import com.flipkart.service.StudentOperations;
import com.flipkart.utils.DateTimeUtil;

//student client class used for managing student functionalities
public class StudentClient {
	private static int studentId;
	
	//constructor to get the id of the student
	public StudentClient(int id) {
		studentId=id;
	}
	
	//logger object initialized for this class
	private static Logger logger = Logger.getLogger(StudentClient.class);
	static Scanner sc = new Scanner(System.in);
	
	//registrationoperation object created for student registration purposes
	static RegistrationOperations registrationOperations = new RegistrationOperations();
	
	//displays the relevant menu
	public void showMenu() {
		int choice = 0;
		do {
			System.out.println();
			logger.info("1: View Catalog");
			logger.info("2: Add a Course");
			logger.info("3: Drop a Course");
			logger.info("4: View courses added for Registration");
			logger.info("5: Proceed to payment");
			logger.info("6: View Report Card");
			logger.info("7: Logout");
			logger.info("8: Exit");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				viewCatalog();
				break;
			case 2:
				addCourse();
				break;
			case 3:
				dropCourse();
				break;
			case 4:
				viewRegistered();
				break;
			case 5:
				makePayment();
				break;
			case 6:
				viewReportCard();
				break;
			case 7:
				logger.info("Logged out at " +DateTimeUtil.getDateTime());
				UserClient.login();
				break;
			}
		}while(choice!=8);	
	}
	
	//view the catalog for the semester
	public void viewCatalog() {
		List <Course> courses = new StudentOperations().viewCatalog();
		//using java8 forEach
		courses.forEach(course -> logger.info(String.format("Id:%2d; Course:%-8s",course.getId(), course.getName())));
	}
	
	//add a course for registration purpose
	public void addCourse() {
		try {
			logger.info("Enter the id of the course you want to add.");
			int courseId = sc.nextInt();
			registrationOperations.addCourse(studentId, courseId);
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} catch (CourseException e) {
			// TODO Auto-generated catch block
			e.viewLog();
		}
	}
	
	//drop a course from the registration list
	public void dropCourse() {
		try {
			logger.info("Enter the id of the course you want to delete.");
			int courseId = sc.nextInt();
			registrationOperations.removeCourse(studentId, courseId);
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} catch (DropRegisteredException e) {
			// TODO Auto-generated catch block
			e.viewLog();
		}
	}
	
	//view the list of courses in the registration list
	public void viewRegistered() {
		try {
			List <Course> registeredcourses = registrationOperations.fetchRegistered(studentId);
			if(registeredcourses!=null) {
				//using java8 forEach
				registeredcourses
				.forEach(course->logger.info(String.format("Id:%2d   Name: %-14s   Registration Status: %10s   DateOfAddition: %s  Mode: %s", course.getId(), new CatalogDao().viewCourseById(course.getId()), course.getStatus(), course.getDate(), course.getMode())));
			} else {
				logger.info("No courses in here so far");
			} 
		}
		catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	
	//finalize the registration by submitting the payment
	public void makePayment() {
		int fees = registrationOperations.calculateFee(studentId);
		boolean allowed = registrationOperations.allowed(studentId);
		if(allowed && fees==0) {
			logger.info("No pending registrations.");
			return;
		}
		if(allowed) {
			try {
				logger.info("Total payment to be made: " + fees);
				logger.info("  Id : Mode of payment");
				registrationOperations.viewPaymentModes();
				logger.info("Enter the mode id");
				int choice = sc.nextInt();
				if(choice==1 || choice==2 || choice==3) {
					System.out.println("Payment successful!");
					registrationOperations.registrationDone(studentId, choice);
				} else {
					throw new PaymentException();
				}
			} catch(PaymentException e) {
				e.viewLog();
			}
		}
	}
	
	//view the courses registered along with the marks submitted by the professor(if any)
	public void viewReportCard() {
		try {
			Map <Integer, String> registeredcourses = registrationOperations.fetchReportCard(studentId);
			if(registeredcourses!=null) {
				//using java8 forEach method
				registeredcourses
				.forEach((key, value)->logger.info(String.format("%8s : %s", new CatalogDao().viewCourseById(key), value)));
			} else {
				logger.info("No courses in here so far");
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
}
