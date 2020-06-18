package com.flipkart.dao;

import java.util.List;

import com.flipkart.model.Course;

//interface for the catalog dao
public interface ICatalogDao {
	//fetch the catalog from the catalog table
	public List<Course> fetchCatalog();
	//get name of the course using the course id
	public String viewCourseById(Integer id);	
	//get fees for a course using the id of the course
	public int getFees(Integer id); 
}
