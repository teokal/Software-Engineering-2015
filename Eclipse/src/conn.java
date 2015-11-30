import java.sql.*;
 
public class CONN {
 
    public Connection c() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
        return con;
    }
}