package com.tcs.project;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/record")
public class RecordREST {
	
	@POST
	@Path("/update/{uid}/{pid}/{policyName}/{description}/{state}/{status}/{type}/{jurisdiction}/{source}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateRecord(@PathParam("uid") int uid, @PathParam("pid") int pid, @PathParam("policyName") String policyName, @PathParam("description") String description, @PathParam("state") String state, @PathParam("status") String status, @PathParam("type") String type, @PathParam("jurisdiction") String jurisdiction, @PathParam("source") String source, @PathParam("year") int year) throws SQLException {
		RecordDAO dao = new RecordDAO();
		return dao.updateRecord(uid,pid,policyName,description,state,status,type,jurisdiction,source,year);
	}
	
	@POST
	@Path("/create/{uid}/{policyName}/{description}/{state}/{status}/{type}/{jurisdiction}/{source}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createRecord(@PathParam("uid") int uid, @PathParam("policyName") String policyName, @PathParam("description") String description, @PathParam("state") String state, @PathParam("status") String status, @PathParam("type") String type, @PathParam("jurisdiction") String jurisdiction, @PathParam("source") String source, @PathParam("year") int year) throws SQLException {
		RecordDAO dao = new RecordDAO();
		return dao.createRecord(uid,policyName,description,state,status,type,jurisdiction,source,year);
	}
	
	@GET
	@Path("/delete/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteRecord(@PathParam("pid") int pid) throws SQLException {
		String res = new RecordDAO().deleteRecord(pid);
		return res;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Record[] showRecords() throws SQLException {
		Record[] res = new RecordDAO().showRecord();
		return res;
	}
	
	@GET
	@Path("/searchrecord/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Record searchRecord(@PathParam("pid") int pid) throws SQLException {
		Record res = new RecordDAO().searchRecord(pid);
		return res;
	}

}
