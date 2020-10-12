package com.tcs.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordDAO {
	
	public String updateRecord(int uid, int pid, String policyName, String description, String state, String status, String type,
			String jurisdiction, String source, int year) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd ="Update RECORDS SET UID=?,POLICYNAME=?,DESCRIPTION=?,STATE=?,STATUS=?,TYPE=?,JURISDICTION=?,SOURCE=?,YEAR=? WHERE PID=?";
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setInt(1, uid);
		pst.setString(2, policyName);
		pst.setString(3, description);
		pst.setString(4, state);
		pst.setString(5, status);
		pst.setString(6, type);
		pst.setString(7, jurisdiction);
		pst.setString(8, source);
		pst.setInt(9, year);
		pst.setInt(10,pid);
		pst.executeUpdate();
		return "Record Updated Successfully";		
	}
	
	public String createRecord(int uid, String policyName, String description, String state, String status, String type,
			String jurisdiction, String source, int year) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "INSERT INTO RECORDS(UID,POLICYNAME,DESCRIPTION,STATE,STATUS,TYPE,JURISDICTION,SOURCE,YEAR) VALUES(?,?,?,?,?,?,?,?,?) " ;
		PreparedStatement  pst = con.prepareStatement(cmd);
		pst.setInt(1, uid);
		pst.setString(2, policyName);
		pst.setString(3, description);
		pst.setString(4, state);
		pst.setString(5, status);
		pst.setString(6, type);
		pst.setString(7, jurisdiction);
		pst.setString(8, source);
		pst.setInt(9, year);
		pst.executeUpdate();
		return "Record Created...";
	}
	
	public String deleteRecord(int pid) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "DELETE FROM RECORDS WHERE PID=?";
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setInt(1,pid);
		pst.execute();		
		return "Record Deleted...";		
	}
	
	public Record searchRecord(int pid) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "Select * from RECORDS WHERE PID=?";
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setInt(1, pid);
		ResultSet rs = pst.executeQuery();
		Record c = null;
		if (rs.next()) {
			c=new Record();
			c.setPid(rs.getInt("PID"));
			c.setUid(rs.getInt("UID"));
			c.setPolicyName(rs.getString("POLICYNAME"));
			c.setDescription(rs.getString("DESCRIPTION"));
			c.setJurisdiction(rs.getString("JURISDICTION"));
			c.setState(rs.getString("STATE"));
			c.setStatus(rs.getString("STATUS"));
			c.setSource(rs.getString("SOURCE"));
			c.setType(rs.getString("TYPE"));
			c.setYear(rs.getInt("YEAR"));
		}
		return c;
	}
	
	public Record[] showRecord() throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		PreparedStatement pst=con.prepareStatement("select count(*) cnt from records");
		ResultSet rs=pst.executeQuery();
		rs.next(); 
		int cnt=rs.getInt("cnt"); 
		Record[] arr=new Record[cnt]; 
		pst=con.prepareStatement("select * from RECORDS"); 
		rs=pst.executeQuery();
		int i=0;
		Record c=null;
		while(rs.next()) {
			c=new Record();
			c.setPid(rs.getInt("PID"));
			c.setUid(rs.getInt("UID"));
			c.setPolicyName(rs.getString("POLICYNAME"));
			c.setDescription(rs.getString("DESCRIPTION"));
			c.setJurisdiction(rs.getString("JURISDICTION"));
			c.setState(rs.getString("STATE"));
			c.setStatus(rs.getString("STATUS"));
			c.setSource(rs.getString("SOURCE"));
			c.setType(rs.getString("TYPE"));
			c.setYear(rs.getInt("YEAR"));
			arr[i]=c;
			i++;
		}
		return arr;
	}

}
