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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AddEditRoomAdminPanel implements Initializable {

	@FXML
	private Label panelTitle;
	@FXML
	private TextField room_name, numBedsSingle, numBedsDouble, room_cost;
	@FXML
	private RadioButton type_stand, type_comf, type_suite;
	@FXML
	private ToggleGroup radioRoomType;

	private Room loadedRoom;
	private boolean update = false;
	
	AdministrationPanel o;
	
	public void setParent(AdministrationPanel o){
		this.o = o;
	}

	public void loadRoom(Room room, AdministrationPanel o) {
		setParent(o);
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
		Alert alert = new Alert(AlertType.ERROR);
		
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

			if (! room_name.getText().trim().equals("") ) {
				ps.setString(1, room_name.getText() );			
			} else {
				alert.setContentText("Room's Title cannot be empty!");
				alert.show();
				return;
			}
			
			String type = getSelectedRoomType();
			if (! type.equals("none") ){
				ps.setString(2, type );
			} else {
				alert.setContentText("Please select room's type!");
				alert.show();
				return;
			}

			try{
				ps.setInt(3, Integer.parseInt( numBedsSingle.getText() ) );
				ps.setInt(4, Integer.parseInt( numBedsDouble.getText() ) );
			} catch (NumberFormatException e) {
				alert.setContentText("Wrong number of beds!\nEnter an integer value!");
				alert.show();
				return;
			}
			
			try{
				ps.setFloat(5, Float.parseFloat(room_cost.getText() ) );
			} catch (NumberFormatException e) {
				alert.setContentText("Wrong cost value!\nPlease try again!");
				alert.show();
				return;
			}
			
			if (update) {
				ps.setInt(6, loadedRoom.getRoom_id() );
			}
			
			int rs = ps.executeUpdate();
			if (rs != 0 ){
				this.o.showAllRooms(null);
				alert.setAlertType(AlertType.INFORMATION);
				if (update) {
					alert.setContentText("Room has been updated!");
				} else {
					alert.setContentText("Room has been created!");
				}
				alert.show();
				Window window = panelTitle.getScene().getWindow();
				if (window instanceof Stage){
					((Stage) window).close();
				}
			} else {
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
		} else if ( type_suite.isSelected() == true ) {
			return "Suite";
		} else {
			return "none";
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
