package events;

import java.net.URL;
import java.util.ResourceBundle;

import application.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

	public void initialize() {
		numBedsSingle.setText("1");
	}
	
	public void loadRoom(Room room) {
		panelTitle.setText("Edit room");
		room_name.setText( room.getRoom_name() );
		numBedsSingle.setText( String.valueOf( room.getSingle_beds() ) );
		numBedsDouble.setText( String.valueOf( room.getDouble_beds() ) );
		
		String type = room.getRoom_type();
		if (type.equals("Standard")) {
			type_stand.setSelected(true);
		} else if ( type.equals("Comfort") ) {
			type_comf.setSelected(true);
		} else if ( type.equals("Suite") ) {
			type_suite.setSelected(true);
		}
		
		room_cost.setText( String.valueOf( room.getCost() ) );
	}
	
	public void saveRoom(ActionEvent event){
		Window window = panelTitle.getScene().getWindow();
		if (window instanceof Stage){
            ((Stage) window).close();
        }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
