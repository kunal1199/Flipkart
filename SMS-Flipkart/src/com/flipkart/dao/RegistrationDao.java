package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLQueries;
import com.flipkart.exception.CourseException;
import com.flipkart.exception.DropRegisteredException;
import com.flipkart.model.Course;
import com.flipkart.utils.DBUtils;

//class for interacting with the sql database for student registration functionalities
public class RegistrationDao implements IRegistrationDao {
	//logger initialized for this class
	private static Logger logger = Logger.getLogger(RegistrationDao.class);
	//initialize connection using the setup done in dbutils class
	Connection conn = DBUtils.getConnection();
	//add a course to the registeredcourses table
	public void addCourse(int studentId, int courseId) throws SQLException, CourseException {
			String sql = SQLQueries.CHECK_COURSE_COUNT;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while(rs.next()){
		         //Retrieve by column name
		         count = rs.getInt("count");
		      }
			if(count>=4) {
				throw new CourseException();
			}
			sql=SQLQueries.ADD_COURSE;
			stmt = conn.prepareStatement(sql);
			Time time = new Time(0L);
			time.setTime(new java.util.Date().getTime());
			Timestamp timestamp = new Timestamp(time.getTime());
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			stmt.setString(3, "pending");
			stmt.setString(4, "Not available");
			stmt.setTimestamp(5, timestamp);
			stmt.setInt(6, 0);
			stmt.executeUpdate();
			logger.info("Course added!");
	}
	//remove a course from the registered courses list
	public void removeCourse(int studentId, int courseId) throws SQLException, DropRegisteredException {
		// TODO Auto-generated method stub
			String sql=SQLQueries.DROP_COURSE;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			int rows = stmt.executeUpdate();
			if(rows==0) {
				throw new DropRegisteredException();
			}
			logger.info("Course removed!");
	}
	//fetch a list of all the registered courses
	public List<Course> fetchRegistered(int studentId) throws SQLException {
		// TODO Auto-generated method stub
		List <Course> registeredcourses = new ArrayList <>();
		String sql=SQLQueries.VIEW_REGISTERED;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, studentId);
		 ResultSet rs = stmt.executeQuery();

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("courseid");
	         String status = rs.getString("status");
	         Timestamp timestamp = rs.getTimestamp("date");
	         String mode = getPaymentMode(rs.getInt("mode"));
	         registeredcourses.add(new Course(id, status, timestamp, mode));
	      }
	      return registeredcourses;
	}
	private String getPaymentMode(int x) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.GET_PAYMENT_MODE;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, x);
			ResultSet rs = stmt.executeQuery();
			String mode="Not paid";
			while(rs.next()) {
				mode = rs.getString("mode");
			}
			return mode;
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	//finalize the registration for all the courses
	public void registrationDone(int studentId, int choice) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.REGISTER;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "successful");
			stmt.setInt(2, choice);
			stmt.setInt(3, studentId);
			stmt.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		}
	}
	//generate a report card of the semester
	public Map <Integer, String> generateReportCard(int studentId) throws SQLException {
		// TODO Auto-generated method stub
		Map <Integer, String> registeredcourses = new HashMap <>();
		String sql=SQLQueries.VIEW_REGISTERED;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, studentId);
		 ResultSet rs = stmt.executeQuery();

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("courseid");
	         String grades = rs.getString("grades");
	         registeredcourses.put(id, grades);
	      }
	      return registeredcourses;
	}
	//check for any pending registration
	public List<Integer> fetchRegisteredPending(int studentId) {
		// TODO Auto-generated method stub
		List <Integer> pendingcourses = new ArrayList<>();
		try {
			String sql=SQLQueries.VIEW_COURSES_BY_ID_REGISTERED;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			 ResultSet rs = stmt.executeQuery();

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         pendingcourses.add(id);
		      }
		      return pendingcourses;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			return null;
		}	
		}
	//check for course constraints
	public boolean allowed(int studentId) {
		// TODO Auto-generated method stub
		try {
			String sql = SQLQueries.CHECK_COURSE_COUNT;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while(rs.next()){
		         //Retrieve by column name
		         count = rs.getInt("count");
		      }
			if(count!=4) {
				throw new CourseException();
			}
		} catch (CourseException e) {
			// TODO Auto-generated catch block
			e.viewLog();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
	public void viewPaymentModes() {
		// TODO Auto-generated method stub
		try {
			String sql = SQLQueries.VIEW_PAYMENT_MODES;
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
		         //Retrieve by column name
		         logger.info(rs.getInt("paymentid")+ " : " + rs.getString("mode"));
		      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
}
