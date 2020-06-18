package com.flipkart.utils;

import java.time.LocalDate;
import java.time.LocalTime;

//Data and time util class
public class DateTimeUtil {
	//returns current time along with the day of the week
	public static String getDateTime() {
		return LocalDate.now().getDayOfWeek() + ", " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
	}
}
