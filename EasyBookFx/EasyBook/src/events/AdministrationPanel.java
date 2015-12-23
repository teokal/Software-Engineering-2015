package events;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;

import application.Book;
import application.Main;
import database.Conn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrationPanel implements Initializable {

	public Connection conn = Conn.connect();

	@FXML
	private TableColumn<Book, String> check_in_clmn;
	@FXML
	private TableColumn<Book, String> paid_clmn;
	@FXML
	private TableColumn<Book, String> name_clmn;
	@FXML
	private TableColumn<Book, String> surname_clmn;
	@FXML
	private TableColumn<Book, String> check_out_clmn;
	@FXML
	private TableColumn<Book, String> tel_clmn;
	@FXML
	private TableColumn<Book, String> email_clmn;
	@FXML
	private TableColumn<Book, String> idnum_clmn;
	@FXML
	private ToggleGroup categoryTypesRooms;
	@FXML
	private Button searchBook;
	@FXML
	private TableColumn<Book, String> persons_clmn;
	@FXML
	private ToggleGroup categoryOffers;
	@FXML
	private ToggleGroup categoryRadioDatesOffers;
	@FXML
	private ToggleGroup categoryBookings;
	@FXML
	private TableColumn<Book, String> code_clmn;
	@FXML
	private TableColumn<Book, String> room_clmn;
	@FXML
	private ToggleGroup categoryBedRooms;
	@FXML
	private Pane bookings_tab;
	@FXML
	private TextField searchBookText;
	@FXML
	private ToggleGroup categoryRadioTypeOffers;
	@FXML
	private ToggleGroup categoryIncomeBooksStatistics;
	@FXML
	private TableView<Book> bookingsTable;
	@FXML
	private Button newBookBtn;
	@FXML
	private ToggleGroup categoryTypeOptions;
    @FXML
    private ToggleButton allBookings;
    @FXML
    private ToggleButton completedBookings;
    @FXML
    private ToggleButton comingSoonBookings;
    @FXML
    private ToggleButton runningBookings;

	private ObservableList<Book> bookingList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showAllBookings(null);
	}

	public void newBook(ActionEvent event) {
		Main main = new Main();
		main.startNewBookPanel();
	}
	
	public void showAllBookings(ActionEvent event) {
		showBookingsOnTable("Select * from bookings");
	}
	
	public void showRunning(ActionEvent event) {
		showBookingsOnTable("Select * from bookings"
				+ " where ( `check_in` >= concat(curdate(), ' ', curtime()) ) "
				+ "AND (`check_out` < concat(curdate(), ' ', curtime()) )");
	}
	
	public void showComingSoon(ActionEvent event) {
		showBookingsOnTable("Select * from bookings"
				+ " where `check_in` < concat( curdate(), ' ', curtime() )");
	}
	
	public void showCompleted(ActionEvent event) {
		showBookingsOnTable("Select * from bookings"
				+ " where `check_out` <= concat( curdate(), ' ', curtime() )");
	}

	public void searchBookFromText(ActionEvent event) {

		String textSearch = searchBookText.getText();

		if (! textSearch.isEmpty() ) {
			
			deselectToggle();
			
			try {
				Statement stmt = (Statement) conn.createStatement();

				String query = "SELECT * FROM bookings "
						+ "WHERE `b_id` LIKE '%" + textSearch + "%'	"
						+ "OR	`code`  LIKE '%" + textSearch + "%'	"
						+ "OR	`name` LIKE '%" + textSearch + "%'	"
						+ "OR	`sname` LIKE '%" + textSearch + "%'	"
						+ "OR 	`tel` LIKE '%" + textSearch + "%'	"
						+ "OR	`email` LIKE '%" + textSearch + "%'	";

				showBookingsOnTable(query);

			} catch (SQLException e ) {
				e.printStackTrace();
			}
		}
	}

	public void showBookingsOnTable(String query) {

		code_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("code") );
		name_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("name") );
		surname_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("sname") );
		check_in_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("check_in") );
		check_out_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("check_out") );
		tel_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("tel") );
		email_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("email") );
		idnum_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("idnum") );
		room_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("name") );
		persons_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("paid") );
		paid_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("paid") );

		bookingList = getTableData(query);
		bookingsTable.setItems( bookingList );

		bookingsTable.getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());

	}

	private class RowSelectChangeListener implements ChangeListener<Number> {

		@Override
		public void changed(ObservableValue<? extends Number> ov, 
				Number oldVal, Number newVal) {

			int ix = newVal.intValue();

			if ((ix < 0) || (ix >= bookingList.size())) {

				return; // invalid data
			}

			Book book = bookingList.get(ix);
			//actionStatus.setText(book.toString());	
		}
	}

	private ObservableList<Book> getTableData(String query) {

		List<Book> list = new ArrayList<Book>();
		
		boolean found = false;

		try {
			Statement stmt = (Statement) conn.createStatement();

			ResultSet rs = stmt.executeQuery( query );

			while (rs.next()) {
				found = true;
				list.add( new Book(
						rs.getInt("b_id"), 
						rs.getString("code"), 
						rs.getString("check_in"),
						rs.getString("check_out"),
						rs.getString("name"),
						rs.getString("sname"),
						rs.getString("tel"),
						rs.getString("email"),
						rs.getString("idnum"),
						rs.getString("payment_method"),
						rs.getFloat("total_cost"),
						rs.getString("paid"),
						rs.getFloat("money_received"),
						rs.getString("status") ) ); 
			}
		} catch (SQLException e )	{ e.printStackTrace();
		} catch (ParseException e)	{ e.printStackTrace();	}
		
//		if ( !found ){
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("Search Results");
//			alert.setContentText("Nothing found!");
//			alert.showAndWait();
//		}
		
		ObservableList<Book> data = FXCollections.observableList(list);

		return data;
	}

	private void deselectToggle(){
		allBookings.setSelected(false);
		completedBookings.setSelected(false);
		runningBookings.setSelected(false);
		comingSoonBookings.setSelected(false);
		
	}
}
