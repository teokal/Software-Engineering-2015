package events;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class ChangeBookPanel implements Initializable {

	private int bookID = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void passID(int bookID) {
		this.bookID = bookID;
	}

}
