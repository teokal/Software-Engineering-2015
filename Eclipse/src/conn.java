import java.sql.*;
 
public class conn {
 
    public Connection c() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
        return con;
    }
}