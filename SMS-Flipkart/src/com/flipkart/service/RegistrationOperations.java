package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.flipkart.dao.CatalogDao;
import com.flipkart.dao.RegistrationDao;
import com.flipkart.exception.CourseException;
import com.flipkart.exception.DropRegisteredException;
import com.flipkart.model.Course;

//class for managing the student registration operations
public class RegistrationOperations {
	//registration dao object used to access the registeredcourses table
	private static RegistrationDao registrationDao = new RegistrationDao();
	
	//operation for adding a course to a student specific list
	public void addCourse(int studentId, int courseId) throws SQLException, CourseException {
		// TODO Auto-generated method stub
		//access dao here
		registrationDao.addCourse(studentId, courseId);
	}
	
	//removing a course from the personalized student list
	public void removeCourse(int studentId, int courseId) throws SQLException, DropRegisteredException {
		// TODO Auto-generated method stub
		registrationDao.removeCourse(studentId, courseId);
	}
	
	//view the list of courses along with the registration status
	public List <Course> fetchRegistered(int studentId) throws SQLException {
		// TODO Auto-generated method stub
		List <Course> registeredcourses = registrationDao.fetchRegistered(studentId);
		return registeredcourses;
	}
	
	//calculate the registration fees
	public int calculateFee(int studentId) {
		// TODO Auto-generated method stub
		List <Integer> registeredcourses = registrationDao.fetchRegisteredPending(studentId);
		int fee = 0;
		for(Integer courseId: registeredcourses) {
			fee = fee + new CatalogDao().getFees(courseId);
		}
		return fee;
	}
	
	//finalize the registration of the courses in the list
	public void registrationDone(int studentId, int choice) {
		// TODO Auto-generated method stub
		registrationDao.registrationDone(studentId, choice);
	}
	
	//view grades for the semester courses
	public Map <Integer, String> fetchReportCard(int studentId) throws SQLException {
		// TODO Auto-generated method stub
		return registrationDao.generateReportCard(studentId);
	}
	
	//returns true if a course can be registered for
	public boolean allowed(int studentId) {
		// TODO Auto-generated method stub
		return registrationDao.allowed(studentId);
	}
	
	//view the mode of payments available
	public void viewPaymentModes() {
		// TODO Auto-generated method stub
		registrationDao.viewPaymentModes();
	}

}
