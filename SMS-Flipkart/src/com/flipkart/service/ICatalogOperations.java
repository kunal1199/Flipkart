package com.flipkart.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.dao.CatalogDao;
import com.flipkart.model.Course;

//using java8 feature of default function in an interface
//student, professor and admin class all have the common functionality of viewing the catalog so they implement this interface
public interface ICatalogOperations {
	static Logger logger = Logger.getLogger(ICatalogOperations.class);
	default List<Course> viewCatalog() {
		// access using Catalog dao
		List <Course> courses = new CatalogDao().fetchCatalog();
		return courses;
	}
}
