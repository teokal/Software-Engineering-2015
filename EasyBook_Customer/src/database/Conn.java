package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conn {

	public static Connection connect() {
		Connection conn = null;

		String connectionUrl = "jdbc:mysql://localhost:3306/";
		String connectionDb = "easybooksql";
		String connectionUser = "root";
		String connectionPassword = "";

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn = DriverManager.getConnection(connectionUrl+connectionDb + "?useUnicode=yes&characterEncoding=UTF-8", connectionUser, connectionPassword);

			if (conn == null) {
				Logger logger = Logger.getLogger("database");
				logger.setLevel(Level.SEVERE);
				logger.info("Connection could not be established");
			}
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}