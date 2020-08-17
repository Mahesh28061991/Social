package social.model;


import social.db.UserDBUtil;

import java.util.ArrayList;
import java.util.List;

public class User {
	int userId;
	String fname;
	String lname;
	String email;
	String password;
	List<User> friends;

	public User() {
		
	}
	public User(String fname, String lname, String email, String password) {
		this.fname=fname;
		this.lname=lname;
		this.email=email;
		this.password=password;
	}

	public User(int userId, String fname, String lname, String email, String password) {
		this.userId = userId;
		this.fname=fname;
		this.lname=lname;
		this.email=email;
		this.password=password;
		this.friends = new ArrayList<>();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getFriends() {
		return this.friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public boolean createUser(UserDBUtil userdb) {
		try {
			 userdb.insertUser(this);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public User loginUser(UserDBUtil userdb) {
		try {
			User tempUser = userdb.loginUser(this);
			if(tempUser != null ) {
				return tempUser;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public void addFriend(int userId, int friendId){
		//TODO add functionality to add friends through requests
		UserDBUtil userDBUtil = new UserDBUtil();
		userDBUtil.insertFriend(userId, friendId);
	}
	
	
	@Override
	public String toString() {
		return "My name is " + this.fname + " " + this.lname +"\n";
	}	
}
