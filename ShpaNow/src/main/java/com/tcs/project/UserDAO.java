package com.tcs.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	
	public String updateUser(int id, String name, String email, String password, String phone) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd ="Update userDetails SET NAME=?,EMAIL=?,PASSWORD=?,PHONE=? WHERE ID=?";
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setString(1, name);
		pst.setString(2, email);
		pst.setString(3, password);
		pst.setString(4, phone);
		pst.setInt(5,id);
		pst.executeUpdate();
		return "User Updated Successfully";		
	}
	
	public String deleteUser(int id) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "DELETE FROM userDetails WHERE ID=?";
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setInt(1,id);
		pst.execute();		
		return "User Deleted...";		
	}
	
	public String registerUser(String name, String email, String password, String phone, int isAdmin) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "insert into userDetails(NAME,EMAIL,PASSWORD,PHONE,ISADMIN) VALUES(?,?,?,?,?) " ;
		PreparedStatement  pst = con.prepareStatement(cmd);
		pst.setString(1, name);
		pst.setString(2, email);
		pst.setString(3, password);
		pst.setString(4, phone);
		pst.setInt(5, isAdmin);
		pst.executeUpdate();
		return "User Registered...";
	}
	
	public int authenticate(String email, String password) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "select count(*) cnt from userDetails where email=? "
				+ " AND password=?";
		PreparedStatement  pst = con.prepareStatement(cmd);
		pst.setString(1, email);
		pst.setString(2, password);
		ResultSet rs = pst.executeQuery();
		rs.next();
		int cnt = rs.getInt("cnt");
		return cnt;
	}
	
	public User searchUser(int id) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "Select * from userDetails WHERE ID=?";
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		User c = null;
		if (rs.next()) {
			c=new User();
			c.setId(rs.getInt("ID"));
			c.setEmail(rs.getString("EMAIL"));
			c.setIsAdmin(rs.getInt("ISADMIN"));
			c.setPassword(rs.getString("PASSWORD"));
			c.setName(rs.getString("NAME"));
			c.setPhone(rs.getString("PHONE"));
		}
		return c;
	}
	
	public User searchUserByEmail(String email) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "Select * from userDetails WHERE EMAIL=?";
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		User c = null;
		if (rs.next()) {
			c=new User();
			c.setId(rs.getInt("ID"));
			c.setEmail(rs.getString("EMAIL"));
			c.setIsAdmin(rs.getInt("ISADMIN"));
			c.setPassword(rs.getString("PASSWORD"));
			c.setName(rs.getString("NAME"));
			c.setPhone(rs.getString("PHONE"));
		}
		return c;
	}
	
	public User[] showUser() throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		PreparedStatement pst=con.prepareStatement("select count(*) cnt from userDetails");
		ResultSet rs=pst.executeQuery();
		rs.next(); 
		int cnt=rs.getInt("cnt"); 
		User[] arr=new User[cnt]; 
		pst=con.prepareStatement("select * from userDetails"); 
		rs=pst.executeQuery();
		int i=0;
		User c=null;
		while(rs.next()) {
			c=new User();
			c.setId(rs.getInt("ID"));
			c.setEmail(rs.getString("EMAIL"));
			c.setIsAdmin(rs.getInt("ISADMIN"));
			c.setPassword(rs.getString("PASSWORD"));
			c.setName(rs.getString("NAME"));
			c.setPhone(rs.getString("PHONE"));
			arr[i]=c;
			i++;
		}
		return arr;
	}
}
