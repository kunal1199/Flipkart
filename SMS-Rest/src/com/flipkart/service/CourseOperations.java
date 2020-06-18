package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.dao.CourseDao;

//operations class to manage course operations
public class CourseOperations {
	
	//initializing a dao to access the database
	CourseDao registrationDao = new CourseDao();
	
	//returns a list of all the courses
	public List<Course> fetchRegistered() {
		// TODO Auto-generated method stub
		return registrationDao.fetchRegistered();
	}

	//returns details of a specific course
	public Course get(int id) {
		// TODO Auto-generated method stub
		return registrationDao.fetchRegistered(id);
	}
	
	//add a course
	public int addCourse(Course course) {
		registrationDao.addCourse(course);
		return course.getId();
	}

	//drop a course
	public void dropCourse(int id) {
		// TODO Auto-generated method stub
		registrationDao.dropCourse(id);
	}
	
	//update details of a course
	public void updateCourse(int id, Course course) {
		// TODO Auto-generated method stub
		registrationDao.updateCourse(id, course);
	}

}
