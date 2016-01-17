package events;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AdministrationLogin implements Initializable  {
	
	
	@FXML
	private TextField usernameUser;
	
	@FXML
	private TextField passUser;
	
	
	public void submit(ActionEvent event) {
		String newRoomDirection = "/application/easybookgui1.fxml";
		Main main = new Main();
		main.openNewPanel(newRoomDirection);
	}
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
