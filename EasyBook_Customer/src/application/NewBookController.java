package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.Conn;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

public class NewBookController implements Initializable {

	@FXML
	private Pane stepOnePane, stepTwoPane, availableRoomsPane;

	@FXML
	private DatePicker checkIn_date, checkOut_date;

	@FXML
	private TextField numOfPeople;

	@FXML
	private ToggleButton type_stand_toggle, type_comf_toggle, type_suite_toggle;

	@FXML
	private Button findRooms_btn, bookNOW_btn;

	@FXML
	private TableView<BookingChoices> availableRoomsTable;

	@FXML
	private TableColumn<BookingChoices, Integer> avRoomSingleBeds_col, avRoomDoubleBeds_col;

	@FXML
	private TableColumn<BookingChoices, String> avRoomName_col, avRoomOfferDis_col;

	@FXML
	private TableColumn<BookingChoices, Float> avRoomPrice_col, avRoomCostPerDay_col;

	@FXML
	private Label totalCost_txt;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	private String searchFrom, searchUntil, searchRoomType;
	private Integer searchNumOfPerson;
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private ObservableList<BookingChoices> bookingChoicesList;

	public void changeBooking(ActionEvent event) {
		
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Edit your booking");
		dialog.setHeaderText("Please enter below your booking's details to continue");

		ButtonType submitBtn = new ButtonType("Continue", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(submitBtn);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField bookingID = new TextField();
		bookingID.setPromptText("Booking ID");
		TextField email = new TextField();
		email.setPromptText("name@domain.com");
		RadioButton editBookingRadio = new RadioButton("Edit Booking");
		ToggleGroup pickAction = new ToggleGroup();
		editBookingRadio.setToggleGroup( pickAction );
		RadioButton cancelBookingRadio = new RadioButton("Cancel Booking");
		cancelBookingRadio.setToggleGroup( pickAction );

		grid.add(new Label("Booking ID:"), 0, 0);
		grid.add(bookingID, 1, 0);
		grid.add(new Label("Email:"), 0, 1);
		grid.add(email, 1, 1);
		grid.add(editBookingRadio, 0, 2);
		grid.add(cancelBookingRadio, 1, 2);

		Node submitButton = dialog.getDialogPane().lookupButton(submitBtn);
		submitButton.setDisable(true);

		bookingID.textProperty().addListener((observable, oldValue, newValue) -> {
			if (! email.getText().trim().isEmpty() ) {
				submitButton.setDisable( newValue.trim().isEmpty() );
			}
		});
		email.textProperty().addListener((observable, oldValue, newValue) -> {
			if (! bookingID.getText().trim().isEmpty() ) {
				submitButton.setDisable( newValue.trim().isEmpty() );
			}
		});

		dialog.getDialogPane().setContent(grid);
		
		// Request focus on the bookingID field by default.
		Platform.runLater(() -> bookingID.requestFocus());

		dialog.showAndWait();

	}
	
	public void findRooms(ActionEvent event){

		Alert alert = new Alert(AlertType.ERROR);

		LocalDate dateI = checkIn_date.getValue();
		LocalDate dateO = checkOut_date.getValue();

		if ( dateI.isBefore( dateO ) || dateI.isEqual( dateO ) ) {

			Date date;
			try {
				date = dateFormatter.parse(dateI.toString() + " 14:00:00");
				searchFrom = dateFormatter.format(date);

				date = dateFormatter.parse(dateO.toString() + " 12:00:00");
				searchUntil = dateFormatter.format(date);	
			} catch (ParseException e) {
				e.printStackTrace();
				alert.setContentText("Please use DatePicker to enter dates!");
				alert.show();
				return;
			}

		} else {
			alert.setContentText("Arrival day cannot be after Departure's day!");
			alert.show();
			return;
		}

		try {
			searchNumOfPerson = Integer.parseInt( numOfPeople.getText() );
			if (searchNumOfPerson <= 0) {
				throw new NumberFormatException();
			}
		} catch ( NumberFormatException e ){
			e.printStackTrace();
			alert.setContentText("Number of people must be a positive non-zero integer!");
			alert.show();
			return;
		}

		searchRoomType = getSelectedRoomType();
		if ( searchRoomType.equals("none") ) {
			alert.setContentText("Please select room's type!");
			alert.show();
			return;
		}

		showRoomsWithDetailsOnTable();
	}

	public String getSelectedRoomType() {
		if ( type_comf_toggle.isSelected() == true ) {
			return "Comfort";
		} else if ( type_stand_toggle.isSelected() == true ) {
			return "Standard";
		} else if ( type_suite_toggle.isSelected() == true ) {
			return "Suite";
		} else {
			return "none";
		}
	}
	public String getSQLRoomType() {
		if ( searchRoomType.equals("Comfort") ) {
			return "`type_comf`";
		} else if ( searchRoomType.equals("Standard") ) {
			return "`type_stand`";
		} else {
			return "`type_suite`";
		} 
	}
	public void showRoomsWithDetailsOnTable() {

		avRoomName_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, String>("room_name") );
		avRoomSingleBeds_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, Integer>("single_beds") );
		avRoomDoubleBeds_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, Integer>("double_beds") );
		avRoomCostPerDay_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, Float>("room_cost") );
		avRoomOfferDis_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, String>("discount_show") );
		avRoomPrice_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, Float>("booking_total") );

		bookingChoicesList = searchRoomsWithDetails();
		availableRoomsPane.setDisable(false);

		availableRoomsTable.setItems( bookingChoicesList );

	}
	public ObservableList<BookingChoices> searchRoomsWithDetails() {

		List<BookingChoices> list = new ArrayList<BookingChoices>();

		try {
			Logger logger = Logger.getLogger("application");
			logger.setLevel(Level.INFO);

			Connection conn = Conn.connect();

			String roomsQuery = "SELECT * FROM `rooms` WHERE `rooms`.`room_type`= ? "
					+ "AND `rooms`.`room_id` NOT IN ( "
					+ 		"SELECT `booked_rooms_and_dates`.`room_id` "
					+ "     FROM `booked_rooms_and_dates` "
					+ "		WHERE ( `booked_rooms_and_dates`.`check_in` BETWEEN ? AND ? ) "
					+ "		     AND (`booked_rooms_and_dates`.`check_out` BETWEEN ? AND ? )"
					+ ")";

			PreparedStatement ps = conn.prepareStatement( roomsQuery );

			logger.info("**** Searching Room with: " + searchRoomType + "," + searchFrom + "," + searchUntil);
			ps.setString(1, searchRoomType);
			ps.setString(2, searchFrom);
			ps.setString(3, searchUntil);
			ps.setString(4, searchFrom);
			ps.setString(5, searchUntil);

			//Available rooms in this period
			ResultSet rooms = ps.executeQuery();

			Date dayUntil = dateFormatter.parse( searchUntil );
			Date dayFrom = dateFormatter.parse( searchFrom );
			long diff = TimeUnit.DAYS.convert(dayUntil.getTime() - dayFrom.getTime(), TimeUnit.MILLISECONDS);
			int numDays = ((int) diff) + 1;

			logger.info("**Number of staying days: " + numDays );

			String offersQuery = "SELECT `o_id`,`name`,`one_bed`,`two_beds`,`three_beds`,`fplus_beds`,"
					+ "`discount_amount`,`discount_percentage` FROM `offers` "
					+ "WHERE `offers`.`required_days` <= ? "
					+ "AND " + getSQLRoomType() + " = 1 "
					+ "AND `offers`.`valid_from` <= ? "
					+ "AND `offers`.`valid_until`>= ? ";

			ps = conn.prepareStatement(offersQuery);

			ps.setInt(1, numDays );
			ps.setString(2, searchFrom );
			ps.setString(3, searchUntil );

			ResultSet offers = ps.executeQuery();
			int i = 0;
			while ( rooms.next() ) {
				BookingChoices bChoice = new BookingChoices(
						rooms.getInt("room_id"),
						rooms.getString("room_name"),
						rooms.getInt("single_beds"),
						rooms.getInt("double_beds"),
						rooms.getString("room_type"),
						rooms.getFloat("cost")
						);
				
				if ( offers.next() ) {
					offers.previous();
					while ( offers.next() ) {
						
						int numBeds = bChoice.getNumberOfBeds();
						boolean apply = false;

						if ( 
								( offers.getInt("one_bed") == 1 && numBeds == 1 ) || 
								( offers.getInt("two_beds") == 1 && numBeds == 2 ) || 
								( offers.getInt("three_beds") == 1 && numBeds == 3 ) ||
								( offers.getInt("fplus_beds") == 1 && numBeds >= 4 ) 
								)
						{
							apply = true;
						}

						if ( apply ) {
							
							BookingChoices bChoice2 = new BookingChoices(bChoice);
							logger.info( ++i + " "+ bChoice2.getRoom_name() + " " + offers.getString("name") );

							bChoice2.setO_id( offers.getInt("o_id") );
							bChoice2.setOffer_name( offers.getString("name") );
							bChoice2.setDiscount_amount( offers.getInt("discount_amount") );
							bChoice2.setDiscount_percentage( offers.getInt("discount_percentage") );

							if ( offers.getInt("discount_amount") != 0 ) {
								bChoice2.setDiscount_show( offers.getString("name") + "(-" + offers.getInt("discount_amount") + "€)" );
								bChoice2.setBooking_total( bChoice2.getRoom_cost() * numDays  - bChoice2.getDiscount_amount() );
							} else {
								bChoice2.setDiscount_show( offers.getString("name") + "(-" + offers.getInt("discount_percentage") + "%)" );
								bChoice2.setBooking_total( bChoice2.getRoom_cost() * numDays * (100 - bChoice2.getDiscount_percentage() )/100 );
							}

							list.add( bChoice2 );
						}
					}
				
				} else {
					bChoice.setBooking_total( bChoice.getRoom_cost() * numDays );
					list.add( bChoice );
				}
				
				offers.beforeFirst();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ObservableList<BookingChoices> data = FXCollections.observableList(list);

		return data;

	}
}
