package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Conn;
import javafx.beans.property.SimpleStringProperty;

public class Room {

	private int room_id;
	private SimpleStringProperty  room_name;
	private SimpleStringProperty room_type;
	private int single_beds;
	private int double_beds;
	private float cost;
	
	public Room ( 
			int room_id,
			String room_name,
			String room_type,
			int single_beds,
			int double_beds,
			float cost){
		
		this.room_id = room_id;
		this.room_name = new SimpleStringProperty(room_name);
		this.room_type = new SimpleStringProperty(room_type);
		this.single_beds = single_beds;
		this.double_beds = double_beds;
		this.cost = cost;
		
	}
	
	public void setRoom_id(int s) {
		room_id = s;
	}
	
	public void setRoom_name(String s) {
		room_name.set(s);
	}
	
	public void setRoom_type(String s){
		room_type.set(s);
	}
	
	public void setSingle_beds(int s){
		single_beds = s;
	}
	
	public void setDouble_beds(int s){
		double_beds = s;
	}
	
	public void setCost(float s){
		cost = s;
	}
	
	public int getRoom_id() {
		return room_id;
	}
	
	public String getRoom_name() {
		return room_name.get();
	}
	
	public String getRoom_type(){
		return room_type.get();
	}
	
	public int getSingle_beds() {

		return single_beds;
	}
	
	public int getDouble_beds() {
		return double_beds;
	}
	
	public float getCost(){
		return cost;
	}
	
	public void deleteThisRoom(){
		try {
			Connection conn = Conn.connect();
			String query = "DELETE FROM `rooms` WHERE `room_id` = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, getRoom_id() );
			
			ps.executeUpdate();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	

}