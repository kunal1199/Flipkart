package com.flipkart.service;

import java.util.List;
import java.util.Scanner;
import com.flipkart.dao.ProfessorDao;

//class for managing the admin operations
public class ProfessorOperations implements ICatalogOperations {
	//creating a professor dao to access professors db
	ProfessorDao professorDao = new ProfessorDao();
	private static Scanner sc = new Scanner(System.in);
	public void enrollForCourse(int professorId, int courseId) {
		// TODO Auto-generated method stub
		professorDao.enrollForCourse(professorId, courseId);
	}
	//view the list of enrolled courses
	public List <Integer> viewEnrolledCourses(int professorId) {
		// TODO Auto-generated method stub
		return professorDao.viewEnrolledCourses(professorId);
	}
	//view list of enrolled students
	public List <Integer> viewEnrolledStudents(int professorId) {
		// TODO Auto-generated method stub
		return professorDao.viewEnrolledCourses(professorId);
	}
	//submit grades for a student
	public void submitGrades(int professorId) {
		// TODO Auto-generated method stub
		//viewEnrolledCourses(professorId);
		viewEnrolledStudents(professorId);
		logger.info("Enter the course id");
		int courseId = sc.nextInt();
		List <Integer> courses = professorDao.viewEnrolledCourses(professorId);
		if(courses.contains(courseId)==false) {
			logger.error("You dont teach this course, please try again");
			submitGrades(professorId);
			return;
		}
		
		logger.info("Enter the student id");
		int studentId = sc.nextInt();
		
		sc.nextLine();
		
		logger.info("Enter the grade ");
		String grade = sc.nextLine();
		
		professorDao.submitGrades(courseId, studentId, grade);
		logger.info("Result Updated");
		return;
	}
}
