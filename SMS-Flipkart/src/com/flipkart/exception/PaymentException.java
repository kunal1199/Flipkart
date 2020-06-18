package com.flipkart.exception;

import org.apache.log4j.Logger;

//exception thrown when the payment gateway fails
public class PaymentException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PaymentException.class);
	public void viewLog() {
		logger.error("Payment gateway failed!");
	}
}
