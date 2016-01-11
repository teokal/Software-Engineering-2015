package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;

public class Offer {
	private int o_id;
	private SimpleStringProperty name;
	private SimpleStringProperty valid_from;
	private SimpleStringProperty valid_until;
	private int req_days;
	private int one_bed;
	private int two_beds;
	private int three_beds;
	private int fplus_beds;
	private int type_stand;
	private int type_comf;
	private int type_suite;
	private int discount_amount;
	private int discount_percentage;

	public Offer(
			int o_id,
			String name,
			String valid_from,
			String valid_until,
			int req_days,
			int one_bed,
			int two_beds,
			int three_beds,
			int fplus_beds,
			int type_stand,
			int type_comf,
			int type_suite,
			int discount_amount,
			int discount_percentage
			){
		this.o_id = o_id;
		this.name = new SimpleStringProperty(name);
		this.valid_from = new SimpleStringProperty(valid_from);
		this.valid_until = new SimpleStringProperty(valid_until);
		this.req_days = req_days;
		this.one_bed = one_bed;
		this.two_beds = two_beds;
		this.three_beds = three_beds;
		this.fplus_beds = fplus_beds;
		this.type_stand = type_stand;
		this.type_comf = type_comf;
		this.type_suite = type_suite;
		this.discount_amount = discount_amount;
		this.discount_percentage = discount_percentage;
	}

	public void setO_id (int  s) {
		o_id = s;
	}

	public void setName (String s) {
		name.set(s);
	}

	public void setValid_from (String s) {
		valid_from.set(s);
	}

	public void setValid_until (String s) {
		valid_until.set(s);
	}

	public void setReq_days (int  s) {
		req_days = s;
	}

	public void setOne_bed (int  s) {
		one_bed = s;
	}

	public void setTwo_beds (int  s) {
		two_beds = s;
	}

	public void setThree_beds (int  s) {
		three_beds = s;
	}

	public void setFplus_beds (int  s) {
		fplus_beds = s;
	}

	public void setType_stand (int  s) {
		type_stand = s;
	}

	public void setType_comf (int  s) {
		type_comf = s;
	}

	public void setType_suite (int  s) {
		type_suite = s;
	}

	public void setDiscount_amount (int  s) {
		discount_amount = s;
	}

	public void setDiscount_percentage (int  s) {
		discount_percentage = s;
	}

	
	public int getO_id () {
		return o_id;
	}

	public String getName () {
		return name.get();
	}

	public int getReq_days () {
		return req_days;
	}

	/* Getters for ticks on TableView | Offers TAB */
	public String getValid_from () throws ParseException {
		String origDate = valid_from.getValueSafe();
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(origDate);
		String newDate = new SimpleDateFormat("dd-MM-yyyy").format(date);

		return newDate;
	}
	public String getValid_until () throws ParseException {
		String origDate = valid_until.getValueSafe();
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(origDate);
		String newDate = new SimpleDateFormat("dd-MM-yyyy").format(date);

		return newDate;
	}

	public String getOne_bed () {
		return (one_bed == 1?"\u2714":""/*"\u2718"*/);
	}
	public String getTwo_beds () {
		return (two_beds == 1?"\u2714":""/*"\u2718"*/);
	}
	public String getThree_beds () {
		return (three_beds == 1?"\u2714":""/*"\u2718"*/);
	}
	public String getFplus_beds () {
		return (fplus_beds == 1?"\u2714":""/*"\u2718"*/);
	}
	public String getType_stand () {
		return (type_stand == 1?"\u2714":""/*"\u2718"*/);
	}
	public String getType_comf () {
		return (type_comf == 1?"\u2714":""/*"\u2718"*/);
	}
	public String getType_suite () {
		return (type_suite == 1?"\u2714":""/*"\u2718"*/);
	}
	/* END: Getters for ticks on TableView | Offers TAB */
	
	/* Getters for edit form on Offers TAB */
	public LocalDate getValid_from_edit () throws ParseException {
		String origDate = valid_from.getValueSafe();
		
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(origDate);
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDate newLocalDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		
		return newLocalDate;
	}
	public LocalDate getValid_until_edit () throws ParseException {
		String origDate = valid_until.getValueSafe();

		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(origDate);
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDate newLocalDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		
		return newLocalDate;
	}

	
	public int getOne_bed_edit () {
		return one_bed;
	}
	public int getTwo_beds_edit () {
		return two_beds;
	}
	public int getThree_beds_edit () {
		return three_beds;
	}
	public int getFplus_beds_edit () {
		return fplus_beds;
	}
	public int getType_stand_edit () {
		return type_stand;
	}
	public int getType_comf_edit () {
		return type_comf;
	}
	public int getType_suite_edit () {
		return type_suite;
	}
	/* END: Getters for edit form on Offers TAB */

	public int getDiscount_amount () {
		return discount_amount;
	}

	public int getDiscount_percentage () {
		return discount_percentage;
	}
}
