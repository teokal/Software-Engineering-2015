package events;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;

import application.Main;
import database.Conn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdministrationPanel implements Initializable {

	public Connection conn = Conn.connect();

	@FXML
	private Button newBookBtn;
	@FXML
	private Button searchBook;
	@FXML
	private TextField searchBookText;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void newBook(ActionEvent event) {
		Main main = new Main();
		main.startNewBookPanel();
	}

	public void searchBookFromText(ActionEvent event) {

		String textSearch = searchBookText.getText();

		try {
			Statement stmt = (Statement) conn.createStatement();

			String query = "SELECT * FROM bookings "
					+ "WHERE `b_id` LIKE '%" + textSearch + "%'	"
					+ "OR	`code`  LIKE '%" + textSearch + "%'	"
					+ "OR	`name` LIKE '%" + textSearch + "%'	"
					+ "OR	`sname` LIKE '%" + textSearch + "%'	"
					+ "OR 	`tel` LIKE '%" + textSearch + "%'	"
					+ "OR	`email` LIKE '%" + textSearch + "%'	";
			
			ResultSet rs = stmt.executeQuery( query );

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1) System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
				System.out.println("");
			}
		} catch (SQLException e ) {

		}


		if (textSearch != null && textSearch != "") {
			String query = "Select * from bookings"; //like '" + textSearch + "'";

			//ResultSet rs = Conn.getData(this, query);
			//Conn.printResult(rs);
		}


	}





}
