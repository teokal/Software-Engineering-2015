package application;

import javafx.beans.property.SimpleStringProperty;

public class BookingChoices {
	private int room_id;
	private SimpleStringProperty room_name;
	private int single_beds;
	private int double_beds;
	private SimpleStringProperty room_type;
	private float room_cost;
	
	private int o_id;
	private int discount_amount = 0;
	private int discount_percentage = 0;
	private SimpleStringProperty discount_show = new SimpleStringProperty("none");;
	private SimpleStringProperty offer_name = new SimpleStringProperty("none");
	
	private float booking_total;
	
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
			Float room_cost ) {
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


	public float getRoom_cost() {
		return room_cost;
	}


	public void setRoom_cost(float room_cost) {
		this.room_cost = room_cost;
	}


	public int getRoom_id() {
		return room_id;
	}


	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}


	public float getBooking_total() {
		return booking_total;
	}


	public void setBooking_total(float booking_total) {
		this.booking_total = booking_total;
	}

}
