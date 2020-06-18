package com.flipkart.client;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.dao.CatalogDao;
import com.flipkart.dao.ProfessorDao;
import com.flipkart.model.Course;
import com.flipkart.service.ProfessorOperations;
import com.flipkart.utils.DateTimeUtil;

//professor client class used for managing student functionalities
public class ProfessorClient {
	//logger object initialized for this class
	private static Logger logger = Logger.getLogger(UserClient.class);
	static Scanner sc = new Scanner(System.in);
	//professoroperation object created for professor functioning
	ProfessorOperations professorOperations = new ProfessorOperations();
	static int professorId;
	public ProfessorClient(int id) {
		// TODO Auto-generated constructor stub
		professorId = id;
	}
	
	//displays the required menu
	public void showMenu() {
		int choice = 0;
		do {
			System.out.println();
			logger.info("1: View Catalog");
			logger.info("2: View enrolled Courses");
			logger.info("3: Enroll to teach for a Course");
			logger.info("4: View list of enrolled students");
			logger.info("5: Submit grades of students");
			logger.info("6: Logout");
			logger.info("7: Exit");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				viewCatalog();
				break;
			case 2:
				viewEnrolledCourses();
				break;
			case 3:
				enrollForCourse();
				break;
			case 4:
				viewEnrolledStudents();
				break;
			case 5:
				submitGrades();
				break;
			case 6:
				logger.info("Logged out at " +DateTimeUtil.getDateTime());
				UserClient.login();
				break;
			}
		}while(choice!=7);	
	}
	
	//enroll for a course available in the catalog
	private void enrollForCourse() {
		// TODO Auto-generated method stub
		logger.info("Enter the id of the course you want to enroll for?");
		int courseId = sc.nextInt();
		professorOperations.enrollForCourse(professorId, courseId);
	}
	
	//submit grades of a student that a professor teaches
	private void submitGrades() {
		// TODO Auto-generated method stub
		viewEnrolledStudents();
		professorOperations.submitGrades(professorId);
	}
	
	//view the list of enrolled courses
	private void viewEnrolledCourses() {
		// TODO Auto-generated method stub
		List <Integer> enrolledcourses = professorOperations.viewEnrolledCourses(professorId);
		if(enrolledcourses!=null)
		{
			logger.info("Here are the courses that you are enrolled in");
			enrolledcourses.forEach(courseId->logger.info(String.format("%d: %8s ", courseId, new CatalogDao().viewCourseById(courseId))));
		} else
			logger.info("You have not enrolled for any courses yet.");
	}
	
	//view the list of students enrolled under the professor
	private void viewEnrolledStudents() {
		// TODO Auto-generated method stub
		List <Integer> enrolledcourses = professorOperations.viewEnrolledCourses(professorId);
		logger.info("Here is a list of students enrolled along with your course offerings");
		for(Integer courseId:enrolledcourses) {
			logger.info(String.format("--------------%d : %s--------------", courseId, new CatalogDao().viewCourseById(courseId)));
			new ProfessorDao().viewEnrolledStudents(courseId);
		}
	}
	
	//view the catalog for the semester
	private void viewCatalog() {
		List<Course> courses = professorOperations.viewCatalog();
		//using java8 forEach
		courses
		.forEach(course -> logger.info(String.format("Id:%2d; Course:%-8s",course.getId(), course.getName())));
	}
}
