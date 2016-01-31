package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Conn;
import javafx.beans.property.SimpleStringProperty;

public class BookingChoices {
	private int room_id;
	private SimpleStringProperty room_name;
	private int single_beds;
	private int double_beds;
	private SimpleStringProperty room_type;
	private double room_cost;
	private double booking_cost;
	
	private int o_id;
	private int discount_amount = 0;
	private int discount_percentage = 0;
	private SimpleStringProperty discount_show = new SimpleStringProperty("none");;
	private SimpleStringProperty offer_name = new SimpleStringProperty("none");

	private double extrasCost;
	
	private String BookerTitle;
	private String BookerName;
	private String BookerSurname;
	private String BookerIDNum;
	private String BookerTel;
	private String BookerEmail;
	
	private int bookingID;
	private String bookingCode;
	
	public BookingChoices(BookingChoices bChoice) {
		this.setRoom_id( bChoice.getRoom_id() );
		this.room_name = new SimpleStringProperty( bChoice.getRoom_name() );
		this.single_beds = bChoice.getSingle_beds();
		this.double_beds = bChoice.getDouble_beds();
		this.room_type = new SimpleStringProperty( bChoice.getRoom_type() );
		this.room_cost = bChoice.getRoom_cost();
	}
	
	public BookingChoices( 
			int room_id,
			String room_name,
			int sB, 
			int dB, 
			String room_type,
			double room_cost ) {
		this.setRoom_id(room_id);
		this.room_name = new SimpleStringProperty(room_name);
		this.single_beds = sB;
		this.double_beds = dB;
		this.room_type = new SimpleStringProperty(room_type);
		this.room_cost = room_cost;
		
	}

	public String getRoom_name() {
		return room_name.get();
	}


	public void setRoom_name(String room_name) {
		this.room_name= new SimpleStringProperty(room_name);
	}


	public int getSingle_beds() {
		return single_beds;
	}


	public void setSingle_beds(int single_beds) {
		this.single_beds = single_beds;
	}


	public int getDouble_beds() {
		return double_beds;
	}


	public void setDouble_beds(int double_beds) {
		this.double_beds = double_beds;
	}

	public int getNumberOfBeds() {
		return single_beds + double_beds;
	}

	public String getRoom_type() {
		return room_type.get();
	}


	public void setRoom_type(String room_type) {
		this.room_type= new SimpleStringProperty(room_type);
	}


	public String getOffer_name() {
		return offer_name.get();
	}


	public void setOffer_name(String s) {
		offer_name = new SimpleStringProperty(s);
	}


	public String getDiscount_show() {
		return discount_show.get();
	}


	public void setDiscount_show(String discount_show) {
		this.discount_show = new SimpleStringProperty(discount_show);
	}


	public int getDiscount_percentage() {
		return discount_percentage;
	}


	public void setDiscount_percentage(int discount_percentage) {
		this.discount_percentage = discount_percentage;
	}


	public int getDiscount_amount() {
		return discount_amount;
	}


	public void setDiscount_amount(int discount_amount) {
		this.discount_amount = discount_amount;
	}


	public int getO_id() {
		return o_id;
	}


	public void setO_id(int o_id) {
		this.o_id = o_id;
	}


	public double getRoom_cost() {
		return room_cost;
	}


	public void setRoom_cost(double room_cost) {
		this.room_cost = room_cost;
	}


	public int getRoom_id() {
		return room_id;
	}


	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public void setBooking_cost ( double booking_cost) {
		this.booking_cost = booking_cost;
	}

	public double getBooking_cost () {
		return booking_cost;
	}
	
	public double getBooking_total() {
		return (double) (Math.round( ( booking_cost  + extrasCost ) * 100.0 ) / 100.0);
	}

	public double getExtrasCost() {
		return extrasCost;
	}

	public void setExtrasCost(double extrasCost) {
		this.extrasCost = extrasCost;
	}

	public String getBookerTitle() {
		return BookerTitle;
	}

	public void setBookerTitle(String bookerTitle) {
		BookerTitle = bookerTitle;
	}

	public String getBookerName() {
		return BookerName;
	}

	public void setBookerName(String bookerName) {
		BookerName = bookerName;
	}

	public String getBookerSurname() {
		return BookerSurname;
	}

	public void setBookerSurname(String bookerSurname) {
		BookerSurname = bookerSurname;
	}

	public String getBookerIDNum() {
		return BookerIDNum;
	}

	public void setBookerIDNum(String bookerIDNum) {
		BookerIDNum = bookerIDNum;
	}

	public String getBookerTel() {
		return BookerTel;
	}

	public void setBookerTel(String bookerTel) {
		BookerTel = bookerTel;
	}

	public String getBookerEmail() {
		return BookerEmail;
	}

	public void setBookerEmail(String bookerEmail) {
		BookerEmail = bookerEmail;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public String getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}
	
	public String[][] prepareData(String from, String to, String numPerson) {
		
		int rowcount = 0;
		String[] 	extras = new String[2];
					extras[0] = "none";
					
		try {

			Connection conn = Conn.connect();
			String query = "SELECT `name` "
					+ "FROM `extras` INNER JOIN `booking_extras` "
					+ "ON `booking_extras`.`e_id`=`extras`.`e_id` "
					+ "WHERE `booking_extras`.`b_id`= ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, getBookingID() );
			ResultSet rs = ps.executeQuery();

			rowcount = 0;
			int i = 0;
			if ( rs.last() ) {
			  rowcount = rs.getRow();
			  rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
			}
			extras = new String[rowcount];
			while ( rs.next() ) {
				extras[i++] = rs.getString("name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String[][] data = new String[19 + rowcount ][2];
		data[0][0] = "*Your details*";
		data[1][0] = "Full Name: "; 	data[1][1] 	= 	getBookerTitle() + " " 
														+ getBookerSurname() + " "
														+ getBookerName();
		data[2][0] = "ID Number: ";		data[2][1] 	= getBookerIDNum();
		data[3][0] = "Email: "; 		data[3][1] 	= getBookerEmail();
		data[4][0] = "Telephone: ";		data[4][1] 	= getBookerTel();
		
		data[5][0] = " ";				data[5][1] = " ";
		data[6][0] = "*Room Details*";	data[6][1] = " ";
		data[7][0] = "Room Name: ";		data[7][1]	= getRoom_name();
		data[8][0] = "Room Type: ";		data[8][1]	= getRoom_type();
		data[9][0] = "Single Beds: ";	data[9][1]	= getSingle_beds() + ", Double Beds: " + getDouble_beds();
		data[10][0] = " ";				data[11][1] = " ";
		data[11][0] = "Check-In: "+from;data[11][1] = " Check-Out: " + to;
		
		data[12][0] = " ";				data[12][1] = " ";
		data[13][0] = "*Offer Details* ";
		data[14][0] = "Offer Applied: ";			data[14][1]	= getOffer_name().toString();
		data[15][0] = "Offer Discount: ";			data[15][1]	= getDiscount_show().toString();
		data[16][0] = "Room Name: ";				data[16][1]	= getRoom_name();
		
		data[17][0] = " ";							data[17][1] = " ";
		data[18][0] = "*Extras*";
		for (int i=0; i<rowcount; i++ ){
			data[19+i][0] = extras[i];
		}
		
		return data;
	}

}
