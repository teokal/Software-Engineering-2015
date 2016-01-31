package events;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import database.Conn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.Window;;

public class AdministrationLogin implements Initializable  {


	@FXML
	private TextField usernameUser, passUser;
	@FXML
	private Hyperlink forgot;
	@FXML
	private Button submitBtn;
	
	public String userType;
	private Main main;
	
	public AdministrationLogin(Main main){
		this.main = main;
	}

	public void submit(ActionEvent event) {
	
		String[] result = checkDetails(usernameUser.getText(), passUser.getText() );

		if ( result[0].equals("valid") ) {
			main.typeUser = result[1];
			Main main = new Main();
			main.openEasyBookGUIAdminPanel( result[1] );
			
			Window window = usernameUser.getScene().getWindow();
			if (window instanceof Stage){
	            ((Stage) window).close();
	        }
			
		} else if ( result[0].equals("unknown") ){
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Please check your credentials!");
			alert.show();			
			return;
			
		}

	}

	public String[] checkDetails(String u, String p ) {

		String[] details = new String[2];

		try {
			Connection conn = Conn.connect(); // NOPMD by teoka on 31/1/2016 7:25 μμ
			String query = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";

			PreparedStatement ps = conn.prepareStatement( query );

			ps.setString(1, u );			
			ps.setString(2, hash(p) );
			ResultSet rs = ps.executeQuery(); // NOPMD by teoka on 31/1/2016 7:25 μμ

			if ( rs.next() ) {
				details[0] = "valid";
				details[1] = rs.getString("user_type");
			} else {
				details[0] = "unknown";
				details[1] = ""; 
			}
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return details;
	}
	
	private String hash(String s) {
		
		MessageDigest m;
		BigInteger bi = null;
		try {
			
			m = MessageDigest.getInstance("MD5");
			m.update( s.getBytes(), 0, s.length() );
			bi = new BigInteger(1, m.digest() );
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return bi.toString(16);
		
	}

	private void forgotPass(){
		
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Forgot credentials");
		dialog.setHeaderText("Please enter your email or username");

		Optional<String> result = dialog.showAndWait();
		if ( result.isPresent() ){
		    //System.out.println("Your name: " + result.get());
			Alert alert = new Alert(AlertType.ERROR);
			if ( result.get().trim().equals("") ) {
				alert.setTitle("Error");
				alert.setContentText("Field cannot be empty!");
			} else {
				alert.setAlertType( AlertType.INFORMATION );
				alert.setTitle("Request Received");
				alert.setContentText("If this account exists, you will shortly receive an email with instructions!");
			}
			alert.show();			
			return;
		}
		
		dialog.close();

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		submitBtn.setDisable(true);

		usernameUser.textProperty().addListener((observable, oldValue, newValue) -> {
			if (! passUser.getText().trim().isEmpty() ) {
				submitBtn.setDisable( newValue.trim().isEmpty() );
			}
		});
		passUser.textProperty().addListener((observable, oldValue, newValue) -> {
			if (! usernameUser.getText().trim().isEmpty() ) {
				submitBtn.setDisable( newValue.trim().isEmpty() );
			}
		});
		
		forgot.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		        forgotPass();
		    }
		});
	}

}
