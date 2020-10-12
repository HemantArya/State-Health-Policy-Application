package com.tcs.project;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserREST {
	
	@POST
	@Path("/validate/{email}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int validate(@PathParam("email") String email, @PathParam("password") String password) throws SQLException {
		UserDAO dao = new UserDAO();
		return dao.authenticate(email, password);
	}
	
	@POST
	@Path("/register/{name}/{email}/{password}/{phone}/{isAdmin}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerUser(@PathParam("name") String name, @PathParam("email") String email, @PathParam("password") String password, @PathParam("phone") String phone, @PathParam("isAdmin") int isAdmin) throws SQLException {
		UserDAO dao = new UserDAO();
		return dao.registerUser(name, email, password, phone, isAdmin);
	}
	
	@POST
	@Path("/update/{id}/{name}/{email}/{password}/{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateUser(@PathParam("id") int id, @PathParam("name") String name, @PathParam("email") String email, @PathParam("password") String password, @PathParam("phone") String phone) throws SQLException {
		UserDAO dao = new UserDAO();
		return dao.updateUser(id, name, email, password, phone);
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("id") int id) throws SQLException {
		String res = new UserDAO().deleteUser(id);
		return res;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User[] showUsers() throws SQLException {
		User[] res = new UserDAO().showUser();
		return res;
	}
	
	@GET
	@Path("/searchuser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User searchUser(@PathParam("id") int id) throws SQLException {
		User res = new UserDAO().searchUser(id);
		return res;
	}
	
	@GET
	@Path("/searchuserbyemail/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public User searchUserByEmail(@PathParam("email") String email) throws SQLException {
		User res = new UserDAO().searchUserByEmail(email);
		return res;
	}

}
