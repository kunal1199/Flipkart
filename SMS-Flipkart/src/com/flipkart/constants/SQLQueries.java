package com.flipkart.constants;

//class for storing final SQL queries used in the application for interaction with the database
public class SQLQueries {
	public static final String DROP_COURSE = "delete from registeredcourses where studentid=? and courseid=? and status=\"pending\"";
	public static final String VIEW_CATALOG = "select * from course";
	public static final String ADD_COURSE = "insert into registeredcourses values(?, ?, ?, ?, ?, ?)";
	public static final String VIEW_REGISTERED = "select * from registeredcourses where studentid=?";
	public static final String GET_ROLE = "select type from roles, users where username=? and password=? and roles.id=users.roleid";
	public static final String FETCH_USER_ID = "select id from users where username=? and password=?";
	public static final String VIEW_COURSES_BY_ID = "select * from course where id=?";
	public static final String REGISTER = "update registeredcourses set status=?, mode=? where studentId=?";
	public static final String ADD_USER = "insert into users(username, password, roleid, gender) values(?, ?, ?, ?)";
	public static final String REMOVE_USER = "delete from users where id=?";
	public static final String ADD_COURSE_CATALOG = "insert into course(catalogid, `course name`, fees) values(1, ?, ?)";
	public static final String REMOVE_COURSE_CATALOG = "delete from course where id=?";
	public static final String VIEW_USERS = "Select * from users";
	public static final String ENROLL_TO_TEACH = "insert into professorandcourse(professorid, courseid) values(?, ?)";
	public static final String VIEW_ENROLLED_COURSES = "select * from professorandcourse where professorid=?";
	public static final String VIEW_ENROLLED_STUDENTS = "select distinct studentid, name from registeredcourses as rc, student as s where rc.courseid = ? and rc.status='successful' and studentid=s.id";
	public static final String SUBMIT_GRADES = "update registeredcourses set grades=? where studentid=? and courseid=?";
	public static final String VIEW_COURSES_BY_ID_REGISTERED = "select * from course where id in ( select courseid from registeredcourses where registeredcourses.studentid=? and status=\"pending\" )";
	public static final String CHECK_COURSE_COUNT = "select count(distinct courseid) as count from registeredcourses where registeredcourses.studentid=?";
	public static final String ADD_STUDENT = "insert into student values(?, ?, ?, ?, ?)";
	public static final String ADD_PROFESSOR = "insert into professor values(?, ?, ?, ?)";
	public static final String INSPECT_STUDENT = "select * from student where id=?";
	public static final String INSPECT_PROFESSOR = "select * from professor where id=?";
	public static final String GET_ROLE_BY_ID = "select roleid from users where id=?";
	public static final String INSPECT_ADMIN = "select * from admin where id=?";
	public static final String VIEW_PAYMENT_MODES = "select * from payment";
	public static final String GET_PAYMENT_MODE = "select * from payment where paymentid=?";
}
