package social.db;

import social.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBUtil {
	
//	private DataSource datasource;
//
//	public UserDBUtil(DataSource datasource) {
//		this.datasource = datasource;
//	}


	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/social";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "July1993";

	Connection conn;

	public Statement getStatement(){

		conn = null;
		Statement stmt = null;

		try{
			Class.forName(JDBC_DRIVER);

			if(conn == null){
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
			}
			stmt = conn.createStatement();
		}catch (Exception e){
			e.printStackTrace();
		}

		return stmt;
	}

	public Connection getConnection(){

		conn = null;
		try{
			Class.forName(JDBC_DRIVER);

			if(conn == null){
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return conn;
	}


	public void insertUser(User user) throws Exception {
		
		Connection conn = getConnection();
//		Statement stmt = null;
		PreparedStatement pstmt = null;
//		ResultSet res = null;
		Statement stmt = null;
		String fname = user.getFname();
		String lname = user.getLname();
		String email = user.getEmail();
		String password = user.getPassword();
		
		try {

			String sql = String.format("INSERT INTO user (fname, lname, email, password)"+
					" values (?, ?, ?, ?)");
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, email);
			pstmt.setString(4, password);

			pstmt.execute();
			
		} finally {
			// TODO: handle finally clause
//			close(conn,stmt,pstmt,res);
			close(pstmt);
		}
	}
	
	public User loginUser(User user) throws Exception {
		Connection conn = getConnection();
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;

		String email = user.getEmail();

		try {

			String sql = String.format("Select * from user WHERE email = ?");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,email);

			res = pstmt.executeQuery();

			if(res.next()) {
				int id = res.getInt("user_id");
				String fname=res.getString("fname");
				String lname=res.getString("lname");
				String logemail=res.getString("email");
				String logpassword=res.getString("password");

				return new User(id,fname,lname,logemail,logpassword);
			}

		} finally {
			// TODO: handle finally clause
			close(pstmt);
		}
		return null;
	}

	public User getUser(int userId){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {

			String sql = String.format("Select * from user WHERE user_id = ?");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,userId);

			res = pstmt.executeQuery();

			if(res.next()) {
				int id = res.getInt("user_id");
				String fname=res.getString("fname");
				String lname=res.getString("lname");
				String logemail=res.getString("email");
				String logpassword=res.getString("password");

				return new User(id, fname,lname,logemail,logpassword);
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		finally
		 {
			// TODO: handle finally clause
			close(pstmt);
		}
		return null;
	}

	public void insertFriend(int userId, int friendId){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;

		try {

			String sql = String.format("INSERT INTO friend_list (user_id, friend_id)"+
					" values (?, ?)");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userId);
			pstmt.setInt(2, friendId);

			pstmt.execute();

		}catch (Exception e){
			e.printStackTrace();
		}
		finally {
			// TODO: handle finally clause
//			close(conn,stmt,pstmt,res);
			close(pstmt);
		}
	}

	public int getUserId(String fname, String lname){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		int id = -1;
		try {

			String sql = String.format("Select user_id from user WHERE fname = ? AND lname = ?");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,fname);
			pstmt.setString(2,lname);

			res = pstmt.executeQuery();

			if(res.next()){
				id= res.getInt("user_id");
			}

			return id;

		}catch (Exception e){
			e.printStackTrace();
		}
		finally
		{
			// TODO: handle finally clause
			close(pstmt);
		}
		return id;
	}


	public List<User> getFriends(int userId){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<User> friends = new ArrayList<>();
		int[] array = null;
		try {

			String sql = String.format("Select friend_id from friend_list WHERE user_id= ?");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,userId);

			res = pstmt.executeQuery();

			User friend = null;

			while(res.next()){
				friend = getUser(res.getInt("friend_id"));
				friends.add(friend);
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		finally
		{
			// TODO: handle finally clause
			close(pstmt);
		}

		return friends;
	}
	
//	private void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet res) {
//
//		try {
//
//			if(conn != null){
//				conn.close();
//			}
//
//			if(stmt != null) {
//				stmt.close();
//			}
//
//			if(pstmt != null) {
//				pstmt.close();
//			}
//
//			if(res != null) {
//				res.close();
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
private void close(PreparedStatement stmt) {

	try {

		if(conn != null){
			conn.close();
		}

		if(stmt != null) {
			stmt.close();
		}

	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}

}
