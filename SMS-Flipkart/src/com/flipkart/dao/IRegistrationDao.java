package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.flipkart.exception.CourseException;
import com.flipkart.exception.DropRegisteredException;
import com.flipkart.model.Course;

//interface for the registration dao
public interface IRegistrationDao {
	//add a course to the registeredcourses table
		public void addCourse(int studentId, int courseId) throws SQLException, CourseException;
		//remove a course from the registered courses list
		public void removeCourse(int studentId, int courseId) throws SQLException, DropRegisteredException;
		//fetch a list of all the registered courses
		public List<Course> fetchRegistered(int studentId) throws SQLException;
		//finalize the registration for all the courses
		public void registrationDone(int studentId, int mode);
		//generate a report card of the semester
		public Map <Integer, String> generateReportCard(int studentId) throws SQLException;
		//check for any pending registration
		public List<Integer> fetchRegisteredPending(int studentId);
		//check for course constraints
		public boolean allowed(int studentId);
}
