package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLQueries;
import com.flipkart.utils.DBUtils;

//class for interacting with the sql database for professor functionalities
public class ProfessorDao implements IProfessorDao {
	//logger initialized for this class
	private static Logger logger = Logger.getLogger(AdminDao.class);
	//initialize connection using the setup done in dbutils class
	Connection conn = DBUtils.getConnection();
	//enroll for a course present to be taken up
	public void enrollForCourse(int professorId, int courseId) {
		// TODO Auto-generated method stub
		try {
			String sql = SQLQueries.ENROLL_TO_TEACH;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  professorId);
			stmt.setInt(2,  courseId);
			stmt.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	//view the list of courses enrolled in
	public List<Integer> viewEnrolledCourses(int professorId) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.VIEW_ENROLLED_COURSES;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorId);
			 ResultSet rs = stmt.executeQuery();
			 List <Integer> enrolledcourses = new ArrayList<Integer>();
		      //Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("courseid");
		         enrolledcourses.add(id);
		      }
		      return enrolledcourses;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	// view the list of students enrolled under the professor
	public void viewEnrolledStudents(int courseId) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.VIEW_ENROLLED_STUDENTS;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, courseId);
			ResultSet rs = stmt.executeQuery();
		      //Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("studentid");
		         String name = rs.getString("name");
		         logger.info(id + " : " + name);
		      }
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	//submit grades for a particular course and student and store in db
	public void submitGrades(int courseId, int studentId, String grade) {
		// TODO Auto-generated method stub
		try {
			String sql = SQLQueries.SUBMIT_GRADES;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, grade);
			stmt.setInt(2,  studentId);
			stmt.setInt(3,  courseId);
			stmt.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}

}
