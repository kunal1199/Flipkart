package com.flipkart.exception;

import org.apache.log4j.Logger;

//exception thrown when a course cannot be droppped
public class DropRegisteredException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DropRegisteredException.class);
	public void viewLog() {
		logger.error("Cannot drop the course. Either you have not added it in your list or it is already registered.");
	}
}
