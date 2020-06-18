package com.flipkart.restClient;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.service.CourseOperations;

//class for managing the rest results
@Path("/courses")
public class CourseRestClient {
	//initializing an operations class object
	CourseOperations courseOperations = new CourseOperations();
	
	//setting up the get method to get all the courses
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewRegistered() {
		return courseOperations.fetchRegistered();
	}
	
	//setting up a get method to get a specific course details
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course get(@PathParam("id") int id) {
	    return courseOperations.get(id);
	}
	
	//post method to add a new course
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course){
		int id = courseOperations.addCourse(course);
		String result = "Course saved at id : " + id;
		return Response.status(201).entity(result).build();
	}
	
	//delete a course by id
	@DELETE
	@Path("{id}")
	public Response deleteCourse(@PathParam("id") int id) {
		courseOperations.dropCourse(id);
		String result = "Course deleted with id : " + id;
		return Response.ok().entity(result).build();
	}
	
	//put method for updating a course details
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCourse(@PathParam("id") int id, Course course) {
		courseOperations.updateCourse(id, course);
		String result = "Course updated!";
		return Response.ok().entity(result).build();
	}
}
