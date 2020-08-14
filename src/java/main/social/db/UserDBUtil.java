package java.main.social.db;

import java.main.social.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDBUtil {
	
	private DataSource datasource;
	
	public UserDBUtil(DataSource datasource) {
		this.datasource = datasource;
	}
	
	
	public void insertUser(User user) throws Exception {
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		String fname = user.getFname();
		String lname = user.getLname();
		String email = user.getEmail();
		String password = user.getPassword();
		
		try {
			
			conn =  this.datasource.getConnection();
			
			String sql = String.format("INSERT INTO user VALUES('%s','%s','%s','%s')",email,fname,lname,password);
			
			stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} finally {
			// TODO: handle finally clause
			close(conn,stmt,pstmt,res);
		}
	}
	
	public User loginUser(User user) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		String email = user.getEmail();
		
		try {
			
			conn =  this.datasource.getConnection();
			
			String sql = String.format("Select * from user WHERE email = ?");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,email);
			
			res = pstmt.executeQuery();
			
			if(res.next()) {
				String fname=res.getString("fname");
				String lname=res.getString("lname");
				String logemail=res.getString("email");
				String logpassword=res.getString("password");
				
				return new User(fname,lname,logemail,logpassword);
			}
			
		} finally {
			// TODO: handle finally clause
			close(conn,stmt,pstmt,res);
		}
		return null;
	}
	
	
	private void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet res) {
		
		try {
			
			if(conn != null){				
				conn.close();
			}
			
			if(stmt != null) {				
				stmt.close();
			}
			
			if(pstmt != null) {				
				pstmt.close();
			}
			
			if(res != null) {				
				res.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
}
