package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.constants.SQLQueries;
import com.flipkart.utils.DBUtils;

//Course dao class for interaction with the course table
public class CourseDao {
	//setting up the db connection
	Connection conn = DBUtils.getConnection();
	
	//returns a list of courses
	public List<Course> fetchRegistered() {
		// TODO Auto-generated method stub
		List <Course> courses = new ArrayList <>();
		try {
			String sql=SQLQueries.VIEW_CATALOG;
			PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);
		      //Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         int catalogId = rs.getInt("catalogId");
		         String name = rs.getString("course name");
		         int fees = rs.getInt("fees");
		         courses.add(new Course(id, catalogId, name, fees));
		      }
		      return courses;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//returns a course By Id
	public Course fetchRegistered(int id) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.VIEW_COURSES_BY_ID;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

		      //Extract data from result set
			  String name="Not Found";
			  int fees=0;
			  int catalogId = -1;
		      while(rs.next()){
		         //Retrieve by column name
		         name = rs.getString("course name");
		         catalogId = rs.getInt("catalogId");
		         fees = rs.getInt("fees");
		      }
		     return new Course(id, catalogId, name, fees);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//add a course
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		try {
			String sql = SQLQueries.ADD_COURSE;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, course.getId());
			stmt.setInt(2,  course.getCatalogId());
			stmt.setString(3, course.getName());
			stmt.setInt(4, course.getFees());
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//delete a course
	public void dropCourse(int id) {
		// TODO Auto-generated method stub
		try {
			String sql=SQLQueries.DROP_COURSE;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//update a course
	public void updateCourse(int id, Course course) {
		// TODO Auto-generated method stub
		try {
			String sql = SQLQueries.UPDATE_COURSE;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, course.getId());
			stmt.setInt(2,  course.getCatalogId());
			stmt.setString(3, course.getName());
			stmt.setInt(4, course.getFees());
			stmt.setInt(5, id);
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
