package applications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Conn;
import javafx.beans.property.SimpleStringProperty;

public class Users {

	private SimpleStringProperty username;
	private SimpleStringProperty user_type;
	private SimpleStringProperty email;
	private SimpleStringProperty sname;
	private SimpleStringProperty name;
	private SimpleStringProperty password;
	
	public Users(String username, String user_type, String email, String sname, String name, String password) {
	
		this.username = new SimpleStringProperty (username);
		this.user_type = new SimpleStringProperty (user_type);
		this.email = new SimpleStringProperty (email);
		this.sname = new SimpleStringProperty (sname);
		this.name =  new SimpleStringProperty (name);
		this.password = new SimpleStringProperty (password);
	}

	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}
	
	public String getUser_type() {
		return user_type.get();
	}

	public void setUser_type(String username) {
		this.user_type.set(username);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String user_type) {
		this.email.set(user_type);
	}
	
	public String getSname() {
		return sname.get();
	}

	public void setSname(String user_type) {
		this.sname.set(user_type);
	}
	
	public String getName() {
		return name.get();
	}

	public void setName(String user_type) {
		this.name.set(user_type);
	}
	
	public String getPass() {
		return password.get();
	}

	public void setPass(String user_type) {
		this.password.set(user_type);
	}
	
	public void deleteThisUser(){
		try {
			Connection conn = Conn.connect();
			String query = "DELETE FROM `users` WHERE `username` = ?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, getUsername() );
			ps.executeUpdate();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
