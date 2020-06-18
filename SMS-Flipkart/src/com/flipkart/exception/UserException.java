package com.flipkart.exception;

import org.apache.log4j.Logger;

public class UserException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UserException.class);
	public void viewLog() {
		logger.error("No such user found, please try again.");
	}
}
