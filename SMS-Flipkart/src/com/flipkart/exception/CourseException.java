package com.flipkart.exception;

import org.apache.log4j.Logger;


//exception throws when course contraints are violated
public class CourseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CourseException.class);
	public void viewLog() {
		logger.error("You need to take up 4 courses for the semester. Try altering your registered courses list.");
	}
}
