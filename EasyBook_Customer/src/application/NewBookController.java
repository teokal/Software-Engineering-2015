package application;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import database.Conn;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

public class NewBookController implements Initializable {

	@FXML
	private Pane stepOnePane, stepTwoPane, stepThreePane, availableRoomsPane;
	@FXML
	private DatePicker checkIn_date, checkOut_date;
	@FXML
	private TextField numOfPeople, nameTxt,sNameTxt, idTxt, telTxt, emailTxt;
	@FXML
	private TextArea commentsBox;
	@FXML
	private ToggleButton type_stand_toggle, type_comf_toggle, type_suite_toggle;
	@FXML
	private ToggleGroup roomTypeToggleCat, paymentRadio;
	@FXML
	private Button findRooms_btn, bookNOW_btn, confirm_btn, backToStepTwo_Btn;
	@FXML
	private TableView<BookingChoices> availableRoomsTable;
	@FXML
	private TableColumn<BookingChoices, Integer> avRoomSingleBeds_col, avRoomDoubleBeds_col;
	@FXML
	private TableColumn<BookingChoices, String> avRoomName_col, avRoomOfferDis_col;
	@FXML
	private TableColumn<BookingChoices, Double> avRoomPrice_col, avRoomCostPerDay_col;
	@FXML
	private Label totalCost_txt, totalCostLbl, breakfastLbl, fullDinnerLbl, poolLbl, hairSalonLbl, 
			fitnessCenterLbl, spaTreatmentsLbl, faceBodyCareLbl, massageTherapiesLbl,
			checkInLbl, checkOutLbl, bookerNameLbl, bookerSurnameLbl,
			bookerIDLbl, bookerTelLbl, bookerEmailLbl,
			numPersonsLbl, numSingleBedsLbl, numDoubleBedsLbl, roomNameLbl, roomTypeLbl,
			previewBreakfastLbl, previewFullDinnerLbl, previewHairSalonLbl,
			previewPoolLbl, previewFitnessLbl, previewSpaLbl, previewFaceBodyLbl,previewMassageLbl,
			paymentLbl, bookingCodeLbl, bookingSuccess, bookingFailed;
	@FXML
	private CheckBox breakfastCkBx, fullDinnerCkBx, poolCkBx, hairSalonCkBx, 
			fitnessCenterCkBx, spaTreatmentsCkBx, faceBodyCareCkBx, massageTherapiesCkBx;
	@FXML
	private SplitMenuButton titleTxt;
	@FXML
	private HBox totalCostHBox, bookingCodeHBox;
	
	private String searchFrom, searchUntil, searchRoomType;
	private int searchNumOfPerson, searchNumOfDays;
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private ObservableList<BookingChoices> bookingChoicesList;
	private double extrasCost;
	private BookingChoices choosed;
	private Stage pStage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		checkIn_date.setValue( LocalDate.now() );
		
		final Callback<DatePicker, DateCell> disableDaysBeforeNow = 
				new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						if (item.isBefore(
								LocalDate.now() )
								) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		
		final Callback<DatePicker, DateCell> disableDaysBeforeCheckIn = 
				new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						if (item.isBefore(
								checkIn_date.getValue() )
								) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		
		checkIn_date.setDayCellFactory(disableDaysBeforeNow);
		checkOut_date.setDayCellFactory(disableDaysBeforeCheckIn);
		
		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        if (event.getSource() instanceof CheckBox) {
		            updateExtras();
		            totalCost_txt.setText( String.valueOf( choosed.getBooking_total() ) );
		        }
		    }
		};
		
		breakfastCkBx.setOnAction(eh);
		fullDinnerCkBx.setOnAction(eh);
		poolCkBx.setOnAction(eh);
		hairSalonCkBx.setOnAction(eh);
		fitnessCenterCkBx.setOnAction(eh);
		spaTreatmentsCkBx.setOnAction(eh);
		faceBodyCareCkBx.setOnAction(eh);
		massageTherapiesCkBx.setOnAction(eh);
		
		setCostsToExtras();
		
		stepThreePane.setVisible(false);
		stepTwoPane.setVisible(false);
		stepOnePane.setVisible(true);
	}
	public NewBookController (Stage pStage) {
		this.pStage = pStage;
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

				if ( dateI.isEqual( dateO ) ) {
					date = dateFormatter.parse(dateO.toString() + " 23:59:59");
				} else {
					date = dateFormatter.parse(dateO.toString() + " 12:00:00");
				}
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
	public void showRoomsWithDetailsOnTable() {

		avRoomName_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, String>("room_name") );
		avRoomSingleBeds_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, Integer>("single_beds") );
		avRoomDoubleBeds_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, Integer>("double_beds") );
		avRoomCostPerDay_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, Double>("room_cost") );
		avRoomOfferDis_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, String>("discount_show") );
		avRoomPrice_col.setCellValueFactory(new PropertyValueFactory<BookingChoices, Double>("booking_cost") );

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
					+ 		"SELECT `bookings`.`room_id` "
					+ "     FROM `bookings` "
					+ "		WHERE ( `bookings`.`check_in` BETWEEN ? AND ? ) "
					+ "		    AND (`bookings`.`check_out` BETWEEN ? AND ? )"
					+ "			AND `bookings`.`status` <> 'cancelled')";

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
			searchNumOfDays = ((int) diff) + 1;

			logger.info("**Number of staying days: " + searchNumOfDays );

			String offersQuery = "SELECT `o_id`,`name`,`one_bed`,`two_beds`,`three_beds`,`fplus_beds`,"
					+ "`discount_amount`,`discount_percentage` FROM `offers` "
					+ "WHERE `offers`.`required_days` <= ? "
					+ "AND " + getSQLRoomType() + " = 1 "
					+ "AND `offers`.`valid_from` <= ? "
					+ "AND `offers`.`valid_until`>= ? ";

			ps = conn.prepareStatement(offersQuery);

			ps.setInt(1, searchNumOfDays );
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
						rooms.getDouble("cost")
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
								bChoice2.setBooking_cost( bChoice2.getRoom_cost() * searchNumOfDays  - bChoice2.getDiscount_amount() );
							} else {
								bChoice2.setDiscount_show( offers.getString("name") + "(-" + offers.getInt("discount_percentage") + "%)" );
								bChoice2.setBooking_cost( bChoice2.getRoom_cost() * searchNumOfDays * (100 - bChoice2.getDiscount_percentage() )/100 );
							}

							list.add( bChoice2 );
						}
					}
				
				} else {
					logger.info( ++i + " "+ bChoice.getRoom_name() );
					bChoice.setBooking_cost( bChoice.getRoom_cost() * searchNumOfDays );
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
	public void backToStepOne (ActionEvent event){
		//stepThreePane.setVisible(false);
		stepTwoPane.setVisible(false);
		stepOnePane.setVisible(true);
		
		breakfastCkBx.setSelected(false);
		fullDinnerCkBx.setSelected(false);
		poolCkBx.setSelected(false);
		hairSalonCkBx.setSelected(false);
		fitnessCenterCkBx.setSelected(false);
		spaTreatmentsCkBx.setSelected(false);
		faceBodyCareCkBx.setSelected(false);
		massageTherapiesCkBx.setSelected(false);
		
		updateExtras();
	}
	
	public void selectRoomToBook (ActionEvent event){
		
		choosed = availableRoomsTable.getSelectionModel().getSelectedItem();
		totalCost_txt.setText( String.valueOf( choosed.getBooking_cost() ) );
		stepOnePane.setVisible(false);
		stepTwoPane.setVisible(true);
		
//		generatePDF();
		
	}
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
	
	private void setCostsToExtras() {

		try {
			Connection conn = Conn.connect();
			String query = "SELECT * FROM `extras`";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			rs.next();breakfastLbl.setText( String.valueOf( rs.getFloat("cost" ) ) );
			rs.next();fullDinnerLbl.setText( String.valueOf( rs.getFloat("cost" ) ) );
			rs.next();poolLbl.setText( String.valueOf( rs.getFloat("cost" ) ) );
			rs.next();hairSalonLbl.setText( String.valueOf( rs.getFloat("cost" ) ) );
			rs.next();fitnessCenterLbl.setText( String.valueOf( rs.getFloat("cost" ) ) );
			rs.next();spaTreatmentsLbl.setText( String.valueOf( rs.getFloat("cost" ) ) );
			rs.next();faceBodyCareLbl.setText( String.valueOf( rs.getFloat("cost" ) ) );
			rs.next();massageTherapiesLbl.setText( String.valueOf( rs.getFloat("cost" ) ) );
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void backToStepTwo (ActionEvent event){
		stepThreePane.setVisible(false);
		stepTwoPane.setVisible(true);
		//stepOnePane.setVisible(false);
	}
	protected void updateExtras() {
		extrasCost = 0;
		double curCost = 0;
		Logger logger = Logger.getLogger("application");
		logger.setLevel(Level.INFO);
		
		if ( breakfastCkBx.isSelected() ) {
			curCost = Double.parseDouble( breakfastLbl.getText() ) * searchNumOfPerson * searchNumOfDays;
			logger.log(Level.INFO, "Breakfast: " + curCost + " -> " + breakfastLbl.getText() + " x " + searchNumOfPerson + "person x " + searchNumOfDays + " days");
        	extrasCost += curCost;
        	previewBreakfastLbl.setText("YES");
        } else { previewBreakfastLbl.setText("NO"); }
		if ( fullDinnerCkBx.isSelected() ) {
			curCost = Double.parseDouble( fullDinnerLbl.getText() ) * searchNumOfPerson * searchNumOfDays;
			logger.info("FullDinner: " + curCost + " -> " + fullDinnerLbl.getText() + " x " + searchNumOfPerson + " person x " + searchNumOfDays + " days");
        	extrasCost += curCost;
        	previewFullDinnerLbl.setText("YES");
        } else { previewFullDinnerLbl.setText("NO"); }
		if ( poolCkBx.isSelected() ) {
			curCost = Double.parseDouble( poolLbl.getText() ) * searchNumOfPerson;
			logger.info("Pool: " + curCost + " -> " + poolLbl.getText() + " x " + searchNumOfPerson + " person");
        	extrasCost += curCost;
    		previewPoolLbl.setText("YES");
        } else { previewPoolLbl.setText("NO"); }
		if ( hairSalonCkBx.isSelected() ) {
			curCost = Double.parseDouble( hairSalonLbl.getText() );
			logger.info("HairSalon: " + curCost + " -> " + hairSalonLbl.getText() );
        	extrasCost += curCost;
    		previewHairSalonLbl.setText("YES");
        } else { previewHairSalonLbl.setText("NO"); }
		if ( fitnessCenterCkBx.isSelected() ) {
			curCost = Double.parseDouble( fitnessCenterLbl.getText() ) * searchNumOfPerson;
			logger.info("Fitness Center: " + curCost + " -> " + fitnessCenterLbl.getText() + " x " + searchNumOfPerson + " person");
        	extrasCost += curCost;
        	previewFitnessLbl.setText("YES");
        } else { previewFitnessLbl.setText("NO"); }
		if ( spaTreatmentsCkBx.isSelected() ) {
			curCost = Double.parseDouble( spaTreatmentsLbl.getText() );
			logger.info("Spa Treatments: " + curCost + " -> " + spaTreatmentsLbl.getText() );
        	extrasCost += curCost;
        	previewSpaLbl.setText("YES");
        } else { previewSpaLbl.setText("NO"); }
		if ( faceBodyCareCkBx.isSelected() ) {
			curCost = Double.parseDouble( faceBodyCareLbl.getText() ) * searchNumOfPerson;
			logger.info("Face & Body Care: " + curCost + " -> " + faceBodyCareLbl.getText() + " x " + searchNumOfPerson + " person");
        	extrasCost += curCost;
    		previewFaceBodyLbl.setText("YES");
        } else { previewFaceBodyLbl.setText("NO"); }
		if ( massageTherapiesCkBx.isSelected() ) {
			curCost = Double.parseDouble( massageTherapiesLbl.getText() ) * searchNumOfPerson;
			logger.info("Massage Therapies: " + curCost + " -> " + massageTherapiesLbl.getText() + " x " + searchNumOfPerson + " person");
        	extrasCost += curCost;
    		previewMassageLbl.setText("YES");
        } else { previewMassageLbl.setText("NO"); }
		
		choosed.setExtrasCost( extrasCost );
	}
	public void proceedToPaymentMethod(){
		Alert alert = new Alert(AlertType.ERROR);
		if ( 	nameTxt.getText().trim().equals("") ||
				sNameTxt.getText().trim().equals("") ||
				idTxt.getText().trim().equals("") ||
				telTxt.getText().trim().equals("") ||
				!validateEmail( emailTxt.getText() ) 
			) {
			
			alert.setContentText("Please check that you have filled all fields properly!");
			alert.show();
			return;
		}
		
		choosed.setBookerTitle( titleTxt.getText() );
		choosed.setBookerName( nameTxt.getText() );
		choosed.setBookerSurname( sNameTxt.getText() );
		choosed.setBookerIDNum( idTxt.getText() );
		choosed.setBookerTel( telTxt.getText() );
		choosed.setBookerEmail( emailTxt.getText() );
		
		stepTwoPane.setVisible(false);
		prepareThirdPane();
		stepThreePane.setVisible(true);
	}
	public static boolean validateEmail(String emailStr) {
		//Found it here: http://stackoverflow.com/questions/8204680/java-regex-email#answer-8204716
		Pattern VALID_EMAIL_ADDRESS_REGEX = 
				Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		return matcher.find();
	}

	private void prepareThirdPane() {
		checkInLbl.setText( searchFrom);
		checkOutLbl.setText( searchUntil );
		bookerNameLbl.setText( choosed.getBookerName() );
		bookerSurnameLbl.setText( choosed.getBookerSurname() );
		bookerIDLbl.setText( choosed.getBookerIDNum() );
		bookerTelLbl.setText( choosed.getBookerTel() );
		bookerEmailLbl.setText( choosed.getBookerEmail() );
		numPersonsLbl.setText( String.valueOf( searchNumOfPerson ) );
		numSingleBedsLbl.setText( String.valueOf( choosed.getSingle_beds() ) );
		numDoubleBedsLbl.setText( String.valueOf( choosed.getDouble_beds() ) );
		roomNameLbl.setText( choosed.getRoom_name() );
		roomTypeLbl.setText( choosed.getRoom_type() );	
		
		totalCostLbl.setText( String.valueOf( choosed.getBooking_total() ) );
		
		totalCostHBox.setVisible(true);
		paymentLbl.setVisible(true);
		confirm_btn.setVisible(true);
		bookingCodeHBox.setVisible(false);
		bookingCodeLbl.setText("");
	}
	
	public void completeBooking(ActionEvent event) {
		
		backToStepTwo_Btn.setVisible(false);
		totalCostHBox.setVisible(false);
		paymentLbl.setVisible(false);
		confirm_btn.setVisible(false);
		
		if ( insertBookingIntoBatabase() && generatePDF() ) {
			bookingCodeHBox.setVisible(true);
			bookingCodeLbl.setText( choosed.getBookingCode() );
			
			bookingSuccess.setVisible(true);
			
		} else {
			bookingFailed.setVisible(true);
		}
		
	}
	
	private boolean insertBookingIntoBatabase() {
		
		try {
			Connection conn = Conn.connect();
			
			String query = "INSERT INTO `easybooksql`.`bookings`"
					+ "(`room_id`,`offer_id`,`check_in`,`check_out`,`numdays`,"
					+ "`title`,`name`,`sname`,`idnum`,`tel`,`email`,`payment_method`,`total_cost`,"
					+ "`paid`,`money_received`,`status`, `comments`,`numOfPerson`) VALUES"
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, choosed.getRoom_id() );
			ps.setInt(2, choosed.getO_id() );
			ps.setString(3, searchFrom);
			ps.setString(4, searchUntil);
			ps.setInt(5, searchNumOfDays);
			ps.setString(6, choosed.getBookerTitle() );
			ps.setString(7, choosed.getBookerName() );
			ps.setString(8, choosed.getBookerSurname() );
			ps.setString(9, choosed.getBookerIDNum() );
			ps.setString(10, choosed.getBookerTel() );
			ps.setString(11, choosed.getBookerEmail() );
			ps.setString(12, "EasyBank" );
			ps.setDouble(13, choosed.getBooking_total() );
			ps.setString(14, "paid" );
			ps.setDouble(15, choosed.getBooking_total() );
			ps.setString(16, "pending" );
			ps.setString(17, commentsBox.getText() );
			ps.setInt(18, searchNumOfPerson);
			
			int affectedRows = ps.executeUpdate();
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating booking failed! Please try again later.");
	        }

	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if ( generatedKeys.next() ) {
	            	choosed.setBookingID( generatedKeys.getInt(1) );
	            	choosed.setBookingCode( hash(String.valueOf( choosed.getBookingID() ) ).substring(0,10).toUpperCase() );
	            	query = "UPDATE `bookings` SET `code` = ? WHERE `b_id` = ?";
	            	ps = conn.prepareStatement(query);
	            	
	            	ps.setString(1, choosed.getBookingCode() );
					ps.setInt(2, choosed.getBookingID() );
					
					ps.executeUpdate();
					
					if ( insertExtrasIntoDatabase() ) {
						return true;
					}
	            } else {
	                throw new SQLException("Creating booking failed, no ID & Code obtained.");
	            }
	        }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private Boolean insertExtrasIntoDatabase() {
		
		try {
			Connection conn = Conn.connect();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM `booking_extras` WHERE `b_id` = ?");
			ps.setInt(1, choosed.getBookingID() );
			ps.executeUpdate();
			
			String query = "INSERT INTO `booking_extras`(`b_id`, `e_id`) VALUES (?, ?)";
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, choosed.getBookingID() );
			
			if ( breakfastCkBx.isSelected() ) {
				ps.setInt(2, 1);
				ps.addBatch();
	        }
			if ( fullDinnerCkBx.isSelected() ) {
				ps.setInt(2, 2);
				ps.addBatch();
			}
			if ( poolCkBx.isSelected() ) {
				ps.setInt(2, 3);
				ps.addBatch();
			}
			if ( hairSalonCkBx.isSelected() ) {
				ps.setInt(2, 4);
				ps.addBatch();
			}
			if ( fitnessCenterCkBx.isSelected() ) {
				ps.setInt(2, 5);
				ps.addBatch();
			}
			if ( spaTreatmentsCkBx.isSelected() ) {
				ps.setInt(2, 6);
				ps.addBatch();
			}
			if ( faceBodyCareCkBx.isSelected() ) {
				ps.setInt(2, 7);
				ps.addBatch();
			}
			if ( massageTherapiesCkBx.isSelected() ) {
				ps.setInt(2, 8);
				ps.addBatch();
			}
			
			ps.executeBatch();
			
			conn.commit();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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

	@SuppressWarnings("resource")
	private boolean generatePDF() {
		
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialFileName("myBooking_" + choosed.getBookingCode() + ".pdf");
            fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf") );
			
			PDDocument doc = new PDDocument();
			PDPage page = new PDPage(PDPage.PAGE_SIZE_A4);
	        PDFont font = PDType1Font.HELVETICA;
			
			PDPageContentStream content = new PDPageContentStream(doc, page);

			content.setFont(font, 20);
			content.beginText();
			content.moveTextPositionByAmount(30, 800);
			content.drawString("Imaginary Hotel");
			content.endText();
			content.beginText();
			content.moveTextPositionByAmount(30, 780);
			content.setFont(font, 15);
			content.drawString("Booking Reference ID: " + choosed.getBookingCode() );
			content.endText();

			content = printDataOnPdf(content, choosed.prepareData(searchFrom, searchUntil, numOfPeople.getText() ), 760 );
			
			content.close();
			
			doc.addPage(page);
			doc.save( fileChooser.showSaveDialog( pStage ) );
            
			doc.close();
			
			return true;
			
		} catch (COSVisitorException | IOException e) {
			e.printStackTrace();
		}
	
		return false;
	}

	private PDPageContentStream printDataOnPdf( PDPageContentStream content, String[][] data, int y ) {

		try {
			for (int i=0; i<data.length; i++ ) {
				if ( data[i][0] == null && data[i][1] == null) {
					y -=20;
					continue;
				}
				content.beginText();
				content.moveTextPositionByAmount(30, y);
				content.drawString( ( data[i][0]!=null?data[i][0]:"" ) + ( data[i][1]!=null?" "+data[i][1]:"") );
				content.endText();
				y -= 20;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}

}
