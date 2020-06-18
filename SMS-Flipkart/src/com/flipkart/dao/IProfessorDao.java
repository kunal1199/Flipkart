package com.flipkart.dao;

import java.util.List;

//interface for the professor dao
public interface IProfessorDao {
	//enroll for a course present to be taken up
	public void enrollForCourse(int professorId, int courseId);
	//view the list of courses enrolled in
	public List<Integer> viewEnrolledCourses(int professorId);
	// view the list of students enrolled under the professor
	public void viewEnrolledStudents(int courseId);
	//submit grades for a particular course and student and store in db
	public void submitGrades(int courseId, int studentId, String grade);
}
