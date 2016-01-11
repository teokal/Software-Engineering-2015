package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import database.Conn;
import javafx.beans.property.SimpleStringProperty;

public class Offer {
	public Connection conn = Conn.connect();

	private int o_id;
	private SimpleStringProperty name;
	public SimpleStringProperty valid_from;
	public SimpleStringProperty valid_until;
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

	public void setValid_from (String s) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(s + " 00:00:00.0");
		String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(date);

		valid_from.set( newDate );
	}

	public void setValid_until (String s) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(s + " 00:00:00.0");
		String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(date);

		valid_until.set( newDate );
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

	public String getDesc_en() {
		String text = "";
		try {
			Statement stmt = (Statement) conn.createStatement();

			ResultSet rs = stmt.executeQuery("Select `lang_en` from offers_lang where `o_id` = " + getO_id() );

			while ( rs.next()) {
				text = rs.getString("lang_en"); 
			}
		} catch (SQLException e) { e.printStackTrace();	}

		return text;
	}
	public String[] getDesc_allLangs() {

		String[] text = new String[4];
		try {
			Statement stmt = (Statement) conn.createStatement();

			ResultSet rs = stmt.executeQuery("Select * from offers_lang where `o_id` = " + getO_id() );

			while ( rs.next()) {
				text[0] = rs.getString("lang_en"); 
				text[1] = rs.getString("lang_gr"); 
				text[2] = rs.getString("lang_de"); 
				text[3] = rs.getString("lang_es"); 
			}
		} catch (SQLException e) { e.printStackTrace();
		}

		return text;
	}

	public boolean updateDesc_en(String s) {

		int rs = 0;

		try {
			String updateQuery = "update `offers_lang` set `lang_en` = ? where `o_id` = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
			preparedStatement.setString(1, s );
			preparedStatement.setInt(2, getO_id() );

			rs = preparedStatement.executeUpdate();

		} catch (SQLException e) { e.printStackTrace(); }

		if ( rs > 0 ) {
			return true;
		} else {
			Logger logger = Logger.getLogger("offers");
			logger.setLevel(Level.SEVERE);
			logger.info("Problem updating english description!");

			return false;
		}

	}
	public boolean updateDescriptions(String s_en, String s_gr, String s_de, String s_es) {

		int rs = 0;

		try {
			String updateQuery = "UPDATE `offers_lang` SET "
					+ "`lang_en` = ?,"
					+ "`lang_gr` = ?,"
					+ "`lang_de` = ?,"
					+ "`lang_es` = ?,"
					+ " WHERE `o_id` = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
			preparedStatement.setString(1, s_en );
			preparedStatement.setString(2, s_gr );
			preparedStatement.setString(3, s_de );
			preparedStatement.setString(4, s_es );
			preparedStatement.setInt(5, getO_id() );

			rs = preparedStatement.executeUpdate();

		} catch (SQLException e) { e.printStackTrace(); }

		if ( rs > 0 ) {
			return true;
		} else {
			Logger logger = Logger.getLogger("offers");
			logger.setLevel(Level.SEVERE);
			logger.info("Problem updating descriptions!");

			return false;
		}

	}
	public boolean updateOffer() throws ParseException{

		int rs = 0;

		try {
			String updateQuery = "UPDATE `easybooksql`.`offers` SET "
					+ "`name` = ?, `valid_from` = ?, `valid_until` = ?, `required_days` = ?,"
					+ "`one_bed` = ?, `two_beds` = ?, `three_beds` = ?, `fplus_beds` = ?, "
					+ "`type_stand` = ?, `type_comf` = ?, `type_suite` = ?, "
					+ "`discount_amount` = ?, `discount_percentage` = ? "
					+ "WHERE `o_id` = ?";

			PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
			preparedStatement.setString(1, getName() );
			preparedStatement.setString(2, valid_from.getValueSafe() );
			preparedStatement.setString(3, valid_until.getValueSafe() );
			preparedStatement.setInt(4, getReq_days() );
			preparedStatement.setInt(5, getOne_bed_edit() );
			preparedStatement.setInt(6, getTwo_beds_edit() );
			preparedStatement.setInt(7, getThree_beds_edit() );
			preparedStatement.setInt(8, getFplus_beds_edit() );
			preparedStatement.setInt(9, getType_stand_edit() );
			preparedStatement.setInt(10, getType_comf_edit() );
			preparedStatement.setInt(11, getType_suite_edit() );
			preparedStatement.setInt(12, getDiscount_amount() );
			preparedStatement.setInt(13, getDiscount_percentage() );
			preparedStatement.setInt(14, getO_id() );

			rs = preparedStatement.executeUpdate();

		} catch (SQLException e) { e.printStackTrace(); }

		if ( rs > 0 ) {
			return true;
		} else {
			Logger logger = Logger.getLogger("offers");
			logger.setLevel(Level.SEVERE);
			logger.info("Problem updating offer details!");

			return false;
		}

	}

}
