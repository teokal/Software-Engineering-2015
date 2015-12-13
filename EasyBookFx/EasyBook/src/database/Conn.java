package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conn {

	private Statement stmt;

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			String connectionUrl = "jdbc:mysql://localhost:3306/easybooksql";
			String connectionUser = "root";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);

			Logger logger = Logger.getLogger("database");
			logger.setLevel(Level.INFO);
			logger.info("Connected to Database!");

			stmt = conn.createStatement();

			//rs = getData(stmt, "Select user_name AS Username, concat(name, ' ', sname) AS `Full Name` from users");
			rs = getData(stmt, "Select * from bookings");
			//printResult(rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}

	public static ResultSet getData(Statement stm, String query) {

		ResultSet rs = null;

		Logger logger = Logger.getLogger("database");
		logger.setLevel(Level.INFO);

		try {
			rs = stm.executeQuery(query);
			logger.info("Query Run Successfully!");
		} catch (Exception e) {
			logger.info("Query Did Not Run!");
			e.printStackTrace();
		}

		return rs;

	}

	private static void printResult(ResultSet rs) {

		Logger logger = Logger.getLogger("database");

		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			String cur_row = "";
			for (int i = 1; i <= columnsNumber; i++) {
				cur_row += rsmd.getColumnName(i) + "\t";
			}
			//logger.info(cur_row);
			System.out.println(cur_row);


			rs.beforeFirst();
			while ( rs.next() ) {
				cur_row = "";
				for (int i = 1; i <= columnsNumber; i++) {
					cur_row += rs.getString(i) + "\t  ";
				}
				//logger.info(cur_row);
				System.out.println(cur_row);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

}