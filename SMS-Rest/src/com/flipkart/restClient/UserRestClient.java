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

import com.flipkart.service.UserOperations;
import com.flipkart.bean.User;

//setting up the base path and defining the rest User class
@Path("/users")
public class UserRestClient {
	//initializing a userOperations object
	UserOperations userOperations = new UserOperations();
	
	//get request to get all the users details
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> showUsers() {
		return userOperations.fetchUsers();
	}
	
	//get request to get details of a specific user
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User showById(@PathParam("id") int id) {
		return userOperations.fetchById(id);
	}
	
	//post request to add a new user
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user){
		int id = userOperations.addUser(user);
		String result = "Course saved at id : " + id;
		return Response.status(201).entity(result).build();
	}
	
	//delete request to delete a user by id
	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") int id) {
		userOperations.dropUser(id);
		String result = "User deleted with id : " + id;
		return Response.ok().entity(result).build();
	}
	
	//put request to update user details
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, User user) {
		userOperations.updateUser(id, user);
		String result = "User updated!";
		return Response.ok().entity(result).build();
	}
	
}
