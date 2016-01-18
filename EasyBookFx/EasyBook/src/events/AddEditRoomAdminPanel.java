package events;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Room;
import database.Conn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AddEditRoomAdminPanel implements Initializable {
	
	@FXML
	private Label panelTitle;
	
	@FXML
	private TextField room_name;
	@FXML
	private TextField numBedsSingle;
	@FXML
	private TextField numBedsDouble;
	@FXML
	private TextField room_cost;
	
	@FXML
	private RadioButton type_stand;
	@FXML
	private RadioButton type_comf;
	@FXML
	private RadioButton type_suite;
	
	@FXML
	private ToggleGroup radioRoomType;
	
	private Room loadedRoom;
	private boolean update = false;

	public void loadRoom(Room room) {
		
		loadedRoom = room;
		update = true;

		panelTitle.setText("Edit room");
		room_name.setText( loadedRoom.getRoom_name() );
		numBedsSingle.setText( String.valueOf( loadedRoom.getSingle_beds() ) );
		numBedsDouble.setText( String.valueOf( loadedRoom.getDouble_beds() ) );

		String type = loadedRoom.getRoom_type();
		if ( type.equals("Standard") ) {
			type_stand.setSelected(true);
		} else if ( type.equals("Comfort") ) {
			type_comf.setSelected(true);
		} else if ( type.equals("Suite") ) {
			type_suite.setSelected(true);
		}

		room_cost.setText( String.valueOf( loadedRoom.getCost() ) );
	}

	public void saveRoom(ActionEvent event){
		
		Connection conn = Conn.connect();
		String query = "";
		if (update) {
			query = "UPDATE `rooms` SET "
					+ "`room_name` = ?,"
					+ "`room_type` = ?,"
					+ "`single_beds` = ?,"
					+ "`double_beds` = ?,"
					+ "`cost` = ? "
					+ "WHERE `room_id` = ?";
		} else {
			query = "INSERT INTO `rooms`(`room_id`,`room_name`,"
					+ "`room_type`,`single_beds`,`double_beds`,`cost`) "
					+ "VALUES (null, ?, ?, ?, ?, ?)";
		}
		
		try {

			PreparedStatement ps = conn.prepareStatement( query );

			ps.setString(1, room_name.getText() );			
			ps.setString(2, getSelectedRoomType() );
			ps.setInt(3, Integer.parseInt( numBedsSingle.getText() ) );
			ps.setInt(4, Integer.parseInt( numBedsSingle.getText() ) );
			ps.setFloat(5, Float.parseFloat(room_cost.getText() ) );
			if (update) {
				ps.setInt(6, loadedRoom.getRoom_id() );
			}
			int rs = ps.executeUpdate();
			
			if (rs != 0 ){
				Window window = panelTitle.getScene().getWindow();
				if (window instanceof Stage){
					((Stage) window).close();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Room details, could not be updated!\nPlease try again later!");
				alert.show();			
				return;
			}
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getSelectedRoomType() {
		if ( type_comf.isSelected() == true ) {
			return "Comfort";
		} else if ( type_stand.isSelected() == true ) {
			return "Standard";
		} else {
			return "Suite";
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
