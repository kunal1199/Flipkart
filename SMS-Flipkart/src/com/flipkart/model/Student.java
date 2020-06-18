package com.flipkart.model;

import org.apache.log4j.Logger;

//student class inherits user class
public class Student extends User {
	String name;
	String phone;
	String email;
	int scholarship;
	//logger initialized for this class
	private static Logger logger = Logger.getLogger(Student.class);
	
	//student class constructor
	public Student(String name, String phone, String email, int scholarship) {
		// TODO Auto-generated constructor stub
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.scholarship = scholarship;
	}
	
	//inspect a student
	public void getDetails() {
		logger.info(String.format("Name: %12s  Phone: %10s  Email: %10s  Scholarship: %d", name, phone, email, scholarship));
	}

}
