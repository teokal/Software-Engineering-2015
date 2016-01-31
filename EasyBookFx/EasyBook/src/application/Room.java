package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Conn;
import javafx.beans.property.SimpleStringProperty;

public class Room { // NOPMD by teoka on 31/1/2016 6:54 μμ

	private int room_id; // NOPMD by teoka on 31/1/2016 7:12 μμ
	private SimpleStringProperty  room_name; // NOPMD by teoka on 31/1/2016 6:57 μμ
	private SimpleStringProperty room_type; // NOPMD by teoka on 31/1/2016 6:58 μμ
	private int single_beds; // NOPMD by teoka on 31/1/2016 7:12 μμ
	private int double_beds; // NOPMD by teoka on 31/1/2016 7:12 μμ
	private float cost; // NOPMD by teoka on 31/1/2016 7:12 μμ
	
	public Room (  // NOPMD by teoka on 31/1/2016 7:11 μμ
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
	
	// Setting Room ID
	public void setRoom_id(int room_id) { // NOPMD by teoka on 31/1/2016 7:12 μμ
		this.room_id = room_id;
	}
	
	// Setting Room Name
	public void setRoom_name(String room_name) { // NOPMD by teoka on 31/1/2016 7:12 μμ
		this.room_name.set(room_name);
	}
	
	// Setting Room Type
	public void setRoom_type(String room_type){ // NOPMD by teoka on 31/1/2016 7:10 μμ
		this.room_type.set(room_type);
	}
	
	// Setting Room Number of Single Beds of the Room
	public void setSingle_beds(int single_beds){ // NOPMD by teoka on 31/1/2016 7:10 μμ
		this.single_beds = single_beds;
	}
	
	// Setting Room Number of Double Beds of the Room
	public void setDouble_beds(int double_beds){ // NOPMD by teoka on 31/1/2016 7:12 μμ
		this.double_beds = double_beds;
	}
	
	// Setting Room Cost of room per day
	public void setCost(float cost){ // NOPMD by teoka on 31/1/2016 7:12 μμ
		this.cost = cost;
	}
	
	// Getting Room ID
	public int getRoom_id() { // NOPMD by teoka on 31/1/2016 7:10 μμ
		return room_id;
	}
	
	// Getting name of Room
	public String getRoom_name() { // NOPMD by teoka on 31/1/2016 7:11 μμ
		return room_name.get();
	}
	
	// Getting Type of Room
	public String getRoom_type(){ // NOPMD by teoka on 31/1/2016 7:10 μμ
		return room_type.get();
	}
	
	// Getting Number of Single Beds of the Room
	public int getSingle_beds() { // NOPMD by teoka on 31/1/2016 7:10 μμ
		return single_beds;
	}
	
	// Getting Double Beds of the Room
	public int getDouble_beds() { // NOPMD by teoka on 31/1/2016 7:10 μμ
		return double_beds;
	}
	
	// Getting cost of room per day
	public float getCost(){ // NOPMD by teoka on 31/1/2016 7:10 μμ
		return cost;
	}
	
	/* Function to delete this specific room from database */
	public void deleteThisRoom(){ // NOPMD by teoka on 31/1/2016 6:37 μμ
		try {
			Connection conn = Conn.connect(); // NOPMD by teoka on 31/1/2016 6:55 μμ
			String query = "DELETE FROM `rooms` WHERE `room_id` = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, getRoom_id() );
			
			preparedStatement.executeUpdate();
			
			conn.close();
		} catch (SQLException e) {
			Logger logger = Logger.getLogger("database");
			logger.setLevel(Level.SEVERE);
			logger.info(e.getMessage() );
		}
		
	}

}