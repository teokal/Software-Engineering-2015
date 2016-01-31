package events;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.zip.DataFormatException;

import application.Book;
import application.Main;
import application.Offer;
import application.Room;
import application.Statistics;
import database.Conn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import jdk.nashorn.internal.runtime.options.Options;

public class AdministrationPanel implements Initializable {

	public AdministrationPanel(String s) {
		this.userType = s;
	}

	@FXML
	private TableColumn<Book, String> check_in_clmn, paid_clmn, name_clmn, surname_clmn, 
	check_out_clmn,tel_clmn, email_clmn, idnum_clmn,
	persons_clmn, code_clmn, room_clmn, status_clmn;

	@FXML
	private TableColumn<Room, Integer> room_name_col, single_beds_col, double_beds_col;
	@FXML
	private TableColumn<Room, String> type_col;
	@FXML
	private TableColumn<Room, Float> cost_col;

	@FXML
	private TableColumn<Offer, String> offer_name_col, offer_req_days_col, 
	offers_type_stand_col, offers_type_comf_col, offers_type_suite_col, 
	offers_beds_one_col, offers_beds_two_col, offers_beds_three_col, offers_beds_fplus_col, 
	offers_disc_per_col, offers_disc_am_col, offers_valid_from_col, offers_valid_until_col;

	@FXML
	private ToggleGroup categoryTypesRooms, categoryOffers, categoryRadioDatesOffers, 
	categoryBookings,  categoryRadioTypeOffers,  categoryTypeOptions, 
	categoryIncomeBooksStatistics,  categoryBedRooms, newUser;

	@FXML
	private TextField searchBookText, searchRoomText, offer_name_text, 
	offer_req_days_text, offer_dis_per_text, offer_dis_am_text,
	breakfastTxt, fullDinnerTxt, poolTxt, hairSalonTxt, fitnessTxt, spaTxt, 
	faceBodyTxt,massageTxt, newUserName, newUserSurame, newUserEmail, newUsername, newUserSearch, newUserPass;


	@FXML
	private DatePicker offer_valid_from_date, offer_valid_until_date;

	@FXML
	private RadioButton offer_dis_per_radio, offer_dis_am_radio,newUserAdmin, newUserUser;

	@FXML
	private TextArea offer_desc_text;

	@FXML
	private CheckBox offer_type_stand_check, offer_type_comf_check, offer_type_suite_check, 
	offer_one_bed_check, offer_two_beds_check, offer_three_beds_check, offer_fplus_beds_check,
	kitchenCkBx, fridgeCkBx, acCkBx, wifiCkBx, radioCkBx, lcdtvCkBx, safeCkBx, fireplaceCkBx, barCkBx, jacuzziCkBx;

	@FXML
	private TableView<Book> bookingsTable;
	@FXML
	private TableView<Room> roomsTable;
	@FXML
	private TableView<Offer> offersTable;
	@FXML
	private TableView<Options> newUserTable;
	@FXML
	private TableColumn<Options, String> showUsersTable_col,showUsersType_col;

	@FXML
	private ToggleButton allBookings, completedBookings, comingSoonBookings, runningBookings,
	allRooms, standardRooms, comfortRooms, suiteRooms, 
	rooms_1bed, rooms_2beds, rooms_3beds,rooms_4plusbeds, 
	roomServicesTypeComf, roomServicesTypeStand, roomServicesTypeeSuite;

	@FXML
	private Tab offersTab, statisticsTab, optionsTab;

	private ObservableList<Book> bookingList;
	private ObservableList<Room> roomsList;
	private ObservableList<Offer> offersList;

	
	
	@FXML
	private Button offer_SAVE_btn, offer_CANCEL_btn, offer_EDIT_btn, offer_DELETE_btn,
	newUserAdd, newUserCancel, newUserEdit, newUserDelete;

	@FXML
	private AnchorPane offer_controls;
	
	@FXML
	private LineChart<String,Number>  lineChart;
	

	private Book booking_toEdit;
	private Offer offer_toEdit;
	private Room room_toEdit;
	private String userType;
	private boolean newOffer = false;
	private boolean update = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if ( userType.equals("admin") ) {
			showAllBookings(null);
			showAllRooms(null);
			showAllOffers(null);

			offers_disableAllControls();
		} else if ( userType.equals("user") ){
			showAllBookings(null);
			showAllRooms(null);

			offersTab.setDisable(true);
			statisticsTab.setDisable(true);
			optionsTab.setDisable(true);
		}

		bookingsTable.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					//editBooking(null);                   
				}
			}
		});

		roomsTable.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					editRoom(null);                   
				}
			}
		});

		offersTable.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					editOffer(null);                  
				}
			}
		});
		
		showStatistics();
		
		roomServicesTypeStand.setSelected(true);
		selectStandardRoomServices(null);

		fillExtrasCosts();
		
	}

	/* Bookings TAB */
	public void newBook(ActionEvent event) {
		String newBookPath = "/application/addBookGui.fxml";
		Main main = new Main();
		main.openNewPanel(newBookPath, "New Book");
	}
	public void cancelBook(ActionEvent event) {
		booking_toEdit = bookingsTable.getSelectionModel().getSelectedItem();

		if ( booking_toEdit != null ) {
			Alert alert = new Alert(AlertType.CONFIRMATION);

			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to cancel the selected booking?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				if ( booking_toEdit.changeStatus("cancelled") ) {
					showSuccess("Booking has been cancelled!");
				} else {
					showError("Booking could not be cancelled!");
				}
				showAllBookings(null);
			} else {
				return;
			}
		}
	}
	public void showAllBookings(ActionEvent event) {
		showBookingsOnTable("SELECT * FROM `bookings`");
	}
	public void showRunning(ActionEvent event) {
		showBookingsOnTable("SELECT * FROM `bookings` "
				+ "WHERE ( ( `check_in` >= concat(curdate(), ' ', curtime()) ) "
				+ "AND (`check_out` < concat(curdate(), ' ', curtime()) ) ) "
				+ "OR `status` <> 'completed'");
	}
	public void showComingSoon(ActionEvent event) {
		showBookingsOnTable("SELECT * FROM `bookings` WHERE `check_in` > concat( curdate(), ' ', curtime() )");
	}
	public void showCompleted(ActionEvent event) {
		showBookingsOnTable("SELECT * FROM `bookings` WHERE `status` = 'completed'");
	}

	public void searchBookFromText(ActionEvent event) {

		String textSearch = searchBookText.getText();

		if (! textSearch.isEmpty() ) {
			textSearch = "%" + textSearch + "%";
			deselectBookingsToggle();

			String query = "SELECT * FROM bookings "
					+ "WHERE `b_id` LIKE ?	"
					+ "OR	`code`  LIKE ?	"
					+ "OR	`name` LIKE ?	"
					+ "OR	`sname` LIKE ?	"
					+ "OR 	`tel` LIKE ?	"
					+ "OR	`email` LIKE ?";

			try {
				Connection conn = Conn.connect();

				PreparedStatement preparedStatement = conn.prepareStatement( query );

				preparedStatement.setString(1, textSearch );
				preparedStatement.setString(2, textSearch );
				preparedStatement.setString(3, textSearch );
				preparedStatement.setString(4, textSearch );
				preparedStatement.setString(5, textSearch );
				preparedStatement.setString(6, textSearch );

				showBookingsOnTable(preparedStatement);

				conn.close();
			} catch (SQLException e) { e.printStackTrace(); }
		}
	}
	public void showBookingsOnTable(Object query) {

		code_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("code") );
		name_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("name") );
		surname_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("sname") );
		check_in_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("check_in") );
		check_out_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("check_out") );
		tel_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("tel") );
		email_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("email") );
		idnum_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("idnum") );
		room_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("room_name") );
		persons_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("numOfPerson") );
		paid_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("paid") );
		status_clmn.setCellValueFactory(new PropertyValueFactory<Book, String>("status") );

		bookingList = getBookingsTableData(query);
		bookingsTable.setItems( bookingList );

	}
	private ObservableList<Book> getBookingsTableData(Object query) {

		List<Book> list = new ArrayList<Book>();
		ResultSet rs;

		try {
			Connection conn = Conn.connect();

			if ( query.getClass().equals(String.class) ) {
				PreparedStatement ps = conn.prepareStatement( (String) query );
				rs = ps.executeQuery();
			} else {
				rs = ((PreparedStatement) query).executeQuery();
			}

			while (rs.next()) {
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
						rs.getString("status"),
						rs.getInt("room_id"),
						rs.getInt("numOfPerson"),
						rs.getString("comments") ) ); 
			}
			conn.close();
		} catch (SQLException|ParseException e )	{ e.printStackTrace(); }

		ObservableList<Book> data = FXCollections.observableList(list);

		return data;
	}
	private void deselectBookingsToggle(){
		allBookings.setSelected(false);
		completedBookings.setSelected(false);
		runningBookings.setSelected(false);
		comingSoonBookings.setSelected(false);
	}

	/* Rooms TAB */
	public void newRoom(ActionEvent event) {
		String newRoomPath = "/application/addRoomGui.fxml";
		Main main = new Main();
		main.openNewPanel(newRoomPath, "New Room");
	}
	public void editRoom(ActionEvent event) {
		room_toEdit = roomsTable.getSelectionModel().getSelectedItem();

		if ( room_toEdit != null ) {
			String newRoomPath = "/application/addRoomGui.fxml";
			Main main = new Main();
			main.openEditRoomPanel(newRoomPath, "Edit Room" , room_toEdit, this, false);
		}
	}
	public void deleteRoom(ActionEvent event){
		room_toEdit = roomsTable.getSelectionModel().getSelectedItem();

		if ( room_toEdit != null ) {
			Alert alert = new Alert(AlertType.CONFIRMATION);

			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Delete " + room_toEdit.getRoom_name() + "?");
			alert.setContentText("You can cannot revert this action!");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				room_toEdit.deleteThisRoom();

				showAllRooms(null);
			} else {
				return;
			}
		}
	}

	public void showAllRooms(ActionEvent event) {
		deselectRoomsBedsToggle();
		showRoomsOnTable("SELECT * FROM `rooms`");
	}
	public void showStandardRooms(ActionEvent event) {
		deselectRoomsBedsToggle();
		showRoomsOnTable("SELECT * FROM `rooms` WHERE `room_type`='Standard'");
	}
	public void showComfortRooms(ActionEvent event) {
		deselectRoomsBedsToggle();
		showRoomsOnTable("SELECT * FROM `rooms` WHERE `room_type`='Comfort'");
	}
	public void showSuiteRooms(ActionEvent event) {
		deselectRoomsBedsToggle();
		showRoomsOnTable("SELECT * FROM `rooms` WHERE `room_type`='Suite'");
	}

	public void showRooms1Bed(ActionEvent event) {
		deselectRoomsTypeToggle();
		showRoomsOnTable("SELECT * FROM `rooms` WHERE `single_beds` + `double_beds` = 1");
	}
	public void showRooms2Beds(ActionEvent event) {
		deselectRoomsTypeToggle();
		showRoomsOnTable("SELECT * FROM `rooms` WHERE `single_beds` + `double_beds` = 2");
	}
	public void showRooms3Beds(ActionEvent event) {
		deselectRoomsTypeToggle();
		showRoomsOnTable("SELECT * FROM `rooms` WHERE `single_beds` + `double_beds` = 3");
	}
	public void showRooms4plusBeds(ActionEvent event) {
		deselectRoomsTypeToggle();
		showRoomsOnTable("SELECT * FROM `rooms` WHERE `single_beds` + `double_beds` >= 4");
	}

	public void searchRoomFromText(ActionEvent event) {

		deselectRoomsTypeToggle();
		deselectRoomsBedsToggle();

		String textSearch = searchRoomText.getText();

		if (! textSearch.isEmpty() ) {

			String query = "SELECT * FROM `rooms` WHERE "
					+ "`room_name` LIKE '%" + textSearch + "%'";

			showRoomsOnTable(query);
		}
	}
	public void showRoomsOnTable(String query) {

		room_name_col.setCellValueFactory(new PropertyValueFactory<Room, Integer>("room_name") );
		type_col.setCellValueFactory(new PropertyValueFactory<Room, String>("room_type") );
		single_beds_col.setCellValueFactory(new PropertyValueFactory<Room, Integer>("single_beds") );
		double_beds_col.setCellValueFactory(new PropertyValueFactory<Room, Integer>("double_beds") );
		cost_col.setCellValueFactory(new PropertyValueFactory<Room, Float>("cost") );

		roomsList = getRoomsTableData(query);
		roomsTable.setItems( roomsList );

	}
	private ObservableList<Room> getRoomsTableData(Object query) {

		List<Room> list = new ArrayList<Room>();

		try {
			ResultSet rs;
			Connection conn = Conn.connect();

			if ( query.getClass().equals(String.class) ) {
				PreparedStatement ps = conn.prepareStatement( (String) query );
				rs = ps.executeQuery();
			} else {
				rs = ((PreparedStatement) query).executeQuery();
			}

			while ( rs.next()) {

				list.add( new Room(
						rs.getInt("room_id"), 
						rs.getString("room_name"), 
						rs.getString("room_type"),
						rs.getInt("single_beds"),
						rs.getInt("double_beds"),
						rs.getFloat("cost") ) ); 
			}
		} catch (SQLException e) { e.printStackTrace();
		}

		ObservableList<Room> data = FXCollections.observableList(list);

		return data;
	}
	private void deselectRoomsTypeToggle(){
		allRooms.setSelected(false);
		standardRooms.setSelected(false);
		comfortRooms.setSelected(false);
		suiteRooms.setSelected(false);
	}
	private void deselectRoomsBedsToggle(){
		rooms_1bed.setSelected(false);
		rooms_2beds.setSelected(false);
		rooms_3beds.setSelected(false);
		rooms_4plusbeds.setSelected(false);
	}


	/* Offers TAB */
	public void showAllOffers(ActionEvent event) {
		showOffersOnTable("SELECT * FROM `offers`");
	}	
	public void showOffersOnTable(String query) {

		offer_name_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("name") );
		offer_req_days_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("req_days") );
		offers_type_stand_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("type_stand") );
		offers_type_comf_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("type_comf") );
		offers_type_suite_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("type_suite") );
		offers_beds_one_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("one_bed") );
		offers_beds_two_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("two_beds") );
		offers_beds_three_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("three_beds") );
		offers_beds_fplus_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("fplus_beds") );
		offers_disc_per_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("discount_percentage") );
		offers_disc_am_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("discount_amount") );
		offers_valid_from_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("valid_from") );
		offers_valid_until_col.setCellValueFactory(new PropertyValueFactory<Offer, String>("valid_until") );

		offersList = getOffersTableData(query);
		offersTable.setItems( offersList );

	}
	private ObservableList<Offer> getOffersTableData(Object query) {

		List<Offer> list = new ArrayList<Offer>();

		try {
			Connection conn = Conn.connect();
			ResultSet rs;

			if ( query.getClass().equals(String.class) ) {
				PreparedStatement ps = conn.prepareStatement( (String) query );
				rs = ps.executeQuery();
			} else {
				rs = ((PreparedStatement) query).executeQuery();
			}

			while ( rs.next()) {

				list.add( new Offer(
						rs.getInt("o_id"),
						rs.getString("name"),
						rs.getString("valid_from"),
						rs.getString("valid_until"),
						rs.getInt("required_days"),
						rs.getInt("one_bed"),
						rs.getInt("two_beds"),
						rs.getInt("three_beds"),
						rs.getInt("fplus_beds"),
						rs.getInt("type_stand"),
						rs.getInt("type_comf"),
						rs.getInt("type_suite"),
						rs.getInt("discount_amount"),
						rs.getInt("discount_percentage"),
						rs.getString("lang_en") ) ); 
			}
		} catch (SQLException e) { e.printStackTrace();
		}

		ObservableList<Offer> data = FXCollections.observableList(list);

		return data;
	}
	public void newOffer (ActionEvent event) {
		newOffer = true;
		offers_default_data();
		offers_enableAllControls();
	}
	public void editOffer (ActionEvent event) {

		offer_toEdit = offersTable.getSelectionModel().getSelectedItem();

		if ( offer_toEdit != null && !newOffer) {

			offer_name_text.setText(String.valueOf( offer_toEdit.getName() ));
			offer_req_days_text.setText(String.valueOf(offer_toEdit.getReq_days() ) );

			try {
				offer_valid_from_date.setValue( (LocalDate) offer_toEdit.getValid_from_edit() );
				offer_valid_until_date.setValue( (LocalDate) offer_toEdit.getValid_until_edit() );
			} catch (ParseException e) {
				e.printStackTrace();
			}

			offer_desc_text.setText( offer_toEdit.getDesc_en() );

			if ( offer_toEdit.getDiscount_amount() == 0 ) {
				offer_dis_am_text.setText( String.valueOf(0) );	
				offer_dis_per_radio.setSelected(true);
				offer_dis_per_text.setText( String.valueOf( offer_toEdit.getDiscount_percentage() ) );
				disableRadioDisAmount(null);
			} else {
				offer_dis_per_text.setText(String.valueOf(0));
				offer_dis_am_radio.setSelected(true);
				offer_dis_am_text.setText( String.valueOf( offer_toEdit.getDiscount_amount() ) );
				disableRadioDisPer(null);
			}

			if ( offer_toEdit.getType_stand_edit() == 1 ) {
				offer_type_stand_check.setSelected(true);
			} else {
				offer_type_stand_check.setSelected(false);
			}

			if ( offer_toEdit.getType_comf_edit() == 1 ) {
				offer_type_comf_check.setSelected(true);
			} else {
				offer_type_comf_check.setSelected(false);
			}

			if ( offer_toEdit.getType_suite_edit() == 1 ) {
				offer_type_suite_check.setSelected(true);
			} else {
				offer_type_suite_check.setSelected(false);
			}

			if ( offer_toEdit.getOne_bed_edit() == 1 ) {
				offer_one_bed_check.setSelected(true);
			} else {
				offer_one_bed_check.setSelected(false);
			}

			if ( offer_toEdit.getTwo_beds_edit() == 1 ) {
				offer_two_beds_check.setSelected(true);
			} else {
				offer_two_beds_check.setSelected(false);
			}

			if ( offer_toEdit.getThree_beds_edit() == 1 ) {
				offer_three_beds_check.setSelected(true);
			} else {
				offer_three_beds_check.setSelected(false);
			}

			if ( offer_toEdit.getFplus_beds_edit() == 1 ) {
				offer_fplus_beds_check.setSelected(true);
			} else {
				offer_fplus_beds_check.setSelected(false);
			}

			offers_enableAllControls();
		}

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
	
	/* Users Section */
	/* New User Details */
	public void newUserAdd (ActionEvent event) throws ParseException{
		
		if ( newUserName.getText()== null ||  newUserName.getText().trim().isEmpty() ) {
			showError("The name of new user cannot be empty!");
			return;
		}if ( newUserSurame.getText()== null ||  newUserSurame.getText().trim().isEmpty() ) {
			showError("Surname of new user cannot be empty!");
			return;
		}if ( newUserEmail.getText()== null ||  newUserEmail.getText().trim().isEmpty() ) {
			showError("Email of new user cannot be empty!");
			return;
		}if ( newUsername.getText()== null ||  newUsername.getText().trim().isEmpty() ) {
			showError("Username of new user cannot be empty!");
			return;
		}if ( newUserPass.getText()== null ||  newUserPass.getText().trim().isEmpty() ) {
			showError("Password of new user cannot be empty!");
			return;
		}if (getSelectedType()== "none"){
			showError("Please choose user`s mode: Admin or User.");
		}
		
		Connection conn = Conn.connect();
		

		String query = "";
		
		if(update){
			query = "UPDATE `users` SET "
					+ "`username` = ?,"
					+ "`password` = ?,"
					+ "`name` = ?,"
					+ "`sname` = ?,"
					+ "`email` = ? "
					+ "`user_type` = ? "
					+ "WHERE `user_id` = ?";
		} else {
			query = "INSERT INTO `users`(`username`,`password`,"
					+ "`name`,`sname`,`email`,`user_type`) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
		}
		
		try {   
				PreparedStatement ps = conn.prepareStatement( query );
			
				ps.setString(1, newUsername.getText() );
				ps.setString(2, hash(newUserPass.getText() ) );
				ps.setString(3, newUserName.getText() );
				ps.setString(4, newUserSurame.getText() );
				ps.setString(5, newUserEmail.getText() );
				String s=getSelectedType();
				ps.setString(6, s );
			
				int rs = ps.executeUpdate();
				if (rs != 0 ){
					showSuccess("New user created!");
				} else {
					showError("Could not create new user!");
				}
				conn.close();				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
	}
	
		public String getSelectedType() {
			if ( newUserAdmin.isSelected() == true ) {
				return "admin";
			} else if ( newUserUser.isSelected() == true ) {
				return "user";
			}  else {
				return "none";
			}
		}
	
		public void newUserCancel (ActionEvent event) throws ParseException{
			newUserName.setText("");
			newUserPass.setText("");
			newUserName.setText("");
			newUserSurame.setText("");
			newUserEmail.setText("");
		}
		
		public void SearchNewUser (ActionEvent event){
			
			String s = searchRoomText.getText();
	
			if (! s.isEmpty() ) {
	
				String query = "SELECT * FROM `users` WHERE "
						+ "`username` LIKE '%" + s + "%'";
	
				showUsersTable(query);
			}
		}
		
		public void showUsersTable (String query){
		
			showUsersTable_col.setCellValueFactory(new PropertyValueFactory<Options, String>("username") );
			showUsersType_col.setCellValueFactory(new PropertyValueFactory<Options, String>("type") );
			
		//	List<Users> listUsers = new ArrayList<Users>();
			
			Connection conn = Conn.connect();
			try {
				PreparedStatement ps = conn.prepareStatement( query );
				ResultSet rs;
				
				rs = ps.executeQuery(query);
				
				
				
				while ( rs.next() ) {
				//	listUsers.add( new user(rs.getString("usernmame"),rs.getString("type"));
	                
	            }
	            conn.close();
				
			} catch (SQLException e) {
			
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
			//usersList = getOffersTableData(query);
		   //newUserTable.setItems(listUsers,listPass);
			
			

			}
			
		
		
		
			
			
			
		
	
		
		
	public void saveOffer (ActionEvent event) throws ParseException {

		if (newOffer) {
			offer_toEdit = new Offer();
		}

		int temp;

		if ( offer_name_text.getText()== null ||  offer_name_text.getText().trim().isEmpty() ) {
			showError("Offer's title cannot be empty!");
			return;
		}

		if ( offer_dis_per_radio.isSelected() ) {

			try {
				temp = Integer.parseInt( offer_dis_per_text.getText() );

				if (  temp <= 0 || temp > 100 ) {
					throw new DataFormatException();
				}

			} catch ( NumberFormatException e ) {
				showError("Discount percentage cannot contain letters or symbols!");
				return;
			} catch ( DataFormatException e ) {
				showError("Discount percentage must be an integer from 1 to 100!");
				return;
			}
		} else if ( offer_dis_am_radio.isSelected() ) {

			try {
				temp = Integer.parseInt( offer_dis_am_text.getText() );

				if (  temp <= 0 ) {
					throw new DataFormatException();
				}

			} catch ( NumberFormatException e ) {
				showError("Discount amount cannot contain letters or symbols!");
				return;
			} catch ( DataFormatException e ) {
				showError("Discount amount must be a positive integer!");
				return;
			}
		}

		if ( offer_req_days_text.getText()!= null &&  !offer_req_days_text.getText().trim().isEmpty() ) {

			try {
				temp = Integer.parseInt( offer_req_days_text.getText() );

				if (  temp < 0 ) {
					throw new DataFormatException();
				}

			} catch ( NumberFormatException e ) {
				showError("Required days cannot contain letters or symbols!");
				return;
			} catch ( DataFormatException e ) {
				showError("Required days must be 0 or a positive integer!");
				return;
			}
		} else {
			showError("Required days field cannot be empty!\nFor 0 days required, fill 0");
			return;
		}

		LocalDate dateF = offer_valid_from_date.getValue();
		LocalDate dateU = offer_valid_until_date.getValue();

		if ( dateF.isBefore( dateU ) || dateF.isEqual( dateU ) ) {

			offer_toEdit.setValid_from( dateF.toString() );
			offer_toEdit.setValid_until( dateU.toString() );	

		} else {
			showError("Starting day of the offer cannot be after the Ending day");
			return;
		}

		offer_toEdit.setName( offer_name_text.getText() );

		if ( offer_type_stand_check.isSelected() ) {
			offer_toEdit.setType_stand(1);
		} else { offer_toEdit.setType_stand(0); }

		if ( offer_type_comf_check.isSelected() ) {
			offer_toEdit.setType_comf(1);
		} else { offer_toEdit.setType_comf(0); }

		if ( offer_type_suite_check.isSelected() ) {
			offer_toEdit.setType_suite(1);
		} else { offer_toEdit.setType_suite(0); }

		if ( offer_one_bed_check.isSelected() ) {
			offer_toEdit.setOne_bed(1);
		} else { offer_toEdit.setOne_bed(0); }

		if ( offer_two_beds_check.isSelected() ) {
			offer_toEdit.setTwo_beds(1);
		} else { offer_toEdit.setTwo_beds(0); }

		if ( offer_three_beds_check.isSelected() ) {
			offer_toEdit.setThree_beds(1);
		} else { offer_toEdit.setThree_beds(0); }

		if ( offer_fplus_beds_check.isSelected() ) {
			offer_toEdit.setFplus_beds(1);
		} else { offer_toEdit.setFplus_beds(0); }

		if ( offer_dis_per_radio.isSelected() ) {
			offer_toEdit.setDiscount_amount(0);
			offer_toEdit.setDiscount_percentage( Integer.parseInt( offer_dis_per_text.getText() ) );
		} else if ( offer_dis_am_radio.isSelected() ){
			offer_toEdit.setDiscount_amount( Integer.parseInt( offer_dis_am_text.getText() ) );
			offer_toEdit.setDiscount_percentage(0);
		}

		offer_toEdit.setReq_days( Integer.parseInt( offer_req_days_text.getText() ) );

		offer_toEdit.setDesc_en( offer_desc_text.getText() );

		Boolean okay = true;

		if ( newOffer ) {
			okay = offer_toEdit.updateOffer(false);
		} else {
			okay = offer_toEdit.updateOffer(true);
		}

		if ( okay ) {
			offers_disableAllControls();
			newOffer = false;
			showAllOffers(null);

			showSuccess("Offer has been successfully saved!");
			offersTable.getSelectionModel().clearSelection();
		} else {
			showError("Offer could not be saved!");
		}
	} 		
	public void cancelOffer (ActionEvent event) {
		offers_default_data();
		offers_disableAllControls();
		newOffer = false;
	}
	public void deleteOffer(ActionEvent event){
		offer_toEdit = offersTable.getSelectionModel().getSelectedItem();

		if ( offer_toEdit != null ) {
			Alert alert = new Alert(AlertType.CONFIRMATION);

			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Delete " + offer_toEdit.getName() + "?");
			alert.setContentText("You can cannot revert this action!");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				offer_toEdit.deleteThisOffer();
				showAllOffers(null);
			} else {
				return;
			}
		}
	}

	public void disableRadioDisAmount (ActionEvent event) {
		offer_dis_am_text.setDisable(true);
		offer_dis_am_text.setEditable(false);
		offer_dis_per_text.setDisable(false);
		offer_dis_per_text.setEditable(true);
	}
	public void disableRadioDisPer (ActionEvent event) {
		offer_dis_am_text.setDisable(false);
		offer_dis_am_text.setEditable(true);
		offer_dis_per_text.setDisable(true);
		offer_dis_per_text.setEditable(false);
	}
	private void offers_default_data() {

		offer_name_text.setText("");
		offer_valid_from_date.setValue( LocalDate.now() );
		offer_valid_until_date.setValue( LocalDate.now() );
		offer_desc_text.setText("");
		offer_dis_am_text.setText("");
		offer_dis_am_radio.setSelected(false);
		offer_dis_per_text.setText("");
		offer_dis_per_radio.setSelected(false);
		offer_req_days_text.setText("");

		offer_one_bed_check.setSelected(false);
		offer_two_beds_check.setSelected(false);
		offer_three_beds_check.setSelected(false);
		offer_fplus_beds_check.setSelected(false);

		offer_type_stand_check.setSelected(false);
		offer_type_comf_check.setSelected(false);
		offer_type_suite_check.setSelected(false);

	}
	private void offers_enableAllControls(){
		offer_controls.setDisable(false);

		offer_EDIT_btn.setDisable(true);
		offer_DELETE_btn.setDisable(true);

		offersTable.setDisable(true);
	}
	private void offers_disableAllControls(){
		offer_controls.setDisable(true);

		offer_EDIT_btn.setDisable(false);
		offer_DELETE_btn.setDisable(false);

		offersTable.setDisable(false);
	}

	/* Statistics TAB */
	private void showStatistics() {
		Statistics stat = new Statistics();
		lineChart = stat.show(lineChart);
	}
	
	/* Options TAB */
	/* Room Services Section */
	public void selectStandardRoomServices(ActionEvent event){
		selectCheckboxes(0);
	}
	public void selectComfortRoomServices(ActionEvent event){
		selectCheckboxes(1);
	}
	public void selectSuiteRoomServices(ActionEvent event){
		selectCheckboxes(2);
	}

	private byte[][] getRoomServicesFromDB() {
		byte[][] data = new byte[0][0];

		try{
			Connection conn = Conn.connect();
			String query = "SELECT * FROM `room_services`";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			int rowCount = 0;
			int i = 0;

			if ( rs.last() ) {
				rowCount = rs.getRow();
				rs.beforeFirst();
			}

			data = new byte[rowCount][3];

			while (rs.next()) {
				data[ i ][0] = rs.getByte("t_standard");
				data[ i ][1] = rs.getByte("t_comfort");
				data[i][2] = rs.getByte("t_suite");

				i++;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}
	private void selectCheckboxes(int i) {
		byte[][] data = getRoomServicesFromDB();

		if ( data[0][i] == 1) {
			kitchenCkBx.setSelected(true);
		} else {
			kitchenCkBx.setSelected(false);
		}

		if ( data[1][i] == 1) {
			fridgeCkBx.setSelected(true);
		} else {
			fridgeCkBx.setSelected(false);
		}

		if ( data[2][i] == 1) {
			acCkBx.setSelected(true);
		} else {
			acCkBx.setSelected(false);
		}

		if ( data[3][i] == 1) {
			wifiCkBx.setSelected(true);
		} else {
			wifiCkBx.setSelected(false);
		}

		if ( data[4][i] == 1) {
			radioCkBx.setSelected(true);
		} else {
			radioCkBx.setSelected(false);
		}

		if ( data[5][i] == 1) {
			lcdtvCkBx.setSelected(true);
		} else {
			lcdtvCkBx.setSelected(false);
		}

		if ( data[6][i] == 1) {
			safeCkBx.setSelected(true);
		} else {
			safeCkBx.setSelected(false);
		}

		if ( data[7][i] == 1) {
			fireplaceCkBx.setSelected(true);
		} else {
			fireplaceCkBx.setSelected(false);
		}

		if ( data[8][i] == 1) {
			barCkBx.setSelected(true);
		} else {
			barCkBx.setSelected(false);
		}

		if ( data[9][i] == 1) {
			jacuzziCkBx.setSelected(true);
		} else {
			jacuzziCkBx.setSelected(false);
		}

	}
	public void saveCheckboxes (ActionEvent event) {
		String cat = "";
		if (roomServicesTypeStand.isSelected() ) {
			cat = "t_standard";
		} else if (roomServicesTypeComf.isSelected() ) {
			cat = "t_comfort";
		} else {
			cat = "t_suite";
		}

		try {
			Connection conn = Conn.connect();


			String query = "UPDATE `room_services` SET `" + cat + "` = ? WHERE `s_id` = ?";
			PreparedStatement ps = conn.prepareStatement(query);

			if ( kitchenCkBx.isSelected() ) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, 6);
			ps.addBatch();

			if ( fridgeCkBx.isSelected() ) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, 7);
			ps.addBatch();

			if ( acCkBx.isSelected() ) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, 8);
			ps.addBatch();

			if ( wifiCkBx.isSelected() ) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, 9);
			ps.addBatch();

			if ( radioCkBx.isSelected() ) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, 10);
			ps.addBatch();

			if ( lcdtvCkBx.isSelected() ) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, 11);
			ps.addBatch();

			if ( safeCkBx.isSelected() ) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, 12);
			ps.addBatch();

			if ( fireplaceCkBx.isSelected() ) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, 13);
			ps.addBatch();

			if ( barCkBx.isSelected() ) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, 14);
			ps.addBatch();

			if ( jacuzziCkBx.isSelected() ) {
				ps.setInt(1, 1);
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, 15);
			ps.addBatch();

			ps.executeBatch();
			showSuccess("Your options have been saved");
		} catch (SQLException e) {
			showError("Your options could not be saved!\nTry again later");
			e.printStackTrace();
		}
	}

	/* Extras Section */
	private Double[] getExtrasFromDB() {
		Double[] data = new Double[8];

		try{
			Connection conn = Conn.connect();
			String query = "SELECT * FROM `extras`";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			int i = 0;

			while (rs.next()) {
				data[i] = rs.getDouble("cost");
				i++;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}
	private void fillExtrasCosts() {
		Double[] data = getExtrasFromDB();

		breakfastTxt.setText( String.valueOf(data[0] ) );
		fullDinnerTxt.setText( String.valueOf(data[1] ) );
		poolTxt.setText( String.valueOf(data[2] ) );
		hairSalonTxt.setText( String.valueOf(data[3] ) );
		fitnessTxt.setText( String.valueOf(data[4] ) );
		spaTxt.setText( String.valueOf(data[5] ) );
		faceBodyTxt.setText( String.valueOf(data[6] ) );
		massageTxt.setText( String.valueOf(data[7] ) );
	}
	public void saveExtrasCost(ActionEvent event) {
		
		boolean notValidFormat = false;
		boolean problem = false;
		try{
			Connection conn = Conn.connect();
			String query = "UPDATE `extras` SET `cost` = ? WHERE `e_id` = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setDouble(1, Double.parseDouble(breakfastTxt.getText() ) );
			ps.setInt(2, 1);
			ps.addBatch();
			ps.setDouble(1, Double.parseDouble(fullDinnerTxt.getText() ) );
			ps.setInt(2, 2);
			ps.addBatch();
			ps.setDouble(1, Double.parseDouble(poolTxt.getText() ) );
			ps.setInt(2, 3);
			ps.addBatch();
			ps.setDouble(1, Double.parseDouble(hairSalonTxt.getText() ) );
			ps.setInt(2, 4);
			ps.addBatch();
			ps.setDouble(1, Double.parseDouble(fitnessTxt.getText() ) );
			ps.setInt(2, 5);
			ps.addBatch();
			ps.setDouble(1, Double.parseDouble(spaTxt.getText() ) );
			ps.setInt(2, 6);
			ps.addBatch();
			ps.setDouble(1, Double.parseDouble(faceBodyTxt.getText() ) );
			ps.setInt(2, 7);
			ps.addBatch();
			ps.setDouble(1, Double.parseDouble(massageTxt.getText() ) );
			ps.setInt(2, 8);
			ps.addBatch();
			
			ps.executeBatch();
			
			conn.close();
		} catch (SQLException e) { problem = true; e.printStackTrace();
		} catch (NumberFormatException e) { notValidFormat = true;}
		
		if (problem) {
			showError("Extras' costs could not be updated");
		} else if (notValidFormat) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Please check that values are in correct format");
			alert.setContentText("Use only numbers and use dot (.) as decimal separator! (e.g. instead of 3,14 use 3.14)");
			alert.showAndWait();
			return;
		} else {
			showSuccess("Changes were saved!");
			fillExtrasCosts();
		}
	}

	

	/* Alert functions for whole class*/
	private void showSuccess(String s) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(s);
		alert.showAndWait();
		return;
	}
	private void showError(String s) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(s);
		alert.showAndWait();
		return;
	}

}
